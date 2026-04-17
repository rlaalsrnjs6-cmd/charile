package IOLog;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Lot.LotDTO;
import Lot.LotService;
import fileLibrary.CommonDTO;

@WebServlet("/log")
public class IOLogControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slog_num = request.getParameter("log_num");
		String ssize = request.getParameter("size");
		String spage = request.getParameter("page");
		String mod = request.getParameter("mod");
		int size = 10;
		int page = 1;
		if (ssize != null && spage != null) {
			size = Integer.parseInt(ssize);
			page = Integer.parseInt(spage);
		}
		int log_num = -1;
		if (slog_num != null) {
			log_num = Integer.parseInt(slog_num);
		}
		IOLogDTO LogDTO = new IOLogDTO();
		CommonDTO pageing = new CommonDTO();
		pageing.setSize(size);
		pageing.setPage(page);
		LogDTO.setLog_num(log_num);
		LogDTO.setMod(mod);
		IOLogservice service = new IOLogservice();
		Map list = service.select(LogDTO, pageing);
		System.out.println("Log마지막 " + list);
		request.setAttribute("map", list);
		if ("detail".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/log/logDetail.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			logDelete(request, response);
			return;
		} else {
			LotDTO LotDTO = new LotDTO();
			LotService lotSV = new LotService();
			List<LotDTO> lot = lotSV.selectall(LotDTO);
			request.setAttribute("lot", lot);
			if ("up".equals(mod)) {
				request.getRequestDispatcher("WEB-INF/views/log/logUp.jsp").forward(request, response);
				return;
			} else if ("add".equals(mod)) {
				request.getRequestDispatcher("WEB-INF/views/log/logAdd.jsp").forward(request, response);
				return;
			}
		}
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

	protected void logUP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slog_num = request.getParameter("log_num");
		String sio_time = request.getParameter("io_time");
		String io_type = request.getParameter("io_type");
		String slot_num = request.getParameter("lot_num");
		String mod = request.getParameter("mod");
		
		Date io_time = Date.valueOf(sio_time);
		int lot_num = Integer.parseInt(slot_num);
		int log_num = Integer.parseInt(slog_num);
		IOLogDTO logDTO = new IOLogDTO();
		logDTO.setLog_num(log_num);
		logDTO.setIo_time(io_time);
		logDTO.setIo_type(io_type);
		logDTO.setLot_num(lot_num);
		logDTO.setMod(mod);
		IOLogservice service = new IOLogservice();
		service.logService(logDTO);
		response.sendRedirect("log");

	}

	protected void logAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String sio_time = request.getParameter("io_time");
		String io_type = request.getParameter("io_type");
		String slot_num = request.getParameter("lot_num");
		String mod = request.getParameter("mod");
		Date io_time = Date.valueOf(sio_time);
		int lot_num = Integer.parseInt(slot_num);
		IOLogDTO logDTO = new IOLogDTO();
		logDTO.setIo_time(io_time);
		logDTO.setIo_type(io_type);
		logDTO.setLot_num(lot_num);
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
		System.out.println("logAdd留덉�留�: " + service.logService(logDTO));
		response.sendRedirect("log");
	}

}
