package Warehouse;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO3;

public class WarehouseDAO extends ParentDAO3<WarehouseDTO, CommonDTO>{

	@Override
	protected String tableName() {
		return "Warehouse";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "Warehouse_num";
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
		    		
		 // 가변 조건
			if ( dto != null ) {
			
				if(commonDTO.getSearch() != "") {
					switch(commonDTO.getSelector()) {
					
					// 전체검색
//					case "search_all": where = " where tableB.code = " 
//											+ "'" + commonDTO.getSearch() + "'"
//											+ " or tableB.name = '" + commonDTO.getSearch() + "'"; break;	
//					/* 컬럼별 검색 */			 
//					case "code" : where = " where tableB.code = '" +  commonDTO.getSearch() + "'"; break;
//					case "name" : where = " where tableA.name = '" +  commonDTO.getSearch() + "'"; break;
					default : break;
					}
				}
			}

		    String groupBy = "";
		    // 추가 조건 붙일 때
		    query += where 
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




}
