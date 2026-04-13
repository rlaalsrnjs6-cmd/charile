package Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReprtDetailSelectAction implements Command{

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
    	int postNum = Integer.parseInt(request.getParameter("post_num"));
		ReportService sv = new ReportService();
		BoardDTO result = sv.detailSelect(postNum);
		request.setAttribute("dto", result);
        return "reportDetail.jsp"; 
    }
}
