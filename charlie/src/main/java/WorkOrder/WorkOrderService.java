package WorkOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Emp.EmpDAO;
import Emp.EmpDTO;
import fileLibrary.CommonDTO;

public class WorkOrderService {
	Map select(WorkOrderDTO dto, CommonDTO pageing){
		WorkOrderDAO dao = new WorkOrderDAO();
		

		pageing.setTableName("work_order");

        // �럹�씠吏��뿉�꽌 蹂댁뿬以� �빆紐� 紐뉕컻�씤吏� 媛쒖닔 由ы꽩
        int totalCount = dao.getTotalCount();
        System.out.println("토탈카운트 " + totalCount);
        

        int size = pageing.getSize(); // �븳 �럹�씠吏��뿉�꽌 蹂댁뿬以� 媛쒖닔
        int page = pageing.getPage(); // �떆�옉 �럹�씠吏�

        int section = pageing.getSection(); // N �럹�씠吏��뵫 �븯湲�

        int start = 0, end = 0;

        System.out.println("order서비스사이즈:"+size);
        System.out.println("order서비스페이지:"+page);
        // �럹�씠吏��뿉�꽌 蹂댁뿬以� 留덉�留� 踰덊샇
        end = size * page;
        // �럹�씠吏��뿉�꽌 蹂댁뿬以� �떆�옉 踰덊샇
        start = end - (size - 1);

        pageing.setEnd(end);
        pageing.setStart(start);
        System.out.println("order서비스end: " + pageing.getEnd());
        System.out.println("order서비스start: " + pageing.getStart());
        Map map = new HashMap();
        // �깮�궛愿�由ъ뿉 �엳�뒗 湲곗〈 DB留� select
        System.out.println("order�꽌鍮꾩뒪mod: " + dto.getMod());
        List<WorkOrderDTO> list = dao.select(dto, pageing);
        System.out.println("�꽌鍮꾩뒪�쓽 list: " + list);

        map.put("list", list); // list
        map.put("totalCount", totalCount);
        map.put("commonDTO", pageing); // common DTO

        return map;
	}
	 
	public List<WorkOrderService> selectall(WorkOrderDTO dto){
		WorkOrderDAO dao = new WorkOrderDAO();
		List list = dao.selectall(dto);
		return list;
	}
	
	int orderService(WorkOrderDTO dto){
		WorkOrderDAO dao = new WorkOrderDAO();
		int list = dao.orderDAO(dto);
		return list ;
	}
}
