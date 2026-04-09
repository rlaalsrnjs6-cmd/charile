package Emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Check")
public class LoginCheck extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String mod = request.getParameter("mod");
		EmpDTO empDTO = new EmpDTO();
		empDTO.setId(id);
		empDTO.setPw(pw);
		empDTO.setMod(mod);
		EmpService service = new EmpService();
		List<EmpDTO> check = service.select(empDTO);
			if (check.size() == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('로그인 하세요');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return;
			}else {
				System.out.println("check로그인완");
				HttpSession session = request.getSession();
				session.setAttribute("login", true);
				session.setAttribute("name", check.get(0).getEname());
				session.setAttribute("level", check.get(0).getEmp_level());
			}
			response.sendRedirect("emp");
		} 
			
	
}
