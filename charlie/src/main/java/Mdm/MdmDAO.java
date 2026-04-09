package Mdm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fileLibrary.ParentDAO;

public class MdmDAO extends ParentDAO<MdmDTO> {

	@Override
	protected MdmDTO setDTO(ResultSet rs) {
		
		MdmDTO dto = new MdmDTO();
		
		try {
			
			dto.setMdm_num(rs.getInt("mdm_num"));
			dto.setCode(rs.getString("code"));
			dto.setName(rs.getString("name"));
			dto.setUnit(rs.getString("unit"));
			dto.setType(rs.getString("type"));
			dto.setPrice(rs.getInt("price"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@Override
	protected String selectQuery(String selector, MdmDTO dto) {
		
		String query = "select * from mdm ";
		
		if ( "".equals(selector) || selector == null || dto == null ) return query;
		
			switch(selector) {
			
		case "all": return query;
		case "num": query += " where mdm_num = '" + dto.getMdm_num() + "'"; return query;
		
		case "search1": query += " where code = '" +  dto.getSearch() + "' or name = '" + dto.getSearch() 
								+ "' or unit = '" + dto.getSearch() + "' or type = '" + dto.getSearch() + "'"; return query;	
		case "search2" : query += " where code = '" +  dto.getSearch() + "'"; return query;
		case "search3" : query += " where name = '" +  dto.getSearch() + "'"; return query;
		case "search4" : query += " where unit = '" +  dto.getSearch() + "'"; return query;
		case "search5" : query += " where type = '" +  dto.getSearch() + "'"; return query;
		default : break ;
		}
			
	return query;
		
	}

	@Override
	protected String insertQuery() {
	
		String query = "INSERT INTO mdm ( mdm_num, code, name, unit, type, price) " 
				+ " VALUES ( mdm_seq.nextval, ?, ?, ?, ?, ?)";
		
		return query;
	}

	@Override
	protected PreparedStatement setPs(PreparedStatement ps, MdmDTO dto, String selector) {

		try {
			ps.setString(1, dto.getCode());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getUnit());
			ps.setString(4, dto.getType());
			ps.setInt(5, dto.getPrice());
			if ("update".equals(selector)) { ps.setInt(6, dto.getMdm_num()); }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	@Override
	protected String modifyQuery() {
		return
				"UPDATE mdm SET "
				+ "	code = ?, "
				+ "	name = ?, "
				+ "	unit = ?, "
				+ "	type = ?, "
				+ " price = ? "
				+ " where mdm_num = ? "
			;
	}

	@Override
	protected String deleteQuery(MdmDTO dto) {
		return "DELETE FROM mdm WHERE mdm_num = " + dto.getMdm_num();
	}




}
