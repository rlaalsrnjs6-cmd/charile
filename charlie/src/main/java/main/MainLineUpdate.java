package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LineUpdate")
public class MainLineUpdate extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		System.out.println("/LineUpdate의 doPost 실행!!");
		
		String lineName = request.getParameter("lineName");
		int lineStatus = Integer.parseInt(request.getParameter("lineStatus"));
		
		MainDTO dto = new MainDTO();
		dto.setLineName(lineName);
		dto.setLineStatus(lineStatus);
		
		MainService ms = new MainService();
		
		int result = ms.lineUpdate(dto);
		System.out.println("result: " + result);
		
		if(result > 0) {
			response.getWriter().write("ok");
		}else {
			response.getWriter().write("no");
		}
		response.getWriter().flush();
	}

}
