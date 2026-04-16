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
	public List<WorkOrderDTO> select(WorkOrderDTO dto, CommonDTO pageing) {
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
//					" SELECT * from work_order ";
	                
					query = "SELECT * from ( "
							+ "SELECT rownum as rnum, subqry.* from ( "
							+ 		"select w.order_num, "
							+ 		"pm.prod_num, "
							+ 		"pm.work_start, "
							+ 		"pm.work_end, "
							+ 		"pm.title, "
							+ 		"pm.content, "
							+ 		"w.daily_target, "
							+ 		"pm.empno, "
							+ 		"pm.mdm_num, "
							+ 		"e.ename, "
							+ 		"w.status       "
							+ 	" from production_management pm "
							+ 	"left outer join emp e "
							+ 	"on (pm.empno = e.empno) "
							+ 	"left outer join work_order w "
							+ 	"on (pm.prod_num = w.prod_num) ";


			if(dto.getOrder_num() != -1) {
				query += "where order_num = ?";
			}
			
			query += "ORDER BY pm.work_end asc, pm.work_start asc "
					+ ") subqry) "
					+ "WHERE rnum >= ? AND rnum <= ?";
					
					
					
			//수정
			ps = conn.prepareStatement(query);
			int idx = 1;
			if(dto.getOrder_num() != -1) {
				
				ps.setInt(idx++, dto.getOrder_num());
			}
			ps.setInt(idx++, pageing.getStart());
			ps.setInt(idx++, pageing.getEnd());
			rs = ps.executeQuery();

			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
				int order_num = rs.getInt("order_num");
				int prod_num = rs.getInt("prod_num");
				Date work_start = rs.getDate("work_start");
				Date work_end = rs.getDate("work_end");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int empno = rs.getInt("empno");
				int mdm_num = rs.getInt("mdm_num");
				String ename = rs.getString("ename");
				String status = rs.getString("status");

				DTO.setOrder_num(order_num);
				DTO.setProd_num(prod_num);
				DTO.setWork_start(work_start);
				DTO.setWork_end(work_end);
				DTO.setTitle(title);
				DTO.setContent(content);
				DTO.setEmpno(empno);
				DTO.setMdm_num(mdm_num);
				DTO.setEname(ename);
				DTO.setStatus(status);
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
						+ "(order_num, prod_num, empno) "
						+ "VALUES (work_order_seq.nextval, ?, ?)";
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
				ps.setInt(3, dto.getDaily_target());
				ps.setInt(4, dto.getEmpno());
				ps.setInt(5, dto.getMdm_num());
				ps.setString(6, dto.getStatus());
				ps.setInt(7, dto.getOrder_num());

			}
			if ("add".equals(dto.getMod())) {
				ps.setInt(1, dto.getProd_num());
				ps.setInt(2, dto.getEmpno());
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
