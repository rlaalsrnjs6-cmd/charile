package Emp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/charlie")
public class Emplogin extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mod = request.getParameter("mod");
		System.out.println("찰리"+mod);
		if("signin".equals(mod)) {
			System.out.println("charlie회원가입보내기");
			request.getRequestDispatcher("/WEB-INF/views/emp/emp_signin.jsp").forward(request, response);	
		} else {
			System.out.println("charlie로그인으로보내기");
		request.getRequestDispatcher("/WEB-INF/views/emp/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
