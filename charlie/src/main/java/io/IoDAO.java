package io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fileLibrary.CommonDTO;
import fileLibrary.LoggableStatement;
import fileLibrary.ParentDAO4;

public class IoDAO extends ParentDAO4<IoDTO, CommonDTO> {
	
	// set Table �젙蹂� 
	@Override
	protected String tableName() {
		return "io";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "io_num";
	}

	@Override
	protected int setDTONum(IoDTO dto) {
		return dto.getIo_num();
	}
	
	protected String innerQuery(IoDTO dto, CommonDTO commonDTO) {

		 String query = 
				   " SELECT i.io_num, i.quantity, i.io_type, i.storage_sec, "
				   + "i.io_date, i.exp_date, i.mdm_num, "
				   + "m.unit, m.name, m.code  "
				 
				 + " FROM io i "
				 + " JOIN mdm m "
				 + " ON i.mdm_num = m.mdm_num ";
			
		 // SET WHERE
		 String where = commonDTO.getWhere();
		 if(("".equals(commonDTO.getWhere()))) where = " WHERE 1 = 1 ";  
		 
		 // 媛�蹂� 議곌굔
		 if(commonDTO.getSearch() != null
				 && !"".equals(commonDTO.getSearch())) {
		
		switch(commonDTO.getSelector()) {
			
		// �쟾泥닿��깋
		case "search_all": where += " AND code LIKE " 
									+ "'%" + commonDTO.getSearch() + "%'"
									+ " or name LIKE '%" + commonDTO.getSearch() + "%'" 
									+ " or unit LIKE '%" + commonDTO.getSearch() + "%'"
		/* 而щ읆蹂� 寃��깋 */			    + " or type LIKE '%" + commonDTO.getSearch() + "%'"; break;	
		case "code" : where += " AND code LIKE '%" +  commonDTO.getSearch() + "%'"; break;
		case "name" : where += " AND name LIKE '%" +  commonDTO.getSearch() + "%'"; break;
		case "unit" : where += " AND unit LIKE '%" +  commonDTO.getSearch() + "%'"; break;
		case "type" : where += " AND type LIKE '%" +  commonDTO.getSearch() + "%'"; break;
		default : break;
		}
	}
	
	String where2 = commonDTO.getWhere2();
   if (where2 == null || "".equals(where2)) {
       where2 = "";
   }
   String where3 = commonDTO.getWhere3();
   if (where3 == null || "".equals(where3)) {
   	where3 = "";
   }
	// 異붽� �궡�슜
	query += where
		  +  where2
	      +  where3;
	      
		
		return query;
	}
	
	@Override
	protected String selectQuery(IoDTO dto, CommonDTO commonDTO) {
		// SET ORDERBY
		String orderBy = pk_Coulum_Name();
		if ( commonDTO.getOrderBy() != null ) orderBy = commonDTO.getOrderBy();
			
		// SET QUERY
		String query = " SELECT * FROM ( "
					 + "	 SELECT rownum as rnum, subqry.* from ( "
					 + innerQuery(dto, commonDTO)
					 +" ORDER BY " + orderBy + " DESC "
					 + " ) subqry )"
		 	  +" WHERE rnum >= ? AND rnum <= ?" ;
		return query;
		
	}
	

	@Override
	protected String insertQuery() {
		return "INSERT INTO io ( io_num, quantity, io_type, storage_sec, exp_date, mdm_num) " 
				+ " VALUES ( io_seq.nextval, ?, ?, ?, ?, ?)";
	}

	@Override
	protected PreparedStatement setPs(PreparedStatement ps, IoDTO dto, String selector) throws SQLException {

			ps.setInt(1, dto.getQuantity());
			ps.setString(2, dto.getIo_type());
			ps.setString(3, dto.getStorage_sec());
			ps.setDate(4, dto.getExp_date());
			ps.setInt(5, dto.getMdm_num());
			if ("update".equals(selector)) { ps.setInt(6, dto.getIo_num()); }

		return ps;
	}

	@Override
	protected String modifyQuery() {
		return
				"UPDATE io SET "
				+ "	quantity = ?, "
				+ "	io_type = ?, "
				+ "	storage_sec = ?, "
				+ "	exp_date = ?, "
				+ " mdm_num = ?, "
				+ " where io_num = ? "
			;
	}
	
	@Override
	protected IoDTO setDTO(ResultSet rs) throws SQLException{
		
		IoDTO dto = new IoDTO();

			dto.setIo_num(rs.getInt("io_num"));
			dto.setQuantity(rs.getInt("quantity"));
			dto.setIo_type(rs.getString("io_type"));
			dto.setStorage_sec(rs.getString("storage_sec"));
			dto.setIo_date(rs.getDate("io_date"));
			dto.setExp_date(rs.getDate("exp_date"));
			dto.setMdm_num(rs.getInt("mdm_num"));
			dto.setName(rs.getString("name"));
			dto.setCode(rs.getString("code"));
			dto.setUnit(rs.getString("unit"));
			
		return dto;
	}

	@Override // 怨좎젙 �궗�슜 CONST
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}
	
	// SELECT QUERY 諛쏆븘�꽌 �궗�슜 
		public List selectCustom() {
			
			List list = new ArrayList();
			
			try ( Connection conn = getConn();
				  PreparedStatement ps = new LoggableStatement(conn,
						  "    SELECT   "
				  		+ "    m.mdm_num,  "
				  		+ "    m.name,  "
				  		+ "    m.unit,  "
				  		+ "    SUM(i.quantity) AS total_quantity,  "
				  		+ "    SUM(i.quantity * m.price) AS total_price  "
				  		+ "    FROM mdm m  "
				  		+ "    JOIN io i   "
				  		+ "    ON m.mdm_num = i.mdm_num  "
				  		+ "	   WHERE m.type IN ('assemble', 'material', 'product')  "
				  		+ "    GROUP BY m.mdm_num, m.name, m.unit" ); ) {
				try (  ResultSet rs = ps.executeQuery(); ) { // 
					
					while (rs.next()) {
						
						IoDTO dto = new IoDTO();
						dto.setMdm_num(rs.getInt("mdm_num"));
						dto.setName(rs.getString("name"));
						dto.setUnit(rs.getString("unit"));
						dto.setTotal_quantity(rs.getLong("total_quantity"));
						dto.setTotal_price(rs.getLong("total_price"));
						
						list.add(dto);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("/DAO select list : " + list);
			return list;
		}
		
//		public List selectCustom2() {
//			
//			List list = new ArrayList();
//			
//			try ( Connection conn = getConn();
//					PreparedStatement ps = new LoggableStatement(conn, 
//							"SELECT  "
//							+ "    m.mdm_num, "
//							+ "    m.name, "
//							+ "    SUM(i.quantity) AS total_quantity, "
//							+ "    SUM(i.quantity * m.price) AS total_price "
//							+ "FROM mdm m "
//							+ "JOIN io i  "
//							+ "    ON m.mdm_num = i.mdm_num "
//							+ "WHERE m.type IN ('assemble', 'material', 'product') "
//							+ "GROUP BY m.mdm_num, m.name "
//							); ) {
//				try (  ResultSet rs = ps.executeQuery(); ) { // 
//					
//					while (rs.next()) {
//						
//						IoDTO dto = new IoDTO();
////						dto.setCanUse(rs.getString("canuse"));
//						
//						list.add(dto);
//					}
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("/DAO select listSize : " + list.size());
//			return list;
//		}
		
	
	// join 

	@Override
	protected String selectAllQuery() {
		return 
				"  SELECT mdm_num, name, code, unit "
				+ "FROM mdm "
				+ "WHERE LOWER(type) IN ( 'material', 'assemble' ) ";
	}

	@Override
	protected IoDTO setJoinDTO(ResultSet rs) throws SQLException {
		IoDTO dto = new IoDTO();
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setName(rs.getString("name"));
		dto.setCode(rs.getString("code"));
		dto.setUnit(rs.getString("unit"));
		return dto;
	}



	
}
