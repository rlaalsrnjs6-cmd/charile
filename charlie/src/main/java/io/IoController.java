package io;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bom.BomService;
import fileLibrary.CommonDTO;

@WebServlet("/io")
public class IoController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("/io [doGet] 실행");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String cmd = request.getParameter("cmd");

		if ("".equals(cmd) || cmd == null) { list(request, response); return; }

		switch (cmd) {

		case "insertPage":  insertPage(request, response); return;
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

	protected void insertPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		IoService service = new IoService();
		List list = service.selectJoinInfo();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/views/io/io_insert.jsp")
														.forward(request, response);
		
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		// setDTO > Service > DAO
		IoService service = new IoService();
		service.insertDB(setDTO(request));

		
		// list page
		response.sendRedirect("io?cmd=list");
	}

	// list
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		IoService service = new IoService();

		Map map = service.selectDB(setDTO(request), setCommonDTO(request, ""));
		System.out.println("/ctrl map : " + map);
		
		request.setAttribute("map", map);
		request.setAttribute("servletName", "io");
		
		
		request.getRequestDispatcher("WEB-INF/views/io/io_list.jsp").forward(request, response);

	}
	
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IoService service = new IoService();
		service.deleteDB(setDTO(request));
		
		response.sendRedirect("io?cmd=list");
	}
	
	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IoService service = new IoService();
		service.modifyDB(setDTO(request));
		
		response.sendRedirect("io?cmd=list");
	}
	

	// detail
	protected void detail(HttpServletRequest request, HttpServletResponse response
			, String selector)
			throws ServletException, IOException {

		System.out.println("/detail 실행");
		// Service > DAO - selectOne
		IoService service = new IoService();
		IoDTO ioDTO = service.selectOne(setDTO(request), setCommonDTO(request, ""));
		// Forward > DTO
		request.setAttribute("ioDTO", ioDTO);
		
		if("detail".equals(selector)) {
			
			request.getRequestDispatcher("WEB-INF/views/io/io_detail.jsp")
				.forward(request, response);
		
		} else {
			
			request.getRequestDispatcher("WEB-INF/views/io/io_modify.jsp")
				.forward(request, response);
			
		}
		
	}
	
	// search
		protected void search(HttpServletRequest request, HttpServletResponse response)
								throws ServletException, IOException {

			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8;");
			
			IoService service = new IoService();
			// 검색 내용받음
			
			// 검색 내용받음
			CommonDTO commonDTO = setCommonDTO(request, "search");
									
			HttpSession session = request.getSession();
			session.setAttribute("ioCommonDTO", commonDTO);
						
					
			Map map = service.selectDB(setDTO(request), setCommonDTO(request, "search"));

			List list = service.selectJoinInfo();
			request.setAttribute("list", list);
			
			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/views/io/io_list.jsp").forward(request, response);

			}
	
	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
	protected IoDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		IoDTO ioDTO = new IoDTO();
		
		int io_num = -1; int quantity = -1;  int mdm_num = -1;
		Date exp_date = null;
		
		if (request.getParameter("io_num") != null 
				&& !("".equals(request.getParameter("io_num")))) {
			
			io_num = Integer.parseInt(request.getParameter("io_num"));
		
			System.out.println( "/set io io_num : " + io_num );
		} 
		
		if (request.getParameter("quantity") != null 
				&& !("".equals(request.getParameter("quantity")))) {
			
			quantity = Integer.parseInt(request.getParameter("quantity"));
			
			System.out.println( "/set io quantity : " + quantity );
		} 
		
		if (request.getParameter("mdm_num") != null 
				&& !("".equals(request.getParameter("mdm_num")))) {
			
			mdm_num = Integer.parseInt(request.getParameter("mdm_num"));
		
			System.out.println( "/set io mdm_num : " + mdm_num );
		} 
		
		// io
		String io_type = request.getParameter("io_type");
		String storage_sec = request.getParameter("storage_sec");
		
		// mdm
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String unit = request.getParameter("unit");
		
		String exp_dateStr = request.getParameter("exp_date");
		if (exp_dateStr != null && !exp_dateStr.isEmpty()) {
			   exp_date = java.sql.Date.valueOf(exp_dateStr);
			   ioDTO.setExp_date(exp_date);
			}
		
		// io
		ioDTO.setIo_num(io_num);
		ioDTO.setQuantity(quantity);
		ioDTO.setIo_type(io_type);
		ioDTO.setStorage_sec(storage_sec);
		ioDTO.setExp_date(exp_date);
		
		// mdm
		ioDTO.setMdm_num(mdm_num);
		ioDTO.setName(name);
		ioDTO.setCode(code);
		ioDTO.setUnit(unit);
		
		System.out.println("/ctrl setDTO" + ioDTO);
		return ioDTO;
		
	}
	
	// 공통 변수
	protected CommonDTO setCommonDTO(HttpServletRequest request, String cmd)
			throws ServletException, IOException {
		
		CommonDTO commonDTO = new CommonDTO();
		
		// 검색 기능 [ search_content ]
		if("search".equals(cmd)) {
			commonDTO.setSelector(request.getParameter("search_select"));
			commonDTO.setSearch(request.getParameter("search_content"));

			String where2 = request.getParameter("selectName");
			if (where2!=null && !"".equals(where2)) { 
				commonDTO.setWhere2("AND type = '" + where2 + "'") ; 
				}
			
			String where3 = request.getParameter("selectChk");
			if (where3!=null && !"".equals(where3)) { 
				commonDTO.setWhere3("AND canUse = '" + where3 + "'") ; 
			}
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
