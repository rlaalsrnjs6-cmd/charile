package Bom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Mdm.MdmDTO;
import fileLibrary.ParentDAO;

public class BomDAO extends ParentDAO<BomDTO>{

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
	protected String deleteQuery(BomDTO dto) {
		return "DELETE FROM " + tableName() + " WHERE " + pk_Coulum_Name() + " =  '" + setDTONum(dto) + "'";
	}

	@Override
	protected String selectQuery(BomDTO dto, String selector) {
		String query = " select * from " + tableName();
		
		if ( "".equals(selector) || selector == null || dto == null ) return query;
		
		switch(selector) {
		
		case "num": query += " where " + pk_Coulum_Name() + " = '" + setDTONum(dto) + "'"; return query;
		
		default : break;
		}
		
		query += " order by " + pk_Coulum_Name();
		return query;
	}

	@Override
	protected BomDTO setDTO(ResultSet rs) {
		BomDTO dto = new BomDTO();
		
		try {
			
			dto.setBom_num(rs.getInt("bom_num"));
			dto.setRequired_weight(rs.getInt("required_weight"));
			dto.setMdm_num(rs.getInt("mdm_num"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
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



}
