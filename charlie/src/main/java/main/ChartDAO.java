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
		String query = "select * from mdm where type='product'";
		
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
		
		//위생관리 불러오기
		public List select(PersonalHygieneDTO dto) {
		    List list = new ArrayList<>();

		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    try {
		        Context ctx = new InitialContext();

		        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		        conn = dataFactory.getConnection();
		        
		        // 페이징용 서브쿼리를 제거한 순수 조회 쿼리
		        String query = "SELECT * FROM personal_hygiene "
		                        // REGIST_TIME이 오늘 00시 혹은 12시 보다 크거나 같은것
		                        + "WHERE REGIST_TIME >= CASE "
		                        + "WHEN to_char(sysdate, 'HH24') < 12 THEN trunc(sysdate) " // 현재시간이 오전이면
		                        + "ELSE trunc(sysdate) + 0.5 " // 현재시간이 오후면
		                        + "END "
		                        // REGIST_TIME이 오늘 12시 혹은 내일00시 보다 작은것		
		                        + "AND REGIST_TIME < CASE "
		                        + "WHEN to_char(sysdate, 'HH24') < 12 THEN trunc(sysdate) + 0.5 " // 현재시간이 오전이면
		                        + "ELSE trunc(sysdate) + 1 " // 현재시간이 오후면
		                        + "END ";

		        // 기존의 "where ph_num" 구문을 "AND ph_num"으로 수정 (SQL 문법 오류 방지)
		        if(dto.getPh_num() != -1) {
		            query += "AND ph_num = ?";
		        }

		        ps = conn.prepareStatement(query);
		        int idx = 1;
		        
		        if(dto.getPh_num() != -1) {
		            ps.setInt(idx++, dto.getPh_num());
		        }
		        
		        // 페이징 start, end 파라미터 세팅 제거 완료
		        rs = ps.executeQuery();

		        while (rs.next()) {
		            PersonalHygieneDTO resultDto = new PersonalHygieneDTO();
		            int ph_num = rs.getInt("ph_num");
		            double body_temper = rs.getDouble("body_temper");
		            Date regist_time = rs.getDate("regist_time");
		            String washed = rs.getString("washed");
		            String memo = rs.getString("memo");
		            String supervisor_chk = rs.getString("supervisor_chk");
		            int empno = rs.getInt("empno");
		            
		            resultDto.setPh_num(ph_num);
		            resultDto.setBody_temper(body_temper);
		            resultDto.setRegist_time(regist_time);
		            resultDto.setWashed(washed);
		            resultDto.setMemo(memo);
		            resultDto.setSupervisor_chk(supervisor_chk);
		            resultDto.setEmpno(empno);
		            
		            list.add(resultDto);
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
}
