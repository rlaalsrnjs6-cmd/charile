package Process;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Mdm.MdmDTO;
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
	
	String innerQuery(ProcessDTO dto, CommonDTO commonDTO) {
		String query = 
                  " SELECT tableA.*, tableB.code, tableB.name "
                + " FROM process tableA "
                + "" // JOIN TABLE B
                + " LEFT OUTER JOIN mdm tableB "
                + " ON tableA.mdm_num = tableB.mdm_num ";
	    			
	    
	    // 고정
	    String where = commonDTO.getWhere();
	    if(("".equals(commonDTO.getWhere()))) where = "WHERE 1 = 1";  

	    String where2 = commonDTO.getSearch();
	    if (where2 == null || "".equals(where2)) {
	        where2 = "";
	    }

	    String groupBy = "";
	    // 추가 조건 붙일 때
	    query += where 
	    	  +  where2
	    	  + groupBy;
	    
		return query;
	}
	// SELECT MAIN QUERY FOR LIST 
	@Override // CHECKED
	protected String selectQuery(ProcessDTO dto, CommonDTO commonDTO) {

		  String orderBy = commonDTO.getOrderBy();
		    if(("".equals(commonDTO.getOrderBy()))) orderBy = pk_Coulum_Name();  
		    		
		    
		String query = // 고정 사용
					"SELECT * FROM ( "
                + "  SELECT rownum AS rnum, subqry.* FROM ( "
				+ 	 innerQuery(dto, commonDTO)
				+ "  ORDER BY " + orderBy + " ) subqry )"
				+ "  WHERE rnum >= ? AND rnum <= ?";
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

	public int getTotalCount(ProcessDTO dto, CommonDTO commonDTO) {
		
		int total = 0;
		
		try {
			//자원을 가지러 가기 위해 문을 열고
			Context ctx = new InitialContext();
			//열어둔 문을 통해 어디로 갈지 경로를 정함
	        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
	        
	        String query = "SELECT COUNT(*) FROM ( "
	                 + innerQuery(dto, commonDTO)
	                 + " )";
	        
	        try(Connection conn = dataFactory.getConnection();
	        	PreparedStatement ps = conn.prepareStatement(query);	
	        		ResultSet rs = ps.executeQuery()){
	        	
	        	if(rs.next()) { // count 1줄 return
	        		total = rs.getInt(1);
	        	}
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

}
