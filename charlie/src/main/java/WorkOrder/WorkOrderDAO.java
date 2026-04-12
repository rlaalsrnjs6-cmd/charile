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

import Emp.EmpDTO;
import PersonalHygiene.PersonalHygieneDTO;

public class WorkOrderDAO {
	public List<WorkOrderDTO> select(WorkOrderDTO dto) {
		List<WorkOrderDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "select * from work_order "; 
//					String query = "select w.order_num, w.title, w.WORK_DATE, e.ename, w.status ";
//					query += "from work_order w left outer join emp e ";
//					query += "on (w.empno = e.empno)";


			if(dto.getOrder_num() != -1) {
				
				query += "where order_num = ?";
			}
	
			ps = conn.prepareStatement(query);
			
			if(dto.getOrder_num() != -1) {
				
				ps.setInt(1, dto.getOrder_num());
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
				int order_num = rs.getInt("order_num");
				Date work_date = rs.getDate("work_date");
				int prod_num = rs.getInt("prod_num");
				int target_quantity = rs.getInt("target_quantity");
				int empno = rs.getInt("empno");
				String title = rs.getString("title");
				String status = rs.getString("status");
				int mdm_num = rs.getInt("mdm_num");

				DTO.setOrder_num(order_num);
				DTO.setWork_date(work_date);
				DTO.setProd_num(prod_num);
				DTO.setTarget_quantity(target_quantity);
				DTO.setEmpno(empno);
				DTO.setTitle(title);
				DTO.setStatus(status);
				DTO.setMdm_num(mdm_num);
				list.add(DTO);
			}
//			while (rs.next()) {
//				WorkOrderDTO DTO = new WorkOrderDTO();
//				int order_num = rs.getInt("order_num");
//				Date work_date = rs.getDate("work_date");
//				String work_order_title = rs.getString("title");
//				String ename = rs.getString("ename");
//				String status = rs.getString("status");
//				
//				DTO.setOrder_num(order_num);
//				DTO.setWork_date(work_date);
//				DTO.setWork_order_title(work_order_title);
//				DTO.setEname(ename);
//				DTO.setStatus(status);
//				list.add(DTO);
//			}
			
			
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
	
public int orderDAO(WorkOrderDTO dto) {
		
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
				  query = "UPDATE work_order "
						+ "SET order_num = ?, "
						+ "work_date = sysdate, "
						+ "title = ?, "
						+ "prod_num = ?, "
						+ "target_quantity = ?, "
						+ "empno = ?, "
						+ "mdm_num = ?, "
						+ "status = ? "
						+ "where order_num = ?";
			}
			// 인서트
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO work_order "
					   + "(order_num, work_date, prod_num, target_quantity, "
					   + "empno, title, mdm_num, status) "
					   + "VALUES (?, SYSDATE, ?, ?, ?, ?, ?, ?)";
			}
			// 딜리트
			if("delete".equals(dto.getMod())) {
				query = "DELETE FROM work_order "
					  + "WHERE order_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getOrder_num());
				ps.setString(2, dto.getTitle());
				ps.setInt(3, dto.getProd_num());
				ps.setInt(4, dto.getTarget_quantity());
				ps.setInt(5, dto.getEmpno());
				ps.setInt(6, dto.getMdm_num());
				ps.setString(7, dto.getStatus());
				ps.setInt(8, dto.getOrder_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				System.out.println(dto.getMdm_num());
				ps.setInt(1, dto.getOrder_num());
				ps.setInt(2, dto.getProd_num());
				ps.setInt(3, dto.getTarget_quantity());
				ps.setInt(4, dto.getEmpno());
				ps.setString(5, dto.getTitle());
				ps.setInt(6, dto.getMdm_num());
				ps.setString(7, dto.getStatus());
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps"+dto.getOrder_num());
				ps.setInt(1, dto.getOrder_num());
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
