package Board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportDeleteAction implements Command {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		int postNum = Integer.parseInt(request.getParameter("postNum"));
		BoardDTO dto = new BoardDTO();
		dto.setPost_num(postNum);
		ReportService sv = new ReportService();
		
		int result = sv.deleteReport(dto);
		
		
		
		return "select.report";
	}
}

