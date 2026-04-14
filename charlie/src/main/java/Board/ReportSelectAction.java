package Board;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReportSelectAction implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		
		
		
		// 값이 없을 때를 대비한 기본값 설정
		int size = 5; // 페이지당 표시 개수
		int page = 1; // 현재 페이지

		String sSize = request.getParameter("size");
		String sPage = request.getParameter("page");

		try {// 원하는 페이지 데이터를 받으면 그 페이지로 값을 넣음
			if (sSize != null) {
				size = Integer.parseInt(sSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 같이 있으면 첫 번째 것이 안 될 때 두 번째 것도 안 되니까 분리
		try {
			if (sPage != null) {
				page = Integer.parseInt(sPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpSession session = request.getSession();
		String selectTitle = request.getParameter("selectTitle");
		if( selectTitle == null) {
			selectTitle = "";
		}
		
		int level = (Integer) session.getAttribute("level");
		int empno = (Integer) session.getAttribute("empno");
		BoardDTO dto = new BoardDTO();
		dto.setSelectTitle(selectTitle);
		dto.setLevel(level);
		dto.setEmpno(empno);
		dto.setSize(size);
		dto.setPage(page);

		ReportService ns = new ReportService();
		Map map = ns.selectList(dto);
		System.out.println("map: " + map);
		request.setAttribute("map", map);


		return "/report.jsp";

	}
}
