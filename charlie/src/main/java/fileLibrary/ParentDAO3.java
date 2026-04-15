package fileLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import fileLibrary.LoggableStatement;


public abstract class ParentDAO3<T, C> {

	// 援ы쁽�빐�꽌 �궗�슜�븷 硫붿냼�뱶
	protected abstract String tableName();
	protected abstract String pk_Coulum_Name();
	protected abstract int setDTONum(T dto);
	// set Query / set DTO(rs)

	protected abstract PreparedStatement selectPs(PreparedStatement ps, C commonDTO) throws SQLException; 
	protected abstract String selectQuery(T dto, C commonDTO);

	protected abstract String selectAllQuery();
	protected abstract T setJoinDTO(ResultSet rs) throws SQLException; // DTO �꽭�똿
	
	protected abstract T setDTO(ResultSet rs) throws SQLException; // DTO �꽭�똿

	protected abstract PreparedStatement setPs(PreparedStatement ps, T dto, String selector) throws SQLException; 
	protected abstract String insertQuery();
	protected abstract String modifyQuery();
	
	// select
	public List selectDB(T dto, C commonDTO) {

		List list = new ArrayList();

		try ( Connection conn = getConn();
			  PreparedStatement ps = new LoggableStatement(conn, selectQuery(dto, commonDTO));) 
					  
		{ selectPs(ps, commonDTO);
		  // DB 조회 결과
		  System.out.println(((LoggableStatement) ps).getQueryString());
		
			try ( ResultSet rs = ps.executeQuery(); ) 
		
			{  while (rs.next()) {
				
					list.add(setDTO(rs));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();					
		}
		return list;
	}
	
	// selectOne
	public T selectOne(T dto, C commonDTO) {
		
		try ( Connection conn = getConn();
			  PreparedStatement ps = new LoggableStatement(conn, 
					  	  selectQuery(dto, commonDTO));) 
		
				{ 	ps.setInt(1, setDTONum(dto));
					ps.setInt(2, 1);
					ps.setInt(3, 1);
		  // DB 조회 결과
		  System.out.println(((LoggableStatement) ps).getQueryString());
		
			try ( ResultSet rs = ps.executeQuery(); ) { 
	
				
				if (rs.next()) {
					
					dto = setDTO(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();					
		}
		return dto;
	}
	

	// insert
	public T insertDB(T dto) {

		T result = dto;

		try (	Connection conn = getConn(); 
				PreparedStatement ps = new LoggableStatement(conn, insertQuery())) {

			// DB 조회 결과
			System.out.println(((LoggableStatement) ps).getQueryString());
			
			setPs(ps, dto, "insert").executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// modify
	public T modifyDB(T dto) {

		try ( 	Connection conn = getConn(); 
				PreparedStatement ps = new LoggableStatement(conn, modifyQuery()); ) {
			
			// DB 조회 결과
			System.out.println(((LoggableStatement) ps).getQueryString());
			
			setPs(ps, dto, "update").executeUpdate();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//delete
	public int deleteDB(T dto) {

		int result = -1;
		String deleteQuery = "DELETE FROM "+ tableName() +" WHERE "+ pk_Coulum_Name() + " = '" + setDTONum(dto) + "'";
		
			try ( Connection conn = getConn(); 
					PreparedStatement ps = new LoggableStatement(conn, deleteQuery); ) {
				
				// DB 조회 결과
				System.out.println(((LoggableStatement) ps).getQueryString());
				
				result = ps.executeUpdate();
			}

		 catch (Exception e) {
			e.printStackTrace();
		}
		return result;

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
	
	// select all 고정 사용 (join table research 용도)
		public List selectJoinInfo() {
			
			List list = new ArrayList();
			
			try ( Connection conn = getConn(); ) {
				
				try (PreparedStatement ps = conn.prepareStatement(selectAllQuery()); // �삤�씪�겢�슜�쑝濡� 而댄뙆�씪
						// SQL �떎�뻾 諛� 寃곌낵 �솗蹂�
						ResultSet rs = ps.executeQuery(); // �뜲�씠�꽣 媛��졇�샂
						) { // 寃곌낵 �솢�슜
					while (rs.next()) {
						list.add(setJoinDTO(rs));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("/DAO select list : " + list);
			return list;
		}
	
	
}
