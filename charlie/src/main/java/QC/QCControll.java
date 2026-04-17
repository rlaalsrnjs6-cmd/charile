package QC;

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
import Lot.LotDTO;
import Lot.LotService;
import fileLibrary.CommonDTO;

@WebServlet("/qc")
public class QCControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sqc_num = request.getParameter("qc_num");
		String mod = request.getParameter("mod");
		String ssize = request.getParameter("size");
		String spage = request.getParameter("page");
		int size = 10;
		int page = 1;
		if(ssize!=null && spage!=null) {
			size = Integer.parseInt(ssize);
			page = Integer.parseInt(spage);
		}
		int qc_num = -1;
		if (sqc_num != null) {
			System.out.println("qcif�솗�씤");
			qc_num = Integer.parseInt(sqc_num);
		}
		QCDTO qcDTO = new QCDTO();
		CommonDTO commonDTO= new CommonDTO();
		commonDTO.setSize(size);
		commonDTO.setPage(page);
		qcDTO.setQc_num(qc_num);
		qcDTO.setMod(mod);
		QCService service = new QCService();
		// �닔�젙 
		
		Map map = service.select(qcDTO, commonDTO);
		System.out.println("qc而⑦듃濡쨗ap: " + map);
		request.setAttribute("map", map);
		
		if ("detail".equals(mod)) 
		{
			System.out.println("qc디테일입장들어가요오오오오오오오오오오옹");
			request.getRequestDispatcher("WEB-INF/views/qc/qcDetail.jsp").forward(request, response);
			return;
		}else if ("delete".equals(mod)) {
			qcDelete(request, response);
			return;
		} else {

			System.out.println("qcadd�옉�룞");
			LotService lotservice = new LotService();
			EmpService empservice = new EmpService();
			List qc = service.selectall(qcDTO);
			EmpDTO empDTO = new EmpDTO();
			LotDTO lotDTO = new LotDTO();
			if ("up".equals(mod)) {
				lotDTO.setQc_num(qc_num);
				lotDTO.setMod(mod);
				List<LotDTO> lotlist = lotservice.selectall(lotDTO);
				List emplist = empservice.selectall(empDTO);
				request.setAttribute("lot", lotlist);
				request.setAttribute("emp", emplist);
				System.out.println("qc리스트 emp:" + emplist);
				System.out.println("qc리스트 lot:" + lotlist);
				request.getRequestDispatcher("WEB-INF/views/qc/qcUp.jsp").forward(request, response);
				return;
			} else if ("add".equals(mod)) {
				
				List<LotDTO> lotlist = lotservice.selectall(lotDTO);
				List emplist = empservice.selectall(empDTO);
				request.setAttribute("lot", lotlist);
				request.setAttribute("emp", emplist);
				request.getRequestDispatcher("WEB-INF/views/qc/qcAdd.jsp").forward(request, response);
				return;
			} 
		}
		request.getRequestDispatcher("WEB-INF/views/qc/qcList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		if ("add".equals(mod)) {
			qcAdd(request, response);
		} else if ("up".equals(mod)) {
			qcUP(request, response);
		} else if ("delete".equals(mod)) {
			qcDelete(request, response);
		}

	}

	protected void qcUP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sqc_num = request.getParameter("qc_num");
		String slot_num = request.getParameter("lot_num");
		String sempno = request.getParameter("empno");
			String slot_count = request.getParameter("lot_count");
			String qc_chk = request.getParameter("qc_chk");
		String mod = request.getParameter("mod");
		int qc_num = Integer.parseInt(sqc_num);
		int lot_num = Integer.parseInt(slot_num);
		int empno = Integer.parseInt(sempno);
			int	lot_count = Integer.parseInt(slot_count);
		QCDTO qcDTO = new QCDTO();
			LotDTO lotDTO = new LotDTO();
		System.out.println(qc_num);
		System.out.println(lot_num);
		System.out.println(empno);
		qcDTO.setQc_num(qc_num);
		qcDTO.setLot_num(lot_num);
		qcDTO.setEmpno(empno);
		qcDTO.setMod(mod);
			lotDTO.setLot_count(lot_count);
			lotDTO.setQc_chk(qc_chk);
			lotDTO.setLot_num(lot_num);
			lotDTO.setMod(mod);
		QCService service = new QCService();
			LotService lotservice = new LotService();
		service.qcService(qcDTO);
			lotservice.lotQcService(lotDTO);
		response.sendRedirect("qc");

	}

	protected void qcAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

//		String sqc_num = request.getParameter("qc_num");
		String slot_num = request.getParameter("lot_num");
		String sempno = request.getParameter("empno");
		String sqc_date = request.getParameter("qc_date");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
//		int qc_num = Integer.parseInt(sqc_num);
		int lot_num = Integer.parseInt(slot_num);
		int empno = Integer.parseInt(sempno);
		Date qc_date = Date.valueOf(sqc_date);

		QCDTO qcDTO = new QCDTO();
//		qcDTO.setQc_num(qc_num);
		qcDTO.setLot_num(lot_num);
		qcDTO.setEmpno(empno);
		qcDTO.setQc_date(qc_date);
		qcDTO.setMod(mod);

		QCService service = new QCService();

		System.out.println("qcAdd留덉�留�: " + service.qcService(qcDTO));

		response.sendRedirect("qc");
	}

	protected void qcDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sqc_num = request.getParameter("qc_num");
		String mod = request.getParameter("mod");
		int qc_num = Integer.parseInt(sqc_num);
		QCDTO qcDTO = new QCDTO();
		qcDTO.setQc_num(qc_num);
		qcDTO.setMod(mod);
		QCService service = new QCService();
		System.out.println("qcAdd留덉�留�: " + service.qcService(qcDTO));
		response.sendRedirect("qc");
	}
}
