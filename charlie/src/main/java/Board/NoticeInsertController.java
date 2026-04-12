package Board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/NoticeInsertController")
public class NoticeInsertController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		System.out.println("/NoticeInsertController의 doPost 실행!!");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		
		
		NoticeService sv = new NoticeService();
		boolean result = sv.insertNotice(dto);
		
		if(result) {
			System.out.println("notice insert 성공");
			response.sendRedirect("./notice/controller");
		}else {
			System.out.println("notice insert 실패");
		}
	}

}
