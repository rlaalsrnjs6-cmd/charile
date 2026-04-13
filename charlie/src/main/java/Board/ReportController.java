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
        	
            action = new ReportSelectAction();
        } else if (com.equals("insert.report")) {        	//insert
        	
            action = new ReportInsertAction();
        }else if(com.equals("detail.report")) {
        	action = new ReprtDetailSelectAction();
        }

        // 3. 일꾼 실행 및 이동
        if (action != null) {
            path = action.execute(request, response);
            request.getRequestDispatcher(path).forward(request, response);
        }
        
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response); // POST 요청이 와도 GET 로직을 똑같이 실행해라!
	}
	

}
