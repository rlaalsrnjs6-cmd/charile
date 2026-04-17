package Process;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO3;

public class ProcessDAO extends ParentDAO3<ProcessDTO, CommonDTO>{

	// TABLE
	@Override // CHECKED
	protected String tableName() {
		return "process";
	}

	// PK COLUMN NAME
	@Override // CHECKED
	protected String pk_Coulum_Name() {
		return "process_num";
	}
 
	// PK NUM FOR SELECTONE
	@Override // CHECKED
	protected int setDTONum(ProcessDTO dto) {
		return dto.getProcess_num();
	}

	
	
	// MODIFY QUERY
	@Override // CHECKED
	protected String modifyQuery() {
		return
				"UPDATE " + tableName() + " SET "
				+ " process_content = ?, "
				+ "	flow = ?, "
				+ "	img_url = ?, "
				+ "	mdm_num = ? "
				+ " where " + pk_Coulum_Name() + " = ? "
			;
	}

	// INSERT QUERY
	@Override // CHECKED
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() 
		+ ", process_content, flow, img_url, mdm_num ) " 
		+ " VALUES ( process_seq.nextval, ?, ?, ?, ?)";
	}
	
	// INSERT & UPDATE
	@Override // NEEDCHECK
	protected PreparedStatement setPs(PreparedStatement ps, ProcessDTO dto, String selector) {
		try {
			ps.setString(1, dto.getProcess_content());
			ps.setInt(2, dto.getFlow());
			ps.setString(3, dto.getImg_url());
			ps.setInt(4, dto.getMdm_num());
			if ("update".equals(selector)) { ps.setInt(5, dto.getProcess_num()); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	// SELECT DTO SET
	@Override
	protected ProcessDTO setDTO(ResultSet rs) throws SQLException {
		
		ProcessDTO dto = new ProcessDTO();

			dto.setProcess_num(rs.getInt("Process_num"));
			dto.setProcess_content(rs.getString("process_content"));
			dto.setFlow(rs.getInt("flow"));
			dto.setImg_url(rs.getString("img_url"));
			dto.setMdm_num(rs.getInt("mdm_num"));
			
			try {	
			dto.setName(rs.getString("name"));
			dto.setCode(rs.getString("code"));
			} catch (SQLException e) {
			e.printStackTrace();
				System.out.println("name 없음!");
			}
			
		return dto;
	}
	
	
	// SELECT MAIN QUERY FOR LIST 
	@Override // CHECKED
	protected String selectQuery(ProcessDTO dto, CommonDTO commonDTO) {

		String query = // 고정 사용
					"SELECT * FROM ( "
                + "  SELECT rownum AS rnum, subqry.* FROM ( "
				+ "" // MAIN TABLE A	
                + "    SELECT tableA.*, tableB.code, tableB.name  "
                + " FROM process tableA "
                + "" // JOIN TABLE B
                + " LEFT OUTER JOIN mdm tableB "
                + " ON tableA.mdm_num = tableB.mdm_num ";
	    			
	    
	    // 고정
	    String where = commonDTO.getWhere();
	    if(("".equals(commonDTO.getWhere()))) where = "1 = 1";  

	    String orderBy = commonDTO.getOrderBy();
	    if(("".equals(commonDTO.getOrderBy()))) orderBy = pk_Coulum_Name();  
	    		

	    String groupBy = "";
	    // 추가 조건 붙일 때
	    query += "Where " + where 
	    	  + groupBy
	    	  + " ORDER BY " + orderBy + " ) subqry )"
	    	  + " WHERE rnum >= ? AND rnum <= ?";
	    return query;
	}
	
	
	
	// JOIN SELECT BEFORE INSERT
	@Override // CHECKED
	protected String selectAllQuery() {
		return "SELECT mdm_num, code, name FROM mdm " +
		           "WHERE type = 'product' AND canuse = 'Y'";
	}

	@Override // CHECKED
	protected ProcessDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		ProcessDTO dto = new ProcessDTO();
		
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setName(rs.getString("name"));
		dto.setCode(rs.getString("code"));
		
		System.out.println("setJoinDTO 정상 작동");
		System.out.println(dto.getName());
		return dto;
	}
	
	
	// PAGING IN SELECT QUERY
	@Override // CHECKED
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}


}
