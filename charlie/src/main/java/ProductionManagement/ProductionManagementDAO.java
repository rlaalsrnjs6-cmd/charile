package ProductionManagement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ProductionManagementDAO {
	private DataSource dataFactory; //캡슐화를 위해 private
	
	ProductionManagementDAO(){
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}//생성자 닫음
	
	
	//목록 페이지에 DB에 있는 데이터 불러와서 넘김
	public List<ProductionManagementDTO> selectPM() { 
		
		List<ProductionManagementDTO> list = new ArrayList();
		String query = "select * from production_management";
		
		try(Connection conn = dataFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery())
		{
			while(rs.next()) {
				ProductionManagementDTO dto = new ProductionManagementDTO();
				dto.setCode(rs.getString("code"));
				dto.setContent(rs.getString("content"));
				dto.setEmpno(rs.getInt("empno"));
				dto.setProd_num(rs.getInt("prod_num"));
				dto.setTarget_quantity(rs.getInt("target_quantity"));
				dto.setTitle(rs.getString("title"));
				dto.setWork_end(rs.getDate("work_end"));
				dto.setWork_start(rs.getDate("work_start"));
				list.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ProductionManagementDTO> selectData() {
		String query = "SELECT "
				+ "    P.prod_num, "
				+ "    P.target_quantity AS \"전체목표\", "
				+ "    SUM(L.lot_count) AS \"현재까지_만든_총합\", "
				+ "    (P.target_quantity - SUM(L.lot_count)) AS \"남은목표_수량\" "
				+ "FROM production_management P\r\n"
				+ "JOIN work_order W ON P.prod_num = W.prod_num  "
				+ "JOIN lot L ON W.order_num = L.order_num "
				+ "GROUP BY P.prod_num, P.target_quantity "
				+ "ORDER BY p.prod_num ASC";
				List<ProductionManagementDTO> list = new ArrayList();
		try(Connection conn = dataFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();)
		{
			while(rs.next()) {
				ProductionManagementDTO dto = new ProductionManagementDTO();
				
				dto.setCurrentCount(rs.getInt("현재까지_만든_총합"));
				dto.setRemainCount(rs.getInt("남은목표_수량"));
				dto.setProd_num(rs.getInt("prod_num"));
				dto.setTarget_quantity(rs.getInt("전체목표"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}///클래스 닫음
