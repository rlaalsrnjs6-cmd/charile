package PersonalHygiene;

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

import Defective.DefectiveDTO;
import WorkOrder.WorkOrderDTO;
import fileLibrary.CommonDTO;

public class PersonalHygieneDAO {
	
	public List<PersonalHygieneDTO> select(PersonalHygieneDTO dto, CommonDTO pageing) {
		List<PersonalHygieneDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from ( "
							+ "SELECT rownum as rnum, subqry.* from ( "
							+ 		"select * from personal_hygiene "
							//REGIST_TIME이 오늘 00시 혹은 12시 보다 크거나 같은것
							+ "WHERE REGIST_TIME >= CASE "
							+ "WHEN to_char(sysdate, 'HH24') < 12 THEN trunc(sysdate) "//현재시간이 오전이면
							+ "ELSE trunc(sysdate) + 0.5 "//현재시간이 오후면
							+ "END "
							//--REGIST_TIME이 오늘 12시 혹은 내일00시 보다 작은것		
							+ "AND REGIST_TIME < CASE "
							+ "WHEN to_char(sysdate, 'HH24') < 12 THEN trunc(sysdate) + 0.5 "//현재시간이 오전이면
							+ "ELSE trunc(sysdate) + 1 "//현재시간이 오후면
							//즉 현재시간이 오전시간대면 오늘00시이후~12시 이전시간대 정보를 보여주고
							// 현재시간이 오후시간대면 오늘12이후~내일00시이전시간대 정보를 보여줘라
							+ "END";

			if(dto.getPh_num() != -1) {
				query += "where ph_num = ?";
			}
			query +=") subqry) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//�닔�젙
			ps = conn.prepareStatement(query);
			int idx = 1;
			if(dto.getPh_num() != -1) {
				
				ps.setInt(idx++, dto.getPh_num());
			}
			ps.setInt(idx++, pageing.getStart());
			ps.setInt(idx++, pageing.getEnd());
			rs = ps.executeQuery();

			while (rs.next()) {
				PersonalHygieneDTO DTO = new PersonalHygieneDTO();
				int ph_num = rs.getInt("ph_num");
				double body_temper = rs.getDouble("body_temper");
				Date regist_time = rs.getDate("regist_time");
				String washed = rs.getString("washed");
				String memo = rs.getString("memo");
				String supervisor_chk = rs.getString("supervisor_chk");
				int empno = rs.getInt("empno");
				
				DTO.setPh_num(ph_num);
				DTO.setBody_temper(body_temper);
				DTO.setRegist_time(regist_time);
				DTO.setWashed(washed);
				DTO.setMemo(memo);
				DTO.setSupervisor_chk(supervisor_chk);
				DTO.setEmpno(empno);
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
		
	public List<PersonalHygieneDTO> selectall(PersonalHygieneDTO dto) {
		List<PersonalHygieneDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from personal_hygiene ";
			if(dto.getPh_num() != -1) {
				query += "where ph_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if(dto.getPh_num() != -1) {
				ps.setInt(1,dto.getPh_num());
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				PersonalHygieneDTO DTO = new PersonalHygieneDTO();
				int ph_num = rs.getInt("ph_num");
				double body_temper = rs.getDouble("body_temper");
				Date regist_time = rs.getDate("regist_time");
				String washed = rs.getString("washed");
				String memo = rs.getString("memo");
				String supervisor_chk = rs.getString("supervisor_chk");
				int empno = rs.getInt("empno");
				
				DTO.setPh_num(ph_num);
				DTO.setBody_temper(body_temper);
				DTO.setRegist_time(regist_time);
				DTO.setWashed(washed);
				DTO.setMemo(memo);
				DTO.setSupervisor_chk(supervisor_chk);
				DTO.setEmpno(empno);
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
	
	public int hygieneDAO(PersonalHygieneDTO dto) {
		
		int result = -1;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			
			String query = "";
			// �뾽�뜲�씠�듃
			if("up".equals(dto.getMod())) {
				  query = "UPDATE personal_hygiene "
						+ "SET ph_num = ?, "
						+ "empno = ?, "
						+ "body_temper = ?, "
						+ "regist_time = SYSDATE, "
						+ "washed = ?, "
						+ "supervisor_chk = ?, "
						+ "memo = ? "
						+ "where ph_num = ?";
			}
			// �씤�꽌�듃
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO personal_hygiene "
					   + "(ph_num, empno, body_temper, regist_time, "
					   + "washed, supervisor_chk, memo) "
					   + "VALUES (hygiene_SEQ.nextval, ?, ?, SYSDATE+9/24, ?, ?, ?)";
			}
			// �뵜由ы듃
			if("delete".equals(dto.getMod())) {
				query = "DELETE FROM personal_hygiene "
					  + "WHERE ph_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getPh_num());
				ps.setInt(2, dto.getEmpno());
				ps.setDouble(3, dto.getBody_temper());
				ps.setString(4, dto.getWashed());
				ps.setString(5, dto.getSupervisor_chk());
				ps.setString(6, dto.getMemo());
				ps.setInt(7, dto.getPh_num());
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setInt(1, dto.getEmpno());
				ps.setDouble(2, dto.getBody_temper());
				ps.setString(3, dto.getWashed());
				ps.setString(4, dto.getSupervisor_chk());
				ps.setString(5, dto.getMemo());
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getPh_num());
			}
			
			result = ps.executeUpdate();
			
			System.out.println("hygieneDAO:"+result);
			
			
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

			String query = "select count(*) from personal_hygiene "
					+ "WHERE REGIST_TIME >= CASE "
					+ "WHEN to_char(sysdate, 'HH24') < 12 THEN trunc(sysdate) "
					+ "ELSE trunc(sysdate) + 0.5 "
					+ "END "
					+ "AND REGIST_TIME < CASE "
					+ "WHEN to_char(sysdate, 'HH24') < 12 THEN trunc(sysdate) + 0.5 "
					+ "ELSE trunc(sysdate) + 1 "
					+ "END";

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
