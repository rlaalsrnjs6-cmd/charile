package Board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.report")
public class ReportController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("reportController 실행!!");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		BoardDTO dto = new BoardDTO();
		
		String uri = request.getRequestURI();
        String com = uri.substring(uri.lastIndexOf("/") + 1);
        
        Command action = null;
        String path = null;
		
     // 2. 명령어 판독기
        if (com.equals("select.report")) {		//리스트 조회
        	System.out.println("셀렉트 리포트로 이동");
            action = new ReportSelectAction();
        } else if (com.equals("insert.report")) {        	//insert
        	System.out.println("인설트 리포트 액션으로 이동");
            action = new ReportInsertAction();
        }else if(com.equals("detail.report") || com.equals("updateForm.report")) {//리포팅 상세, 리포팅 수정하기 누르면 데이터 전달용
        	action = new ReprtDetailSelectAction();
        }else if (com.equals("download.report")) {
            action = new ReportDownloadAction(); // 다운로드 전용 일꾼 생성
        }else if (com.equals("delete.report")) {
		action = new ReportDeleteAction(); 
	}else if (com.equals("update1.report")) {//리포팅 수정
		System.out.println("update1.report타고감");
		action = new ReportUpdate(); 
	}

        // 3. 일꾼 실행 및 이동
        if (action != null) {
            path = action.execute(request, response);

            
            if (path != null) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                // 로그를 찍어보면 다운로드 시에 이쪽으로 들어오는 걸 볼 수 있음
                System.out.println("컨트롤러: path가 null이므로 포워딩을 생략합니다. (다운로드 완료)");
            }
        }
        
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response); // POST 요청이 와도 GET 로직을 똑같이 실행해라!
	}
	

}
