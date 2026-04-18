package Defective;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Lot.LotDTO;
import Lot.LotService;
import QC.QCDTO;
import QC.QCService;
import fileLibrary.CommonDTO;

@WebServlet("/defective")
public class DefectiveControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sdefective_num = request.getParameter("defective_num");
		String ssize = request.getParameter("size");
		String spage = request.getParameter("page");
		String mod = request.getParameter("mod");

		int size = 10;
		int page = 1;
		if (ssize != null && spage != null) {
			size = Integer.parseInt(ssize);
			page = Integer.parseInt(spage);
		}

		int defective_num = -1;
		if (sdefective_num != null) {
			defective_num = Integer.parseInt(sdefective_num);
		}
		DefectiveDTO defectiveDTO = new DefectiveDTO();
		CommonDTO pageing = new CommonDTO();
		pageing.setSize(size);
		pageing.setPage(page);
		defectiveDTO.setDefective_num(defective_num);
		defectiveDTO.setMod(mod);
		DefectiveService service = new DefectiveService();
		Map list = service.select(defectiveDTO, pageing);
		request.setAttribute("map", list);
		System.out.println("디펜시브 확인:"+list);
		if ("detail".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/defective/defectiveDetail.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			defectiveDelete(request, response);
			return;
		} else {
			LotDTO LotDTO = new LotDTO();
			LotService lotSV = new LotService();
			List<LotDTO> lot = lotSV.selectall(LotDTO);
			request.setAttribute("lot", lot);
			if ("up".equals(mod)) {
				request.getRequestDispatcher("WEB-INF/views/defective/defectiveUp.jsp").forward(request, response);
				return;
			} else if ("add".equals(mod)) {
				request.getRequestDispatcher("WEB-INF/views/defective/defectiveAdd.jsp").forward(request, response);
				return;
			}
		}
		request.getRequestDispatcher("WEB-INF/views/defective/defectiveList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		System.out.println("mod확인포스트"+mod);
		if ("add".equals(mod)) {
			defectiveAdd(request, response);
		} else if ("up".equals(mod)) {
			defectiveUP(request, response);
		} else if ("delete".equals(mod)) {
			defectiveDelete(request, response);
		}

	}

	protected void defectiveUP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sdefective_num = request.getParameter("defective_num");
		String category = request.getParameter("category");
		String scount = request.getParameter("count");
		String slot_num = request.getParameter("lot_num");
		String action = request.getParameter("action");
		String mod = request.getParameter("mod");
		int defective_num = Integer.parseInt(sdefective_num);
		int count = Integer.parseInt(scount);
		int lot_num = Integer.parseInt(slot_num);
		DefectiveDTO defectiveDTO = new DefectiveDTO();
		defectiveDTO.setDefective_num(defective_num);
		defectiveDTO.setCategory(category);
		defectiveDTO.setCount(count);
		defectiveDTO.setLot_num(lot_num);
		defectiveDTO.setAction(action);
		defectiveDTO.setMod(mod);
		DefectiveService service = new DefectiveService();
		service.defectiveService(defectiveDTO);
		response.sendRedirect("defective");

	}

	protected void defectiveAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String slot_num = request.getParameter("lot_num");
		String category = request.getParameter("category");
		String scount = request.getParameter("count");
		String action = request.getParameter("action");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
		int count = Integer.parseInt(scount);
		int lot_num = Integer.parseInt(slot_num);

		DefectiveDTO defectiveDTO = new DefectiveDTO();
		defectiveDTO.setCategory(category);
		defectiveDTO.setCount(count);
		defectiveDTO.setAction(action);
		defectiveDTO.setLot_num(lot_num);
		defectiveDTO.setMod(mod);

		DefectiveService service = new DefectiveService();

		service.defectiveService(defectiveDTO);

		response.sendRedirect("defective");
	}

	protected void defectiveDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sdefective_num = request.getParameter("defective_num");
		String mod = request.getParameter("mod");
		int defective_num = Integer.parseInt(sdefective_num);
		DefectiveDTO defectiveDTO = new DefectiveDTO();
		defectiveDTO.setDefective_num(defective_num);
		defectiveDTO.setMod(mod);
		DefectiveService service = new DefectiveService();
		service.defectiveService(defectiveDTO);
		response.sendRedirect("defective");
	}

}
