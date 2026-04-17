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
				System.out.println("Main DAO에서 list" + list);
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MainDAO loadLs() 예외 발생");
		return null;	
	} //loadLs() 닫음
	
	
	
	
	
	
}//클래스 닫음
