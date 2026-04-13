package Warehouse;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Lot.LotDTO;
import Lot.LotService;

@WebServlet("/warehouse")
public class WarehouseControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String swarehouse_num = request.getParameter("warehouse_num");
		String mod = request.getParameter("mod");

		int warehouse_num = -1;
		if (swarehouse_num != null) {
			warehouse_num = Integer.parseInt(swarehouse_num);
		}
		WarehouseDTO warehouseDTO = new WarehouseDTO();
		warehouseDTO.setWarehouse_num(warehouse_num);
		warehouseDTO.setMod(mod);
		WarehouseService service = new WarehouseService();
		List<WarehouseDTO> list = service.select(warehouseDTO);
		System.out.println("warehouse컨트롤마지막: "+list);
		request.setAttribute("warehouse", list);
		if ("detail".equals(mod)) {
			System.out.println("디테일로고고씽");
			request.getRequestDispatcher("WEB-INF/views/warehouse/warehouseDetail.jsp").forward(request, response);
			return;
		} else if ("up".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/warehouse/warehouseUp.jsp").forward(request, response);
			return;
		} else if ("add".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/warehouse/warehouseAdd.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			warehouseDelete(request, response);
			return;
		}
		System.out.println("리스트로 고고씽");
		request.getRequestDispatcher("WEB-INF/views/warehouse/warehouseList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		if ("add".equals(mod)) {
			warehouseAdd(request, response);
		} else if ("up".equals(mod)) {
			warehouseUP(request, response);
		} else if ("delete".equals(mod)) {
			warehouseDelete(request, response);
		}

	}

	protected void warehouseUP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String swarehouse_num = request.getParameter("warehouse_num");
		String section = request.getParameter("section");
		String floor_level = request.getParameter("floor_level");
		String stemperature = request.getParameter("temperature");
		String humidity = request.getParameter("humidity");
		String wh_status_chk = request.getParameter("wh_status_chk");
		String mod = request.getParameter("mod");
		int warehouse_num = Integer.parseInt(swarehouse_num);
		int temperature = Integer.parseInt(stemperature);
		WarehouseDTO warehouseDTO = new WarehouseDTO();
		warehouseDTO.setWarehouse_num(warehouse_num);
		warehouseDTO.setSection(section);
		warehouseDTO.setFloor_level(floor_level);
		warehouseDTO.setTemperature(temperature);
		warehouseDTO.setHumidity(humidity);
		warehouseDTO.setWh_status_chk(wh_status_chk);
		warehouseDTO.setMod(mod);
		WarehouseService service = new WarehouseService();
		service.warehouseService(warehouseDTO);
		response.sendRedirect("warehouse");

	}

	protected void warehouseAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String swarehouse_num = request.getParameter("warehouse_num");
		String section = request.getParameter("section");
		String floor_level = request.getParameter("floor_level");
		String stemperature = request.getParameter("temperature");
		String humidity = request.getParameter("humidity");
		String wh_status_chk = request.getParameter("wh_status_chk");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
		int warehouse_num = Integer.parseInt(swarehouse_num);
		int temperature = Integer.parseInt(stemperature);

		WarehouseDTO warehouseDTO = new WarehouseDTO();
		warehouseDTO.setWarehouse_num(warehouse_num);
		warehouseDTO.setSection(section);
		warehouseDTO.setFloor_level(floor_level);
		warehouseDTO.setTemperature(temperature);
		warehouseDTO.setHumidity(humidity);
		warehouseDTO.setWh_status_chk(wh_status_chk);
		warehouseDTO.setMod(mod);

		WarehouseService service = new WarehouseService();

		System.out.println("lotadd마지막: " + service.warehouseService(warehouseDTO));

		response.sendRedirect("warehouse");
	}

	protected void warehouseDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String swarehouse_num = request.getParameter("warehouse_num");
		String mod = request.getParameter("mod");
		int warehouse_num = Integer.parseInt(swarehouse_num);
		WarehouseDTO warehouseDTO = new WarehouseDTO();
		warehouseDTO.setWarehouse_num(warehouse_num);
		warehouseDTO.setMod(mod);
		WarehouseService service = new WarehouseService();
		System.out.println("lotAdd마지막: " + service.warehouseService(warehouseDTO));
		response.sendRedirect("warehouse");
	}

}
