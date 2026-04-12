package Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ProductionManagement.ProductionManagementDTO;

public class NoticeDAO {
	private DataSource dataFactory;
	
	NoticeDAO(){
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//생성자 닫음
	
	
	//공지사항 목록 셀렉트 메서드
	public List selectNotice(BoardDTO dto) { 
		System.out.println("noticeDAO의 selectNotice() 실행");
		List list = new ArrayList();
		
		String query = "SELECT * FROM (SELECT ROWNUM AS rnum, a.* FROM "
	             + "(SELECT * FROM post WHERE category='공지사항' ORDER BY post_num DESC) a "
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

	//페이지에서 보여줄 항목 몇개인지 개수 리턴
		public int getTotalCount() {
			
			int total = 0;
			
			try {
				//자원을 가지러 가기 위해 문을 열고
				Context ctx = new InitialContext();
				//열어둔 문을 통해 어디로 갈지 경로를 정함
		        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		        
		        String query ="select count(*) from post where category='공지사항'"; 
		        
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
	

		//공지사항(notice)insert 메서드 
		public boolean noticeInsert(BoardDTO dto) {
			String query = "insert into post (post_num, category, title, content, write_time, empno, file_num)"
					+ " values(SEQ_POST_NO.nextval, '공지사항', ?, ?, sysdate, 1, 0 )";
				
			try(Connection conn = dataFactory.getConnection();
					PreparedStatement ps = conn.prepareStatement(query);)
				{
					//inser into다음 values에 들어갈 ?에 해당하는 값을 순서대로 넣음
					ps.setString(1, dto.getTitle());
					ps.setString(2, dto.getContent());

					int result = ps.executeUpdate();
					return result > 0; 
				}catch(Exception e) {
					e.printStackTrace();
				}
			System.out.println("Notice insertDAO 예외 발생");
				return false;	
		}
		
		//디테일 select 메서드
		public List detailSelect(BoardDTO dto) {
			List list = new ArrayList();
			String query = "select * from post where post_num=?";
			
			try (Connection conn = dataFactory.getConnection(); 
					PreparedStatement ps =  conn.prepareStatement(query)) {
				
					ps.setInt(1, dto.getPost_num());
					
					try (ResultSet rs = ps.executeQuery()) {
						if(rs.next()) {
							BoardDTO dto2 = new BoardDTO();
							dto2.setPost_num(rs.getInt("post_num"));
			                dto2.setTitle(rs.getString("title"));
			                dto2.setCategory(rs.getString("category"));
			                dto2.setContent(rs.getString("content"));
			                dto2.setWrite_time(rs.getDate("write_time"));
							list.add(dto2);
						}
					}
					return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("noticeDAO에서 detailSelect()의 예외 발생");
			return null;
			
		}

		
		public int update(BoardDTO dto) {
			String query = "update post set title=?, content=?"
					+ " where post_num=?";
			
			try(Connection conn = dataFactory.getConnection();
					PreparedStatement ps = conn.prepareStatement(query);){
							ps.setString(1, dto.getTitle());
							ps.setString(2, dto.getContent());
							ps.setInt(3, dto.getPost_num());

					int result = ps.executeUpdate();
							return result  ;
					}catch(Exception e) {
						e.printStackTrace();
					}
			System.out.println("DAO의 update 예외 발생");
				return 0;
		}
		
		//공지사항 delete메서드
		public int noticeDelete(BoardDTO dto) {
			
			String query = "delete from post where post_num=?";
			
			try(Connection conn = dataFactory.getConnection();
					PreparedStatement ps = conn.prepareStatement(query);){
							ps.setInt(1, dto.getPost_num());
							int result = ps.executeUpdate();
							return result ;
					}catch(Exception e) {
						e.printStackTrace();
					}
			System.out.println("DAO에서 delete메서드 오류남");
				return 0;
		}
}//클래스 닫음
