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

@WebServlet("/check")
public class LoginCheck extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String mod = request.getParameter("mod");
		String status = request.getParameter("status");
		EmpDTO empDTO = new EmpDTO();
		empDTO.setId(id);
		empDTO.setPw(pw);
		empDTO.setMod(mod);
		EmpService service = new EmpService();
		List<EmpDTO> check = service.select(empDTO);
		
		if (check.size() == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 회원입니다');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return;
			} else if("N".equals(check.get(0).status) || check.get(0).status == null) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('퇴사한 사원입니다');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return;
			} else {
				System.out.println("check로그인완");
				HttpSession session = request.getSession();
				session.setAttribute("login", true);
				session.setAttribute("empno", check.get(0).getEmpno());
				session.setAttribute("name", check.get(0).getEname());
				session.setAttribute("level", check.get(0).getEmp_level());
				response.sendRedirect("emp");
			}
		} 
			
	
}
