package Board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/NoticeUpdateController")
public class NoticeUpdateController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/NoticeUpdateController의 doPost 실행!!");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		int postNum = Integer.parseInt(request.getParameter("post_num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO dto = new BoardDTO();
		
		dto.setPost_num(postNum);
		dto.setTitle(title);
		dto.setContent(content);
		
		NoticeService sc = new NoticeService();
		int result = sc.noticeUpdate(dto);
		
		response.sendRedirect("/charlie/notice/controller");
	}

}
