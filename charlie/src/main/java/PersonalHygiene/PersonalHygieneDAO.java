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

import WorkOrder.WorkOrderDTO;

public class PersonalHygieneDAO {
	public List<PersonalHygieneDTO> select(PersonalHygieneDTO dto) {
		List<PersonalHygieneDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM personal_hygiene h ";
					query += "LEFT OUTER JOIN emp e ";
					query += "on(h.empno = e.empno)";

			if(dto.getPh_num() != -1) {	
				query += "where ph_num = ?";
			}
			ps = conn.prepareStatement(query);
			if(dto.getPh_num() != -1) {	
				ps.setInt(1, dto.getPh_num());
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
			System.out.println("hygieneDAO:"+list);
			
			
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
				System.out.println("최상단DAO:"+dto.getMod());
			
			String query = "";
			// 업데이트
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
			// 인서트
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO personal_hygiene "
					   + "(ph_num, empno, body_temper, regist_time, "
					   + "washed, supervisor_chk, memo) "
					   + "VALUES (?, ?, ?, SYSDATE, ?, ?, ?)";
			}
			// 딜리트
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
				ps.setInt(1, dto.getPh_num());
				ps.setInt(2, dto.getEmpno());
				ps.setDouble(3, dto.getBody_temper());
				ps.setString(4, dto.getWashed());
				ps.setString(5, dto.getSupervisor_chk());
				ps.setString(6, dto.getMemo());
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
}
