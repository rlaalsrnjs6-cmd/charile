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

import fileLibrary.CommonDTO;

public class WorkOrderDAO {
	public List<WorkOrderDTO> selectOne(WorkOrderDTO dto) {
		List<WorkOrderDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = //수정
					" SELECT * from work_order ";
	                
//					String query = "select w.order_num, w.title, w.WORK_DATE, e.ename, w.status ";
//					query += "from work_order w left outer join emp e ";
//					query += "on (w.empno = e.empno)";


			if(dto.getOrder_num() != -1) {
				query += "where order_num = ?";
			}
			//수정
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
				String title = rs.getString("work_order_title");
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
			System.out.println("최상단DAO:" + dto.getMod());

			String query = "";
			// 업데이트
			if ("up".equals(dto.getMod())) {
				query = "UPDATE work_order " + "SET order_num = ?, " + "work_date = sysdate, "
						+ "work_order_title = ?, " + "prod_num = ?, " + "target_quantity = ?, " + "empno = ?, "
						+ "mdm_num = ?, " + "status = ? " + "where order_num = ?";
			}
			// 인서트
			if ("add".equals(dto.getMod())) {
				query = "INSERT INTO work_order " 
						+ "(order_num, work_date, prod_num, target_quantity, "
						+ "empno, work_order_title, mdm_num, status) " 
						+ "VALUES (work_order_seq.nextval, SYSDATE, ?, ?, ?, ?, ?, ?)";
			}
			// 딜리트
			if ("delete".equals(dto.getMod())) {
				query = "DELETE FROM work_order " + "WHERE order_num = ?";
			}
			ps = conn.prepareStatement(query);

			if ("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setString(1, dto.getTitle());
				ps.setInt(2, dto.getProd_num());
				ps.setInt(3, dto.getTarget_quantity());
				ps.setInt(4, dto.getEmpno());
				ps.setInt(5, dto.getMdm_num());
				ps.setString(6, dto.getStatus());
				ps.setInt(7, dto.getOrder_num());

			}

			if ("add".equals(dto.getMod())) {
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

			if ("delete".equals(dto.getMod())) {
				System.out.println("deleteps" + dto.getOrder_num());
				ps.setInt(1, dto.getOrder_num());
			}

			result = ps.executeUpdate();

			System.out.println("hygieneDAO:" + result);

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

	public List<WorkOrderDTO> select(WorkOrderDTO dto, CommonDTO pageing ) {
		List<WorkOrderDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = //수정
					"   SELECT * from (   "
	                + "   SELECT rownum as rnum, subqry.* from (   " 
					+ "     select * from work_order w "
					+ "	ORDER BY w.order_num asc "
					+ "     ) subqry "
					+ " ) "
             		+ " WHERE rnum >= ? AND rnum <= ?";
			ps = conn.prepareStatement(query);
			
			System.out.println("스타트:" +  pageing.getStart());
			System.out.println("엔드:" +  pageing.getEnd());
			ps.setInt(1, pageing.getStart());
			ps.setInt(2, pageing.getEnd());

			rs = ps.executeQuery();

			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
				int order_num = rs.getInt("order_num");
				Date work_date = rs.getDate("work_date");
				int prod_num = rs.getInt("prod_num");
				int target_quantity = rs.getInt("target_quantity");
				int empno = rs.getInt("empno");
				String title = rs.getString("work_order_title");
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
	
//Use paging 수정
	public int getTotalCount() {

		int total = 0;

		try {
			// 자원을 가지러 가기 위해 문을 열고
			Context ctx = new InitialContext();
			// 열어둔 문을 통해 어디로 갈지 경로를 정함
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");

			String query = "select count(*) from work_order";

			try (Connection conn = dataFactory.getConnection();
					PreparedStatement ps = conn.prepareStatement(query);
					ResultSet rs = ps.executeQuery()) {

				if (rs.next()) { // count 1줄 return
					total = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
