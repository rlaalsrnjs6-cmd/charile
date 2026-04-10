package Bom;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bom.BomDTO;
import Bom.BomService;


@WebServlet("/bom")
public class BomControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("/bom [doGet] 실행");

		response.setContentType("text/html; charset=utf-8;");

		String cmd = request.getParameter("cmd");

		if ("".equals(cmd) || cmd == null) { list(request, response); return; }

		switch (cmd) {

		case "insertPage": request.getRequestDispatcher("WEB-INF/views/bom/bom_insert.jsp").forward(request, response); return;
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
		BomService service = new BomService();
		service.insertDB(setDTO(request));

		// list page
		response.sendRedirect("bom?cmd=list");
	}

	// list
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8;");

		BomService service = new BomService();
		List list = (List) service.selectAll();

		System.out.println( "/ctrl list : " + list );
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/views/bom/bom_list.jsp").forward(request, response);

	}
	
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BomService service = new BomService();
		service.deleteDB(setDTO(request));
		
		response.sendRedirect("bom?cmd=list");
	}
	
	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BomService service = new BomService();
		service.modifyDB(setDTO(request));
		
		response.sendRedirect("bom?cmd=list");
	}
	

	// detail
	protected void detail(HttpServletRequest request, HttpServletResponse response, String selector)
			throws ServletException, IOException {

		System.out.println("/detail 실행");

		// Service > DAO - selectOne
		BomService service = new BomService();
		List bomInfo = service.selectDB(setDTO(request), "num");

		// Forward > DTO
		request.setAttribute("bomInfo", bomInfo);
		
		if("detail".equals(selector)) { // 상세 페이지
			
			request.getRequestDispatcher("WEB-INF/views/bom/bom_detail.jsp")
				.forward(request, response);
		
		} else { // 수정 페이지
			
			request.getRequestDispatcher("WEB-INF/views/bom/bom_modify.jsp")
				.forward(request, response);
			
		}
		
	}

	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
	protected BomDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		BomDTO bomDTO = new BomDTO();
		
		int bom_num = -1; int required_weight= -1; int mdm_num = -1;
		
		if (request.getParameter("bom_num") != null 
				&& !("".equals(request.getParameter("bom_num")))) {
			
			bom_num = Integer.parseInt(request.getParameter("bom_num"));
		
			System.out.println( "/set bom bom_num : " + bom_num );
		} 
		
		if (request.getParameter("required_weight") != null 
				&& !("".equals(request.getParameter("required_weight")))) {
			
			required_weight = Integer.parseInt(request.getParameter("required_weight"));
			
			System.out.println( "/set bom required_weight : " + required_weight );
		} 
		
		if (request.getParameter("mdm_num") != null 
				&& !("".equals(request.getParameter("mdm_num")))) {
			
			mdm_num = Integer.parseInt(request.getParameter("mdm_num"));
			
			System.out.println( "/set bom mdm_num : " + mdm_num );
		} 
	
		
		bomDTO.setBom_num(bom_num);
		bomDTO.setRequired_weight(required_weight);
		bomDTO.setMdm_num(mdm_num);
		
		return bomDTO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
