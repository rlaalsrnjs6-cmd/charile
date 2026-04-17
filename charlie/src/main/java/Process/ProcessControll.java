package Process;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Process.ProcessDTO;
import Process.ProcessService;
import Mdm.MdmService;
import fileLibrary.CommonDTO;

@WebServlet("/process")
public class ProcessControll extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("/process [doGet] 실행");

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
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		ProcessService service = new ProcessService();
		List list = service.selectJoinInfo();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/views/process/process_insert.jsp")
														.forward(request, response);
		
	}
	
	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		// setDTO > Service > DAO
		ProcessService service = new ProcessService();
		service.insertDB(setDTO(request));

		// list page
		response.sendRedirect("process?cmd=list");
	}

	// list
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8;");

		ProcessService service = new ProcessService();
		Map map = service.selectDB(setDTO(request), setCommonDTO(request, ""));
		
		request.setAttribute("map", map);
		request.setAttribute("servletName", "process");
		
		List joinList = service.selectJoinInfo();
		request.setAttribute("joinList", joinList);
		
		request.getRequestDispatcher("WEB-INF/views/process/process_list.jsp").forward(request, response);

	}
	
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProcessService service = new ProcessService();
		service.deleteDB(setDTO(request));
		
		response.sendRedirect("process?cmd=list");
	}
	
	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProcessService service = new ProcessService();
		service.modifyDB(setDTO(request));
		
		response.sendRedirect("process?cmd=list");
	}
	

	// detail
	protected void detail(HttpServletRequest request, HttpServletResponse response, String selector)
			throws ServletException, IOException {

		System.out.println("/detail 실행");

		// Service > DAO - selectOne
		ProcessService service = new ProcessService();
		ProcessDTO processDTO = service.selectOne(setDTO(request), setCommonDTO(request, ""));

		// Forward > DTO
		request.setAttribute("processDTO", processDTO);
		
		if("detail".equals(selector)) { // 상세 페이지
			
			request.getRequestDispatcher("WEB-INF/views/process/process_detail.jsp")
				.forward(request, response);
		
		} else { // 수정 페이지
			
			List list = service.selectJoinInfo();
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("WEB-INF/views/process/process_modify.jsp")
				.forward(request, response);
			
		}
		
	}
	
	// search
		protected void search(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			
			ProcessService service = new ProcessService();
			// 검색 내용받음
			
			Map map = service.selectDB(setDTO(request), setCommonDTO(request, "search"));

			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/views/process/process_list.jsp").forward(request, response);

		}

	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
		// set primarykey & return DTO 
		protected ProcessDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
			
			ProcessDTO processDTO = new ProcessDTO();
			
			int process_num = -1; int flow= -1; int mdm_num = -1; 
			
			
			if (request.getParameter("process_num") != null 
					&& !("".equals(request.getParameter("process_num")))) {
				
				process_num = Integer.parseInt(request.getParameter("process_num"));
			
				System.out.println( "/set process process_num : " + process_num );
			} 
			
			if (request.getParameter("flow") != null 
					&& !("".equals(request.getParameter("flow")))) {
				
				flow = Integer.parseInt(request.getParameter("flow"));
				
				System.out.println( "/set process flow : " + flow );
			} 
			
			if (request.getParameter("mdm_num") != null 
					&& !("".equals(request.getParameter("mdm_num")))) {
				
				mdm_num = Integer.parseInt(request.getParameter("mdm_num"));
				
				System.out.println( "/set process mdm_num : " + mdm_num );
			} 
			
			
			String process_content = request.getParameter("process_content");
			String img_url = request.getParameter("img_url");
			
			processDTO.setProcess_num(process_num);
			processDTO.setProcess_content(process_content);
			processDTO.setFlow(flow);
			processDTO.setImg_url(img_url);
			processDTO.setMdm_num(mdm_num);
			
			return processDTO;
		}
	
	// 공통 변수
		protected CommonDTO setCommonDTO(HttpServletRequest request, String cmd)
				throws ServletException, IOException {
			
			CommonDTO commonDTO = new CommonDTO();
			
			// ORDER BY [COLUMN]
			String orderBy = " tableA.process_num DESC ";
			commonDTO.setOrderBy(orderBy);
			// GROUP BY 부터 작성
			String groupBy = ""; 
			commonDTO.setGroupBy(groupBy);
			// WHERE 1=1
			String where = ""; 
			commonDTO.setWhere(where);
			
			// 검색 기능 [ search_content ]
			if("search".equals(cmd)) {
				commonDTO.setSelector(request.getParameter("search_select"));
				commonDTO.setSearch(request.getParameter("search_content"));
				System.out.println(commonDTO.getSelector());
				System.out.println(commonDTO.getSearch());
			}
			
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
