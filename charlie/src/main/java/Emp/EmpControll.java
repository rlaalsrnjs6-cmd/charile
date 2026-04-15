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
		System.out.println("emp컨트롤마지막:"+list);
		request.setAttribute("emp", list);
		if("detail".equals(mod)) {
			request.getRequestDispatcher("/WEB-INF/views/emp/empDetail.jsp").forward(request, response);
		} else if("up".equals(mod)) {
			request.getRequestDispatcher("/WEB-INF/views/emp/empUp.jsp").forward(request, response);
		} else if("delete".equals(mod)) {
			empDelete(request,response);
		} else {
			System.out.println("emp리스트확인용");
			request.getRequestDispatcher("/WEB-INF/views/emp/empList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		if("add".equals(mod)) {
			empAdd(request,response);
		} else if("up".equals(mod)) {
			empUp(request,response);
		} else if("delete".equals(mod)) {
			empDelete(request,response);
		}
	}
	
	protected void empUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sempno = request.getParameter("empno");
		String ename = request.getParameter("ename");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String semp_level = request.getParameter("emp_level");
		String tel = request.getParameter("tel");
		String ssal = request.getParameter("sal");
		String addr = request.getParameter("addr");
		String sbirthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		String mod = request.getParameter("mod");
		Date birthday = Date.valueOf(sbirthday);
		int empno = Integer.parseInt(sempno);
		int emp_level = Integer.parseInt(semp_level);
		int sal = Integer.parseInt(ssal);
		System.out.println("empUp:"+status);
	
		EmpDTO empDTO = new EmpDTO();
		empDTO.setEmpno(empno);
		empDTO.setEname(ename);
		empDTO.setId(id);
		empDTO.setPw(pw);
		empDTO.setEmp_level(emp_level);
		empDTO.setTel(tel);
		empDTO.setSal(sal);
		empDTO.setAddr(addr);
		empDTO.setBirthday(birthday);
		empDTO.setEmail(email);
		empDTO.setStatus(status);
		empDTO.setMod(mod);
		EmpService service = new EmpService();
		service.empService(empDTO);
		response.sendRedirect("emp");
		
	}

	protected void empAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		System.out.println("emp입장");
		try {
			String mod = request.getParameter("mod");
			String ename = request.getParameter("ename");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String tel = request.getParameter("tel");
			String ssal = request.getParameter("sal");
			String addr = request.getParameter("addr");
			String sbirthday = request.getParameter("birthday");
			String email1 = request.getParameter("email1");
			String email2 = request.getParameter("email2");
			String email3 = request.getParameter("email3");
			
			String email = email1+email2+email3; 
			
			System.out.println("이메일"+email);
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

				if (id == null || !id.matches("^[a-z][a-z0-9]{4,14}$")) {
					System.out.println("컨트롤: member_id오류");
					return;
				}
				if (pw == null
						|| !pw.trim().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,20}$")) {
					System.out.println("컨트롤: member_pw오류");
					return;
				}
				if (email == null || !email.trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
					System.out.println("컨트롤: member_email오류");
					return;
				}
			}
			
			EmpDTO dto = new EmpDTO();
			// 회원가입
			dto.setMod(mod);
			EmpService service = new EmpService();
			if ("add".equals(mod)) {
				System.out.println("회원가입시작");
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
			System.out.println("인서트확인용");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected void empDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sempno = request.getParameter("empno");
		String mod = request.getParameter("mod");
		int empno = Integer.parseInt(sempno);
		EmpDTO empDTO = new EmpDTO();
		empDTO.setEmpno(empno);
		empDTO.setMod(mod);
		EmpService service = new EmpService();
		System.out.println("emp딜리트마지막: " + service.empService(empDTO));
		response.sendRedirect("emp");
	}
	
}