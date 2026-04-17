package WorkOrder;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Emp.EmpDTO;
import Emp.EmpService;
import ProductionManagement.ProductionManagementDTO;
import ProductionManagement.ProductionManagementService;
import fileLibrary.CommonDTO;

@WebServlet("/order")
public class WorkOrderControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommonDTO commonDTO= new CommonDTO();
		String sorder_num = request.getParameter("order_num");
		String ssize = request.getParameter("size");
		String spage= request.getParameter("page");
		String mod = request.getParameter("mod");
		String sprod_num = request.getParameter("prod_num");
		int size = 10;
		int page = 1;
		if (ssize != null && spage != null) {
		size = Integer.parseInt(ssize);
		page = Integer.parseInt(spage);
		}
		int order_num = -1;
		if (sorder_num != null) {
			order_num = Integer.parseInt(sorder_num);
		}
		WorkOrderDTO orderDTO = new WorkOrderDTO();
		CommonDTO pageing = new CommonDTO();
		pageing.setSize(size);
		pageing.setPage(page);
		orderDTO.setOrder_num(order_num);
		orderDTO.setMod(mod);
		WorkOrderService service = new WorkOrderService();
		Map map = service.select(orderDTO, pageing);
		request.setAttribute("map", map);
		if("detail".equals(mod)) {
			System.out.println("detail濡�");
			request.getRequestDispatcher("/WEB-INF/views/order/orderDetail.jsp").forward(request, response);
		}else if("add".equals(mod)){
			ProductionManagementService sv = new ProductionManagementService();
			EmpService empservice = new EmpService();
			EmpDTO empDTO = new EmpDTO();
			ProductionManagementDTO pmdto = new ProductionManagementDTO();
			int prod_num=-1;
			if(sprod_num!=null) {
				prod_num = Integer.parseInt(sprod_num);
			}
			pmdto.setProd_num(prod_num);
			List pmlist = sv.selectall(pmdto);
			List emplist = empservice.selectall(empDTO);
			request.setAttribute("pm", pmlist);
			request.setAttribute("emp", emplist);
			System.out.println("wo컨트롤 emp:" + emplist);
			System.out.println("wo컨트롤 pm:" + pmlist);
			request.getRequestDispatcher("/WEB-INF/views/order/orderAdd.jsp").forward(request, response);
		}
		else if("up".equals(mod)){
			EmpService empservice = new EmpService();
			EmpDTO empDTO = new EmpDTO();
			List emplist = empservice.selectall(empDTO);
			request.setAttribute("emp", emplist);
			System.out.println("wo異쒕컻 emp:" + emplist);
			System.out.println("UP濡�");
			request.getRequestDispatcher("/WEB-INF/views/order/orderUp.jsp").forward(request, response);
		}else if("delete".equals(mod)){
			orderDelete(request, response);
		}else {
			System.out.println("由ъ뒪�듃濡�");
			request.getRequestDispatcher("/WEB-INF/views/order/orderList.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");

		if ("up".equals(mod)) {
			orderUp(request, response);
		}else if ("add".equals(mod)) {
			orderAdd(request, response);
		}else if ("delete".equals(mod)) {
			orderDelete(request, response);
		}
	}

	protected void orderUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sorder_num = request.getParameter("order_num");
		String swork_date = request.getParameter("work_date");
		String sdaily_target = request.getParameter("daily_target");
		String sempno = request.getParameter("empno");
		String work_order_title = request.getParameter("work_order_title");
		String status = request.getParameter("status");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
		int order_num = Integer.parseInt(sorder_num);
		int empno = Integer.parseInt(sempno);
		int daily_target = Integer.parseInt(sdaily_target);
		Date work_date = Date.valueOf(swork_date);
		System.out.println("�뾽異쒕컻");
		WorkOrderDTO orderDTO = new WorkOrderDTO();
		orderDTO.setWork_date(work_date);
		orderDTO.setOrder_num(order_num);
		orderDTO.setEmpno(empno);
		orderDTO.setWork_order_title(work_order_title);
		orderDTO.setStatus(status);
		orderDTO.setDaily_target(daily_target);
		orderDTO.setMod(mod);
		
		WorkOrderService service = new WorkOrderService();
		System.out.println("order�뾽留덉�留�: "+service.orderService(orderDTO));
		response.sendRedirect("order");
	}
	
	protected void orderAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String status = request.getParameter("status");
			String sprod_num = request.getParameter("prod_num");
			String sempno = request.getParameter("empno");
			String sdaily_target = request.getParameter("daily_target");
			String swork_date = request.getParameter("work_date");
			String work_order_title = request.getParameter("work_order_title");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
			int empno = Integer.parseInt(sempno);
			int prod_num = Integer.parseInt(sprod_num);
			int daily_target = Integer.parseInt(sdaily_target);
			Date work_date = Date.valueOf(swork_date);
		
		WorkOrderDTO orderDTO = new WorkOrderDTO();
		orderDTO.setStatus(status);
			orderDTO.setProd_num(prod_num);
			orderDTO.setEmpno(empno);
			orderDTO.setDaily_target(daily_target);
			orderDTO.setWork_order_title(work_order_title);
		orderDTO.setMod(mod);
		orderDTO.setWork_date(work_date);
		
		WorkOrderService service = new WorkOrderService();
		service.orderService(orderDTO);
		response.sendRedirect("order");
	}
	
	protected void orderDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sorder_num = request.getParameter("order_num");
		String mod = request.getParameter("mod");
		int order_num = Integer.parseInt(sorder_num);
		WorkOrderDTO orderDTO = new WorkOrderDTO();
		orderDTO.setOrder_num(order_num);
		orderDTO.setMod(mod);
		WorkOrderService service = new WorkOrderService();
		service.orderService(orderDTO);
		response.sendRedirect("order");
	}


}
