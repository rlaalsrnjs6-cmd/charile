package Defective;

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


public class DefectiveDAO {
		public List<DefectiveDTO> select(DefectiveDTO dto) {
			List<DefectiveDTO> list = new ArrayList();
			
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				Context ctx = new InitialContext();

				DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//				System.out.println("DAOMODselect:"+dto.getMod());
				conn = dataFactory.getConnection();
				String query = "select * from defective ";

				if(dto.getDefective_num()!=-1) {
					query += "where defective_num = ?";
				}
				ps = conn.prepareStatement(query);

				if(dto.getDefective_num()!=-1) {
					ps.setInt(1,dto.getDefective_num());
				}
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					DefectiveDTO DTO = new DefectiveDTO();
					int defective_num = rs.getInt("defective_num");
					String category = rs.getString("category");
					int count = rs.getInt("count");
					int qc_num = rs.getInt("qc_num");
					String action = rs.getString("action");
					int mdm_num = rs.getInt("mdm_num");
					
					DTO.setDefective_num(defective_num);
					DTO.setCategory(category);
					DTO.setCount(count);
					DTO.setQc_num(qc_num);
					DTO.setAction(action);
					DTO.setMdm_num(mdm_num);
					
					list.add(DTO);
				}
//				System.out.println("DAOlist:"+list);
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
				// 업데이트
				if("up".equals(dto.getMod())) {
					  query = "UPDATE defective "
							+ "SET defective_num = ?, "
							+ "category = ?, "
							+ "count = ?, "
							+ "qc_num = ?, "
							+ "action = ?, "
							+ "mdm_num = ? "
							+ "where defective_num = ?";
				}
				// 인서트
				if("add".equals(dto.getMod())) {
					 query = "INSERT INTO defective "//아직안만듬
						   + "(defective_num, category, count, qc_num, action, mdm_num) "
						   + "VALUES (?, ?, ?, ?, ?, ?)";
				}
				// 딜리트
				if("delete".equals(dto.getMod())) { //만드는중
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
					ps.setInt(6, dto.getMdm_num());
					ps.setInt(7, dto.getDefective_num());
					
				}
				
				if("add".equals(dto.getMod())) {
					System.out.println("addps");
					ps.setInt(1, dto.getDefective_num());
					ps.setString(2, dto.getCategory());
					ps.setInt(3, dto.getCount());
					ps.setInt(4, dto.getQc_num());
					ps.setString(5, dto.getAction());
					ps.setInt(6, dto.getMdm_num());
					
				}
				
				if("delete".equals(dto.getMod())) {
					System.out.println("deleteps");
					ps.setInt(1, dto.getDefective_num());
				}
				
				result = ps.executeUpdate();
				
				System.out.println("qcDAO리솔트:"+result);
				
				
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
