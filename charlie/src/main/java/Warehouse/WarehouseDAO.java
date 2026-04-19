package Warehouse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Machinery.MachineryDTO;
import fileLibrary.CommonDTO;
import fileLibrary.LoggableStatement;
import fileLibrary.ParentDAO3;

public class WarehouseDAO extends ParentDAO3<WarehouseDTO, CommonDTO>{

	@Override
	protected String tableName() {
		return "warehouse";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "warehouse_num";
	}

	@Override
	protected int setDTONum(WarehouseDTO dto) {
		return dto.getWarehouse_num();
	}

	@Override
	protected String insertQuery() {
		return "INSERT INTO " + tableName() + " ( " + pk_Coulum_Name() 
			 + ", wh_section, floor_level, temperature, humidity, wh_status_chk) " 
				+ " VALUES ( warehouse_seq.nextval, ?, ?, ?, ?, ? )";
	}

	@Override
	protected String modifyQuery() {
		return
				"UPDATE " + tableName() + " SET "
				+ "wh_section = ?, "
				+ "floor_level = ?, "
				+ "temperature = ?, "
				+ "humidity = ?, "
				+ "wh_status_chk = ? "
				+ " where " + pk_Coulum_Name() + " = ? "
			;
	}
	
	@Override
	protected PreparedStatement setPs(PreparedStatement ps, WarehouseDTO dto, String selector) {
		
		try {
			ps.setString(1, dto.getWh_section());
			ps.setString(2, dto.getFloor_level());
			ps.setDouble(3, dto.getTemperature());
			ps.setDouble(4, dto.getHumidity());
			ps.setString(5, dto.getWh_status_chk());
			if ("update".equals(selector)) { ps.setInt(6, dto.getWarehouse_num()); }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	@Override
	protected WarehouseDTO setDTO(ResultSet rs) {
		WarehouseDTO dto = new WarehouseDTO();
		
		// DTO > SET number 
		try {
			
			int warehouse_num = rs.getInt("warehouse_num");
			String wh_section = rs.getString("wh_section");
			String floor_level = rs.getString("floor_level");
			double temperature = rs.getDouble("temperature");
			double humidity = rs.getDouble("humidity");
			Date wh_chk_date = rs.getDate("wh_chk_date");
			String wh_status_chk = rs.getString("wh_status_chk");
			
			dto.setWarehouse_num(warehouse_num);
			dto.setWh_section(wh_section);
			dto.setFloor_level(floor_level);
			dto.setTemperature(temperature);
			dto.setHumidity(humidity);
			dto.setWh_chk_date(wh_chk_date);
			dto.setWh_status_chk(wh_status_chk);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	// SELECT MAIN QUERY FOR LIST 
		@Override // NEEDCHECKED
		protected String selectQuery(WarehouseDTO dto, CommonDTO commonDTO) {

			String query = // 고정 사용
						"SELECT * FROM ( "
	                + "  SELECT rownum AS rnum, subqry.* FROM ( "
					+ "" // MAIN TABLE A	
	                + "    SELECT tableA.* FROM warehouse tableA ";
	               
		    			
		    	// tableA = warehouse / tableB = mdm (재료용) / tableC = mdm (제품용)
			
		    // 고정
		    String where = commonDTO.getWhere();
		    if(("".equals(commonDTO.getWhere()))) where = "where 1 = 1";  

		    String orderBy = commonDTO.getOrderBy();
		    if(("".equals(commonDTO.getOrderBy()))) orderBy = pk_Coulum_Name();  
		    		
		    String groupBy = "";
		    if(!(commonDTO.getGroupBy() == null && "".equals(commonDTO.getGroupBy()))) {
		    	groupBy = commonDTO.getGroupBy();
		    }
		    
		    String where2 = commonDTO.getWhere2();
		    if (where2 == null || "".equals(where2)) {
		        where2 = "";
		    }
		    String where3 = commonDTO.getWhere3();
		    if (where3 == null || "".equals(where3)) {
		    	where3 = "";
		    }
		    	
		    // 추가 조건 붙일 때
		    query += where 
		    	  +  where2
		    	  +  where3
		    	  + groupBy
		    	  + " ORDER BY " + orderBy + " ) subqry )"
		    	  + " WHERE rnum >= ? AND rnum <= ?";
		    return query;
		}

	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}


	@Override
	protected String selectAllQuery() {
		return null;
	}

	@Override // CHECKED
	protected WarehouseDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		WarehouseDTO dto = new WarehouseDTO();
		return dto;
	}

	// SELECT QUERY 받아서 사용 
	public List selectCustom() {
		
		List list = new ArrayList();
		
		try ( Connection conn = getConn();
			  PreparedStatement ps = new LoggableStatement(conn, 
					  "SELECT DISTINCT wh_section FROM warehouse "); ) {
			try (  ResultSet rs = ps.executeQuery(); ) { // 
				
				while (rs.next()) {
					
					WarehouseDTO dto = new WarehouseDTO();
					dto.setWh_section(rs.getString("wh_section"));
					
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
						"SELECT DISTINCT wh_status_chk FROM warehouse "); ) {
			try (  ResultSet rs = ps.executeQuery(); ) { // 
				
				while (rs.next()) {
					
					WarehouseDTO dto = new WarehouseDTO();
					dto.setWh_status_chk(rs.getString("wh_status_chk"));
					
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



}
