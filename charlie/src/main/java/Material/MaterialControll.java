package Material;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Material.MaterialDTO;
import Material.MaterialService;
import Material.MaterialDTO;
import Material.MaterialService;
import fileLibrary.CommonDTO;

@WebServlet("/material")
public class MaterialControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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
		
		default: return;
		
		}

	}

	protected void insertPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8;");

		MaterialService service = new MaterialService();
		Map map = service.selectJoinInfo();
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("WEB-INF/views/material/material_insert.jsp")
														.forward(request, response);
		
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
		
		CommonDTO commonDTO = setCommonDTO(request, "");
		
		HttpSession session = request.getSession();
		
		CommonDTO sessionDTO = (CommonDTO) session.getAttribute("materialCommonDTO");
		
		if(sessionDTO != null) {
			sessionDTO.setPage(commonDTO.getPage());
			sessionDTO.setSize(commonDTO.getSize());
			
			commonDTO = sessionDTO;
		}
		
		Map map = service.selectDB(setDTO(request), commonDTO);
		
		request.setAttribute("map", map);
		request.setAttribute("servletName", "material");
		
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


		// Service > DAO - selectOne
		MaterialService service = new MaterialService();
		MaterialDTO materialDTO = service.selectOne(setDTO(request), setCommonDTO(request, ""));

		// Forward > DTO
		request.setAttribute("materialDTO", materialDTO);
		
		if("detail".equals(selector)) { 
			
			request.getRequestDispatcher("WEB-INF/views/material/material_detail.jsp")
				.forward(request, response);
		
		} else { 
			
			Map map = service.selectJoinInfo();
			request.setAttribute("map", map);
			
			request.getRequestDispatcher("WEB-INF/views/material/material_modify.jsp")
				.forward(request, response);
			
		}
		
	}
	
	// search
		protected void search(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			
			MaterialService service = new MaterialService();
			
			// 검색 내용받음
			CommonDTO commonDTO = setCommonDTO(request, "search");
						
		    HttpSession session = request.getSession();
			session.setAttribute("materialCommonDTO", commonDTO);
			
			Map map = service.selectDB(setDTO(request), setCommonDTO(request, "search"));

			request.setAttribute("map", map);
			request.getRequestDispatcher("WEB-INF/views/material/material_list.jsp").forward(request, response);

		}

	
		protected MaterialDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
			
			MaterialDTO materialDTO = new MaterialDTO();
			
			int material_num = -1;  int area_quantity= -1; 
			int mdm_num = -1; int warehouse_num = -1;
			
			if (request.getParameter("material_num") != null 
					&& !("".equals(request.getParameter("material_num")))) {
				
				material_num = Integer.parseInt(request.getParameter("material_num"));
			
				System.out.println( "/set material material_num : " + material_num );
			} 
			
			
			if (request.getParameter("area_quantity") != null 
					&& !("".equals(request.getParameter("area_quantity")))) {
				
				area_quantity = Integer.parseInt(request.getParameter("area_quantity"));
				
				System.out.println( "/set material total_quantity : " + area_quantity );
			} 
			
			if (request.getParameter("warehouse_num") != null 
					&& !("".equals(request.getParameter("warehouse_num")))) {
				
				warehouse_num = Integer.parseInt(request.getParameter("warehouse_num"));
				
				System.out.println( "/set material warehouse_num : " + warehouse_num );
			} 
			
			if (request.getParameter("mdm_num") != null 
					&& !("".equals(request.getParameter("mdm_num")))) {
				
				mdm_num = Integer.parseInt(request.getParameter("mdm_num"));
				
			} 
		
			
			materialDTO.setMaterial_num(material_num);
			materialDTO.setArea_quantity(area_quantity);
			materialDTO.setWarehouse_num(warehouse_num);
			materialDTO.setMdm_num(mdm_num);
			
			return materialDTO;
		}
	
		protected CommonDTO setCommonDTO(HttpServletRequest request, String cmd)
				throws ServletException, IOException {
			
			CommonDTO commonDTO = new CommonDTO();
			
			String where2 = request.getParameter("selectName");
			if (where2!=null && !"".equals(where2)) { 
				commonDTO.setWhere2("AND type = '" + where2 + "'") ; 
				}
			
			String orderBy = request.getParameter("orderBy");
			commonDTO.setOrderBy(orderBy);
			
			
			
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
