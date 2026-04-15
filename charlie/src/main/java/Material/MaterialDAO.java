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
	protected int setDTONum(MaterialDTO dto) {
		return dto.getMaterial_num();
	}


	@Override
	protected MaterialDTO setDTO(ResultSet rs) {
		MaterialDTO dto = new MaterialDTO();
		try {
			
			// material
			//dto.setMaterial_num(rs.getInt("material_num"));
			dto.setTotal_quantity(rs.getInt("total_quantity"));
			
			// mdm
			dto.setCode(rs.getString("code"));
			dto.setName(rs.getString("name"));
			dto.setUnit(rs.getString("unit"));
			dto.setTotal_price(rs.getInt("total_price_sum")); // Alias와 매칭

			// warehouse
			//dto.setWarehouse_num(rs.getInt("warehouse_num"));
			dto.setWh_status_chk(rs.getString("wh_status_chk"));
			dto.setTemperature(rs.getInt("temperature"));
			dto.setHumidity(rs.getInt("humidity"));
			dto.setWh_section(rs.getString("wh_section"));
			dto.setFloor_level(rs.getString("floor_level"));
			
		} catch (SQLException e) {
			System.out.println("MaterialDAO setDTO 에러: " + e.getMessage());
		}
		return dto;
	}


	@Override
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}

	@Override
	protected String selectQuery(MaterialDTO dto, CommonDTO commonDTO) {
		// 고정
		String query =
				  "   SELECT * from (   "
				+ "   SELECT rownum as rnum, subqry.* from (   "
				+ "SELECT   "
				+ "	tableA.code,   "
				+ "	tableA.name,   "
				+ "    SUM(tableA.quantity) AS total_quantity,  "
				+ "    tableA.unit,  "
				+ "    SUM(tableA.price * tableA.quantity) AS total_price_sum,  "
				+ "    tableC.wh_status_chk,  "
				+ "    tableC.temperature,  "
				+ "    tableC.humidity,  "
				+ "    tableC.wh_section,  "
				+ "   	tableC.floor_level  "
				+ "FROM mdm tableA  "
				+ "LEFT OUTER JOIN material tableB  "
				+ "ON (tableA.mdm_num = tableB.mdm_num)  "
				+ "LEFT OUTER JOIN warehouse tableC  "
				+ "ON (tableB.warehouse_num = tableC.warehouse_num)  "
				+ "WHERE UPPER(tableA.canuse) = 'Y'  "
				+ "GROUP BY tableA.code,   "
				+ "		 tableA.name,   "
				+ "		 tableA.unit,   "
				+ "		 tableC.wh_status_chk,   "
				+ "		 tableC.temperature,   "
				+ "		 tableC.humidity,  "
				+ " 		 tableC.wh_section,   "
				+ " 		 tableC.floor_level  "
				+ " 		ORDER BY tableA.code ASC  "
				+ " 	) subqry   "
				+ " ) ";
	    query += " WHERE rnum >= ? AND rnum <= ?";
	    return query;
	}
	@Override
	protected String selectAllQuery() {
	    return "SELECT mdm_num, code, name FROM mdm " +
	           "WHERE type IN ('assemble', 'product', 'material') AND canuse = 'Y'";
	}

	@Override
	protected MaterialDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		MaterialDTO dto = new MaterialDTO();
		
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setCode(rs.getString("code"));
		dto.setName(rs.getString("name"));
		
		return dto;
	}
		@Override
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() 
			 + ", total_quantity, warehouse_num, mdm_num) " 
			 + " VALUES ( material_seq.nextval, ?, ?, ?)";
	}

	@Override
	protected String modifyQuery() {
		return "UPDATE " + tableName() + " SET "
			 + " total_quantity = ?, "
			 + " warehouse_num = ?, "
			 + " mdm_num = ? "
			 + " WHERE " + pk_Coulum_Name() + " = ?";
	}

	protected PreparedStatement setPs(PreparedStatement ps, MaterialDTO dto, String selector) throws SQLException {
	    try {
	        ps.setInt(1, dto.getTotal_quantity());
	        ps.setInt(2, dto.getWarehouse_num());
	        ps.setInt(3, dto.getMdm_num());
	        if ("update".equals(selector)) {
	            ps.setInt(4, dto.getMaterial_num());
	        }
	    } catch (SQLException e) {
	        System.out.println("MaterialDAO setPs 에러 발생: " + e.getMessage());
	        throw e; 
	    }
	    return ps;
	}
}
