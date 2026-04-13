package IOLog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Warehouse.WarehouseDTO;
import Warehouse.WarehouseService;

@WebServlet("/log")
public class IOLogControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slog_num = request.getParameter("log_num");
		String mod = request.getParameter("mod");

		int log_num = -1;
		if (slog_num != null) {
			log_num = Integer.parseInt(slog_num);
		}
		IOLogDTO LogDTO = new IOLogDTO();
		LogDTO.setLog_num(log_num);
		LogDTO.setMod(mod);
		IOLogservice service = new IOLogservice();
		List<IOLogDTO> list = service.select(LogDTO);
		System.out.println("Log컨트롤마지막: "+list);
		request.setAttribute("log", list);
		if ("detail".equals(mod)) {
			System.out.println("디테일로고고씽");
			request.getRequestDispatcher("WEB-INF/views/log/logDetail.jsp").forward(request, response);
			return;
		} else if ("up".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/log/logUp.jsp").forward(request, response);
			return;
		} else if ("add".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/log/logAdd.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			logDelete(request, response);
			return;
		}
		System.out.println("리스트로 고고씽");
		request.getRequestDispatcher("WEB-INF/views/log/logList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		if ("add".equals(mod)) {
			logAdd(request, response);
		} else if ("up".equals(mod)) {
			logUP(request, response);
		} else if ("delete".equals(mod)) {
			logDelete(request, response);
		}

	}

	protected void logUP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slog_num = request.getParameter("log_num");
		String io_type = request.getParameter("io_type");
		String slot_num = request.getParameter("lot_num");
		String smdm_num = request.getParameter("mdm_num");
		String mod = request.getParameter("mod");
		int log_num = Integer.parseInt(slog_num);
		int lot_num = Integer.parseInt(slot_num);
		int mdm_num = Integer.parseInt(smdm_num);
		IOLogDTO logDTO = new IOLogDTO();
		logDTO.setLog_num(log_num);
		logDTO.setIo_type(io_type);
		logDTO.setLot_num(lot_num);
		logDTO.setMdm_num(mdm_num);
		logDTO.setMod(mod);
		IOLogservice service = new IOLogservice();
		service.logService(logDTO);
		response.sendRedirect("log");

	}

	protected void logAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String slog_num = request.getParameter("log_num");
		String io_type = request.getParameter("io_type");
		String slot_num = request.getParameter("lot_num");
		String smdm_num = request.getParameter("mdm_num");
		String mod = request.getParameter("mod");
		int log_num = Integer.parseInt(slog_num);
		int lot_num = Integer.parseInt(slot_num);
		int mdm_num = Integer.parseInt(smdm_num);
		IOLogDTO logDTO = new IOLogDTO();
		logDTO.setLog_num(log_num);
		logDTO.setIo_type(io_type);
		logDTO.setLot_num(lot_num);
		logDTO.setMdm_num(mdm_num);
		logDTO.setMod(mod);
		IOLogservice service = new IOLogservice();
		service.logService(logDTO);
		response.sendRedirect("log");
	}

	protected void logDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slog_num = request.getParameter("log_num");
		String mod = request.getParameter("mod");
		int log_num = Integer.parseInt(slog_num);
		IOLogDTO logDTO = new IOLogDTO();
		logDTO.setLog_num(log_num);
		logDTO.setMod(mod);
		IOLogservice service = new IOLogservice();
		System.out.println("logAdd마지막: " + service.logService(logDTO));
		response.sendRedirect("log");
	}

}
