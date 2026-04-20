package Machinery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Process.ProcessDTO;
import fileLibrary.CommonDTO;
import fileLibrary.LoggableStatement;
import fileLibrary.ParentDAO4;

public class MachineryDAO extends ParentDAO4<MachineryDTO, CommonDTO>{

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
				dto.setMachinery_num(rs.getInt("machinery_num"));
				dto.setMachinery_type(rs.getString("machinery_type"));
				dto.setMachinery_status(rs.getString("machinery_status"));
				dto.setError_sign(rs.getString("error_sign"));
				dto.setMdm_num(rs.getInt("mdm_num"));
				dto.setName(rs.getString("name"));
				dto.setCode(rs.getString("code"));
				dto.setM_action(rs.getString("m_action"));
				dto.setM_log_time(rs.getDate("m_log_time"));

			} catch (SQLException e) {
				System.out.println("SET DTO ERROR 발생");
				e.printStackTrace();
			}
			return dto;
		}

	protected String innerQuery(MachineryDTO dto, CommonDTO commonDTO) {
		String query =
		           // join data
                 " SELECT tableA.*, tableB.name, tableB.code"
                + " from machinery tableA "
                // join on
                + " JOIN mdm tableB ON tableA.mdm_num = tableB.mdm_num ";
   
				// 고정
				String where = commonDTO.getWhere();
				if(("".equals(commonDTO.getWhere()))) where = "WHERE LOWER(TRIM(tableB.type)) = 'equip'";  

				

				String groupBy = "";
   
				String where2 = commonDTO.getSearch();
				if (where2 == null || "".equals(where2)) {
					where2 = "";
				}
   
				// 추가 조건 붙일 때
				query += where 
						+  where2
						+  groupBy;
				
   		return query;
	}
	
	// SELECT MAIN QUERY FOR LIST 
	@Override // CHECKED
	protected String selectQuery(MachineryDTO dto, CommonDTO commonDTO) {

		String orderBy = commonDTO.getOrderBy();
		if(("".equals(commonDTO.getOrderBy()))) orderBy = pk_Coulum_Name();  
		orderBy = " tableA.machinery_num DESC ";
		
		// 고정
	    String query = " SELECT * from ( "
	                 + " SELECT rownum as rnum, subqry.* from ( "
	                 // rownum 용 subquery 껍데기 고정
	                 + innerQuery(dto, commonDTO)
	                 +" ORDER BY " + orderBy + " ) subqry )"
	                 +" WHERE rnum >= ? AND rnum <= ?";
	  
	    return query;
	}
	
	
	
	
	// SELECT QUERY 받아서 사용 
			public List selectCustom() {
				
				List list = new ArrayList();
				
				try ( Connection conn = getConn();
					  PreparedStatement ps = new LoggableStatement(conn, 
							  "SELECT DISTINCT name FROM mdm WHERE type = 'equip'"); ) {
					try (  ResultSet rs = ps.executeQuery(); ) { // 
						
						while (rs.next()) {
							
							MachineryDTO dto = new MachineryDTO();
							dto.setName(rs.getString("name"));
							
							list.add(dto);
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("/DAO select list : " + list);
				return list;
			}
	
	
	
	
	// JOIN SELECT BEFORE INSERT
	@Override // CHECKED
	protected String selectAllQuery() {
		return "SELECT mdm_num, name FROM mdm WHERE type = 'equip'";
	}

	@Override // CHECKED
	protected MachineryDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		MachineryDTO dto = new MachineryDTO();
		
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setName(rs.getString("name"));
		
		return dto;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// PAGING IN SELECTQUERY
	@Override // CHECKED
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}
	
	
	// DB link
		private Connection getConn() {
			Connection conn = null;
			try {
				Context ctx = new InitialContext();
				DataSource dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/charlie");
				conn = dataFactory.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public int getTotalCount(MachineryDTO dto, CommonDTO commonDTO) {
			
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
