package Lot;

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

import QC.QCDTO;
import WorkOrder.WorkOrderDTO;
import fileLibrary.CommonDTO;

public class LotDAO {
	public List<LotDTO> select(LotDTO dto, CommonDTO pageing) {
		List<LotDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from ( "
							+ "SELECT rownum as rnum, subqry.* from ( "
							+ 		"SELECT l.lot_num, "
							+ "(l.lot_count - NVL(d.count, 0)) AS lot_count, "
							+ "l.order_num, "
							+ "l.qc_chk "
							+ "from lot l "
							+ "LEFT JOIN ( "
							+ "SELECT lot_num, SUM(count) AS count "
							+ "from defective "
							+ "group by lot_num "
							+ ") d on l.lot_num = d. lot_num  ";

			if(dto.getLot_num() != -1) {
				query += "where l.lot_num = ? ";
			}
			query +=") subqry) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//�닔�젙
			ps = conn.prepareStatement(query);
			int idx = 1;
			if(dto.getLot_num() != -1) {
				
				ps.setInt(idx++, dto.getOrder_num());
			}
			ps.setInt(idx++, pageing.getStart());
			ps.setInt(idx++, pageing.getEnd());
			rs = ps.executeQuery();

			while (rs.next()) {
				LotDTO DTO = new LotDTO();
				int lot_num = rs.getInt("lot_num");
				int lot_count = rs.getInt("lot_count");
				int order_num = rs.getInt("order_num");
				String qc_chk = rs.getString("qc_chk");
				
				DTO.setOrder_num(order_num);
				DTO.setLot_num(lot_num);
				DTO.setLot_count(lot_count);
				DTO.setOrder_num(order_num);
				DTO.setQc_chk(qc_chk);
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
	
	public List<LotDTO> selectall(LotDTO dto) {
		List<LotDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT l.lot_num, "
						+ "(l.lot_count - NVL(d.count, 0)) AS lot_count, "
						+ "l.order_num, "
						+ "l.qc_chk "
						+ "from lot l "
						+ "LEFT JOIN ( "
						+ "SELECT lot_num, SUM(count) AS count "
						+ "from defective "
						+ "group by lot_num "
						+ ") d on l.lot_num = d. lot_num ";
			if(dto.getLot_num()!=-1) {
				query += "where l.lot_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if(dto.getLot_num()!=-1) {
				ps.setInt(1,dto.getLot_num());
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				LotDTO DTO = new LotDTO();
				int lot_num = rs.getInt("lot_num");
				int lot_count = rs.getInt("lot_count");
				int order_num = rs.getInt("order_num");
				String qc_chk = rs.getString("qc_chk");
				
				DTO.setOrder_num(order_num);
				DTO.setLot_num(lot_num);
				DTO.setLot_count(lot_count);
				DTO.setOrder_num(order_num);
				DTO.setQc_chk(qc_chk);
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
	public List<WorkOrderDTO> selectalll(LotDTO dto) {
		List<WorkOrderDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from work_order ";
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
				int daily_target = rs.getInt("daily_target");
				String work_order_title = rs.getString("work_order_title");
				
				DTO.setOrder_num(order_num);
				DTO.setDaily_target(daily_target);
				DTO.setWork_order_title(work_order_title);
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
	
	public WorkOrderDTO selectallll(LotDTO dto) {
		WorkOrderDTO result = null;
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from work_order ";
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
				int daily_target = rs.getInt("daily_target");
				String work_order_title = rs.getString("work_order_title");
				
				DTO.setOrder_num(order_num);
				DTO.setDaily_target(daily_target);
				DTO.setWork_order_title(work_order_title);
				result = DTO;
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
		return result;
	}
	
public int lotDAO(LotDTO dto, WorkOrderDTO woDTO) {
		
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
				System.out.println("qc수정으로lot실행");
				  query = "UPDATE lot "
						+ "SET lot_num = ?, "
						+ "lot_count = ?, "
						+ "order_num = ?, "
						+ "qc_chk = ?, "
						+ "material_num = ? "
						+ "where lot_num = ?";
			}
			// 인서트
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO lot "
					   + "(lot_num, "
					   + "lot_count, "
					   + "order_num, "
					   + "qc_chk, "
					   + "empno) "
					   + "  VALUES (lot_seq.nextval, ?, ?, ?, ?)";
			}
			// 딜리트
			if("delete".equals(dto.getMod())) { //만드는중
				query = "DELETE FROM lot "
					  + "WHERE lot_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getLot_num());
				ps.setInt(2, dto.getLot_count());
				ps.setInt(3, dto.getOrder_num());
				ps.setString(4, dto.getQc_chk());
				ps.setInt(5, dto.getMaterial_num());
				ps.setInt(6, dto.getLot_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setInt(1, woDTO.getDaily_target());
				ps.setInt(2, woDTO.getOrder_num());
				ps.setString(3, dto.getQc_chk());
				ps.setInt(4, woDTO.getEmpno());
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getLot_num());
			}
			
			result = ps.executeUpdate();
			
			System.out.println("lotDAO리솔트:"+result);
			
			
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

public int lotQcDAO(LotDTO dto) {
	
	int result = -1;
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		Context ctx = new InitialContext();
		
		DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
		conn = dataFactory.getConnection();
		
		String	query = "UPDATE lot "
					+ "SET lot_num = ?, "
					+ "lot_count = ?, "
					+ "qc_chk = ? "
					+ "where lot_num = ?";
		
		ps = conn.prepareStatement(query);
		
			System.out.println("upps");
			ps.setInt(1, dto.getLot_num());
			ps.setInt(2, dto.getLot_count());
			ps.setString(3, dto.getQc_chk());
			ps.setInt(4, dto.getLot_num());
			
		
		result = ps.executeUpdate();
		
		System.out.println("lotDAO리솔트:"+result);
		
		
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

		String query = "select count(*) from lot";

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
