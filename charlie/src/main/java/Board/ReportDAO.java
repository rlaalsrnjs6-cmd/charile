package Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReportDAO {
	private DataSource dataFactory;
	
	public ReportDAO() {
        try {
            Context ctx = new InitialContext();
            dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public List selectAll(BoardDTO dto) {
		List list = new ArrayList();
		String query = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM "
	             + "(SELECT * FROM post WHERE category LIKE '리포트%' ORDER BY post_num DESC) a "
	             + "WHERE ROWNUM <= ?) WHERE rnum >= ?";
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {
				
				ps.setInt(1, dto.getEnd());
				ps.setInt(2, dto.getStart());
				
				

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						
						BoardDTO dto2 = new BoardDTO();
						dto2.setPost_num(rs.getInt("post_num"));
		                dto2.setTitle(rs.getString("title"));
		                dto2.setCategory(rs.getString("category"));
		                dto2.setContent(rs.getString("content"));
		                dto2.setWrite_time(rs.getDate("write_time"));
						list.add(dto2);
					}
				}
				System.out.println("DAO에서 list" + list);
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("NoticeDAO selectNotice() 예외 발생");
		return null;
	}
	
	public int getTotalCount() {
		
		int total = 0;
		
		try {
			//자원을 가지러 가기 위해 문을 열고
			Context ctx = new InitialContext();
			//열어둔 문을 통해 어디로 갈지 경로를 정함
	        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
	        
	        String query ="select count(*) from post where category LIKE '리포트%'"; 
	        
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
}
