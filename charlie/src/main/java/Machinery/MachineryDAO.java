package Machinery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO2;

public class MachineryDAO extends ParentDAO2<MachineryDTO, CommonDTO>{

	// TABLE
	@Override // CHECKED
	protected String tableName() {
		return "machinery";
	}

	// PK COLUMN NAME
	@Override // CHECKED
	protected String pk_Coulum_Name() {
		return "machinery_num";
	}
 
	// PK NUM FOR SELECTONE
	@Override // CHECKED
	protected int setDTONum(MachineryDTO dto) {
		return dto.getMachinery_num();
	}

	
	
	// MODIFY QUERY
	@Override // CHECKED
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

	// INSERT QUERY
	@Override // CHECKED
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() 
		+ ", machinery_type, machinery_status, error_sign, m_action, mdm_num) " 
		+ " VALUES ( machinery_seq.nextval, ?, ?, ?, ?, ?)";
	}
	
	// INSERT & UPDATE
	@Override // CHECKED
	protected PreparedStatement setPs(PreparedStatement ps, MachineryDTO dto, String selector) {
		try {
			ps.setString(1, dto.getMachinery_type());
			ps.setString(2, dto.getMachinery_status());
			ps.setString(3, dto.getError_sign());
			ps.setString(4, dto.getM_action());
			ps.setInt(5, dto.getMdm_num());
			//ps.setString(6, dto.getName());
			if ("update".equals(selector)) { ps.setInt(6, dto.getMachinery_num()); }
		} catch (SQLException e) {
			System.out.println("SET PS ERROR 발생");
			e.printStackTrace();
		}
		return ps;
	}

	

	// SELECT DTO SET
	@Override // CHECKED
	protected MachineryDTO setDTO(ResultSet rs) {
		
		MachineryDTO dto = new MachineryDTO();

			try {
				 System.out.println("-DEBUGING-");
				 
				dto.setMachinery_num(rs.getInt("Machinery_num"));
				dto.setMachinery_type(rs.getString("machinery_type"));
				dto.setMachinery_status(rs.getString("machinery_status"));
				dto.setError_sign(rs.getString("error_sign"));
				dto.setMdm_num(rs.getInt("mdm_num"));
				dto.setName(rs.getString("MDM_NAME"));
				dto.setM_action(rs.getString("m_action"));
				dto.setM_log_time(rs.getDate("m_log_time"));

			} catch (SQLException e) {
				System.out.println("SET DTO ERROR 발생");
				e.printStackTrace();
			}
			return dto;
		}

	// SELECT MAIN QUERY FOR LIST 
	@Override // CHECKED
	protected String selectQuery(MachineryDTO dto, CommonDTO commonDTO) {

		// 고정
	    String query = " select * from ( "
	                 + " select rownum as rnum, subqry.* from ( "
	                 // rownum 용 subquery 껍데기 고정
	                 
	                 
	                 // join data
	                 + " select tableA.*, tableB.name as MDM_NAME "
	                 + " from machinery tableA "
	                 // join on
	                 + " join mdm tableB on tableA.mdm_num = tableB.mdm_num "
	                 
	                 + "WHERE LOWER(TRIM(tableB.type)) = 'equip'";
	    			
	    
	    // 고정
	    String where = "";

//	    String orderBy = "tableA." + pk_Coulum_Name();
//	    if (commonDTO.getOrderBy() != null) {
//	    }
	    String orderBy = " tableA.machinery_num DESC ";

	    // 추가 조건 붙일 때
	    query += where;

	    query += " order by " + orderBy + " ) subqry )";
	    query += " WHERE rnum >= ? AND rnum <= ?";
	    return query;
	}
	
	
	
	// JOIN SELECT BEFORE INSERT
	@Override // CHECKED
	protected String selectAllQuery() {
		return "SELECT mdm_num, name as MDM_NAME FROM mdm WHERE type = 'equip'";
	}

	@Override // CHECKED
	protected MachineryDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		MachineryDTO dto = new MachineryDTO();
		
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setName(rs.getString("MDM_NAME"));
		
		return dto;
	}
	
	
	// PAGING IN SELECTQUERY
	@Override // CHECKED
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}

}
