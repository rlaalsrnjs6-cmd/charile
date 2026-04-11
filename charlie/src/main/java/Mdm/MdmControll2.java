package Mdm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileLibrary.TestDTO;

@WebServlet("/mdm2")
public class MdmControll2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("/mdm [doGet] 실행");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String cmd = request.getParameter("cmd");

		if ("".equals(cmd) || cmd == null) { list(request, response); return; }

		switch (cmd) {

		case "insertPage": request.getRequestDispatcher("WEB-INF/views/mdm/mdm_insert2.jsp").forward(request, response); return;
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
		MdmServiceTest service = new MdmServiceTest();
		service.insertDB(setDTO(request));

		// list page
		response.sendRedirect("mdm2?cmd=list");
	}

	// list
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		MdmServiceTest service = new MdmServiceTest();

		MdmDTO mdmDTO = null;
		List list = (List) service.selectAll();

		System.out.println("/ctrl list : " + list);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/views/mdm/mdm_list2.jsp").forward(request, response);

	}
	
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MdmServiceTest service = new MdmServiceTest();
		service.deleteDB(setDTO(request));
		
		response.sendRedirect("mdm2?cmd=list");
	}
	
	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MdmServiceTest service = new MdmServiceTest();
		service.modifyDB(setDTO(request));
		
		response.sendRedirect("mdm2?cmd=list");
	}
	

	// detail
	protected void detail(HttpServletRequest request, HttpServletResponse response
			, String selector)
			throws ServletException, IOException {

		System.out.println("/detail 실행");
		// Service > DAO - selectOne
		MdmServiceTest service = new MdmServiceTest();
		Map mdmInfo = service.selectDB(setDTO(request), setTestDTO(request, "num"));
		//!!!!!!!!!!!!!!!!!!!!
		// Forward > DTO
		request.setAttribute("mdmInfo", mdmInfo);
		
		if("detail".equals(selector)) {
			
			request.getRequestDispatcher("WEB-INF/views/mdm/mdm_detail2.jsp")
				.forward(request, response);
		
		} else {
			
			request.getRequestDispatcher("WEB-INF/views/mdm/mdm_modify2.jsp")
				.forward(request, response);
			
		}
		
	}
	
	
	////////////
	//!!!!!!!!!!!!!!!!
	// search
	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		MdmServiceTest service = new MdmServiceTest();
		String search_select = request.getParameter("search_select");
		Map map = service.selectDB(setDTO(request), setTestDTO(request, search_select));

		request.setAttribute("map", map);
		request.getRequestDispatcher("WEB-INF/views/mdm/mdm_list2.jsp").forward(request, response);

	}
	
	////////////
	
	
	
	

	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
	protected MdmDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		MdmDTO mdmDTO = new MdmDTO();
		
		
		int mdm_num = -1; int price= -1; 
		
		if (request.getParameter("mdm_num") != null 
				&& !("".equals(request.getParameter("mdm_num")))) {
			
			mdm_num = Integer.parseInt(request.getParameter("mdm_num"));
		
			System.out.println( "/set mdm mdm_num : " + mdm_num );
		} 
		
		if (request.getParameter("price") != null 
				&& !("".equals(request.getParameter("price")))) {
			
			price = Integer.parseInt(request.getParameter("price"));
		
			System.out.println( "/set mdm price : " + price );
		} 
		
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String unit = request.getParameter("unit");
		String type = request.getParameter("type");
		
		mdmDTO.setMdm_num(mdm_num);
		mdmDTO.setCode(code);
		mdmDTO.setName(name);
		mdmDTO.setUnit(unit);
		mdmDTO.setType(type);
		mdmDTO.setPrice(price);
		
		return mdmDTO;
		
	}
	
	protected TestDTO setTestDTO(HttpServletRequest request, String cmd)
			throws ServletException, IOException {
		
		TestDTO testDTO = new TestDTO();
		
		// 검색 기능
		String search_content = request.getParameter("search_content");
		testDTO.setSearch_content(search_content);
		
		String orderBy = request.getParameter("orderBy");
		testDTO.setOrderBy(orderBy);
		
		testDTO.setSelector(cmd);
		
		return testDTO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
