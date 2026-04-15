package Bom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bom.BomDTO;
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
			if ("update".equals(selector)) { ps.setInt(3, dto.getBom_num()); }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	@Override
	protected BomDTO setDTO(ResultSet rs) {
		BomDTO dto = new BomDTO();
		
		try {
			
			dto.setBom_num(rs.getInt("bom_num"));
			dto.setRequired_weight(rs.getInt("required_weight"));
			dto.setMdm_num(rs.getInt("mdm_num"));
			
			try {	
				dto.setName(rs.getString("name"));
				dto.setCode(rs.getString("code"));
				dto.setUnit(rs.getString("unit"));
				} catch (SQLException e) {
				e.printStackTrace();
					System.out.println("join 정보 없음!");
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	// SELECT MAIN QUERY FOR LIST 
		@Override // NEEDCHECKED
		protected String selectQuery(BomDTO dto, CommonDTO commonDTO) {

			String query = // 고정 사용
						"SELECT * FROM ( "
	                + "  SELECT rownum AS rnum, subqry.* FROM ( "
					+ "" // MAIN TABLE A	
	                + "    SELECT tableA.*, tableB.code, tableB.name, tableB.unit  "
	                + " FROM bom tableA "
	                + "" // JOIN TABLE B
	                + " LEFT OUTER JOIN mdm tableB "
	                + " ON tableA.mdm_num = tableB.mdm_num ";
		    			
		    
		    // 고정
		    String where = commonDTO.getWhere();
		    if(("".equals(commonDTO.getWhere()))) where = "where 1 = 1";  

		    String orderBy = commonDTO.getOrderBy();
		    if(("".equals(commonDTO.getOrderBy()))) orderBy = pk_Coulum_Name();  
		    		
		 // 가변 조건
			if ( dto != null ) {
			
				if(commonDTO.getSearch() != "") {
					switch(commonDTO.getSelector()) {
					
					// 전체검색
					case "search_all": where = " where tableB.code = " 
											+ "'" + commonDTO.getSearch() + "'"
											+ " or tableB.name = '" + commonDTO.getSearch() + "'"; break;	
					/* 컬럼별 검색 */			 
					case "code" : where = " where tableB.code = '" +  commonDTO.getSearch() + "'"; break;
					case "name" : where = " where tableB.name = '" +  commonDTO.getSearch() + "'"; break;
					default : break;
					}
				}
			}

		    String groupBy = "";
		    // 추가 조건 붙일 때
		    query += where 
		    	  + groupBy
		    	  + " ORDER BY " + orderBy + " ) subqry )"
		    	  + " WHERE rnum >= ? AND rnum <= ?";
		    return query;
		}



	@Override
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() + ", Required_weight, Mdm_num) " 
				+ " VALUES ( bom_seq.nextval, ?, ?)";
	}

	@Override
	protected String modifyQuery() {
		return
				"UPDATE " + tableName() + " SET "
				+ " Required_weight = ?, "
				+ "	Mdm_num = ? "
				
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
		return "SELECT mdm_num, code, unit, name FROM mdm " +
		           "WHERE type IN ('assemble', 'product', 'material') AND canuse = 'Y'";
	}

	@Override // CHECKED
	protected BomDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		BomDTO dto = new BomDTO();
		
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setName(rs.getString("name"));
		dto.setCode(rs.getString("code"));
		dto.setUnit(rs.getString("unit"));
		
		return dto;
	}




}
