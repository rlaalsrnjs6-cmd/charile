import java.util.List;

public class InspectionService {

    private InspectionDAO dao = new InspectionDAO();

    public List<InspectionDTO> list(String name, String status, String date) {
        return dao.selectList(name, status, date);
    }
}