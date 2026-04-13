package UploadFile;

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

import IOLog.IOLogDTO;

public class UploadFileDAO {
	public List<UploadFileDTO> select(UploadFileDTO dto) {
		List<UploadFileDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "select * from upload_file ";

			if(dto.getFile_num()!=-1) {
				query += "where file_num = ?";
			}
			ps = conn.prepareStatement(query);

			if(dto.getFile_num()!=-1) {
				ps.setInt(1,dto.getFile_num());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				UploadFileDTO DTO = new UploadFileDTO();
				int file_num = rs.getInt("file_num");
				String url = rs.getString("url");
				int random_code = rs.getInt("random_code");
				Date upload_time = rs.getDate("upload_time");
				
				DTO.setFile_num(file_num);
				DTO.setUrl(url);
				DTO.setRandom_code(random_code);
				DTO.setUpload_time(upload_time);
				
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
	
public int fileDAO(UploadFileDTO dto) {
		
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
			System.out.println("fileDAOmod:"+dto.getMod());
			// 업데이트
			if("up".equals(dto.getMod())) {
				  query = "UPDATE  upload_file "
						+ "SET file_num = ?, "
						+ "url = ?, "
						+ "random_code = ?, "
						+ "upload_time = sysdate "
						+ "where file_num = ?";
			}
			// 인서트
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO upload_file "//아직안만듬
					   + "(file_num, "
					   + "url, "
					   + "random_code, "
					   + "upload_time) "
					   + "VALUES (?, ?, ?, sysdate)";
			}
			// 딜리트
			if("delete".equals(dto.getMod())) { //만드는중
				query = "DELETE FROM upload_file "
					  + "WHERE file_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getFile_num());
				ps.setString(2, dto.getUrl());
				ps.setInt(3, dto.getRandom_code());
				ps.setInt(4, dto.getFile_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setInt(1, dto.getFile_num());
				ps.setString(2, dto.getUrl());
				ps.setInt(3, dto.getRandom_code());
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getFile_num());
			}
			
			result = ps.executeUpdate();
			
			System.out.println("FileDAO리솔트:"+result);
			
			
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
