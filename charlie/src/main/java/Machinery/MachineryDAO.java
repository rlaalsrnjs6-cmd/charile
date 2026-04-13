package Machinery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO2;

public class MachineryDAO extends ParentDAO2<MachineryDTO, CommonDTO>{

	@Override
	protected String tableName() {
		return "machinery";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "machinery_num";
	}

	@Override
	protected int setDTONum(MachineryDTO dto) {
		return dto.getMachinery_num();
	}

	@Override
	protected String selectQuery(MachineryDTO dto, String selector) {
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
	protected MachineryDTO setDTO(ResultSet rs) {
		MachineryDTO dto = new MachineryDTO();

		try {

			dto.setMachinery_num(rs.getInt("Machinery_num"));
			dto.setMachinery_type(rs.getString("machinery_type"));
			dto.setMachinery_status(rs.getString("machinery_status"));
			dto.setError_sign(rs.getString("error_sign"));
			dto.setMdm_num(rs.getInt("mdm_num"));
			dto.setM_action(rs.getString("m_action"));
			dto.setM_log_time(rs.getDate("m_log_time"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	protected PreparedStatement setPs(PreparedStatement ps, MachineryDTO dto, String selector) {
		try {
			ps.setString(1, dto.getMachinery_type());
			ps.setString(2, dto.getMachinery_status());
			ps.setString(3, dto.getError_sign());
			ps.setString(4, dto.getM_action());
			ps.setInt(5, dto.getMdm_num());
			if ("update".equals(selector)) { ps.setInt(6, dto.getMachinery_num()); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	@Override
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() 
		+ ", machinery_type, machinery_status, error_sign, m_action, mdm_num) " 
				+ " VALUES ( machinery_seq.nextval, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String modifyQuery() {
		return
				"UPDATE " + tableName() + " SET "
				+ " machinery_type = ?, "
				+ "	machinery_status = ?, "
				+ "	error_sign = ?, "
				+ "	m_action = ?, "
				+ "	mdm_num = ? "
				+ " where " + pk_Coulum_Name() + " = ? "
			;
	}

	@Override
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String selectQuery(MachineryDTO dto, CommonDTO commonDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
