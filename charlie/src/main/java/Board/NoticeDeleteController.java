package Board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/NoticeDeleteController")
public class NoticeDeleteController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		System.out.println("/NoticeDeleteController의 doPost 실행!!");
		
		int postNum = Integer.parseInt(request.getParameter("post_num"));
		BoardDTO dto = new BoardDTO();
		dto.setPost_num(postNum);
		
		NoticeService sc = new NoticeService();
		int result = sc.delete(dto);
		
		response.sendRedirect("/charlie/notice/controller");
		
	}

}
