package myPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/mypage")
public class MypageController extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/mypage의 doGet 실행!!");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		MypageDTO dto = new MypageDTO();
		HttpSession session = request.getSession();
		int empno = (Integer)session.getAttribute("empno");
		dto.setEmpno(empno);
		
		MypageService ms = new MypageService();
		List result = ms.selectAll(dto);
		request.setAttribute("list", result);
		request.getRequestDispatcher("myPage.jsp").forward(request, response);
		
		
	}

}
