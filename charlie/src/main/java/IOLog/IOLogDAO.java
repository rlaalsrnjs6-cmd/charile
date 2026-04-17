package IOLog;

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
import Warehouse.WarehouseDTO;
import fileLibrary.CommonDTO;

public class IOLogDAO {
	public List<IOLogDTO> select(IOLogDTO dto, CommonDTO pageing) {
		List<IOLogDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from ( "
							+ "SELECT rownum as rnum, subqry.* from ( "
							+ 		"select * from io_log ";

			if(dto.getLog_num() != -1) {
				query += "where log_num = ?";
			}
			query +=") subqry) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//�닔�젙
			ps = conn.prepareStatement(query);
			int idx = 1;
			if(dto.getLog_num() != -1) {
				
				ps.setInt(idx++, dto.getLog_num());
			}
			ps.setInt(idx++, pageing.getStart());
			ps.setInt(idx++, pageing.getEnd());
			rs = ps.executeQuery();

			while (rs.next()) {
				IOLogDTO DTO = new IOLogDTO();
				int log_num = rs.getInt("log_num");
				Date io_time = rs.getDate("io_time");
				String io_type = rs.getString("io_type");
				int lot_num = rs.getInt("lot_num");
				
				DTO.setLog_num(log_num);
				DTO.setIo_time(io_time);
				DTO.setIo_type(io_type);
				DTO.setLot_num(lot_num);
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
		
	public List<IOLogDTO> selectall(IOLogDTO dto) {
		List<IOLogDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from io_log ";
			if(dto.getLog_num() != -1) {
				query += "where log_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if(dto.getLog_num() != -1) {
				ps.setInt(1,dto.getLog_num());
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				IOLogDTO DTO = new IOLogDTO();
				int log_num = rs.getInt("log_num");
				Date io_time = rs.getDate("io_time");
				String io_type = rs.getString("io_type");
				int lot_num = rs.getInt("lot_num");
				
				DTO.setLog_num(log_num);
				DTO.setIo_time(io_time);
				DTO.setIo_type(io_type);
				DTO.setLot_num(lot_num);
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
	
public int logDAO(IOLogDTO dto) {
		
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
			// �뾽�뜲�씠�듃
			if("up".equals(dto.getMod())) {
				  query = "UPDATE  io_log "
						+ "SET log_num = ?, "
						+ "io_time = ?, "
						+ "io_type = ?, "
						+ "lot_num = ? "
						+ "where log_num = ?";
			}
			// �씤�꽌�듃
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO io_log "//�븘吏곸븞留뚮벉
					   + "(log_num, "
					   + "io_type, "
					   + "io_time, "
					   + "lot_num) "
					   + "VALUES (io_log_SEQ.nextval, ?, ?, ?)";
			}
			// �뵜由ы듃
			if("delete".equals(dto.getMod())) { //留뚮뱶�뒗以�
				query = "DELETE FROM io_log "
					  + "WHERE log_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getLog_num());
				ps.setDate(2, dto.getIo_time());
				ps.setString(3, dto.getIo_type());
				ps.setInt(4, dto.getLot_num());
				ps.setInt(5, dto.getLog_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setString(1, dto.getIo_type());
				ps.setDate(2, dto.getIo_time());
				ps.setInt(3, dto.getLot_num());
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getLog_num());
			}
			
			result = ps.executeUpdate();
			
			System.out.println("logDAO마지막:"+result);
			
			
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

		String query = "select count(*) from io_log";

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
