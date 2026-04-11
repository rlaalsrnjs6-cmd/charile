package QC;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PersonalHygiene.PersonalHygieneDTO;

@WebServlet("/qc")
public class QCControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sqc_num = request.getParameter("qc_num");
		String mod = request.getParameter("mod");
		
		int qc_num = -1;
		System.out.println("qc컨트롤"+sqc_num);
		if(sqc_num != null) {
			System.out.println("qcif확인");
			qc_num = Integer.parseInt(sqc_num);
		}
		QCDTO qcDTO = new QCDTO();
		qcDTO.setQc_num(qc_num);
		qcDTO.setMod(mod);
		QCService service = new QCService();
		List<QCDTO> list = service.select(qcDTO);
		System.out.println(list);
		request.setAttribute("qc",list);
		if("detail".equals(mod)) {
			System.out.println("디테일로고고씽");
			request.getRequestDispatcher("qcDetail.jsp").forward(request, response);
			return;
		}else if("up".equals(mod)) {
			request.getRequestDispatcher("qcUp.jsp").forward(request, response);
			return;
		}else if("delete".equals(mod)) {
			request.getRequestDispatcher("qcDelete.jsp").forward(request, response);
			return;
		}
		System.out.println("리스트로 고고씽");
		request.getRequestDispatcher("qcList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		if("add".equals(mod)) {
			request.getRequestDispatcher("qcDetail.jsp").forward(request, response);
			return;
		}else if("up".equals(mod)) {
			qcUP(request,response);
		}else if("delete".equals(mod)) {
			qcDelete(request,response);
		}
		
	}
	protected void qcUP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sqc_num = request.getParameter("qc_num");
		String slot_num = request.getParameter("lot_num");
		String sempno = request.getParameter("empno");
		String mod = request.getParameter("mod");
		int qc_num = Integer.parseInt(sqc_num); 
		int lot_num = Integer.parseInt(slot_num); 
		int empno = Integer.parseInt(sempno); 
		QCDTO qcDTO = new QCDTO();
		System.out.println(qc_num);
		System.out.println(lot_num);
		System.out.println(empno);
		qcDTO.setQc_num(qc_num);
		qcDTO.setLot_num(lot_num);
		qcDTO.setEmpno(empno);
		qcDTO.setMod(mod);
		QCService service = new QCService();
		service.qcService(qcDTO);
		response.sendRedirect("qc");
		
	}
	protected void qcDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
	}
}
