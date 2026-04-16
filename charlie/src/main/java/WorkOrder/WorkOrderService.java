package WorkOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Emp.EmpDTO;
import fileLibrary.CommonDTO;

public class WorkOrderService {
	Map select(WorkOrderDTO dto, CommonDTO pageing){
		WorkOrderDAO dao = new WorkOrderDAO();
		

		pageing.setTableName("work_order");

        // 페이지에서 보여줄 항목 몇개인지 개수 리턴
        int totalCount = dao.getTotalCount();
        System.out.println("토탈사이즈: " + totalCount);
        

        int size = pageing.getSize(); // 한 페이지에서 보여줄 개수
        int page = pageing.getPage(); // 시작 페이지

        int section = pageing.getSection(); // N 페이지씩 하기

        int start = 0, end = 0;

        System.out.println("서비스사이즈:"+size);
        System.out.println("서비스페이지:"+page);
        // 페이지에서 보여줄 마지막 번호
        end = size * page;
        // 페이지에서 보여줄 시작 번호
        start = end - (size - 1);

        pageing.setEnd(end);
        pageing.setStart(start);
        System.out.println("서비스end: " + pageing.getEnd());
        System.out.println("서비스end: " + pageing.getStart());
        Map map = new HashMap();
        // 생산관리에 있는 기존 DB만 select
        System.out.println("order서비스mod: " + dto.getMod());
        List<WorkOrderDTO> list = dao.select(dto, pageing);
        System.out.println("서비스의 list: " + list);

        map.put("list", list); // list
        map.put("totalCount", totalCount);
        map.put("commonDTO", pageing); // common DTO

        return map;
	}
	 
	int orderService(WorkOrderDTO dto){
		WorkOrderDAO dao = new WorkOrderDAO();
		int list = dao.orderDAO(dto);
		return list ;
	}
}
