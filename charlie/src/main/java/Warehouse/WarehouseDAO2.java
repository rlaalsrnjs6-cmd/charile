package Warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Lot.LotDTO;

public class WarehouseDAO2 {
	public List<WarehouseDTO> select(WarehouseDTO dto) {
		List<WarehouseDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "select * from warehouse ";

			if(dto.getWarehouse_num()!=-1) {
				query += "where warehouse_num = ?";
			}
			ps = conn.prepareStatement(query);

			if(dto.getWarehouse_num()!=-1) {
				ps.setInt(1,dto.getWarehouse_num());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				WarehouseDTO DTO = new WarehouseDTO();
				int warehouse_num = rs.getInt("warehouse_num");
				String section = rs.getString("section");
				String floor_level = rs.getString("floor_level");
				int temperature = rs.getInt("temperature");
				String humidity = rs.getString("humidity");
				String wh_status_chk = rs.getString("wh_status_chk");
				
				DTO.setWarehouse_num(warehouse_num);
				DTO.setSection(section);
				DTO.setFloor_level(floor_level);
				DTO.setTemperature(temperature);
				DTO.setHumidity(humidity);
				DTO.setWh_status_chk(wh_status_chk);
				
				list.add(DTO);
			}
//			System.out.println("DAOlist:"+list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
public int warehouseDAO(WarehouseDTO dto) {
		
		int result = -1;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			
			String query = "";
			System.out.println("QCDAOmod:"+dto.getMod());
			// 업데이트
			if("up".equals(dto.getMod())) {
				  query = "UPDATE  warehouse "
						+ "SET warehouse_num = ?, "
						+ "section = ?, "
						+ "floor_level = ?, "
						+ "temperature = ?, "
						+ "humidity = ?, "
						+ "wh_status_chk = ? "
						+ "where warehouse_num = ?";
			}
			// 인서트
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO warehouse "//아직안만듬
					   + "(warehouse_num, "
					   + "section, "
					   + "floor_level, "
					   + "temperature, "
					   + "humidity, "
					   + "wh_status_chk) "
					   + "VALUES (?, ?, ?, ?, ?, ?)";
			}
			// 딜리트
			if("delete".equals(dto.getMod())) { //만드는중
				query = "DELETE FROM warehouse "
					  + "WHERE warehouse_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getWarehouse_num());
				ps.setString(2, dto.getSection());
				ps.setString(3, dto.getFloor_level());
				ps.setInt(4, dto.getTemperature());
				ps.setString(5, dto.getHumidity());
				ps.setString(6, dto.getWh_status_chk());
				ps.setInt(7, dto.getWarehouse_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setInt(1, dto.getWarehouse_num());
				ps.setString(2, dto.getSection());
				ps.setString(3, dto.getFloor_level());
				ps.setInt(4, dto.getTemperature());
				ps.setString(5, dto.getHumidity());
				ps.setString(6, dto.getWh_status_chk());
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getWarehouse_num());
			}
			
			result = ps.executeUpdate();
			
			System.out.println("lotDAO리솔트:"+result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
