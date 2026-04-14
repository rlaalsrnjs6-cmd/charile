package Material;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Material.MaterialDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO2;

public class MaterialDAO extends ParentDAO2<MaterialDTO, CommonDTO> {

	@Override
	protected String tableName() {
		return "material";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "material_num";
	}


	@Override
	protected MaterialDTO setDTO(ResultSet rs) {
		
		MaterialDTO dto = new MaterialDTO();

		try {
			 System.out.println("-DEBUGING-");

			dto.setMaterial_num(rs.getInt("material_num"));
			dto.setTotal_quantity(rs.getInt("total_quantity"));
			dto.setWarehouse_num(rs.getInt("warehouse_num"));
			dto.setMdm_num(rs.getInt("mdm_num"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	
	}


	@Override
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() 
		+ ", total_quantity, warehouse_num, code, name, quantity, unit) " 
				+ " VALUES ( material_seq.nextval, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String modifyQuery() {
		return
				"UPDATE " + tableName() + " SET "
				+ "	total_quantity = ?, "
				+ "	warehouse_num = ?, "
				+ "	mdm_num = ? "
				+ " where " + pk_Coulum_Name() + " = ? "
			;
	}

	@Override
	protected int setDTONum(MaterialDTO dto) {
		return dto.getMaterial_num();
	}

	@Override
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}

	@Override
	protected String selectQuery(MaterialDTO dto, CommonDTO commonDTO) {
		return null;
	}
	@Override
	protected String selectAllQuery() {
	    return "SELECT mdm_num, code, name, quantity, unit FROM mdm " +
	           "WHERE type IN ('assemble', 'product', 'material') AND canuse = 'Y'";
	}

	@Override
	protected MaterialDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		MaterialDTO dto = new MaterialDTO();
		
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setCode(rs.getString("code"));
		dto.setName(rs.getString("name"));
		dto.setQuantity(rs.getInt("quantity"));
		dto.setUnit(rs.getString("unit"));
		
		return dto;
	}
	

	@Override
	protected PreparedStatement setPs(PreparedStatement ps, MaterialDTO dto, String selector) throws SQLException {
		try {
			ps.setInt(1, dto.getTotal_quantity());
			ps.setInt(2, dto.getWarehouse_num());
			ps.setString(3, dto.getCode());
			ps.setString(4, dto.getName());
			ps.setInt(5, dto.getQuantity());
			ps.setString(6, dto.getUnit());
			if ("update".equals(selector)) { ps.setInt(7, dto.getMaterial_num()); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
}
