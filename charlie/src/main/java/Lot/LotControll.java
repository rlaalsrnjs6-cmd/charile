package Lot;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Defective.DefectiveDTO;
import Defective.DefectiveService;
import Material.MaterialDTO;
import Material.MaterialService;
import WorkOrder.WorkOrderDTO;
import WorkOrder.WorkOrderService;
import fileLibrary.CommonDTO;

@WebServlet("/lot")
public class LotControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slot_num = request.getParameter("lot_num");
		String ssize = request.getParameter("size");
		String spage = request.getParameter("page");
		String mod = request.getParameter("mod");

		int size = 10;
		int page = 1;
		if(ssize!=null && spage!=null) {
			size = Integer.parseInt(ssize);
			page = Integer.parseInt(spage);
		}
		int lot_num = -1;
		if (slot_num != null) {
			lot_num = Integer.parseInt(slot_num);
		}
		LotDTO lotDTO = new LotDTO();
		DefectiveDTO defectiveDTO = new DefectiveDTO();
		CommonDTO pageing = new CommonDTO();
		defectiveDTO.setLot_num(lot_num);
		pageing.setSize(size);
		pageing.setPage(page);
		lotDTO.setLot_num(lot_num);
		lotDTO.setMod(mod);
		DefectiveService defectiveSv = new DefectiveService();
		LotService service = new LotService();
		Map list = service.select(lotDTO,pageing);
		List<DefectiveDTO> defective = defectiveSv.selectall(defectiveDTO);
		request.setAttribute("defective", defective);
		request.setAttribute("map", list);
		System.out.println(list);
		System.out.println("디테일 들어가기전");
		if ("detail".equals(mod)) {
			System.out.println("들어갓나?");
			List lot = service.selectall(lotDTO);
			System.out.println(lot);
			request.setAttribute("lot", lot);
			request.getRequestDispatcher("WEB-INF/views/lot/lotDetail.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			lotDelete(request, response);
			return;
		} else {
			System.out.println("입장~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			WorkOrderService ordersv = new WorkOrderService();
			WorkOrderDTO orderDTO = new WorkOrderDTO();
			List<LotDTO> lot = service.selectall(lotDTO);
			List order = ordersv.selectalll(orderDTO);
			request.setAttribute("order", order);
			request.setAttribute("lot", lot);
			System.out.println("lot에 order"+order);
				if ("up".equals(mod)) {
					request.getRequestDispatcher("WEB-INF/views/lot/lotUp.jsp").forward(request, response);
					return;
				} else if ("add".equals(mod)) {
					System.out.println("lotadd");
					request.getRequestDispatcher("WEB-INF/views/lot/lotAdd.jsp").forward(request, response);
					return;
				}
		}
		request.getRequestDispatcher("WEB-INF/views/lot/lotList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		if ("add".equals(mod)) {
			lotAdd(request, response);
		} else if ("up".equals(mod)) {
			lotUP(request, response);
		} else if ("delete".equals(mod)) {
			lotDelete(request, response);
		}

	}

	protected void lotUP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slot_num = request.getParameter("lot_num");
		String slot_count = request.getParameter("lot_count");
		String sorder_num = request.getParameter("order_num");
		String qc_chk = request.getParameter("qc_chk");
		String mod = request.getParameter("mod");
		int lot_num = Integer.parseInt(slot_num);
		int lot_count = Integer.parseInt(slot_count);
		int order_num = Integer.parseInt(sorder_num);
		LotDTO lotDTO = new LotDTO();
		lotDTO.setLot_num(lot_num);
		lotDTO.setLot_count(lot_count);
		lotDTO.setOrder_num(order_num);
		lotDTO.setQc_chk(qc_chk);
		lotDTO.setMod(mod);
		LotService service = new LotService();
		service.lotService(lotDTO);
		response.sendRedirect("lot");

	}

	protected void lotAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String sdefective_num = request.getParameter("defective_num");
//		String slot_count = request.getParameter("lot_count");
		String sorder_num = request.getParameter("order_num");
		String qc_chk = request.getParameter("qc_chk");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
//		int lot_count = Integer.parseInt(slot_count);
		int order_num = Integer.parseInt(sorder_num);
		LotDTO lotDTO = new LotDTO();
//		lotDTO.setLot_count(lot_count);
		lotDTO.setOrder_num(order_num);
		lotDTO.setQc_chk(qc_chk);
		lotDTO.setMod(mod);
		
		LotService service = new LotService();
		
		service.lotService(lotDTO);

		response.sendRedirect("lot");
	}

	protected void lotDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slot_num = request.getParameter("lot_num");
		String mod = request.getParameter("mod");
		int lot_num = Integer.parseInt(slot_num);
		LotDTO lotDTO = new LotDTO();
		lotDTO.setLot_num(lot_num);
		lotDTO.setMod(mod);
		LotService service = new LotService();
		System.out.println("lotAdd留덉�留�: " + service.lotService(lotDTO));
		response.sendRedirect("lot");
	}

}
