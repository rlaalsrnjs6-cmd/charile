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

import Defective.DefectiveDTO;
import fileLibrary.CommonDTO;

public class EmpDAO {
	
	public List<EmpDTO> select(EmpDTO dto, CommonDTO pageing) {
		List<EmpDTO> list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "SELECT * from ( "
					+ "SELECT rownum as rnum, subqry.* from ( "
					+ "select * from emp ";

			System.out.println("empDAOmod: "+dto.getMod());
			System.out.println("empDAOid: "+dto.getId());
			System.out.println("empDAOpw: "+dto.getPw());
			System.out.println("empDAO스타트: "+pageing.getStart());
			System.out.println("empDAO엔드: "+pageing.getEnd());
			
			
			if("detail".equals(dto.getMod()) || "up".equals(dto.getMod())) {
				
				query += "where empno = ? ";
			}
			
			if("add".equals(dto.getMod())) {
				query += "where empno = ? or id = ? or email = ? ";
			}

			if("login".equals(dto.getMod())) {
				query += "where id = ? and pw = ? ";
			}

			query += "ORDER BY emp_level asc, empno asc "
					+ ") subqry) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			
			ps = conn.prepareStatement(query);

			int idx = 1;
			
			if("detail".equals(dto.getMod()) || "up".equals(dto.getMod())) {
				System.out.println("empnoDAO:"+dto.getEmpno());
				ps.setInt(idx++,dto.getEmpno());
			}
			
			if("add".equals(dto.getMod())) {
				ps.setInt(idx++,dto.getEmpno());
				ps.setString(idx++,dto.getId());
				ps.setString(idx++,dto.getEmail());
			}

			if("login".equals(dto.getMod())) {
				ps.setString(idx++,dto.getId());
				ps.setString(idx++,dto.getPw());
			}
			
			ps.setInt(idx++, pageing.getStart());
		       ps.setInt(idx++, pageing.getEnd());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				EmpDTO DTO = new EmpDTO();
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				int level = rs.getInt("emp_level");
				String tel = rs.getString("tel");
				int sal = rs.getInt("sal");
				String addr = rs.getString("addr");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				String status = rs.getString("status");
				
				DTO.setEmpno(empno);
				DTO.setEname(ename);
				DTO.setId(id);
				DTO.setPw(pw);
				DTO.setEmp_level(level);
				DTO.setTel(tel);
				DTO.setSal(sal);
				DTO.setAddr(addr);
				DTO.setBirthday(birthday);
				DTO.setEmail(email);
				DTO.setStatus(status);
				list.add(DTO);
			}
//			System.out.println("DAOlist:"+list);
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
	
	public List<EmpDTO> selectpage(EmpDTO dto,CommonDTO pageing) {
		List<EmpDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query ="SELECT * from ( "
					+ "SELECT rownum as rnum, subqry.* from ( "
					+ "select * from emp "
					+ ") subqry) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			
			
			ps = conn.prepareStatement(query);
			
				ps.setInt(1,pageing.getSize());
				ps.setInt(2,pageing.getPage());
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				EmpDTO DTO = new EmpDTO();
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				int level = rs.getInt("emp_level");
				String tel = rs.getString("tel");
				int sal = rs.getInt("sal");
				String addr = rs.getString("addr");
				Date birthday = rs.getDate("birthday");
				String email = rs.getString("email");
				String status = rs.getString("status");
				
				DTO.setEmpno(empno);
				DTO.setEname(ename);
				DTO.setId(id);
				DTO.setPw(pw);
				DTO.setEmp_level(level);
				DTO.setTel(tel);
				DTO.setSal(sal);
				DTO.setAddr(addr);
				DTO.setBirthday(birthday);
				DTO.setEmail(email);
				DTO.setStatus(status);
				list.add(DTO);
			}
//			System.out.println("DAOlist:"+list);
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

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			String query ="";
			conn = dataFactory.getConnection();
			if("add".equals(dto.getMod())) {
			query = "insert into emp (empno, ename, id, pw, "
				 + "tel, addr, birthday, email) "
				 + "values (emp_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ? )";
			}
			ps = conn.prepareStatement(query);
			if("add".equals(dto.getMod())) {
				System.out.println("DAO인서트 입장");
				ps.setString(1,dto.getEname());
				ps.setString(2,dto.getId());
				ps.setString(3,dto.getPw());
				ps.setString(4,dto.getTel());
				ps.setString(5,dto.getAddr());
				ps.setDate(6,dto.getBirthday());
				ps.setString(7,dto.getEmail());
			}
			
			result = ps.executeUpdate();
//			System.out.println("DAOinsertResult:"+result);
			
			
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
	
	public int empDAO(EmpDTO dto) {
		
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
			System.out.println("EmpDAO모드:"+dto.getMod());
			System.out.println("EmpDAO스테:"+dto.getStatus());
			// 업데이트
			if("up".equals(dto.getMod())) {
				  query = "UPDATE emp "
						+ "SET empno = ?, "
						+ "ename = ?, "
						+ "emp_level = ?, "
						+ "tel = ?, "
						+ "sal = ?, "
						+ "addr = ?, "
						+ "birthday = ?, "
						+ "email = ?, "
						+ "status = ? "
						+ "where empno = ?";
			}
			// 딜리트
			if("delete".equals(dto.getMod())) { //만드는중
				query = "DELETE FROM emp "
					  + "WHERE empno = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getEmpno());
				ps.setString(2, dto.getEname());
				ps.setInt(3, dto.getEmp_level());
				ps.setString(4, dto.getTel());
				ps.setInt(5, dto.getSal());
				ps.setString(6, dto.getAddr());
				ps.setDate(7, dto.getBirthday());
				ps.setString(8, dto.getEmail());
				ps.setString(9, dto.getStatus());
				ps.setInt(10, dto.getEmpno());
				
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getEmpno());
			}
			
			result = ps.executeUpdate();
			
			System.out.println("upDAO리솔트:"+result);
			
			
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
	
	//Use paging 수정
	public int getTotalCount() {

	    int total = 0;

	    try {
	        //자원을 가지러 가기 위해 문을 열고
	        Context ctx = new InitialContext();
	        //열어둔 문을 통해 어디로 갈지 경로를 정함
	        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");

	        String query ="select count(*) from emp"; 

	        try(Connection conn = dataFactory.getConnection();
	            PreparedStatement ps = conn.prepareStatement(query);
	                ResultSet rs = ps.executeQuery()){

	            if(rs.next()) { // count 1줄 return
	                total = rs.getInt(1);
	            }
	        }
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	    return total;
	}
}
