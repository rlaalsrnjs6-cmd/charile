package Material;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fileLibrary.ParentDAO;

public class MaterialDAO extends ParentDAO<MaterialDTO> {

	@Override
	protected String tableName() {
		return "material";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "material_num";
	}

	@Override
	protected int setDTONum(MaterialDTO dto) {
		return dto.getMaterial_num();
	}

	@Override
	protected String deleteQuery(MaterialDTO dto) {
		return "DELETE FROM " + tableName() + " WHERE " + pk_Coulum_Name() + " =  '" + setDTONum(dto) + "'";
	}

	@Override
	protected String selectQuery(MaterialDTO dto, String selector) {
		String query = " select * from " + tableName();

		if ("".equals(selector) || selector == null || dto == null)
			return query;

		switch (selector) {

		case "num":
			query += " where " + pk_Coulum_Name() + " = '" + setDTONum(dto) + "'";
			return query;

		default:
			break;
		}

		query += " order by " + pk_Coulum_Name();
		return query;
	}

	@Override
	protected MaterialDTO setDTO(ResultSet rs) {
		MaterialDTO dto = new MaterialDTO();

		try {

			dto.setMaterial_num(rs.getInt("Material_num"));
			dto.setTotal_quantity(rs.getInt("total_quantity"));
			dto.setWarehouse_num(rs.getInt("warehouse_num"));
			dto.setMdm_num(rs.getInt("mdm_num"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	protected PreparedStatement setPs(PreparedStatement ps, MaterialDTO dto, String selector) {
		try {
			ps.setInt(1, dto.getTotal_quantity());
			ps.setInt(2, dto.getWarehouse_num());
			ps.setInt(3, dto.getMdm_num());
			if ("update".equals(selector)) { ps.setInt(4, dto.getMaterial_num()); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	@Override
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() 
		+ ", total_quantity, warehouse_num, Mdm_num) " 
				+ " VALUES ( material_seq.nextval, ?, ?, ?)";
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

}
