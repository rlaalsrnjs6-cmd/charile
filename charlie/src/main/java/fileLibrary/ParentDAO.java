package fileLibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public abstract class ParentDAO<T> {

	// 援ы쁽�빐�꽌 �궗�슜�븷 硫붿냼�뱶
	protected abstract String tableName();
	protected abstract String pk_Coulum_Name();
	protected abstract int setDTONum(T dto);
	protected abstract String deleteQuery(T dto); 
	// set Query / set DTO(rs)


	protected abstract String selectQuery(T dto, String selector);
	
	protected abstract T setDTO(ResultSet rs); // DTO �꽭�똿

	protected abstract PreparedStatement setPs(PreparedStatement ps, T dto, String selector); 
	protected abstract String insertQuery();
	protected abstract String modifyQuery();



	// 二쇱슂 硫붿냼�뱶 濡쒖쭅 (DTO �닔�젙�빐�꽌 �궗�슜 �궗�슜 怨좎젙)
	// select
	public List selectDB(T dto, String selector) {

		List list = new ArrayList();

		try (Connection conn = getConn();) {

			try (PreparedStatement ps = conn.prepareStatement(selectQuery(dto, selector)); // �삤�씪�겢�슜�쑝濡� 而댄뙆�씪
					// SQL �떎�뻾 諛� 寃곌낵 �솗蹂�
					ResultSet rs = ps.executeQuery(); // �뜲�씠�꽣 媛��졇�샂
			) { // 寃곌낵 �솢�슜
				while (rs.next()) {

					list.add(setDTO(rs));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/DAO select list : " + list);
		return list;
	}

	public List selectDB(T dto) {

		List list = new ArrayList();

		try ( Connection conn = getConn(); ) {

			try (PreparedStatement ps = conn.prepareStatement(selectAllQuery()); // �삤�씪�겢�슜�쑝濡� 而댄뙆�씪
					// SQL �떎�뻾 諛� 寃곌낵 �솗蹂�
					ResultSet rs = ps.executeQuery(); // �뜲�씠�꽣 媛��졇�샂
			) { // 寃곌낵 �솢�슜
				while (rs.next()) {

					list.add(setDTO(rs));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/DAO select list : " + list);
		return list;
	}

	// insert
	public T insertDB(T dto) {

		T result = dto;

		try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(insertQuery())) {

			// set ? 荑쇰━ 梨꾩슦湲�
			setPs(ps, dto, "insert").executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/DAO insert result : " + result);
		return result;
	}

	// modify
	public T modifyDB(T dto) {

		try ( Connection conn = getConn(); 
				PreparedStatement ps = new LoggableStatement(conn, modifyQuery()); ) {
			
			setPs(ps, dto, "update").executeUpdate();

			ps.executeUpdate();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/DAO modify result : " + dto);
		return dto;
	}
	
	// select all 고정 사용
	public List selectAll() {
		
		List list = new ArrayList();
		
		try ( Connection conn = getConn(); ) {
			
			try (PreparedStatement ps = conn.prepareStatement(selectAllQuery()); // �삤�씪�겢�슜�쑝濡� 而댄뙆�씪
					// SQL �떎�뻾 諛� 寃곌낵 �솗蹂�
					ResultSet rs = ps.executeQuery(); // �뜲�씠�꽣 媛��졇�샂
					) { // 寃곌낵 �솢�슜
				while (rs.next()) {
					
					list.add(setDTO(rs));
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/DAO select list : " + list);
		return list;
	}
	
	//delete
	public int deleteDB(T dto) {

		int result = -1;
		
			try ( Connection conn = getConn(); 
					PreparedStatement ps = new LoggableStatement(conn, deleteQuery(dto)); ) {
				
				result = ps.executeUpdate();
			}

		 catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/DAO delete result : " + result);
		return result;

	}

	// �궗�슜 怨좎젙
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
	
	protected String selectAllQuery() {
		return " SELECT * FROM " + tableName() + " ORDER BY " +  pk_Coulum_Name() ; 
	};
	
	
	
	
}
