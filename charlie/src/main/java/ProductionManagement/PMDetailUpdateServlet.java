package ProductionManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PMDetailUpdateServlet")
public class PMDetailUpdateServlet extends HttpServlet {
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/PMDetailUpdateServlet의  doPost 실행!!");
		//요청의 한글 깨짐 방지
		request.setCharacterEncoding("utf-8");
		//응답의 한글 깨짐 방지
		response.setContentType("text/html; charset=utf-8;");
		String  startDate1 =  request.getParameter("startDate");
		String endDate1 = request.getParameter("endDate");
		
		int prodNum = Integer.parseInt(request.getParameter("prod_num"));
		String title = request.getParameter("title");
//		String mdmName = request.getParameter("mdmName");
		int targetAll =Integer.parseInt(request.getParameter("targetAll"));
//		int currentTarget = Integer.parseInt(request.getParameter("currentTarget"));
//		int remain = Integer.parseInt(request.getParameter("remain"));
		java.sql.Date startDate = java.sql.Date.valueOf(startDate1);
		java.sql.Date endDate = java.sql.Date.valueOf(endDate1);
		
		ProductionManagementDTO dto = new ProductionManagementDTO();
		dto.setProd_num(prodNum);
		dto.setTitle(title);
//		dto.setMdmName(mdmName);
		dto.setTarget_quantity(targetAll);
//		dto.setCurrentCount(currentTarget);
//		dto.setRemainCount(remain);
		dto.setWork_start(startDate);
		dto.setWork_end(endDate);
		
		PMDetailService sv = new PMDetailService();
		 int result = sv.update(dto);
		System.out.println("dao 다녀옴");
		 if(result > 0) {
			 response.sendRedirect(request.getContextPath() + "c/PMDetailServlet?prod_num=" + dto.getProd_num());

		 }else {
			 response.getWriter().println("<script>alert('Fail'); history.back();</script>");
		 }
		
		
	}

}
