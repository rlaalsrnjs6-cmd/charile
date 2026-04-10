package ProductionManagement;


import java.sql.Connection;
import java.sql.Date;
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
	
	
	//목록 페이지에 DB에 있는 데이터 불러와서 넘김(페이징 하며필요 없어져서 삭제)
//	public List<ProductionManagementDTO> selectPM() { 
//		
//		List<ProductionManagementDTO> list = new ArrayList();
//		String query = "select * from production_management";
//		
//		try(Connection conn = dataFactory.getConnection();
//				PreparedStatement ps = conn.prepareStatement(query);
//				ResultSet rs = ps.executeQuery())
//		{
//			while(rs.next()) {
//				ProductionManagementDTO dto = new ProductionManagementDTO();
//				dto.setCode(rs.getString("code"));
//				dto.setContent(rs.getString("content"));
//				dto.setEmpno(rs.getInt("empno"));
//				dto.setProd_num(rs.getInt("prod_num"));
//				dto.setTarget_quantity(rs.getInt("target_quantity"));
//				dto.setTitle(rs.getString("title"));
//				dto.setWork_end(rs.getDate("work_end"));
//				dto.setWork_start(rs.getDate("work_start"));
//				list.add(dto);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	//	남은수량 , 만든 총합 출력
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
	
	//페이지에서 보여줄 항목 몇개인지 개수 리턴
	public int getTotalCount() {
		
		int total = 0;
		
		try {
			//자원을 가지러 가기 위해 문을 열고
			Context ctx = new InitialContext();
			//열어둔 문을 통해 어디로 갈지 경로를 정함
	        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
	        
	        String query ="select count(*) from production_management"; 
	        
	        try(Connection conn = dataFactory.getConnection();
	        	PreparedStatement ps = conn.prepareStatement(query);	
	        		ResultSet rs = ps.executeQuery()){
	        	
	        	if(rs.next()) {
	        		 // COUNT(*)가 emp 테이블에 사원이 몇명 있는지 검사해서 숫자를 리턴 해주기 때문에
	        		//while을 돌릴 필요 없이 if로 해당 숫자만 돌려받음
	        		
	        		//rs.getInt(1);을 한 이유:
	        		//count(*)을 하면 사원 수를 count컬럼 한 줄에 찍어서 돌려주니까 한 줄만 받겠다
	        		total = rs.getInt(1);
	        	}
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public List<ProductionManagementDTO> selectPage(ProductionManagementDTO dto) {
		List list = new ArrayList();
		
		String query = "SELECT * FROM ("
                + "  SELECT rownum AS rnum, a.* FROM ("
                + "    SELECT "
                + "        P.prod_num, "
                + "        P.title, " 
                + "        P.target_quantity AS \"전체목표\", "
                + "        SUM(L.lot_count) AS \"현재까지_만든_총합\", "
                + "        (P.target_quantity - SUM(L.lot_count)) AS \"남은목표_수량\" "
                + "    FROM production_management P "
                + "    JOIN work_order W ON P.prod_num = W.prod_num "
                + "    JOIN lot L ON W.order_num = L.order_num "
                + "    GROUP BY P.prod_num, P.title, P.target_quantity " 
                + "    ORDER BY P.prod_num ASC"
                + "  ) a"
                + ") WHERE rnum >= ? AND rnum <= ?";
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {
				
				ps.setInt(1, dto.getStart());
				ps.setInt(2, dto.getEnd());
				
				

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						ProductionManagementDTO dto2 = new ProductionManagementDTO();
						dto2.setProd_num(rs.getInt("prod_num"));
		                dto2.setTitle(rs.getString("title"));
		                dto2.setTarget_quantity(rs.getInt("전체목표"));
		                dto2.setCurrentCount(rs.getInt("현재까지_만든_총합"));
		                dto2.setRemainCount(rs.getInt("남은목표_수량"));
						list.add(dto2);
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DAO의 list: " + list);
		return list;
	}
	
}///클래스 닫음
