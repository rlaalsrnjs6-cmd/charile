package Board;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ProductionManagement.ProductionManagementDTO;

@WebServlet("/notice/controller")
public class NoticeControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		System.out.println("/notice의 doGET 실행");
		
		
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
				
				BoardDTO dto = new BoardDTO();
				dto.setSize(size);
				dto.setPage(page);
		
		NoticeService ns = new NoticeService();
		Map map = ns.loadNotice(dto);
		System.out.println("map: " + map);
		request.setAttribute("map", map);
		System.out.println("/notice에서 map 넘겼음");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/notice.jsp");
		dispatcher.forward(request, response);
		
		
	}



}
