package ProductionManagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PMDelete")
public class PMDelete extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/PMDelete의 doPost 실행!!");
		
		int prodNum = Integer.parseInt(request.getParameter("prod_num"));
		
		ProductionManagementDTO dto = new ProductionManagementDTO();
		
		dto.setProd_num(prodNum);
		
		PMDetailService sv = new PMDetailService();
		sv.delete(dto);
		
		response.sendRedirect("/charlie/production/management");
		
		
		
		
	}

}
