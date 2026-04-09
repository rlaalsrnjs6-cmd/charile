package ProductionManagement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/production/management")
public class ProductionManagementControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/production/management의 doGet 실행!!");
		//요청의 한글 깨짐 방지
		request.setCharacterEncoding("utf-8");
		//응답의 한글 깨짐 방지
		response.setContentType("text/html; charset=utf-8;");
		
		
		ProductionManagementService sv = new ProductionManagementService();
		
		//생산관리에 있는 기존 데이터 select
		List list = sv.loadPM(); 
		
		// 전체목표, 만든 개수, 남은 수량 select
		List list2 = sv.loadData();
		System.out.println(list2);
		
		Map map = new HashMap();
		map.put("List1", list);
		map.put("List2", list2);
		
		HttpSession session = request.getSession();
		session.setAttribute("map", map);
		request.getRequestDispatcher("/ProductionManagement.jsp").forward(request, response);
	}



}
