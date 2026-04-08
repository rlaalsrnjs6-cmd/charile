package Emp;

import java.io.IOException;
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sempno = request.getParameter("empno");
		EmpService service= new EmpService();
		int empno = -1;
		if(sempno != null) {
			empno = Integer.parseInt(sempno);
		}
		EmpDTO DTO = new EmpDTO();
		DTO.setEmpno(empno);
		List list = service.select(DTO);
		System.out.println("sadsadsad"); 
		request.setAttribute("emp", list);
		request.getRequestDispatcher("charlie/WEB-INF/views/Emp.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String mod = request.getParameter("mod");
			String sempno = request.getParameter("empno");
			String ename = request.getParameter("ename");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String slevel = request.getParameter("level");
			String tel = request.getParameter("tel");
			String ssal = request.getParameter("sal");
			String addr = request.getParameter("addr");
			String sbirthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			int empno = -1;
			int level = -1;
			int sal = 3;
			Date birthday = null;
			if("add".equals(mod)){
			birthday = Date.valueOf(sbirthday);
			empno = Integer.parseInt(sempno);
			level = Integer.parseInt(slevel);
			sal = Integer.parseInt(ssal);
			}
			EmpDTO dto = new EmpDTO();
			if("add".equals(mod)){
			dto.setEmpno(empno);
			dto.setEname(ename);
			dto.setId(id);
			dto.setPw(pw);
			dto.setLevel(level);
			dto.setTel(tel);
			dto.setSal(sal);
			dto.setAddr(addr);
			dto.setBirthday(birthday);
			dto.setEmail(email);
			dto.setMod(mod);
			}
			EmpService service = new EmpService();
			service.insert(dto);
			
			response.sendRedirect("emp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
