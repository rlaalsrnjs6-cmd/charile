package WorkOrder;

import java.util.List;

import PersonalHygiene.PersonalHygieneDAO;
import PersonalHygiene.PersonalHygieneDTO;

public class WorkOrderService {
	List<WorkOrderDTO> select(WorkOrderDTO dto){
		WorkOrderDAO dao = new WorkOrderDAO();
		List list = dao.select(dto);
		return list;
	}
	
	int orderService(WorkOrderDTO dto){
		WorkOrderDAO dao = new WorkOrderDAO();
		int list = dao.orderDAO(dto);
		return list ;
	}
}
