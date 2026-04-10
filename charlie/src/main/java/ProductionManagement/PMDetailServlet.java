package ProductionManagement;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PMDetailServlet")
public class PMDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/PMDetailServlet의 doGet 실행!!");
		
		int prodNum = Integer.parseInt(request.getParameter("prod_num"));
		
		PMDetailService ps = new PMDetailService();
		
		List list = ps.loadPM(prodNum);
		System.out.println("Servlet의 list");
		request.setAttribute("result", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ProductionManagementDetail.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

}
