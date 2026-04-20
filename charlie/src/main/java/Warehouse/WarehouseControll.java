package Warehouse;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Warehouse.WarehouseDTO;
import Warehouse.WarehouseService;
import Warehouse.WarehouseService;
import Warehouse.WarehouseService;
import fileLibrary.CommonDTO;


@WebServlet("/warehouse")
public class WarehouseControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("/warehouse [doGet] �떎�뻾");

		response.setContentType("text/html; charset=utf-8;");

		String cmd = request.getParameter("cmd");

		if ("".equals(cmd) || cmd == null) { list(request, response); return; }

		switch (cmd) {

		case "insertPage": insertPage(request, response); return;
		case "insert": insert(request, response); return;
		case "list": list(request, response); return;
		case "detail": detail(request, response, "detail"); return;
		case "modify": detail(request, response, "modify"); return;
		case "update": update(request, response); return;
		case "delete": delete(request, response); return;
		
		case "search": search(request, response); return;
		
		default: System.out.println("�옒紐삳맂 �젒洹쇱엯�땲�떎"); return;
		
		}

	}
	
	protected void insertPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/views/warehouse/warehouse_insert.jsp")
														.forward(request, response);
		
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		// setDTO > Service > DAO
		WarehouseService service = new WarehouseService();
		service.insertDB(setDTO(request));

		// list page
		response.sendRedirect("warehouse?cmd=list");
	}

	// list
	protected void list(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
		response.setContentType("text/html; charset=utf-8;");

		WarehouseService service = new WarehouseService();
		
		CommonDTO commonDTO = setCommonDTO(request, "");
		
		HttpSession session = request.getSession();
		
		CommonDTO sessionDTO = (CommonDTO) session.getAttribute("whCommonDTO");
		
		if(sessionDTO != null) {
			sessionDTO.setPage(commonDTO.getPage());
			sessionDTO.setSize(commonDTO.getSize());
			
			commonDTO = sessionDTO;
		}
		
		Map map = service.selectDB(setDTO(request), commonDTO);
			
		request.setAttribute("map", map);
		request.setAttribute("servletName", "warehouse");
			
		request.getRequestDispatcher("WEB-INF/views/warehouse/warehouse_list.jsp").forward(request, response);

		}
	
	// delete
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		WarehouseService service = new WarehouseService();
		service.deleteDB(setDTO(request));
		
		response.sendRedirect("warehouse?cmd=list");
	}
	
	// update
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		WarehouseService service = new WarehouseService();
		service.modifyDB(setDTO(request));
		
		response.sendRedirect("warehouse?cmd=list");
	}
	

	// detail
	protected void detail(HttpServletRequest request, HttpServletResponse response, String selector)
			throws ServletException, IOException {


		// Service > DAO - selectOne
		WarehouseService service = new WarehouseService();
		WarehouseDTO warehouseDTO = service.selectOne(setDTO(request), setCommonDTO(request, ""));

		// Forward > DTO
		request.setAttribute("warehouseDTO", warehouseDTO);
		
		if("detail".equals(selector)) { // �긽�꽭 �럹�씠吏�
			
			request.getRequestDispatcher("WEB-INF/views/warehouse/warehouse_detail.jsp")
				.forward(request, response);
		
		} else { 
			
//			List list = service.selectJoinInfo();
//			request.setAttribute("list", list);
			
			request.getRequestDispatcher("WEB-INF/views/warehouse/warehouse_modify.jsp")
				.forward(request, response);
			
		}
		
	}
	
	// search
	protected void search(HttpServletRequest request, HttpServletResponse response)
							throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
			WarehouseService service = new WarehouseService();
			// 寃��깋 �궡�슜諛쏆쓬
				
			CommonDTO commonDTO = setCommonDTO(request, "search");
			
			HttpSession session = request.getSession();
			session.setAttribute("whCommonDTO", commonDTO);
			
			Map map = service.selectDB(setDTO(request), commonDTO);

			request.setAttribute("map", map);
			
			request.getRequestDispatcher("WEB-INF/views/warehouse/warehouse_list.jsp").forward(request, response);

			}


	// set primarykey & return DTO 
	protected WarehouseDTO setDTO(HttpServletRequest request) throws ServletException, IOException {
		
		WarehouseDTO warehouseDTO = new WarehouseDTO();
		
		int warehouse_num = -1; double temperature = 1.0; double humidity = -1.1;
		if (request.getParameter("warehouse_num") != null 
				&& !("".equals(request.getParameter("warehouse_num")))) {
			
			warehouse_num = Integer.parseInt(request.getParameter("warehouse_num"));
		
			System.out.println( "/set warehouse warehouse_num : " + warehouse_num );
		} 
		
		if (request.getParameter("temperature") != null 
				&& !("".equals(request.getParameter("temperature")))) {
			
			temperature = Double.parseDouble(request.getParameter("temperature"));
			
		} 
		
		if (request.getParameter("humidity") != null 
				&& !("".equals(request.getParameter("humidity")))) {
			
			humidity = Double.parseDouble(request.getParameter("humidity"));
			
		} 
		
	
		warehouseDTO.setWarehouse_num(warehouse_num);
		warehouseDTO.setTemperature(temperature);
		warehouseDTO.setHumidity(humidity);
		
		warehouseDTO.setWh_section(request.getParameter("wh_section"));
		warehouseDTO.setFloor_level(request.getParameter("floor_level"));
		warehouseDTO.setWh_status_chk(request.getParameter("wh_status_chk"));
		
		return warehouseDTO;
	}
	
	// 怨듯넻 蹂��닔
			protected CommonDTO setCommonDTO(HttpServletRequest request, String cmd)
					throws ServletException, IOException {
				
				CommonDTO commonDTO = new CommonDTO();
				
				// ORDER BY [COLUMN]
				String orderBy = "wh_chk_date DESC ";
				commonDTO.setOrderBy(orderBy);
				// GROUP BY 遺��꽣 �옉�꽦
				String groupBy = ""; 
				commonDTO.setGroupBy(groupBy);
				
				if("search".equals(cmd)) {
					
					String where2 = request.getParameter("selectName");
					if (where2!=null && !"".equals(where2)) { 
						commonDTO.setWhere2("AND wh_section = '" + where2 + "'") ; 
						}
					
					String where3 = request.getParameter("selectChk");
					if (where3!=null && !"".equals(where3)) { 
						commonDTO.setWhere3("AND wh_status_chk = '" + where3 + "'") ; 
					}
					
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
