package Lot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import QC.QCDTO;

public class LotDAO {
	public List<LotDTO> select(LotDTO dto) {
		List<LotDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "select * from lot ";

			System.out.println("lotadomod:"+dto.getMod());
			if("up".equals(dto.getMod())) {
				System.out.println("실행됐나?");
				query = "select * "
						+ "from qc q "
						+ "join lot l "
						+ "on (q.lot_num = l.lot_num) "
						+ "where qc_num = ?";
			}

			if(!("up".equals(dto.getMod())) && dto.getLot_num()!=-1) {
				query += "where lot_num = ?";
			}
			ps = conn.prepareStatement(query);

			if("up".equals(dto.getMod())) {
				ps.setInt(1,dto.getQc_num());
				System.out.println("tlfgodehoTsk2:"+dto.getQc_num());
			}

			if(!("up".equals(dto.getMod())) && dto.getLot_num()!=-1) {
				ps.setInt(1,dto.getLot_num());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				LotDTO DTO = new LotDTO();
				int lot_num = rs.getInt("lot_num");
				int lot_count = rs.getInt("lot_count");
				int order_num = rs.getInt("order_num");
				String qc_chk = rs.getString("qc_chk");
				int material_num = rs.getInt("material_num");
				int mdm_num = rs.getInt("mdm_num");
				
				DTO.setLot_num(lot_num);
				DTO.setLot_count(lot_count);
				DTO.setOrder_num(order_num);
				DTO.setQc_chk(qc_chk);
				DTO.setMaterial_num(material_num);
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
	
public int lotDAO(LotDTO dto) {
		
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
						+ "material_num = ?, "
						+ "mdm_num = ? "
						+ "where lot_num = ?";
			}
			// 인서트
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO lot "//아직안만듬
					   + "(lot_num, "
					   + "lot_count, "
					   + "order_num, "
					   + "qc_chk, "
					   + "material_num, "
					   + "mdm_num) "
					   + "VALUES (?, ?, ?, ?, ?, ?)";
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
				ps.setInt(6, dto.getMdm_num());
				ps.setInt(7, dto.getLot_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setInt(1, dto.getLot_num());
				ps.setInt(2, dto.getLot_count());
				ps.setInt(3, dto.getOrder_num());
				ps.setString(4, dto.getQc_chk());
				ps.setInt(5, dto.getMaterial_num());
				ps.setInt(6, dto.getMdm_num());
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
}
