package Emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/emp")
public class EmpControll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		String sempno = request.getParameter("empno");
		String mod = request.getParameter("mod");
		EmpService service = new EmpService();
		int empno = -1;
		if (sempno != null) {
			empno = Integer.parseInt(sempno);
		}
		EmpDTO DTO = new EmpDTO();
		DTO.setEmpno(empno);

		DTO.setMod(mod);
		List list = service.select(DTO);

		request.setAttribute("emp", list);
		request.getRequestDispatcher("/WEB-INF/views/emp/main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String mod = request.getParameter("mod");
			String sempno = request.getParameter("empno");
			String ename = request.getParameter("ename");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String tel = request.getParameter("tel");
			String ssal = request.getParameter("sal");
			String addr = request.getParameter("addr");
			String sbirthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			System.out.println("생년월일"+sbirthday);
			if(sbirthday==null || sbirthday.trim().isEmpty()) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('생년월일을 입력하세요')");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return;
			}
			int empno = -1;
			Date birthday = null;
			if ("add".equals(mod)) {
				birthday = Date.valueOf(sbirthday);
				empno = Integer.parseInt(sempno);

				if (id == null || !id.matches("^[a-z][a-z0-9]{4,14}$")) {
					System.out.println("컨트롤: member_id오류");
					return;
				}
				if (pw == null
						|| !pw.trim().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,20}$")) {
					System.out.println("컨트롤: member_pw오류");
					return;
				}
				if (email == null || !email.trim().matches("^[a-z][a-z0-9]{4,14}$")) {
					System.out.println("컨트롤: member_email오류");
					return;
				}
			}
			
			EmpDTO dto = new EmpDTO();
			// 회원가입
			dto.setMod(mod);
			EmpService service = new EmpService();
			if ("add".equals(mod)) {
				dto.setEmpno(empno);
				dto.setEname(ename);
				dto.setId(id);
				dto.setPw(pw);
				dto.setTel(tel);
				dto.setAddr(addr);
				dto.setBirthday(birthday);
				dto.setEmail(email);
				if (service.insert(dto) == -1) {
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('중복된 id입니다');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
					return;
				}
			}
			response.sendRedirect("charlie");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}