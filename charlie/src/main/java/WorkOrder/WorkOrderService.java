package WorkOrder;

import java.util.List;

public class WorkOrderService {
	List<WorkOrderDTO> select(WorkOrderDTO dto){
		WorkOrderDAO dao = new WorkOrderDAO();
		List list = dao.select(dto);
		return list;
	}
}
