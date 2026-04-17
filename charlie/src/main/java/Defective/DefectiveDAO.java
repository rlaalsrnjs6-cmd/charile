package Defective;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Lot.LotDAO;
import Lot.LotDTO;
import fileLibrary.CommonDTO;


public class DefectiveDAO {
	public List<DefectiveDTO> select(DefectiveDTO dto, CommonDTO pageing) {
		List<DefectiveDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from ( "
							+ "SELECT rownum as rnum, subqry.* from ( "
							+ 		"select * from defective ";

			if(dto.getDefective_num() != -1) {
				query += "where defective_num = ?";
			}
			query +=") subqry) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//�닔�젙
			ps = conn.prepareStatement(query);
			int idx = 1;
			if(dto.getDefective_num() != -1) {
				
				ps.setInt(idx++, dto.getDefective_num());
			}
			ps.setInt(idx++, pageing.getStart());
			ps.setInt(idx++, pageing.getEnd());
			rs = ps.executeQuery();

			while (rs.next()) {
				DefectiveDTO DTO = new DefectiveDTO();
				int defective_num = rs.getInt("defective_num");
				String category = rs.getString("category");
				int count = rs.getInt("count");
				int qc_num = rs.getInt("qc_num");
				String action = rs.getString("action");
//				String status = rs.getString("status");
				
				DTO.setDefective_num(defective_num);
				DTO.setCategory(category);
				DTO.setCount(count);
				DTO.setQc_num(qc_num);
				DTO.setAction(action);
//				DTO.setStatus(status);
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
		
	public List<DefectiveDTO> selectall(DefectiveDTO dto) {
		List<DefectiveDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from defective ";
			if(dto.getDefective_num() != -1) {
				query += "where lot_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if(dto.getDefective_num() != -1) {
				ps.setInt(1,dto.getDefective_num());
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				DefectiveDTO DTO = new DefectiveDTO();
				int defective_num = rs.getInt("defective_num");
				String category = rs.getString("category");
				int count = rs.getInt("count");
				int qc_num = rs.getInt("qc_num");
				String action = rs.getString("action");
//				String status = rs.getString("status");
				
				DTO.setDefective_num(defective_num);
				DTO.setCategory(category);
				DTO.setCount(count);
				DTO.setQc_num(qc_num);
				DTO.setAction(action);
//				DTO.setStatus(status);
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
	
	public int defectiveDAO(DefectiveDTO dto) {
			
			int result = -1;
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				Context ctx = new InitialContext();
				
				DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//				System.out.println("DAOMODselect:"+dto.getMod());
				conn = dataFactory.getConnection();
				
				String query = "";
				System.out.println("QCDAOmod:"+dto.getMod());
				// �뾽�뜲�씠�듃
				if("up".equals(dto.getMod())) {
					  query = "UPDATE defective "
							+ "SET defective_num = ?, "
							+ "category = ?, "
							+ "count = ?, "
							+ "qc_num = ?, "
							+ "action = ? "
							+ "where defective_num = ?";
				}
				// �씤�꽌�듃
				if("add".equals(dto.getMod())) {
					 query = "INSERT INTO defective "//�븘吏곸븞留뚮벉
						   + "(defective_num, category, count, qc_num, action) "
						   + "VALUES (defective_SEQ.nextval, ?, ?, ?, ?)";
				}
				// �뵜由ы듃
				if("delete".equals(dto.getMod())) { //留뚮뱶�뒗以�
					query = "DELETE FROM defective "
						  + "WHERE defective_num = ?";
				}
				ps = conn.prepareStatement(query);
				
				if("up".equals(dto.getMod())) {
					System.out.println("upps");
					ps.setInt(1, dto.getDefective_num());
					ps.setString(2, dto.getCategory());
					ps.setInt(3, dto.getCount());
					ps.setInt(4, dto.getQc_num());
					ps.setString(5, dto.getAction());
					ps.setInt(6, dto.getDefective_num());
				}
				
				if("add".equals(dto.getMod())) {
					System.out.println("addps");
					ps.setString(1, dto.getCategory());
					ps.setInt(2, dto.getCount());
					ps.setInt(3, dto.getQc_num());
					ps.setString(4, dto.getAction());
					
				}
				
				if("delete".equals(dto.getMod())) {
					System.out.println("deleteps");
					ps.setInt(1, dto.getDefective_num());
				}
				
				result = ps.executeUpdate();
				
				
				
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

			String query = "select count(*) from defective";

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
