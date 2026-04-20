package WorkOrder;

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

import Bom.BomDTO;
import Lot.LotDTO;
import ProductionManagement.ProductionManagementDTO;
import fileLibrary.CommonDTO;
import io.IoDTO;

public class WorkOrderDAO {
	public List<WorkOrderDTO> select(WorkOrderDTO dto, CommonDTO pageing) {
		List<WorkOrderDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from ( "
						+ "SELECT rownum as rnum, subqry.* from ( "
						+ "SELECT * from work_order ";
			
			if("select".equals(dto.getMod()) && "am".equals(dto.getTimefilter())) {
				System.out.println("셀렉트 오전");
				query += "WHERE work_date >= TRUNC(SYSDATE+9/24) "
						+ "AND work_date < TRUNC(SYSDATE+9/24) + 0.5 ";
			}
			if("select".equals(dto.getMod()) && "pm".equals(dto.getTimefilter())) {
				System.out.println("셀렉트 오후");
				query += "where work_date >= TRUNC(SYSDATE+9/24) + 0.5 "
						+ "AND work_date < TRUNC(SYSDATE+9/24) + 1 ";
			}
			if("select".equals(dto.getMod()) && ("Y".equals(dto.getStatustitle()) || "N".equals(dto.getStatustitle())) && !("total".equals(dto.getTimefilter()))) {
					System.out.println("스테이터스 yN AND:" + dto.getStatustitle());
				query+= "and status = ? ";
			}
			if("select".equals(dto.getMod()) && ("Y".equals(dto.getStatustitle()) || "N".equals(dto.getStatustitle())) && "total".equals(dto.getTimefilter())) {
				System.out.println("스테이터스 yN WHERE:" + dto.getStatustitle());
				query+= "WHERE status = ? ";
			}
			
			query += " ) subqry) "
					+ " WHERE rnum >= ? AND rnum <= ? ";
			//�닔�젙
			ps = conn.prepareStatement(query);
			int idx = 1;
			
			if("select".equals(dto.getMod()) && ("Y".equals(dto.getStatustitle()) || "N".equals(dto.getStatustitle())) && !("total".equals(dto.getTimefilter()))) {
				ps.setString(idx++, dto.getStatustitle());
			}
			if("select".equals(dto.getMod()) && ("Y".equals(dto.getStatustitle()) || "N".equals(dto.getStatustitle())) && "total".equals(dto.getTimefilter())) {
				ps.setString(idx++, dto.getStatustitle());
			}
			ps.setInt(idx++, pageing.getStart());
			ps.setInt(idx++, pageing.getEnd());
			rs = ps.executeQuery();

			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
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
	
	
	
	public List<WorkOrderDTO> selectall(WorkOrderDTO dto) {
		List<WorkOrderDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT "
						+ 		"pm.mdm_num, "
						+ 		"wo.empno, "
						+ 		"wo.status, "
						+		"wo.order_num, "
						+ 		"wo.work_date, "
						+ 		"wo.work_order_title, "
						+ 		"wo.daily_target, "
						+ 		"m.code, "
						+ 		"b.required_weight, "
						+ 		"m.unit, "
						+ 		"p.process_content, "
						+ 		"p.flow, "
						+ 		"p.img_url "
						+ 	"FROM WORK_order wo "
						+ 	"LEFT OUTER JOIN production_management pm ON (wo.prod_num = pm.prod_num) "
						+ 	"LEFT OUTER JOIN mdm m ON (m.mdm_num = pm.mdm_num) "
						+ 	"LEFT OUTER JOIN bom b ON (b.mdm_num = m.mdm_num) "
						+ 	"LEFT OUTER JOIN process p ON (p.mdm_num = m.mdm_num) "
						+ 	"LEFT OUTER JOIN emp e on(e.empno = wo.empno) " 
						+ 	"where order_num = ? "
						+ 	"ORDER BY p.flow ";
			
			ps = conn.prepareStatement(query);
	
			ps.setInt(1, dto.getOrder_num());
			
			rs = ps.executeQuery();
			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
				int mdm_num = rs.getInt("mdm_num");
				int order_num = rs.getInt("order_num");
				int empno = rs.getInt("empno");
				String status = rs.getString("status");
				Date work_date = rs.getDate("work_date");
				String work_order_title = rs.getString("work_order_title");
				String code = rs.getString("code");
				int required_weight = rs.getInt("required_weight");
				int daily_target = rs.getInt("daily_target");
				String unit = rs.getString("unit");
				String process_content = rs.getString("process_content");
				int flow = rs.getInt("flow");
				String img_url = rs.getString("img_url");
				
				DTO.setMdm_num(mdm_num);
				DTO.setOrder_num(order_num);
				DTO.setEmpno(empno);
				DTO.setStatus(status);
				DTO.setWork_date(work_date);
				DTO.setWork_order_title(work_order_title);
				DTO.setDaily_target(daily_target);
				DTO.setCode(code);
				DTO.setRequired_weight(required_weight);
				DTO.setUnit(unit);
				DTO.setProcess_content(process_content);
				DTO.setFlow(flow);
				DTO.setImg_url(img_url);
				list.add(DTO);
				System.out.println("mdmmdmmdmmdmmdmmdm:::"+DTO.getMdm_num());
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
	public List<WorkOrderDTO> selectalll(WorkOrderDTO dto) {
		List<WorkOrderDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from work_order ";
					
			
			ps = conn.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
				int order_num = rs.getInt("order_num");
				int daily_target = rs.getInt("daily_target");
				String work_order_title = rs.getString("work_order_title");
				
				DTO.setOrder_num(order_num);
				DTO.setDaily_target(daily_target);
				DTO.setWork_order_title(work_order_title);
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
	public List<BomDTO> selectBom(IoDTO dto) {
		List<BomDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = 
					"   SELECT mdm_num, required_weight "
					+ " FROM bom "
					+ " WHERE target_mdm_num = ?";
			
			ps = conn.prepareStatement(query);
			System.out.println("selectBom : " + dto.getMdm_num());
			System.out.println(dto.getMdm_num());
			System.out.println(dto.getMdm_num());
			System.out.println(dto.getMdm_num());
			System.out.println(dto.getMdm_num());
			System.out.println(dto.getMdm_num());
			ps.setInt(1, dto.getMdm_num()); // order에서 사용중인 제품 mdm_num
			rs = ps.executeQuery();
			while (rs.next()) {
				BomDTO DTO = new BomDTO();
				int required_weight = rs.getInt("required_weight");
				int mdm_num = rs.getInt("mdm_num");
				
				
				DTO.setRequired_weight(required_weight);
				DTO.setMdm_num(mdm_num);
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
	
	public ProductionManagementDTO selectMdm(WorkOrderDTO dto) {
		ProductionManagementDTO result = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = 
					"   SELECT * from production_management "
							+ " WHERE prod_num = ?";
			System.out.println("DAODAODOAODAODADAO"+dto.getProd_num());
			ps = conn.prepareStatement(query);
			ps.setInt(1, dto.getProd_num()); // order에서 사용중인 제품 mdm_num
			rs = ps.executeQuery();
			while (rs.next()) {
//				ProductionManagementDTO DTO = new ProductionManagementDTO();
				int prod_num = rs.getInt("prod_num");
				int mdm_num = rs.getInt("mdm_num");
				
				result = new ProductionManagementDTO();
				
				result.setProd_num(prod_num);
				result.setMdm_num(mdm_num);
				System.out.println("DAO리솔트진짜임 손가락걸고 : " + result.getMdm_num());
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
		return result;
	}

	
	
	
	public WorkOrderDTO orderDAO(WorkOrderDTO dto) {
		
		WorkOrderDTO result = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "";
			if ("up".equals(dto.getMod())) {
				query = "UPDATE work_order " 
						+ "SET work_order_title = ?, " 
						+ "work_date = ?, "
						+ "daily_target = ?, " 
						+ "empno = ?, "
						+ "status = ? " 
						+ "where order_num = ?";
			}
			if ("add".equals(dto.getMod())) {
				query = "INSERT INTO work_order " 
						+ "(order_num, prod_num, work_order_title, work_date, daily_target, empno, status) "
						+ "values( "
						+ "work_order_seq.nextval, "
						+ "?, "
						+ "?, "
						+ "?, "
						+ "?, "
						+ "?, "
						+ "? "
						+ ") ";
			}
			if ("delete".equals(dto.getMod())) {
				query = "DELETE FROM work_order " + "WHERE order_num = ?";
			}
			ps = conn.prepareStatement(query);

			if ("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setString(1, dto.getWork_order_title());
				ps.setDate(2, dto.getWork_date());
				ps.setInt(3, dto.getDaily_target());
				ps.setInt(4, dto.getEmpno());
				ps.setString(5, dto.getStatus());
				ps.setInt(6, dto.getOrder_num());

			}
			if ("add".equals(dto.getMod())) {
				ps.setInt(1, dto.getProd_num());
				ps.setString(2, dto.getWork_order_title());
				ps.setDate(3, dto.getWork_date());
				ps.setInt(4, dto.getDaily_target());
				ps.setInt(5, dto.getEmpno());
				ps.setString(6, dto.getStatus());
				}
			
			if ("delete".equals(dto.getMod())) {
				System.out.println("deleteps" + dto.getOrder_num());
				ps.setInt(1, dto.getOrder_num());
			}

			ps.executeUpdate();
			
			result = dto;

			System.out.println("orderDAO:" + result);

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
		return result;
	}

	public int lotInsert(WorkOrderDTO wodto, LotDTO dto) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "insert into lot "
					+ "(lot_num, lot_count, order_num, qc_date, qc_chk, empno) "
					+ "values ( "
					+ "lot_seq.nextval, "
					+ "?, "
					+ "? "
					+ "sysdate+9/24, "
					+ "?, "
					+ "?) ";
			
			
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, wodto.lot_num());
			ps.setInt(2, dto.getMdm_num());
			ps.setInt(3, dto.getMdm_num());
			ps.setInt(4, dto.getMdm_num());
			
			result = ps.executeUpdate();
			
			System.out.println("orderDAO:" + result);
			
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
		return result;
	}
	public int ioInsert(WorkOrderDTO wodto, BomDTO dto) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "insert into io "
					+ "(io_num, io_type, storage_sec, exp_date, quantity, mdm_num) "
					+ "values ( "
					+ "io_seq.nextval, "
					+ "'OUT', "
					+ "null, "
					+ "null, "
					+ "?, "
					+ "?) ";
				

				ps = conn.prepareStatement(query);
			
				System.out.println("찐막확인1"+wodto.getDaily_target());
				System.out.println("찐막확인2"+dto.getRequired_weight());
				System.out.println("찐막확인3"+dto.getMdm_num());
				ps.setInt(1, wodto.getDaily_target()*dto.getRequired_weight());
				ps.setInt(2, dto.getMdm_num());
			
			result = ps.executeUpdate();
			
			System.out.println("orderDAO:" + result);
			
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
		return result;
	}

	
	
	public int getTotalCount() {

		int total = 0;

		try {
			Context ctx = new InitialContext();
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");

			String query = "select count(*) from work_order";

			try (Connection conn = dataFactory.getConnection();
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery()) {

				if (rs.next()) { 
					total = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
