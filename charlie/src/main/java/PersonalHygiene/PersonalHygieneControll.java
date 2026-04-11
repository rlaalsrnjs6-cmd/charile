package PersonalHygiene;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WorkOrder.WorkOrderDTO;
import WorkOrder.WorkOrderService;

@WebServlet("/hygiene")
public class PersonalHygieneControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sph_num = request.getParameter("ph_num");
		String detail = request.getParameter("detail");
		String mod = request.getParameter("mod");
		System.out.println("hygiene:"+sph_num);
		int ph_num = -1;
		if (sph_num != null) {
			ph_num = Integer.parseInt(sph_num);
		}
		PersonalHygieneDTO hygieneDTO = new PersonalHygieneDTO();
		hygieneDTO.setPh_num(ph_num);
		PersonalHygieneService service = new PersonalHygieneService();
		List<PersonalHygieneDTO> list = service.select(hygieneDTO);
		System.out.println("hygieneCon: " + list);
		System.out.println(list.get(0).getPh_num());
		request.setAttribute("hygiene", list);
		if(detail!=null) {
			request.getRequestDispatcher("hygieneDetail.jsp").forward(request, response);
		}else if("up".equals(mod)){
			request.getRequestDispatcher("hygieneUp.jsp").forward(request, response);
		}else if("delete".equals(mod)){
			HygieneDelete(request, response);
		}else {
			request.getRequestDispatcher("hygiene.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");

		if ("write".equals(mod)) {
			Write(request, response);
		} else if ("up".equals(mod)) {
			HygieneUp(request, response);
		}else if ("add".equals(mod)) {
			HygieneUp(request, response);
		}else if ("delete".equals(mod)) {
			HygieneDelete(request, response);
		}
	}

	protected void Write(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("hygieneAdd.jsp");

	}

	protected void HygieneUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
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
	
	protected void HygieneDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sph_num = request.getParameter("ph_num");
		String mod = request.getParameter("mod");
		int ph_num = Integer.parseInt(sph_num);
		System.out.println("딜리트"+ph_num);
		System.out.println("딜리트"+mod);
		PersonalHygieneDTO hygieneDTO = new PersonalHygieneDTO();
		hygieneDTO.setPh_num(ph_num);
		hygieneDTO.setMod(mod);
		PersonalHygieneService service = new PersonalHygieneService();
		service.hygieneService(hygieneDTO);
		response.sendRedirect("hygiene");
	}

}
