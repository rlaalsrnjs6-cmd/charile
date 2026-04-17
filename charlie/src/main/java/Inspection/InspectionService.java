import java.util.List;

public class InspectionService {

    private InspectionDAO dao = new InspectionDAO();

    public List<InspectionDTO> list(String status, String date, int page) {
        return dao.selectList(status, date, page);
    }
}