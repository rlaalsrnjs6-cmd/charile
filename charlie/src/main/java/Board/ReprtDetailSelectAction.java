package Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReprtDetailSelectAction implements Command{

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("리포트 디테일 액션 들어옴");
		int postNum = Integer.parseInt(request.getParameter("post_num"));
		ReportService sv = new ReportService();
		BoardDTO result = sv.detailSelect(postNum);
		request.setAttribute("dto", result);
		
		String uri = request.getRequestURI();
		System.out.println("현재 뽑힌 URI: " + uri);
		if (uri.contains("updateForm.report")) {
			System.out.println("reportUpdate.jsp로 이동");
            return "reportUpdate.jsp"; // 수정하기 버튼 눌러서 오면 폼 주소로 리턴
        }
        return "reportDetail.jsp"; 
    }
}
