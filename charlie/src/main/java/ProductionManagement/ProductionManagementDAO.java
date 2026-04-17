package ProductionManagement;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import WorkOrder.WorkOrderDTO;




public class ProductionManagementDAO {
	private DataSource dataFactory; //캡슐화를 위해 private
	
	ProductionManagementDAO(){
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}//생성자 닫음
	
	
	

	
	//페이지에서 보여줄 항목 몇개인지 개수 리턴
	public int getTotalCount(ProductionManagementDTO dto) {
		
		int total = 0;
		String selectTitle = dto.getSelectTitle();
	    boolean hasSearch = (selectTitle != null && !selectTitle.trim().isEmpty());

	    StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM production_management");
	    
	    if (hasSearch) {
	        query.append(" WHERE title LIKE '%' || ? || '%'");
	    }
	    
	    try (Connection conn = dataFactory.getConnection();
	            PreparedStatement ps = conn.prepareStatement(query.toString())) {
	           
	           if (hasSearch) {
	               ps.setString(1, selectTitle);
	           }

	           try (ResultSet rs = ps.executeQuery()) {
	               if (rs.next()) total = rs.getInt(1);
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return total;
	   }
	
	public List<ProductionManagementDTO> selectPage(ProductionManagementDTO dto) {
		List list = new ArrayList();
		String selectTitle = dto.getSelectTitle();
	    boolean hasSearch = (selectTitle != null && !selectTitle.trim().isEmpty());
	    StringBuilder query = new StringBuilder();
	    
	    query.append("SELECT * FROM (");
	    query.append("  SELECT rownum AS rnum, a.* FROM (");
	    query.append("    SELECT ");
	    query.append("        P.prod_num, P.title, ");
	    query.append("        P.target_quantity AS \"전체목표\", ");
	    query.append("        NVL(SUM(L.lot_count), 0) AS \"현재까지_만든_총합\", "); // null일 경우 0처리
	    query.append("        (P.target_quantity - NVL(SUM(L.lot_count), 0)) AS \"남은목표_수량\" ");
	    query.append("    FROM production_management P ");
	    query.append("    LEFT JOIN work_order W ON P.prod_num = W.prod_num ");
	    query.append("    LEFT JOIN lot L ON W.order_num = L.order_num ");
	    
	    if (hasSearch) {
	        query.append("    WHERE P.title LIKE '%' || ? || '%' ");
	    }
		
	    query.append("    GROUP BY P.prod_num, P.title, P.target_quantity ");
	    query.append("    ORDER BY P.prod_num DESC");
	    query.append("  ) a");
	    query.append(") WHERE rnum >= ? AND rnum <= ?");
	    
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query.toString())) {
				
			int idx = 1;
			if (hasSearch) {
	            ps.setString(idx++, selectTitle);
	        }
				
			ps.setInt(idx++, dto.getStart());
	        ps.setInt(idx++, dto.getEnd());

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						ProductionManagementDTO dto2 = new ProductionManagementDTO();
						dto2.setProd_num(rs.getInt("prod_num"));
		                dto2.setTitle(rs.getString("title"));
		                dto2.setTarget_quantity(rs.getInt("전체목표"));
		                dto2.setCurrentCount(rs.getInt("현재까지_만든_총합"));
		                dto2.setRemainCount(rs.getInt("남은목표_수량"));
						list.add(dto2);
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DAO의 list: " + list);
		return list;
	}

	//insert하는 메서드
	public int insertData(ProductionManagementDTO dto) {
	
		String query = "insert into production_management (prod_num, target_quantity, work_start, work_end, title, mdm_num, content, empno)"
				+ " values(production_mgmt_seq.nextval, ?, ?, ?, ?, ?, '비고', ?)";
		
		try(Connection conn = dataFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);)
			{
				//inser into다음 values에 들어갈 ?에 해당하는 값을 순서대로 넣음
				ps.setInt(1, dto.getTarget_quantity());
				ps.setDate(2, dto.getWork_start());
				ps.setDate(3, dto.getWork_end());
				ps.setString(4, dto.getTitle());
				ps.setInt(5, dto.getMdmNum());
				ps.setInt(6, dto.getEmpno());

				return ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
		System.out.println("insertDAO 예외 발생");
			return 0;
		}
	
	public List<ProductionManagementDTO> selectall(ProductionManagementDTO dto) {
		List<ProductionManagementDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "SELECT * from productionmanagement";
			
			//수정
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ProductionManagementDTO DTO = new ProductionManagementDTO();
				int order_num = rs.getInt("order_num");
				Date work_date = rs.getDate("work_date");
				int prod_num = rs.getInt("prod_num");
				int daily_target = rs.getInt("daily_target");
				int empno = rs.getInt("empno");
				String work_order_title = rs.getString("work_order_title");
				String status = rs.getString("status");
				
				DTO.setOrder_num(order_num);
				DTO.setWork_date(work_date);
				DTO.setProd_num(prod_num);
				DTO.setDaily_target(daily_target);
				DTO.setEmpno(empno);
				DTO.setWork_order_title(work_order_title);
				DTO.setStatus(status);
				list.add(DTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}///클래스 닫음
