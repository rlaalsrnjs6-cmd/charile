package PersonalHygiene;

import java.util.List;

import WorkOrder.WorkOrderDAO;
import WorkOrder.WorkOrderDTO;

public class PersonalHygieneService {
	List<PersonalHygieneDTO> select(PersonalHygieneDTO dto){
		PersonalHygieneDAO dao = new PersonalHygieneDAO();
		List list = dao.select(dto);
		return list;
	}
	
	int hygieneService(PersonalHygieneDTO dto){
		PersonalHygieneDAO dao = new PersonalHygieneDAO();
		int list = dao.hygieneDAO(dto);
		return list ;
	}

}
