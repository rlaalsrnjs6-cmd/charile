package Bom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Mdm.MdmDAO;
import Mdm.MdmDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO3;

public class BomDAO extends ParentDAO3<BomDTO, CommonDTO>{

	@Override
	protected String tableName() {
		return "Bom";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "Bom_num";
	}

	@Override
	protected int setDTONum(BomDTO dto) {
		return dto.getBom_num();
	}

	@Override
	protected PreparedStatement setPs(PreparedStatement ps, BomDTO dto, String selector) {
		
		try {
			ps.setInt(1, dto.getRequired_weight());
			ps.setInt(2, dto.getMdm_num());
			ps.setInt(3, dto.getTarget_mdm_num());
			if ("update".equals(selector)) { ps.setInt(4, dto.getBom_num()); }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	@Override
	protected BomDTO setDTO(ResultSet rs) {
		BomDTO dto = new BomDTO();
		// DTO > SET number 
		try {
			
			// tableA
			dto.setBom_num(rs.getInt("bom_num"));
			dto.setRequired_weight(rs.getInt("required_weight"));
			dto.setMdm_num(rs.getInt("mdm_num"));
			
			// tableB
			dto.setName(rs.getString("name"));
			dto.setCode(rs.getString("code"));
			dto.setUnit(rs.getString("unit"));
			dto.setType(rs.getString("type"));
			
			// tableC
			dto.setTarget_mdm_num(rs.getInt("target_mdm_num"));
			dto.setTarget_name(rs.getString("target_name"));
			dto.setTarget_code(rs.getString("target_code"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
	String innerQuery(BomDTO dto, CommonDTO commonDTO) {
		String query =
                  " SELECT tableA.*, tableB.code, tableB.name, tableB.unit, tableB.type, tableC.name AS target_name, tableC.code AS target_code "
                + " FROM bom tableA "
                + "" // JOIN TABLE B
                + " LEFT OUTER JOIN mdm tableB "
                + " ON tableA.mdm_num = tableB.mdm_num "
                + " LEFT JOIN mdm tableC "
                + " ON tableA.target_mdm_num = tableC.mdm_num ";
	    			
	    // 고정
	    String where = commonDTO.getWhere();
	    if(("".equals(where))) where = "where 1 = 1";  

	
	    String where2 = commonDTO.getSearch();
	    if (where2 == null || "".equals(where2)) {
	        where2 = "";
	    }
	  
	    String groupBy = "";
	    // 추가 조건 붙일 때
	    query += where 
	    	  +  where2
	    	  + groupBy;
	    return query;
	}
	// SELECT MAIN QUERY FOR LIST 
		@Override // NEEDCHECKED
		protected String selectQuery(BomDTO dto, CommonDTO commonDTO) {
			
		    String orderBy = commonDTO.getOrderBy();
		    if(("".equals(commonDTO.getOrderBy()))) orderBy = pk_Coulum_Name();
		    
		 
			String query = // 고정 사용
						"SELECT * FROM ( "
	                + "  SELECT rownum AS rnum, subqry.* FROM ( "
	                + innerQuery(dto, commonDTO)
		    	 	+ " ORDER BY " + orderBy + " ) subqry )"
		    	 	+ " WHERE rnum >= ? AND rnum <= ?";
		    
		    return query;
		}



	@Override
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() + ", Required_weight, Mdm_num, target_mdm_num) " 
				+ " VALUES ( bom_seq.nextval, ?, ?, ?)";
	}

	@Override
	protected String modifyQuery() {
		return
				"UPDATE " + tableName() + " SET "
				+ " Required_weight = ?, "
				+ "	mdm_num = ?, "
				+ "	target_mdm_num = ? "
				+ " where " + pk_Coulum_Name() + " = ? "
			;
	}

	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}


	@Override
	protected String selectAllQuery() {
		return "SELECT mdm_num, code, name, unit, type FROM mdm "
				+ "WHERE type in ('product', 'material', 'assemble') AND canuse = 'Y'";
	}

	@Override // CHECKED
	protected BomDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		BomDTO dto = new BomDTO();
		
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setName(rs.getString("name"));
		dto.setCode(rs.getString("code"));
		dto.setUnit(rs.getString("unit"));
		dto.setType(rs.getString("type"));
		
		return dto;
	}

	// Use paging 
	public int getTotalCount(BomDTO dto, CommonDTO commonDTO) {
		
		int total = 0;
		
		try {
			//자원을 가지러 가기 위해 문을 열고
			Context ctx = new InitialContext();
			//열어둔 문을 통해 어디로 갈지 경로를 정함
	        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
	        
	        String query = "SELECT COUNT(*) FROM ( "
	                 + innerQuery(dto, commonDTO)
	                 + " )";
	        
	        try(Connection conn = dataFactory.getConnection();
	        	PreparedStatement ps = conn.prepareStatement(query);	
	        		ResultSet rs = ps.executeQuery()){
	        	
	        	if(rs.next()) { // count 1줄 return
	        		total = rs.getInt(1);
	        	}
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}


}
