package Mdm;

import java.io.IOException;
import java.util.ArrayList;
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

		if ("".equals(cmd) || cmd == null) { // null
			request.getRequestDispatcher("WEB-INF/views/mdm/mdm_insert.jsp").forward(request, response);
			return;
		}

		switch (cmd) {

		case "insert": insert(request, response); break;

		case "list": list(request, response); break;

		case "detail": detail(request, response, "detail"); break;
			
		case "modify": detail(request, response, "modify"); break;
		
		case "delete": delete(request, response); break;
		

		default: System.out.println("잘못된 접근입니다");
		
		}

	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		// Service > DAO
		MdmService service = new MdmService();
		service.insertDB(setDTO(request));

		request.getRequestDispatcher("mdm?cmd=list").forward(request, response);
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
	
	asdfasdfasdfsdfjkls;j
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MdmService service = new MdmService();
		service.deleteDB(setDTO(request));
		
		request.getRequestDispatcher("mdm?cmd=list").forward(request, response);

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
	
	
	
	
	
	
	

	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
	protected MdmDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		MdmDTO mdmDTO = new MdmDTO();
		
		int num = -1; String code = null; String name = null; 
		String unit=null; String type=null; int price= -1;
		
		if (request.getParameter("num") != null 
				&& !("".equals(request.getParameter("num")))) {
			
			num = Integer.parseInt(request.getParameter("num"));
		
			System.out.println( "/set mdm Num : " + num );
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
		
		mdmDTO.setMdm_num(num);
		mdmDTO.setCode(code);
		mdmDTO.setName(name);
		mdmDTO.setUnit(unit);
		mdmDTO.setType(type);
		mdmDTO.setPrice(price);
		
		return mdmDTO;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
