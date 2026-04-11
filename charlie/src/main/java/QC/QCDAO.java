package QC;

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

import Emp.EmpDTO;
import PersonalHygiene.PersonalHygieneDTO;

public class QCDAO {
	public List<QCDTO> select(QCDTO dto) {
		List<QCDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "select * from qc ";

			if(dto.getQc_num()!=-1) {
				query += "where qc_num = ?";
			}
			ps = conn.prepareStatement(query);

			if(dto.getQc_num()!=-1) {
				ps.setInt(1,dto.getQc_num());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				QCDTO DTO = new QCDTO();
				int qc_num = rs.getInt("qc_num");
				int lot_num = rs.getInt("lot_num");
				Date qc_date = rs.getDate("qc_date");
				int empno = rs.getInt("empno");
				
				DTO.setQc_num(qc_num);
				DTO.setLot_num(lot_num);
				DTO.setQc_date(qc_date);
				DTO.setEmpno(empno);
				
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
	
public int qcDAO(QCDTO dto) {
		
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
			System.out.println("QCDAOmod:"+dto.getMod());
			// 업데이트
			if("up".equals(dto.getMod())) {
				  query = "UPDATE qc "
						+ "SET qc_num = ?, "
						+ "empno = ?, "
						+ "lot_num = ?, "
						+ "qc_date = SYSDATE "
						+ "where qc_num = ?";
			}
			// 인서트
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO qc "//아직안만듬
					   + "(ph_num, empno, body_temper, regist_time, "
					   + "washed, supervisor_chk, memo) "
					   + "VALUES (?, ?, ?, SYSDATE, ?, ?, ?)";
			}
			// 딜리트
			if("delete".equals(dto.getMod())) { //만드는중
				query = "DELETE FROM personal_hygiene "
					  + "WHERE ph_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getQc_num());
				ps.setInt(2, dto.getEmpno());
				ps.setInt(3, dto.getLot_num());
				ps.setInt(4, dto.getQc_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setInt(1, dto.getQc_num());
				ps.setInt(2, dto.getEmpno());
				ps.setInt(3, dto.getLot_num());
				ps.setDate(4, dto.getQc_date());
				
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getQc_num());
			}
			
			result = ps.executeUpdate();
			
			System.out.println("qcDAO:"+result);
			
			
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
