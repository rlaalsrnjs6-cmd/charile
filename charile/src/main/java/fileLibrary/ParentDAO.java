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
	// set Query / set DTO(rs)
	protected abstract String selectQuery(String selector);
	protected abstract T setDTO(ResultSet rs); // DTO �꽭�똿

	protected abstract String insertQuery();
	protected abstract PreparedStatement setInsertPs(PreparedStatement ps); 

	protected abstract String modifyQuery();
	protected abstract PreparedStatement setModifyPs(PreparedStatement ps); // set ps �빀移� �닔 �엳�뒗吏� �솗�씤 �븘�슂

	protected abstract String deleteQuery(); // ps �뾾�쓬


	// 二쇱슂 硫붿냼�뱶 濡쒖쭅 (DTO �닔�젙�빐�꽌 �궗�슜 �궗�슜 怨좎젙)
	// select
	public List selectDB(T dto, String selector) {

		List list = new ArrayList();

		try (Connection conn = getConn();) {

			try (PreparedStatement ps = conn.prepareStatement(selectQuery(selector)); // �삤�씪�겢�슜�쑝濡� 而댄뙆�씪
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
		System.out.println("/select list : " + list);
		return list;
	}

	// insert
	public T insertDB(T dto) {

		T result = dto;

		try (Connection conn = getConn(); PreparedStatement ps = conn.prepareStatement(insertQuery())) {

			// set ? 荑쇰━ 梨꾩슦湲�
			setInsertPs(ps).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/insert result : " + result);
		return result;
	}

	// modify
	public T modifyDB(T dto) {

		try ( Connection conn = getConn(); 
				PreparedStatement ps = new LoggableStatement(conn, modifyQuery()); ) {
			
			setModifyPs(ps).executeUpdate();

			ps.executeUpdate();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/modify result : " + dto);
		return dto;
	}
	
	//delete
	public int deleteDB(T dto) {

		int result = -1;
		
			try ( Connection conn = getConn(); 
					PreparedStatement ps = new LoggableStatement(conn, deleteQuery()); ) {
				
				result = ps.executeUpdate();
			}

		 catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("/delete result : " + result);
		return result;

	}

	// �궗�슜 怨좎젙
	private Connection getConn() {
		Connection conn = null;
		try {
			// JNDI 諛⑹떇
			// context.xml�뿉 �엳�뒗 DB �젙蹂대줈 而ㅻ꽖�뀡 ���쓣 媛��졇�삩�떎
			Context ctx = new InitialContext();
			// DataSource : 而ㅻ꽖�뀡 �� 愿�由ъ옄
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle");

			// DB �젒�냽(洹몃윴�뜲 �씠�젣 而ㅻ꽖�뀡 ��濡�)
			conn = dataFactory.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
