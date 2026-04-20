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
			conn = dataFactory.getConnection();
			String query = "SELECT * from ( "
							+ "SELECT rownum as rnum, subqry.* from ( "
							+ 		"select * from work_order ";
			
							System.out.println("오전오후: "+dto.getTimefilter());
			if(!("select".equals(dto.getMod())) && dto.getOrder_num() != -1) {
				query += "where order_num = ? ";
			}
			
			if("select".equals(dto.getMod()) && "am".equals(dto.getTimefilter())) {
				System.out.println("셀렉트 오전");
				query += "WHERE work_date >= TRUNC(SYSDATE+9/24) "
						+ "AND work_date < TRUNC(SYSDATE+9/24) + 0.5 ";
			}
			if("select".equals(dto.getMod()) && "pm".equals(dto.getTimefilter())) {
				System.out.println("셀렉트 오후");
				query += "where work_date >= TRUNC(SYSDATE+9/24) + 0.5 "
						+ "AND work_date < TRUNC(SYSDATE+9/24) + 1 ";
			}
			if("select".equals(dto.getMod()) && ("Y".equals(dto.getStatustitle()) || "N".equals(dto.getStatustitle())) && !("total".equals(dto.getTimefilter()))) {
					System.out.println("스테이터스 yN AND:" + dto.getStatustitle());
				query+= "and status = ? ";
			}
			if("select".equals(dto.getMod()) && ("Y".equals(dto.getStatustitle()) || "N".equals(dto.getStatustitle())) && "total".equals(dto.getTimefilter())) {
				System.out.println("스테이터스 yN WHERE:" + dto.getStatustitle());
				query+= "WHERE status = ? ";
			}
			
			query +=" order by work_date asc "
					+ " ) subqry) "
					+ " WHERE rnum >= ? AND rnum <= ? ";
			//�닔�젙
			ps = conn.prepareStatement(query);
			int idx = 1;
			if(!("select".equals(dto.getMod())) && dto.getOrder_num() != -1) {
				
				ps.setInt(idx++, dto.getOrder_num());
			}
			
			if("select".equals(dto.getMod()) && ("Y".equals(dto.getStatustitle()) || "N".equals(dto.getStatustitle())) && !("total".equals(dto.getTimefilter()))) {
				ps.setString(idx++, dto.getStatustitle());
			}
			if("select".equals(dto.getMod()) && ("Y".equals(dto.getStatustitle()) || "N".equals(dto.getStatustitle())) && "total".equals(dto.getTimefilter())) {
				ps.setString(idx++, dto.getStatustitle());
			}
			ps.setInt(idx++, pageing.getStart());
			ps.setInt(idx++, pageing.getEnd());
			rs = ps.executeQuery();

			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
				int order_num = rs.getInt("order_num");
				Date work_date = rs.getDate("work_date");
				int prod_num = rs.getInt("prod_num");
				int daily_target = rs.getInt("daily_target");
				int empno = rs.getInt("empno");
				String work_order_title = rs.getString("work_order_title");
				String status = rs.getString("status");

				DTO.setOrder_num(order_num);
				DTO.setWork_date(work_date);
				DTO.setProd_num(prod_num);
				DTO.setDaily_target(daily_target);
				DTO.setEmpno(empno);
				DTO.setWork_order_title(work_order_title);
				DTO.setStatus(status);
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
	
	public List<WorkOrderDTO> selectall(WorkOrderDTO dto) {
		List<WorkOrderDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from work_order";
			
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
				int order_num = rs.getInt("order_num");
				Date work_date = rs.getDate("work_date");
				int prod_num = rs.getInt("prod_num");
				int daily_target = rs.getInt("daily_target");
				int empno = rs.getInt("empno");
				String work_order_title = rs.getString("work_order_title");
				String status = rs.getString("status");
				
				DTO.setOrder_num(order_num);
				DTO.setWork_date(work_date);
				DTO.setProd_num(prod_num);
				DTO.setDaily_target(daily_target);
				DTO.setEmpno(empno);
				DTO.setWork_order_title(work_order_title);
				DTO.setStatus(status);
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
			String query = "";
			if ("up".equals(dto.getMod())) {
				query = "UPDATE work_order " 
						+ "SET work_order_title = ?, " 
						+ "work_date = ?, "
						+ "daily_target = ?, " 
						+ "empno = ?, "
						+ "status = ? " 
						+ "where order_num = ?";
			}
			if ("add".equals(dto.getMod())) {
				query = "INSERT INTO work_order " 
						+ "(order_num, prod_num, work_order_title, work_date, daily_target, empno, status) "
						+ "values( "
						+ "work_order_seq.nextval, "
						+ "?, "
						+ "?, "
						+ "?, "
						+ "?, "
						+ "?, "
						+ "? "
						+ ") ";
			}
			if ("delete".equals(dto.getMod())) {
				query = "DELETE FROM work_order " + "WHERE order_num = ?";
			}
			ps = conn.prepareStatement(query);

			if ("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setString(1, dto.getWork_order_title());
				ps.setDate(2, dto.getWork_date());
				ps.setInt(3, dto.getDaily_target());
				ps.setInt(4, dto.getEmpno());
				ps.setString(5, dto.getStatus());
				ps.setInt(6, dto.getOrder_num());

			}
			if ("add".equals(dto.getMod())) {
				ps.setInt(1, dto.getProd_num());
				ps.setString(2, dto.getWork_order_title());
				ps.setDate(3, dto.getWork_date());
				ps.setInt(4, dto.getDaily_target());
				ps.setInt(5, dto.getEmpno());
				ps.setString(6, dto.getStatus());
				}
			
			if ("delete".equals(dto.getMod())) {
				System.out.println("deleteps" + dto.getOrder_num());
				ps.setInt(1, dto.getOrder_num());
			}

			result = ps.executeUpdate();

			System.out.println("orderDAO:" + result);

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

			String query = "select count(*) from work_order";

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
