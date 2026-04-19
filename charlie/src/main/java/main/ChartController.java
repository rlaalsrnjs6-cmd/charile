package main;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/chart")
public class ChartController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/chart의 doGet실행!");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		ChartService cs = new ChartService();
		
		Map map = cs. selectMdm();
		
		request.setAttribute("map", map);
		request.getRequestDispatcher("/chart.jsp").forward(request, response);
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
