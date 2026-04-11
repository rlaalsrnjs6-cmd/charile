package Material;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Material.MaterialDTO;
import Material.MaterialService;

@WebServlet("/material")
public class MaterialControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("/material [doGet] 실행");

		response.setContentType("text/html; charset=utf-8;");

		String cmd = request.getParameter("cmd");

		if ("".equals(cmd) || cmd == null) { list(request, response); return; }

		switch (cmd) { // get cmd > method

		case "insertPage": request.getRequestDispatcher("WEB-INF/views/material/material_insert.jsp").forward(request, response); return;
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
		MaterialService service = new MaterialService();
		service.insertDB(setDTO(request));

		// list page
		response.sendRedirect("material?cmd=list");
	}

	// list
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8;");

		MaterialService service = new MaterialService();
		List list = (List) service.selectAll();

		System.out.println( "/ctrl list : " + list );
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/views/material/material_list.jsp").forward(request, response);

	}
	
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MaterialService service = new MaterialService();
		service.deleteDB(setDTO(request));
		
		response.sendRedirect("material?cmd=list");
	}
	
	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MaterialService service = new MaterialService();
		service.modifyDB(setDTO(request));
		
		response.sendRedirect("material?cmd=list");
	}
	

	// detail
	protected void detail(HttpServletRequest request, HttpServletResponse response, String selector)
			throws ServletException, IOException {

		System.out.println("/detail 실행");

		// Service > DAO - selectOne
		MaterialService service = new MaterialService();
		List materialInfo = service.selectDB(setDTO(request), "num");

		// Forward > DTO
		request.setAttribute("materialInfo", materialInfo);
		
		if("detail".equals(selector)) { // 상세 페이지
			
			request.getRequestDispatcher("WEB-INF/views/material/material_detail.jsp")
				.forward(request, response);
		
		} else { // 수정 페이지
			
			request.getRequestDispatcher("WEB-INF/views/material/material_modify.jsp")
				.forward(request, response);
			
		}
		
	}

	// 거의 고정해서 사용
	
	// set primarykey & return DTO 
	protected MaterialDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		MaterialDTO materialDTO = new MaterialDTO();
		
		int material_num = -1;  int total_quantity= -1; 
		int mdm_num = -1; int warehouse_num = -1;
		
		if (request.getParameter("material_num") != null 
				&& !("".equals(request.getParameter("material_num")))) {
			
			material_num = Integer.parseInt(request.getParameter("material_num"));
		
			System.out.println( "/set material material_num : " + material_num );
		} 
		
		
		if (request.getParameter("total_quantity") != null 
				&& !("".equals(request.getParameter("total_quantity")))) {
			
			total_quantity = Integer.parseInt(request.getParameter("total_quantity"));
			
			System.out.println( "/set material total_quantity : " + total_quantity );
		} 
		
		if (request.getParameter("warehouse_num") != null 
				&& !("".equals(request.getParameter("warehouse_num")))) {
			
			warehouse_num = Integer.parseInt(request.getParameter("warehouse_num"));
			
			System.out.println( "/set material warehouse_num : " + warehouse_num );
		} 
		
		if (request.getParameter("mdm_num") != null 
				&& !("".equals(request.getParameter("mdm_num")))) {
			
			mdm_num = Integer.parseInt(request.getParameter("mdm_num"));
			
			System.out.println( "/set material mdm_num : " + mdm_num );
		} 
	
		
		materialDTO.setMaterial_num(material_num);
		materialDTO.setTotal_quantity(total_quantity);
		materialDTO.setWarehouse_num(warehouse_num);
		materialDTO.setMdm_num(mdm_num);
		
		return materialDTO;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
