package Mdm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mdm")
public class MdmControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("/mdm [doGet] 실행");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String cmd = request.getParameter("cmd");

		if ("".equals(cmd) || cmd == null) { list(request, response); return; }

		switch (cmd) {

		case "insertPage": request.getRequestDispatcher("WEB-INF/views/mdm/mdm_insert.jsp").forward(request, response); return;
		case "insert": insert(request, response); return;
		case "list": list(request, response); return;
		case "detail": detail(request, response, "detail"); return;
		case "modify": detail(request, response, "modify"); return;
		case "update": update(request, response); return;
		case "delete": delete(request, response); return;
		
		case "search": search(request, response); return;
		
		default: System.out.println("잘못된 접근입니다"); return;
		
		}

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		// setDTO > Service > DAO
		MdmService service = new MdmService();
		service.insertDB(setDTO(request));

		// list page
		response.sendRedirect("mdm?cmd=list");
	}

	// list
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		MdmService service = new MdmService();

		MdmDTO mdmDTO = null;
		List list = (List) service.selectDB(mdmDTO, "all");

		System.out.println("/ctrl list : " + list);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/views/mdm/mdm_list.jsp").forward(request, response);

	}
	
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MdmService service = new MdmService();
		service.deleteDB(setDTO(request));
		
		response.sendRedirect("mdm?cmd=list");
	}
	
	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MdmService service = new MdmService();
		service.modifyDB(setDTO(request));
		
		response.sendRedirect("mdm?cmd=list");
	}
	

	// detail
	protected void detail(HttpServletRequest request, HttpServletResponse response
			, String selector)
			throws ServletException, IOException {

		System.out.println("/detail 실행");

		// Service > DAO - selectOne
		MdmService service = new MdmService();
		List mdmInfo = service.selectDB(setDTO(request), "num");

		// Forward > DTO
		request.setAttribute("mdmInfo", mdmInfo);
		
		if("detail".equals(selector)) {
			
			request.getRequestDispatcher("WEB-INF/views/mdm/mdm_detail.jsp")
				.forward(request, response);
		
		} else {
			
			request.getRequestDispatcher("WEB-INF/views/mdm/mdm_modify.jsp")
				.forward(request, response);
			
		}
		
	}
	
	
	////////////
	
	// search
	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		MdmService service = new MdmService();
		String search_select = request.getParameter("search_select");
		List list = service.selectDB(setDTO(request), search_select);

		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/views/mdm/mdm_list.jsp").forward(request, response);

	}
	
	////////////
	
	
	
	

	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
	protected MdmDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		MdmDTO mdmDTO = new MdmDTO();
		
		int mdm_num = -1; String code = null; String name = null; 
		String unit=null; String type=null; int price= -1; String search_content = null;
		
		if (request.getParameter("mdm_num") != null 
				&& !("".equals(request.getParameter("mdm_num")))) {
			
			mdm_num = Integer.parseInt(request.getParameter("mdm_num"));
		
			System.out.println( "/set mdm mdm_num : " + mdm_num );
		} 
		
		if (request.getParameter("code") != null 
				&& !("".equals(request.getParameter("code")))){
			
			code = request.getParameter("code");
		}
		
		if (request.getParameter("name") != null 
				&& !("".equals(request.getParameter("name")))){
			
			name = request.getParameter("name");
		}
		
		if (request.getParameter("unit") != null 
				&& !("".equals(request.getParameter("unit")))){
			
			unit = request.getParameter("unit");
		}
		
		if (request.getParameter("type") != null 
				&& !("".equals(request.getParameter("type")))){
			
			type = request.getParameter("type");
			
			System.out.println( "/set mdm type : " + type );
		}
		
		if (request.getParameter("price") != null 
				&& !("".equals(request.getParameter("price")))) {
			
			price = Integer.parseInt(request.getParameter("price"));
		
			System.out.println( "/set mdm price : " + price );
		} 
		
		mdmDTO.setMdm_num(mdm_num);
		mdmDTO.setCode(code);
		mdmDTO.setName(name);
		mdmDTO.setUnit(unit);
		mdmDTO.setType(type);
		mdmDTO.setPrice(price);
		
		// 검색 기능
		if (request.getParameter("search_content") != null 
				&& !("".equals(request.getParameter("search_content")))) {
			
				search_content = request.getParameter("search_content");
		} else {
			System.out.println("검색어를 입력하세요");
		}
		
		mdmDTO.setSearch(search_content);
		
		return mdmDTO;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
