package Board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Board.Command;
public class ReportInsertAction implements Command{

	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
		ReportService sv = new ReportService();
		int result = sv.insertList();
        return "select.report"; 
    }
}
