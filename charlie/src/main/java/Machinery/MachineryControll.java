package Machinery;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileLibrary.CommonDTO;

@WebServlet("/machinery")
public class MachineryControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("/machinery [doGet] 실행");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String cmd = request.getParameter("cmd");

		if ("".equals(cmd) || cmd == null) { list(request, response); return; }

		switch (cmd) { // get cmd > method

		case "insertPage": insertPage(request, response); return;
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
		
		response.setContentType("text/html; charset=utf-8;");

		MachineryService service = new MachineryService();
		List list = service.selectJoinInfo();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/views/machinery/machinery_insert.jsp")
														.forward(request, response);
		
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
		
		CommonDTO commonDTO = setCommonDTO(request, "");
		
		HttpSession session = request.getSession();
		
		CommonDTO sessionDTO = (CommonDTO) session.getAttribute("machineryCommonDTO");
		
		if(sessionDTO != null) {
			sessionDTO.setPage(commonDTO.getPage());
			sessionDTO.setSize(commonDTO.getSize());
			
			commonDTO = sessionDTO;
		}
		
		Map map = service.selectDB(setDTO(request), commonDTO);
		
		request.setAttribute("map", map);
		request.setAttribute("servletName", "machinery");
		
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
		MachineryDTO machineryDTO = service.selectOne(setDTO(request), setCommonDTO(request, ""));

		// Forward > DTO
		request.setAttribute("machineryDTO", machineryDTO);
		
		if("detail".equals(selector)) { // 상세 페이지
			
			request.getRequestDispatcher("WEB-INF/views/machinery/machinery_detail.jsp")
				.forward(request, response);
		
		} else { // 수정 페이지
			
			request.getRequestDispatcher("WEB-INF/views/machinery/machinery_modify.jsp")
				.forward(request, response);
			
		}
		
	}
	
	// search
		protected void search(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			
			MachineryService service = new MachineryService();
			// 검색 내용받음
			CommonDTO commonDTO = setCommonDTO(request, "search");
			
			HttpSession session = request.getSession();
			session.setAttribute("machineryCommonDTO", commonDTO);
			
			Map map = service.selectDB(setDTO(request), setCommonDTO(request, "search"));

			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/views/machinery/machinery_list.jsp").forward(request, response);

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
		
			machinery_type = request.getParameter("machinery_type");
			machinery_status = request.getParameter("machinery_status");
			error_sign = request.getParameter("error_sign");
			m_action = request.getParameter("m_action");
			
		
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
	
	// 공통 변수
		protected CommonDTO setCommonDTO(HttpServletRequest request, String cmd)
				throws ServletException, IOException {
			
			CommonDTO commonDTO = new CommonDTO();
			
			// 검색 기능 [ search_content ]
			if("search".equals(cmd)) {
				String where = request.getParameter("selectName");
				if (where!=null && !"".equals(where)) { 
					commonDTO.setSearch("AND tableB.name = '" + where + "'") ; 
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
