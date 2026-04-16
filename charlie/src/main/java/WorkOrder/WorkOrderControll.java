package WorkOrder;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileLibrary.CommonDTO;

@WebServlet("/order")
public class WorkOrderControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sorder_num = request.getParameter("order_num");
		String ssize = request.getParameter("size");
		String spage= request.getParameter("page");
		String mod = request.getParameter("mod");
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
		WorkOrderService service = new WorkOrderService();
		Map map = service.select(orderDTO, pageing);
		request.setAttribute("map", map);
		if("detail".equals(mod)) {
			System.out.println("detail로");
			request.getRequestDispatcher("/WEB-INF/views/order/orderDetail.jsp").forward(request, response);
		}else if("add".equals(mod)){
			System.out.println("add로");
			request.getRequestDispatcher("/WEB-INF/views/order/orderAdd.jsp").forward(request, response);
		}else if("up".equals(mod)){
			System.out.println("UP로");
			request.getRequestDispatcher("/WEB-INF/views/order/orderUp.jsp").forward(request, response);
		}else if("delete".equals(mod)){
			orderDelete(request, response);
		}else {
			System.out.println("리스트로");
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
		String sprod_num = request.getParameter("prod_num");
		String starget_quantity = request.getParameter("target_quantity");
		String sempno = request.getParameter("empno");
		String title = request.getParameter("title");
		String smdm_num = request.getParameter("mdm_num");
		String status = request.getParameter("status");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
		int order_num = Integer.parseInt(sorder_num);
		int prod_num = Integer.parseInt(sprod_num);
		int target_quantity = Integer.parseInt(starget_quantity);
		int mdm_num = Integer.parseInt(smdm_num);
		int empno = Integer.parseInt(sempno);
		
		WorkOrderDTO orderDTO = new WorkOrderDTO();
		orderDTO.setOrder_num(order_num);
		orderDTO.setProd_num(prod_num);
		orderDTO.setDaily_target(target_quantity);
		orderDTO.setEmpno(empno);
		orderDTO.setTitle(title);
		orderDTO.setStatus(status);
		orderDTO.setMdm_num(mdm_num);
		orderDTO.setMod(mod);
		
		WorkOrderService service = new WorkOrderService();
		service.orderService(orderDTO);
		response.sendRedirect("order");
	}
	
	protected void orderAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sorder_num = request.getParameter("order_num");
		String starget_quantity = request.getParameter("target_quantity");
		String status = request.getParameter("status");
		String smdm_num = request.getParameter("mdm_num");
			String sprod_num = request.getParameter("prod_num");
			String sempno = request.getParameter("empno");
			String title = request.getParameter("title");
			String swork_start = request.getParameter("work_start");
			String swork_end = request.getParameter("work_end");
			String content = request.getParameter("content");
			String sweekly_target = request.getParameter("weekly_target");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
		int order_num = Integer.parseInt(sorder_num);
		int mdm_num = Integer.parseInt(smdm_num);
		int target_quantity = Integer.parseInt(starget_quantity);
			int empno = Integer.parseInt(sempno);
			int prod_num = Integer.parseInt(sprod_num);
			Date work_start = Date.valueOf(swork_start);
			Date work_end = Date.valueOf(swork_end);
			int weekly_target = Integer.parseInt(sweekly_target);
		
		WorkOrderDTO orderDTO = new WorkOrderDTO();
		orderDTO.setOrder_num(order_num);
		orderDTO.setDaily_target(target_quantity);
		orderDTO.setStatus(status);
		orderDTO.setMdm_num(mdm_num);
			orderDTO.setProd_num(prod_num);
			orderDTO.setEmpno(empno);
			orderDTO.setTitle(title);
			orderDTO.setWork_start(work_start);
			orderDTO.setWork_end(work_end);
			orderDTO.setWeekly_target(weekly_target);
			orderDTO.setContent(content);
		orderDTO.setMod(mod);
		
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
