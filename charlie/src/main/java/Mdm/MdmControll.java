package Mdm;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileLibrary.CommonDTO;

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

		Map map = service.selectDB(setDTO(request), setCommonDTO(request, ""));
		System.out.println("/ctrl map : " + map);
		
		request.setAttribute("map", map);
		request.setAttribute("servletName", "mdm");
		
		
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
		MdmDTO mdmDTO = service.selectOne(setDTO(request), setCommonDTO(request, ""));
		// Forward > DTO
		request.setAttribute("mdmDTO", mdmDTO);
		
		if("detail".equals(selector)) {
			
			request.getRequestDispatcher("WEB-INF/views/mdm/mdm_detail.jsp")
				.forward(request, response);
		
		} else {
			
			request.getRequestDispatcher("WEB-INF/views/mdm/mdm_modify.jsp")
				.forward(request, response);
			
		}
		
	}
	
	// search
	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		MdmService service = new MdmService();
		// 검색 내용받음
		
		Map map = service.selectDB(setDTO(request), setCommonDTO(request, "search"));

		request.setAttribute("map", map);
		request.getRequestDispatcher("WEB-INF/views/mdm/mdm_list.jsp").forward(request, response);

	}
	

	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
	protected MdmDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		MdmDTO mdmDTO = new MdmDTO();
		
		
		int mdm_num = -1; int price= -1;  int quantity= -1; 
		Date exp_date = null;
		
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
		
		if (request.getParameter("quantity") != null 
				&& !("".equals(request.getParameter("quantity")))) {
			
			quantity = Integer.parseInt(request.getParameter("quantity"));
			
			System.out.println( "/set mdm quantity : " + quantity );
		} 
		
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String unit = request.getParameter("unit");
		String type = request.getParameter("type");
		String canUse = request.getParameter("canUse");
		
		String exp_dateStr = request.getParameter("exp_date");
		if (exp_dateStr != null && !exp_dateStr.isEmpty()) {
			   exp_date = java.sql.Date.valueOf(exp_dateStr);
			   mdmDTO.setExp_date(exp_date);
			}
		
		mdmDTO.setMdm_num(mdm_num);
		mdmDTO.setCode(code);
		mdmDTO.setName(name);
		mdmDTO.setQuantity(quantity);
		mdmDTO.setUnit(unit);
		mdmDTO.setType(type);
		mdmDTO.setPrice(price);
		mdmDTO.setCanUse(canUse);
		
		System.out.println("/ctrl setDTO" + mdmDTO);
		return mdmDTO;
		
	}
	
	// 공통 변수
	protected CommonDTO setCommonDTO(HttpServletRequest request, String cmd)
			throws ServletException, IOException {
		
		CommonDTO commonDTO = new CommonDTO();
		
		// 검색 기능 [ search_content ]
		if("search".equals(cmd)) {
			commonDTO.setSelector(request.getParameter("search_select"));
			commonDTO.setSearch(request.getParameter("search_content"));
			System.out.println(commonDTO.getSelector());
			System.out.println(commonDTO.getSearch());
		}
		
		// orderBy [ column ]
		String orderBy = request.getParameter("orderBy");
		commonDTO.setOrderBy(orderBy);
		
		
		// paging 
		int size= 10, page= 1;
		
		try {
			size = Integer.parseInt(request.getParameter("size"));
		} catch(Exception e) { }
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) { }
		
		commonDTO.setSize(size);
		commonDTO.setPage(page);
		
		return commonDTO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
