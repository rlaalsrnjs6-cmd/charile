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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/production/management의 doGet 실행!!");
		// 요청의 한글 깨짐 방지
		request.setCharacterEncoding("utf-8");
		// 응답의 한글 깨짐 방지
		response.setContentType("text/html; charset=utf-8;");

		// 값이 없을 때를 대비한 기본값 설정
		int size = 5; // 페이지당 표시 개수
		int page = 1; // 현재 페이지
		
		String sSize = request.getParameter("size");
		String sPage = request.getParameter("page");
		
		try {//원하는 페이지 데이터를 받으면 그 페이지로 값을 넣음
			if (sSize != null) {
				size = Integer.parseInt(sSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//같이 있으면 첫 번째 것이 안 될 때 두 번째 것도 안 되니까 분리
		try {
			if (sPage != null) {
				page = Integer.parseInt(sPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ProductionManagementDTO dto = new ProductionManagementDTO();
		dto.setSize(size);
		dto.setPage(page);

		ProductionManagementService sv = new ProductionManagementService();

		// 생산관리에 있는 기존 DB만 select
		// 전체목표, 만든 개수, 남은 수량 select
		// 페이지에서 보여줄 항목 몇개인지 개수 리턴
		// 을 담은 map
		Map map = sv.loadPM(dto);
		System.out.println("들어온 map: " + map);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/ProductionManagement.jsp").forward(request, response);
	}

}
