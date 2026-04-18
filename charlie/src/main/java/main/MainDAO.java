package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Board.BoardDTO;

public class MainDAO {
	private DataSource dataFactory;
	
	MainDAO(){
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}//생성자 닫음
	
	public List loadDn() {
List list = new ArrayList();
		
		String query = "select m.*, to_char(m.ds_date, 'yyyy-mm-dd') as dsDate"
				+ " from  dash_notice m";
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						MainDTO dto = new MainDTO();
						dto.setDsNum(rs.getInt("ds_num"));
		                dto.setDsLine(rs.getString("ds_line"));
		                dto.setDsDate(rs.getString("dsDate"));
		                dto.setDsStatus(rs.getInt("ds_status"));
		                
						list.add(dto);
					}
				}
				System.out.println("Main DAO에서 list" + list);
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MainDAO loadDn() 예외 발생");
		return null;
	}//loadDn() 닫음
	
	// line 테이블 조회  
	public List loadLs() {
		List list = new ArrayList();
		String query = "select * from line";
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						MainDTO dto = new MainDTO();
						dto.setLineNum(rs.getInt("line_num"));
		                dto.setLineName(rs.getString("line_name"));
		                dto.setLineStatus(rs.getInt("line_status"));
		                dto.setLineContent(rs.getString("line_content"));
						list.add(dto);
					}
				}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MainDAO loadLs() 예외 발생");
		return null;	
	} //loadLs() 닫음
	
	
	//기계 정비 및 상태 update 로직
	public int lineUpdateStatus(MainDTO dto) {
		
		String query = "update line set line_status=? where line_name=?";
		
		try(Connection conn = dataFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
						ps.setInt(1, dto.getLineStatus());
						ps.setString(2, dto.getLineName());
	
						int result = ps.executeUpdate();
						return result  ;
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println("기계 정비 및 상태 update 로직 예외처리 됨");
			return 0;
		}//updatePM()닫음
	
	public List loadWh() {
		List list =  new ArrayList();
		String query = "select * from warehouse";
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						MainDTO dto = new MainDTO();
						dto.setWhSection(rs.getString("wh_section"));
		                dto.setFloorLevel(rs.getString("floor_level"));
		                dto.setTemperature(rs.getDouble("temperature"));
		                dto.setHumidity(rs.getDouble("humidity"));
						list.add(dto);
					}
				}
				System.out.println("Main DAO loadWh()의 list:  " + list);
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MainDAO loadWh() 예외 발생");
		return null;
	}
	
	
	
}//클래스 닫음
