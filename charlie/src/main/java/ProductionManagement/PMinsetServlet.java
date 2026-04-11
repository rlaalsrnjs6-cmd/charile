package ProductionManagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PMinsetServlet")
public class PMinsetServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		System.out.println("/PMinsetServlet의 doPost 실행!!");
		
		int mdmNum = Integer.parseInt(request.getParameter("mdm_num"));
		String workStart1 = request.getParameter("workStart");
		String workEnd1 = request.getParameter("workEnd");
		
		int targetQuantity = Integer.parseInt(request.getParameter("taget_quantity"));
		String title = request.getParameter("title");
		
		ProductionManagementDTO dto = new ProductionManagementDTO();
		
		//Date로 형변환 
		if(workStart1 != null && ! workStart1.isEmpty()) {
			java.sql.Date workStart = java.sql.Date.valueOf(workStart1);
			dto.setWork_start(workStart);
		}else {
			System.out.println("Date 형식 잘못 됐음");
		}
		if(workEnd1 != null && ! workEnd1.isEmpty()) {
			java.sql.Date workEnd = java.sql.Date.valueOf(workEnd1);
			dto.setWork_end(workEnd);
		}else {
			System.out.println("Date 형식 잘 못 됐음");
		}
		dto.setTarget_quantity(targetQuantity);
		dto.setTitle(title);
		dto.setMdmNum(mdmNum);
		
		ProductionManagementService sv = new ProductionManagementService();
		int result = sv.insert(dto);
		
		if(result > 0) {
			System.out.println("insert 성공");
			response.sendRedirect("production/management");
		}else {
			System.out.println("insert 실패");
		}
		
		
	}

}
