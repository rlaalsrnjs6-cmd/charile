package myPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Emp.EmpDTO;


@WebServlet("/mypageuc")
public class MypagUpdateController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/mypageuc의 doPost 실행!!");
		HttpSession session = request.getSession();
		String empPw = (String) session.getAttribute("pw");

		String pw = request.getParameter("pw");
		
		int empno = Integer.parseInt(request.getParameter("empno"));
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 +"@"+ email2;
		
		MypageDTO dto = new MypageDTO();
		if(pw == null || pw.trim().isEmpty()) {
			dto.setPw(empPw);
		}else {			
			dto.setPw(pw);
		}
		dto.setEmpno(empno);
		dto.setTel(tel);
		dto.setAddr(addr);
		dto.setEmail(email);
		
		MypageService ms = new MypageService();
		int result = ms.update(dto);
		
		if(result > 0) {
			request.setAttribute("result", result );
			request.getRequestDispatcher("/mypage").forward(request, response);
		}
	}

}
