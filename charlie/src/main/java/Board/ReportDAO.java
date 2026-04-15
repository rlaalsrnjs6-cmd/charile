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
		String selectTitle = dto.getSelectTitle();
		boolean hasSearch = (selectTitle != null && !selectTitle.trim().isEmpty());
		
		List list = new ArrayList();
		StringBuilder query = new StringBuilder();
	    query.append("SELECT * FROM (");
	    query.append("    SELECT ROWNUM AS rnum, a.* FROM (");
	    query.append("        SELECT p.*, e.ename ");
	    query.append("        FROM post p ");
	    query.append("        LEFT OUTER JOIN emp e ON p.empno = e.empno ");
	    query.append("        WHERE p.category LIKE '리포트%' ");
	    
	    if (hasSearch) {
	        query.append("        AND p.title LIKE '%' || ? || '%' ");
	    }
	    
	    //관리자가 아니면 트루
	    boolean isNotAdmin = 1 != dto.getLevel();
	    if (isNotAdmin) {//관리자가 아니면 본인이 작성한 글만 볼 수 있음
	        query.append("        AND p.empno = ? ");
	    }

	    query.append("        ORDER BY p.post_num DESC");
	    query.append("    ) a ");
	    query.append("    WHERE ROWNUM <= ?");
	    query.append(") WHERE rnum >= ?");
		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query.toString())) {
				
				int idx = 1;
				
				if (hasSearch) {
			        ps.setString(idx++, selectTitle);
			    }
				
				if(isNotAdmin) {
					ps.setInt(idx++, dto.getEmpno());
				}
				ps.setInt(idx++, dto.getEnd());
		        ps.setInt(idx++, dto.getStart());

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						
						BoardDTO dto2 = new BoardDTO();
						dto2.setPost_num(rs.getInt("post_num"));
		                dto2.setTitle(rs.getString("title"));
		                dto2.setCategory(rs.getString("category"));
		                dto2.setContent(rs.getString("content"));
		                dto2.setEname(rs.getString("ename"));
		                dto2.setWrite_time(rs.getDate("write_time"));
						list.add(dto2);
					}
				}
				System.out.println("ReportDAO에서 list" + list);
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ReportDAO selectNotice() 예외 발생");
		return null;
	}
	
	public int getTotalCount(BoardDTO dto) {
		
		int total = 0;
		String selectTitle = dto.getSelectTitle();
	    boolean hasSearch = (selectTitle != null && !selectTitle.trim().isEmpty());
	    boolean isNotAdmin = (dto.getLevel() == 2); // 관리자가 아니면 true로 만들고~
	    
		try {
			//자원을 가지러 가기 위해 문을 열고
			Context ctx = new InitialContext();
			//열어둔 문을 통해 어디로 갈지 경로를 정함
	        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
	        
	        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM post WHERE category LIKE '리포트%'");
	        
	        if (hasSearch) {
	            query.append(" AND title LIKE '%' || ? || '%'");
	          }
	        if (isNotAdmin) {
	            query.append(" AND empno = ?");
	        }
	        try(Connection conn = dataFactory.getConnection();
	        	PreparedStatement ps = conn.prepareStatement(query.toString());	
	        		){
	        	int idx = 1;
	        	
	        	if (hasSearch) {
	                ps.setString(idx++, selectTitle);
	            }

	            if (isNotAdmin) {
	                ps.setInt(idx++, dto.getEmpno());
	            }
	            
	        	try(ResultSet rs = ps.executeQuery()){
	        		if(rs.next()) {
		        		 // COUNT(*)가 emp 테이블에 사원이 몇명 있는지 검사해서 숫자를 리턴 해주기 때문에
		        		//while을 돌릴 필요 없이 if로 해당 숫자만 돌려받음
		        		
		        		//rs.getInt(1);을 한 이유:
		        		//count(*)을 하면 사원 수를 count컬럼 한 줄에 찍어서 돌려주니까 한 줄만 받겠다
		        		total = rs.getInt(1);
		        	}
	        	}
	        	
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	//detail select 하는 메서드
	public BoardDTO selectData(int postNum) {
		String query = "SELECT p.*, e.ename, f.url, f.file_num " +
	               "FROM post p " +
	               "JOIN emp e ON p.empno = e.empno " +
	               "LEFT OUTER JOIN upload_file f ON p.file_num = f.file_num " +
	               "WHERE p.post_num = ?";

		
		try (Connection conn = dataFactory.getConnection(); 
				PreparedStatement ps =  conn.prepareStatement(query)) {
				
				ps.setInt(1, postNum);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						BoardDTO dto2 = new BoardDTO();
						dto2.setEname(rs.getString("ename"));
						dto2.setPost_num(rs.getInt("post_num"));
		                dto2.setTitle(rs.getString("title"));
		                dto2.setCategory(rs.getString("category"));
		                dto2.setContent(rs.getString("content"));
		                dto2.setWrite_time(rs.getDate("write_time"));
		                dto2.setFileNum(rs.getInt("file_num"));
		                String url = rs.getString("url");
		                if (url == null || url.trim().isEmpty()) {
		                    dto2.setUrl("no_file");
		                } else {
		                    dto2.setUrl(url);
		                }
						return dto2;
		                
					}
				}


		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ReportDAO selectNotice() 예외 발생");
		return null;
	}
	
	public int insertData(BoardDTO dto) {
		

		    int result = 0;
		    
		    // 파일 정보 인서트
		    String sqlFile = "INSERT INTO UPLOAD_FILE (FILE_NUM, URL, UPLOAD_TIME) " +
		                     "VALUES (SEQ_UPLOAD_FILE_NUM.NEXTVAL, ?, SYSDATE)";
		                     
		    // 게시글 인서트 (SEQ_UPLOAD_FILE_NUM.CURRVAL 사용)
		    String sqlPost = "INSERT INTO POST (POST_NUM, TITLE, CONTENT, EMPNO, CATEGORY, FILE_NUM, WRITE_TIME) " +
		                     "VALUES ( SEQ_POST_NO.NEXTVAL, ?, ?, ?, '리포트', SEQ_UPLOAD_FILE_NUM.CURRVAL, SYSDATE)";

		    try (Connection conn = dataFactory.getConnection()) {
		        conn.setAutoCommit(false); // 트랜잭션 수동 관리 시작

		        try (PreparedStatement psFile = conn.prepareStatement(sqlFile);
		             PreparedStatement psPost = conn.prepareStatement(sqlPost)) {
		            
		            // 1. UPLOAD_FILE 인서트
		            psFile.setString(1, dto.getUrl());
		            psFile.executeUpdate();

		            // 2. POST 인서트
		            psPost.setString(1, dto.getTitle());
		            psPost.setString(2, dto.getContent());
		            psPost.setInt(3, dto.getEmpno());
		            
		            result = psPost.executeUpdate();
		            System.out.println("DAO: DB 커밋 완료");
		            conn.commit(); // 모든 쿼리가 성공하면 최종 확정
		        } catch (Exception e) {
		            conn.rollback(); // 하나라도 에러 나면 폴더에 저장된 파일은 어쩔 수 없지만 DB는 복구
		            throw e;
		        }
		    } catch (Exception e) {
		    	System.out.println("리포트 인서트 예외 발생함");
		        e.printStackTrace();
		    }
		    System.out.println("result: " + result);
		    return result;
		}
	
	//리포트 데이터 삭제
	public int deleteData(BoardDTO dto) {
		
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
	
	//update 메서드
	public int updateData(BoardDTO dto) {
		int result = 0;
	    
	    // 1. 파일 정보 업데이트 (post 테이블이 참조하는 file_num의 url 변경)
	    String sqlFile = "UPDATE UPLOAD_FILE SET URL = ?, UPLOAD_TIME = SYSDATE " +
	                     "WHERE FILE_NUM = (SELECT FILE_NUM FROM POST WHERE POST_NUM = ?)";
	                     
	    // 2. 게시글 정보 업데이트 (제목, 내용 수정)
	    String sqlPost = "UPDATE POST SET TITLE = ?, CONTENT = ? WHERE POST_NUM = ?";

	    try (Connection conn = dataFactory.getConnection()) {
	        conn.setAutoCommit(false); // 트랜잭션 시작

	        try (PreparedStatement psFile = conn.prepareStatement(sqlFile);
	             PreparedStatement psPost = conn.prepareStatement(sqlPost)) {
	            
	            // 1. UPLOAD_FILE 업데이트
	            psFile.setString(1, dto.getUrl());
	            psFile.setInt(2, dto.getPost_num());
	            psFile.executeUpdate();

	            // 2. POST 업데이트
	            psPost.setString(1, dto.getTitle());
	            psPost.setString(2, dto.getContent());
	            psPost.setInt(3, dto.getPost_num());
	            
	            result = psPost.executeUpdate();
	            
	            conn.commit(); // 둘 다 성공하면 확정
	            System.out.println("DAO: " + dto.getPost_num() + "번 데이터 수정 및 커밋 완료");
	            
	        } catch (Exception e) {
	            conn.rollback(); // 에러 시 롤백
	            throw e;
	        }
	    } catch (Exception e) {
	        System.out.println("리포트 업데이트 예외 발생");
	        e.printStackTrace();
	    }
	    
	    return result;
	}
	
}
