package WorkOrder;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order")
public class WorkOrderControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sorder_num = request.getParameter("order_num");
		int order_num = -1;
		if (sorder_num != null) {
			order_num = Integer.parseInt(sorder_num);
		}
		WorkOrderDTO orderDTO = new WorkOrderDTO();
		orderDTO.setOrder_num(order_num);
		WorkOrderService service = new WorkOrderService();
		List list = service.select(orderDTO);
		System.out.println("order: " + list);
		request.setAttribute("order", list);
		request.getRequestDispatcher("Order.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mod = request.getParameter("mod");

		if ("write".equals(mod)) {
			write(request, response);
		}

	}

	protected void write(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("write.jsp");
	}

}
