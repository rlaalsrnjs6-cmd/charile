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

import Warehouse.WarehouseDTO;

public class IOLogDAO {
	public List<IOLogDTO> select(IOLogDTO dto) {
		List<IOLogDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "select * from io_log ";

			if(dto.getLog_num()!=-1) {
				query += "where log_num = ?";
			}
			ps = conn.prepareStatement(query);

			if(dto.getLog_num()!=-1) {
				ps.setInt(1,dto.getLog_num());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				IOLogDTO DTO = new IOLogDTO();
				int log_num = rs.getInt("log_num");
				Date io_time = rs.getDate("io_time");
				String io_type = rs.getString("io_type");
				int lot_num = rs.getInt("lot_num");
				int mdm_num = rs.getInt("mdm_num");
				
				DTO.setLog_num(log_num);
				DTO.setIo_time(io_time);
				DTO.setIo_type(io_type);
				DTO.setLot_num(lot_num);
				DTO.setMdm_num(mdm_num);
				
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
			// 업데이트
			if("up".equals(dto.getMod())) {
				  query = "UPDATE  io_log "
						+ "SET log_num = ?, "
						+ "io_time = sysdate, "
						+ "io_type = ?, "
						+ "lot_num = ?, "
						+ "mdm_num = ? "
						+ "where log_num = ?";
			}
			// 인서트
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO io_log "//아직안만듬
					   + "(log_num, "
					   + "io_type, "
					   + "lot_num, "
					   + "mdm_num) "
					   + "VALUES (?, ?, ?, ?)";
			}
			// 딜리트
			if("delete".equals(dto.getMod())) { //만드는중
				query = "DELETE FROM io_log "
					  + "WHERE log_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getLog_num());
				ps.setString(2, dto.getIo_type());
				ps.setInt(3, dto.getLot_num());
				ps.setInt(4, dto.getMdm_num());
				ps.setInt(5, dto.getLog_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setInt(1, dto.getLog_num());
				ps.setString(2, dto.getIo_type());
				ps.setInt(3, dto.getLot_num());
				ps.setInt(4, dto.getMdm_num());
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getLog_num());
			}
			
			result = ps.executeUpdate();
			
			System.out.println("logDAO리솔트:"+result);
			
			
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
