package main;

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

import PersonalHygiene.PersonalHygieneDTO;
import ProductionManagement.ProductionManagementDTO;

public class ChartDAO {
	
	private DataSource dataFactory;
	
	ChartDAO(){
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}//생성자 닫음
	
	//mdm에 있는 product 데이터 불러오기
	public List selectProduct() {
		List list =  new ArrayList();
		String query = "SELECT " +
                "    m.NAME, " +
                "    m.TYPE, " + 
                "    i.QUANTITY, " +
                "    m.PRICE, " +
                "    (i.QUANTITY * m.PRICE) AS TOTAL_SALES, " +
                "    TO_CHAR(i.IO_DATE, 'YYYY-MM-DD HH24:MI:SS') AS IO_DATE " +
                "FROM io i " +
                "JOIN mdm m ON i.MDM_NUM = m.MDM_NUM " +
                "WHERE m.TYPE = 'product' " +
                "  AND i.IO_TYPE = 'OUT' " +
                "ORDER BY i.IO_DATE DESC";
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						ChartDTO dto = new ChartDTO();
						dto.setMdmName(rs.getString("NAME")); 
						dto.setMdmPrice(rs.getInt("PRICE"));
						dto.setMdmType(rs.getString("TYPE")); 

						dto.setMdmQuantity(rs.getInt("QUANTITY")); // 현재 출고된 수량

						dto.setTotalSales(rs.getInt("TOTAL_SALES")); 

						dto.setIoDate(rs.getString("IO_DATE"));
						list.add(dto);
//						System.out.println("list: == " + list);
					}
				}
//				System.out.println("ChartDAO selectMeterial()의 list:  " + list);
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ChartDAO selectMeterial() 예외 발생");
		return null;
	}
	
	public int sal() {
		ChartDTO dto = new ChartDTO();

		String query = "select sal from emp";
		int result = 0;
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						int i = rs.getInt("sal");
							result += i;
							System.out.println("i===" + i);
					}
				}
				System.out.println("result : " + result);
				return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ChartDAO sal() 예외 발생");
		return 0;
	}
	
	
	//mdm에 있는 material data 가져오기
	public List selectMaterial() {
		List list =  new ArrayList();
		String query = "select * from mdm where type='material'";
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						ChartDTO dto = new ChartDTO();
						dto.setMdmName(rs.getString("name"));
		                dto.setMdmUnit(rs.getString("unit"));
		                dto.setMdmType(rs.getString("type"));
		                dto.setMdmPrice(rs.getInt("price"));
		                dto.setMdmQuantity(rs.getInt("quantity"));
						list.add(dto);
					}
				}
				System.out.println("ChartDAO selectMeterial()의 list:  " + list);
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ChartDAO selectMeterial() 예외 발생");
		return null;
	}
	
	
	//mdm에 있는 assemble data 가져오기
		public List selectAssemble() {
			List list =  new ArrayList();
			String query = "select * from mdm where type='assemble'";
			
			try (Connection conn = dataFactory.getConnection(); 
					PreparedStatement ps =  conn.prepareStatement(query)) {

					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {
							ChartDTO dto = new ChartDTO();
							dto.setMdmName(rs.getString("name"));
			                dto.setMdmUnit(rs.getString("unit"));
			                dto.setMdmType(rs.getString("type"));
			                dto.setMdmPrice(rs.getInt("price"));
			                dto.setMdmQuantity(rs.getInt("quantity"));
							list.add(dto);
						}
					}
					System.out.println("ChartDAO selectAssemble()의 list:  " + list);
					return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("ChartDAO selectMeterial() 예외 발생");
			return null;
		}
		
		
		
		public ChartDTO selectTotalAchievement() {
		    // List 필요 없음, 결과 담을 DTO 하나만 생성
			ChartDTO resultDto = new ChartDTO();
		    StringBuilder query = new StringBuilder();

		    // 검색, 페이징 다 빼고 전체 목표와 전체 실적 합계만 구하는 쿼리
		    query.append("SELECT ");
		    query.append("  SUM(\"전체목표\") AS TOTAL_TARGET, ");
		    query.append("  SUM(\"현재까지_만든_총합\") AS TOTAL_ACTUAL ");
		    query.append("FROM ( ");
		    query.append("  SELECT P.WEEKLY_TARGET AS \"전체목표\", ");
		    query.append("         NVL(SUM(L.lot_count), 0) AS \"현재까지_만든_총합\" ");
		    query.append("  FROM production_management P ");
		    query.append("  LEFT JOIN work_order W ON P.prod_num = W.prod_num ");
		    query.append("  LEFT JOIN lot L ON W.order_num = L.order_num ");
		    query.append("  GROUP BY P.prod_num, P.WEEKLY_TARGET ");
		    query.append(") ");

		    try (Connection conn = dataFactory.getConnection(); 
		         PreparedStatement ps = conn.prepareStatement(query.toString());
		         ResultSet rs = ps.executeQuery()) {
		            
		        if (rs.next()) {
		            resultDto.setTarget_quantity(rs.getInt("TOTAL_TARGET")); // 전체 목표량
		            resultDto.setCurrentCount(rs.getInt("TOTAL_ACTUAL"));    // 전체 실적
		        }
		        return resultDto;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    System.out.println("chartDAO에서 생산량 뽑는거 예외처리 됨");
		    return null;
		}
		
		//불량률 뽑는 메서드
		public int selectdefective() {
			String query = "SELECT SUM(count) AS total"
					+ " FROM defective"
					+ " WHERE action != '통과'";
			int result = 0;
			try (Connection conn = dataFactory.getConnection(); 
					PreparedStatement ps =  conn.prepareStatement(query)) {

					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {

							result = rs.getInt("total");
						}
					}
					return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("ChartDAO selectMeterial() 예외 발생");
			return 0;
		}
		

}
