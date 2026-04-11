package Mdm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fileLibrary.ParentDAO;

public class MdmDAO extends ParentDAO<MdmDTO> {
	
	@Override
	protected String selectQuery(MdmDTO dto, String selector) {
		
		String query = " select * from ( "
					 + "	 select rownum as rnum, subqry.* from ( "
					 + "	 select " + tableName() + ".* from " + tableName() ;
		String where = "";
		
		if ( dto != null ) {
		
			switch(selector) {
		
				case "all": return query;
				case "num": where = " where " + pk_Coulum_Name() + " = '" + dto.getMdm_num() + "'"; break;
		
				case "search1": where = " where code = "
										+ "'" + dto.getSearch() + "'"
										+ " or name = '" + dto.getSearch() + "'" 
										+ " or unit = '" + dto.getSearch() + "'"
										+ " or type = '" + dto.getSearch() + "'"; break;	
				case "search2" : where = " where code = '" +  dto.getSearch() + "'"; break;
				case "search3" : where = " where name = '" +  dto.getSearch() + "'"; break;
				case "search4" : where = " where unit = '" +  dto.getSearch() + "'"; break;
				case "search5" : where = " where type = '" +  dto.getSearch() + "'"; break;
				default : break;
			}
		}
		// order by 조건 함수로
		query += where;
		query += " order by " + pk_Coulum_Name() + " ) subqry )";
		return query;
		
	}
	


	@Override
	protected String insertQuery() {
		return "INSERT INTO mdm ( mdm_num, code, name, unit, type, price) " 
				+ " VALUES ( mdm_seq.nextval, ?, ?, ?, ?, ?)";
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
	protected String tableName() {
		return "mdm";
	}



	@Override
	protected String pk_Coulum_Name() {
		return "mdm_num";
	}

	@Override
	protected int setDTONum(MdmDTO dto) {
		return dto.getMdm_num();
	}
	
	@Override
	protected String deleteQuery(MdmDTO dto) {
		return "DELETE FROM " + tableName() + " WHERE " + pk_Coulum_Name() + " =  '" + setDTONum(dto) + "'";
	}; 



}
