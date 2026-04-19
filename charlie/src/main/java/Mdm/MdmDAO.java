package Mdm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Mdm.MdmDTO;
import fileLibrary.CommonDTO;
import fileLibrary.LoggableStatement;
import fileLibrary.ParentDAO2;

public class MdmDAO extends ParentDAO2<MdmDTO, CommonDTO> {
	
	// set Table 정보 
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
	protected String selectQuery(MdmDTO dto, CommonDTO commonDTO) {
		
		// SET QUERY
		String query = " select * from ( "
					 + "	 select rownum as rnum, subqry.* from ( "
					 + "	 select " + tableName() + ".* from " + tableName() ;
		
		// SET WHERE
		String where = commonDTO.getWhere();
	    if(("".equals(commonDTO.getWhere()))) where = " WHERE 1 = 1 ";  
		// SET ORDERBY
		String orderBy = pk_Coulum_Name();
		if ( commonDTO.getOrderBy() != null ) orderBy = commonDTO.getOrderBy();
		
		// 가변 조건
		if(commonDTO.getSearch() != null
				&& !"".equals(commonDTO.getSearch())) {
			
			switch(commonDTO.getSelector()) {
				
			// 전체검색
			case "search_all": where = " WHERE code LIKE " 
										+ "'%" + commonDTO.getSearch() + "%'"
										+ " or name LIKE '%" + commonDTO.getSearch() + "%'" 
										+ " or unit LIKE '%" + commonDTO.getSearch() + "%'"
			/* 컬럼별 검색 */			    + " or type LIKE '%" + commonDTO.getSearch() + "%'"; break;	
			case "code" : where = " where code LIKE '%" +  commonDTO.getSearch() + "%'"; break;
			case "name" : where = " where name LIKE '%" +  commonDTO.getSearch() + "%'"; break;
			case "unit" : where = " where unit LIKE '%" +  commonDTO.getSearch() + "%'"; break;
			case "type" : where = " where type LIKE '%" +  commonDTO.getSearch() + "%'"; break;
			default : break;
			}
		}
		
		String where2 = commonDTO.getWhere2();
	    if (where2 == null || "".equals(where2)) {
	        where2 = "";
	    }
	    String where3 = commonDTO.getWhere3();
	    if (where3 == null || "".equals(where3)) {
	    	where3 = "";
	    }
		// 추가 내용
		query += where
			  +  where2
		      +  where3
		      +" ORDER BY "+ orderBy +" ) subqry )"
		 	  +" WHERE rnum >= ? AND rnum <= ?" ;
		return query;
		
	}
	

	@Override
	protected String insertQuery() {
		return "INSERT INTO mdm ( mdm_num, code, name, unit, type, quantity, price, canuse) " 
				+ " VALUES ( mdm_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected PreparedStatement setPs(PreparedStatement ps, MdmDTO dto, String selector) throws SQLException {

			ps.setString(1, dto.getCode());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getUnit());
			ps.setString(4, dto.getType());
			ps.setInt(5, dto.getQuantity());
			ps.setInt(6, dto.getPrice());
			ps.setString(7, dto.getCanUse());
			if ("update".equals(selector)) { ps.setInt(8, dto.getMdm_num()); }

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
				+ "	quantity = ?, "
				+ " price = ?, "
				+ " canuse = ? "
				+ " where mdm_num = ? "
			;
	}
	
	@Override
	protected MdmDTO setDTO(ResultSet rs) throws SQLException{
		
		MdmDTO dto = new MdmDTO();

			dto.setMdm_num(rs.getInt("mdm_num"));
			dto.setCode(rs.getString("code"));
			dto.setName(rs.getString("name"));
			dto.setQuantity(rs.getInt("quantity"));
			dto.setUnit(rs.getString("unit"));
			dto.setType(rs.getString("type"));
			dto.setPrice(rs.getInt("price"));
			dto.setCanUse(rs.getString("canuse"));
			
			System.out.println(dto.getExp_date());
		return dto;
	}

	@Override // 고정 사용 CONST
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}
	
	// SELECT QUERY 받아서 사용 
		public List selectCustom() {
			
			List list = new ArrayList();
			
			try ( Connection conn = getConn();
				  PreparedStatement ps = new LoggableStatement(conn, 
						  "SELECT DISTINCT type FROM mdm "); ) {
				try (  ResultSet rs = ps.executeQuery(); ) { // 
					
					while (rs.next()) {
						
						MdmDTO dto = new MdmDTO();
						dto.setType(rs.getString("type"));
						
						list.add(dto);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("/DAO select list : " + list);
			return list;
		}
		
		public List selectCustom2() {
			
			List list = new ArrayList();
			
			try ( Connection conn = getConn();
					PreparedStatement ps = new LoggableStatement(conn, 
							"SELECT DISTINCT canuse FROM mdm "); ) {
				try (  ResultSet rs = ps.executeQuery(); ) { // 
					
					while (rs.next()) {
						
						MdmDTO dto = new MdmDTO();
						dto.setCanUse(rs.getString("canuse"));
						
						list.add(dto);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("/DAO select listSize : " + list.size());
			return list;
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
	
	// join X

	@Override
	protected String selectAllQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MdmDTO setJoinDTO(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// Use paging 
		public int getTotalCount() {
			
			int total = 0;
			
			try {
				//자원을 가지러 가기 위해 문을 열고
				Context ctx = new InitialContext();
				//열어둔 문을 통해 어디로 갈지 경로를 정함
		        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		        
		        String query ="select count(*) from " + tableName(); 
		        
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
