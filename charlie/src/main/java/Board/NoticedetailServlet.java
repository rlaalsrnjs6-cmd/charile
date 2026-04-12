package Board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NoticedetailServlet")
public class NoticedetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/NoticedetailServlet의 doGET 실행!!");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		int postNum = Integer.parseInt(request.getParameter("post_num"));
		BoardDTO dto = new BoardDTO();
		dto.setPost_num(postNum);
		
		NoticeService sc = new NoticeService();
		List list = sc.noticeDetail(dto);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/noticeDetail.jsp").forward(request, response);
		
		
		
		
	}

}
