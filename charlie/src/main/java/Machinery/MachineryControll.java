package Machinery;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Machinery.MachineryDTO;
import Machinery.MachineryService;

@WebServlet("/machinery")
public class MachineryControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("/machinery [doGet] 실행");

		response.setContentType("text/html; charset=utf-8;");

		String cmd = request.getParameter("cmd");

		if ("".equals(cmd) || cmd == null) { list(request, response); return; }

		switch (cmd) { // get cmd > method

		case "insertPage": request.getRequestDispatcher("WEB-INF/views/machinery/machinery_insert.jsp").forward(request, response); return;
		case "insert": insert(request, response); return;
		case "list": list(request, response); return;
		case "detail": detail(request, response, "detail"); return;
		case "modify": detail(request, response, "modify"); return;
		case "update": update(request, response); return;
		case "delete": delete(request, response); return;
		
		default: System.out.println("잘못된 접근입니다"); return;
		
		}

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		// setDTO > Service > DAO
		MachineryService service = new MachineryService();
		service.insertDB(setDTO(request));

		// list page
		response.sendRedirect("machinery?cmd=list");
	}

	// list
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8;");

		MachineryService service = new MachineryService();
		List list = (List) service.selectAll();

		System.out.println( "/ctrl list : " + list );
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/views/machinery/machinery_list.jsp").forward(request, response);

	}
	
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MachineryService service = new MachineryService();
		service.deleteDB(setDTO(request));
		
		response.sendRedirect("machinery?cmd=list");
	}
	
	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MachineryService service = new MachineryService();
		service.modifyDB(setDTO(request));
		
		response.sendRedirect("machinery?cmd=list");
	}
	

	// detail
	protected void detail(HttpServletRequest request, HttpServletResponse response, String selector)
			throws ServletException, IOException {

		System.out.println("/detail 실행");

		// Service > DAO - selectOne
		MachineryService service = new MachineryService();
		List machineryInfo = service.selectDB(setDTO(request), "num");

		// Forward > DTO
		request.setAttribute("machineryInfo", machineryInfo);
		
		if("detail".equals(selector)) { // 상세 페이지
			
			request.getRequestDispatcher("WEB-INF/views/machinery/machinery_detail.jsp")
				.forward(request, response);
		
		} else { // 수정 페이지
			
			request.getRequestDispatcher("WEB-INF/views/machinery/machinery_modify.jsp")
				.forward(request, response);
			
		}
		
	}

	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
	protected MachineryDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		MachineryDTO machineryDTO = new MachineryDTO();
		
		int machinery_num = -1; String machinery_type = null; String machinery_status = null;
		String error_sign = null;String m_action = null; int mdm_num = -1; 
		
		if (request.getParameter("machinery_num") != null 
				&& !("".equals(request.getParameter("machinery_num")))) {
			
			machinery_num = Integer.parseInt(request.getParameter("machinery_num"));
		
			System.out.println( "/set machinery machinery_num : " + machinery_num );
		} 
		
		if (request.getParameter("machinery_type") != null 
				&& !("".equals(request.getParameter("machinery_type")))) {
			
			machinery_type = request.getParameter("machinery_type");
			
			System.out.println( "/set machinery machinery_type : " + machinery_type );
		} 
		if (request.getParameter("machinery_status") != null 
				&& !("".equals(request.getParameter("machinery_status")))) {
			
			machinery_status = request.getParameter("machinery_status");
			
			System.out.println( "/set machinery machinery_status : " + machinery_status );
		} 
		if (request.getParameter("error_sign") != null 
				&& !("".equals(request.getParameter("error_sign")))) {
			
			error_sign = request.getParameter("error_sign");
			
			System.out.println( "/set machinery error_sign : " + error_sign );
		} 
		
		if (request.getParameter("m_action") != null 
				&& !("".equals(request.getParameter("m_action")))) {
			
			m_action = request.getParameter("m_action");
			
			System.out.println( "/set machinery m_action : " + m_action );
		} 
		
		if (request.getParameter("mdm_num") != null 
				&& !("".equals(request.getParameter("mdm_num")))) {
			
			mdm_num = Integer.parseInt(request.getParameter("mdm_num"));
			
			System.out.println( "/set machinery mdm_num : " + mdm_num );
		} 
	
		
		machineryDTO.setMachinery_num(machinery_num);
		machineryDTO.setMachinery_type(machinery_type);
		machineryDTO.setMachinery_status(machinery_status);
		machineryDTO.setError_sign(error_sign);
		machineryDTO.setM_action(m_action);
		machineryDTO.setMdm_num(mdm_num);
		
		return machineryDTO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
