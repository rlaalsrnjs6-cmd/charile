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
	
	public List<EmpDTO> select(EmpDTO productDTO) {
		List<EmpDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle");

			conn = dataFactory.getConnection();
			String query = "select * from emp ";
			if(productDTO.getEmpno() != -1) {
				query += "where empno= ?";
			}
			
			ps = conn.prepareStatement(query);
			
			if(productDTO.getEmpno() != -1) {
				ps.setInt(1,productDTO.getEmpno());
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
	
	public int insert(EmpDTO productDTO) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle");

			conn = dataFactory.getConnection();
			String query = "insert into emp (empno, ename, id, pw, "
						 + "tel, sal, addr, birthday, email) "
						 + "values (?, ?, ?, ?, ?, ?, ?, ?, ? )";
			
			ps = conn.prepareStatement(query);
			
				ps.setInt(1,productDTO.getEmpno());
				ps.setString(2,productDTO.getEname());
				ps.setString(3,productDTO.getId());
				ps.setString(4,productDTO.getPw());
				ps.setString(5,productDTO.getTel());
				ps.setInt(6,productDTO.getSal());
				ps.setString(7,productDTO.getAddr());
				ps.setDate(8,productDTO.getBirthday());
				ps.setString(9,productDTO.getEmail());
			
			
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
