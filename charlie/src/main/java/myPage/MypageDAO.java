package myPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Board.BoardDTO;

public class MypageDAO {

	private DataSource dataFactory; //캡슐화를 위해 private
	
	MypageDAO(){
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}//생성자 닫음
	
	public List selectData(MypageDTO dto) {
		List list = new ArrayList();
		String query = "select * from emp where empno=?";
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {
				
				ps.setInt(1, dto.getEmpno());

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						MypageDTO dto2 = new MypageDTO();
						dto2.setEmpno(rs.getInt("empno"));
		                dto2.setEname(rs.getString("ename"));
		                dto2.setId(rs.getString("id"));
		                dto2.setPw(rs.getString("pw"));
		                dto2.setEmpLevel(rs.getInt("emp_level"));
		                dto2.setTel(rs.getString("tel"));
		                dto2.setAddr(rs.getString("addr"));
		                dto2.setEmail(rs.getString("email"));
		                dto2.setDept(rs.getString("dept"));
		                dto2.setHireDate(rs.getString("hiredate"));
						list.add(dto2);
					}
				}
				System.out.println("DAO에서 list" + list);
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Mypage 셀렉트메서드 예외 발생");
		return null;
	}
		

}
