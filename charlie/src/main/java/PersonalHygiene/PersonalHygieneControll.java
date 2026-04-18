package PersonalHygiene;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Emp.EmpDTO;
import Emp.EmpService;
import fileLibrary.CommonDTO;

@WebServlet("/hygiene")
public class PersonalHygieneControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sph_num = request.getParameter("ph_num");
		String ssize = request.getParameter("size");
		String spage = request.getParameter("page");
		String mod = request.getParameter("mod");
		int size = 10;
		int page = 1;
		if (ssize != null && spage != null) {
			size = Integer.parseInt(ssize);
			page = Integer.parseInt(spage);
		}
		int ph_num = -1;
		if (sph_num != null) {
			ph_num = Integer.parseInt(sph_num);
		}
		PersonalHygieneDTO hygieneDTO = new PersonalHygieneDTO();
		CommonDTO pageing = new CommonDTO();
		pageing.setSize(size);
		pageing.setPage(page);
		hygieneDTO.setPh_num(ph_num);
		hygieneDTO.setMod(mod);
		PersonalHygieneService service = new PersonalHygieneService();
		Map list = service.select(hygieneDTO, pageing);
		System.out.println("hygieneCon: " + list);
		request.setAttribute("map", list);
		if ("detail".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/hygiene/hygieneDetail.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			HygieneDelete(request, response);
			return;
		} else {
			EmpDTO empDTO = new EmpDTO();
			EmpService empSV = new EmpService();
			List<EmpDTO> emp = empSV.selectall(empDTO);
			List<PersonalHygieneDTO> hygiene = service.selectall(hygieneDTO);
			System.out.println("emp컨트롤all"+emp);
			request.setAttribute("emp", emp);
			request.setAttribute("hygiene", hygiene);
			if ("add".equals(mod)) {
				request.getRequestDispatcher("WEB-INF/views/hygiene/hygieneAdd.jsp").forward(request, response);
				return;
			} else if ("up".equals(mod)) {
				request.getRequestDispatcher("WEB-INF/views/hygiene/hygieneUp.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("WEB-INF/views/hygiene/hygieneList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");

		if ("up".equals(mod)) {
			HygieneUp(request, response);
		} else if ("add".equals(mod)) {
			HygieneAdd(request, response);
		} else if ("delete".equals(mod)) {
			HygieneDelete(request, response);
		}
	}

	protected void HygieneUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		System.out.println("이게 갑자기 왜 실행되노");
		String sph_num = request.getParameter("ph_num");
		String sbody_temper = request.getParameter("body_temper");
		String washed = request.getParameter("washed");
		String memo = request.getParameter("memo");
		String upervisor_chk = request.getParameter("supervisor_chk");
		String sempno = request.getParameter("empno");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
		int ph_num = Integer.parseInt(sph_num);
		int empno = Integer.parseInt(sempno);
		double body_temper = Double.parseDouble(sbody_temper);

		PersonalHygieneDTO hygieneDTO = new PersonalHygieneDTO();
		hygieneDTO.setPh_num(ph_num);
		hygieneDTO.setBody_temper(body_temper);
		hygieneDTO.setWashed(washed);
		hygieneDTO.setMemo(memo);
		hygieneDTO.setSupervisor_chk(upervisor_chk);
		hygieneDTO.setEmpno(empno);
		hygieneDTO.setMod(mod);

		PersonalHygieneService service = new PersonalHygieneService();
		service.hygieneService(hygieneDTO);
		response.sendRedirect("hygiene");
	}

	protected void HygieneAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sbody_temper = request.getParameter("body_temper");
		String washed = request.getParameter("washed");
		String memo = request.getParameter("memo");
		String upervisor_chk = request.getParameter("supervisor_chk");
		String sempno = request.getParameter("empno");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
		int empno = Integer.parseInt(sempno);
		double body_temper = Double.parseDouble(sbody_temper);

		PersonalHygieneDTO hygieneDTO = new PersonalHygieneDTO();
		hygieneDTO.setBody_temper(body_temper);
		hygieneDTO.setWashed(washed);
		hygieneDTO.setMemo(memo);
		hygieneDTO.setSupervisor_chk(upervisor_chk);
		hygieneDTO.setEmpno(empno);
		hygieneDTO.setMod(mod);

		PersonalHygieneService service = new PersonalHygieneService();
		service.hygieneService(hygieneDTO);
		response.sendRedirect("hygiene");
	}

	protected void HygieneDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sph_num = request.getParameter("ph_num");
		String mod = request.getParameter("mod");
		int ph_num = Integer.parseInt(sph_num);
		PersonalHygieneDTO hygieneDTO = new PersonalHygieneDTO();
		hygieneDTO.setPh_num(ph_num);
		hygieneDTO.setMod(mod);
		PersonalHygieneService service = new PersonalHygieneService();
		service.hygieneService(hygieneDTO);
		response.sendRedirect("hygiene");
	}

}
