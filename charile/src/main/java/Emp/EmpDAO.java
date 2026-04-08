package Emp;

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

public class EmpDAO {
	
	public List<EmpDTO> select(EmpDTO dto) {
		List<EmpDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle");

			conn = dataFactory.getConnection();
			String query = "select * from emp ";
			if(dto.getEmpno() != -1) {
				query += "where empno= ?";
			}
			
			ps = conn.prepareStatement(query);
			
			if(dto.getEmpno() != -1) {
				ps.setInt(1,dto.getEmpno());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				EmpDTO DTO = new EmpDTO();
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				int level = rs.getInt("level");
				String tel = rs.getString("tel");
				int sal = rs.getInt("sal");
				String addr = rs.getString("addr");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				
				DTO.setEmpno(empno);
				DTO.setEname(ename);
				DTO.setId(id);
				DTO.setPw(pw);
				DTO.setLevel(level);
				DTO.setTel(tel);
				DTO.setSal(sal);
				DTO.setAddr(addr);
				DTO.setBirthday(birthday);
				DTO.setEmail(email);
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
	
	public int insert(EmpDTO dto) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle");
			String query ="";
			conn = dataFactory.getConnection();
			if("add".equals(dto.getMod())) {
			query = "insert into emp (empno, ename, id, pw, "
				 + "tel, sal, addr, birthday, email) "
				 + "values (?, ?, ?, ?, ?, ?, ?, ?, ? )";
			}
			ps = conn.prepareStatement(query);
			if("add".equals(dto.getMod())) {
				ps.setInt(1,dto.getEmpno());
				ps.setString(2,dto.getEname());
				ps.setString(3,dto.getId());
				ps.setString(4,dto.getPw());
				ps.setString(5,dto.getTel());
				ps.setInt(6,dto.getSal());
				ps.setString(7,dto.getAddr());
				ps.setDate(8,dto.getBirthday());
				ps.setString(9,dto.getEmail());
			}
			
			result = ps.executeUpdate();
			
			
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
