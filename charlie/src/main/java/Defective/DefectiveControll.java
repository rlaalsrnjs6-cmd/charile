package Defective;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/defective")
public class DefectiveControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sdefective_num = request.getParameter("defective_num");
		String mod = request.getParameter("mod");

		int defective_num = -1;
		if (sdefective_num != null) {
			System.out.println("qcif확인");
			defective_num = Integer.parseInt(sdefective_num);
		}
		DefectiveDTO defectiveDTO = new DefectiveDTO();
		defectiveDTO.setDefective_num(defective_num);
		defectiveDTO.setMod(mod);
		DefectiveService service = new DefectiveService();
		List<DefectiveDTO> list = service.select(defectiveDTO);
		System.out.println("defective컨트롤마지막: "+list);
		request.setAttribute("defective", list);
		if ("detail".equals(mod)) {
			System.out.println("디테일로고고씽");
			request.getRequestDispatcher("defectiveDetail.jsp").forward(request, response);
			return;
		} else if ("up".equals(mod)) {
			request.getRequestDispatcher("defectiveUp.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			defectiveDelete(request, response);
			return;
		}
		System.out.println("리스트로 고고씽");
		request.getRequestDispatcher("defectiveList.jsp").forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		if ("add".equals(mod)) {
			defectiveAdd(request, response);
		} else if ("up".equals(mod)) {
			defectiveUP(request, response);
		} else if ("delete".equals(mod)) {
			defectiveDelete(request, response);
		}

	}

	protected void defectiveUP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sdefective_num = request.getParameter("defective_num");
		String category = request.getParameter("category");
		String scount = request.getParameter("count");
		String sqc_num = request.getParameter("qc_num");
		String action = request.getParameter("action");
		String smdm_num = request.getParameter("mdm_num");
		String mod = request.getParameter("mod");
		int defective_num = Integer.parseInt(sdefective_num);
		int count = Integer.parseInt(scount);
		int qc_num = Integer.parseInt(sqc_num);
		int mdm_num = Integer.parseInt(smdm_num);
		DefectiveDTO defectiveDTO = new DefectiveDTO();
		defectiveDTO.setDefective_num(defective_num);
		defectiveDTO.setCategory(category);
		defectiveDTO.setCount(count);
		defectiveDTO.setQc_num(qc_num);
		defectiveDTO.setAction(action);
		defectiveDTO.setMdm_num(mdm_num);
		defectiveDTO.setMod(mod);
		DefectiveService service = new DefectiveService();
		service.defectiveService(defectiveDTO);
		response.sendRedirect("defective");

	}

	protected void defectiveAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		String sdefective_num = request.getParameter("defective_num");
		String sqc_num = request.getParameter("qc_num");
		String smdm_num = request.getParameter("mdm_num");
		String category = request.getParameter("category");
		String scount = request.getParameter("count");
		String action = request.getParameter("action");
		String mod = request.getParameter("mod");
		System.out.println("up:" + mod);
		int defective_num = Integer.parseInt(sdefective_num);
		int count = Integer.parseInt(scount);
		int mdm_num = Integer.parseInt(smdm_num);
		int qc_num = Integer.parseInt(sqc_num);

		DefectiveDTO defectiveDTO = new DefectiveDTO();
		defectiveDTO.setDefective_num(defective_num);
		defectiveDTO.setCategory(category);
		defectiveDTO.setCount(count);
		defectiveDTO.setAction(action);
		defectiveDTO.setMdm_num(mdm_num);
		defectiveDTO.setQc_num(qc_num);
		defectiveDTO.setMod(mod);

		DefectiveService service = new DefectiveService();

		System.out.println("DefectiveAdd마지막: " + service.defectiveService(defectiveDTO));

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
		System.out.println("qcAdd마지막: " + service.defectiveService(defectiveDTO));
		response.sendRedirect("defective");
	}

}
