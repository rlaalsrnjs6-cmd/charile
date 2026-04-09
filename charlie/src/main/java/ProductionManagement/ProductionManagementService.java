package ProductionManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductionManagementService {
	ProductionManagementDAO dao = new ProductionManagementDAO();
	
	
	public Map loadPM(){ 
		Map map = new HashMap();
		//생산관리에 있는 기존 DB만 select
		List list1 = dao.selectPM();
		
		// 전체목표, 만든 개수, 남은 수량 select
		List list2 = dao.selectData();
		
		//페이지에서 보여줄 항목 몇개인지 개수 리턴
		int pageCount = dao.getTotalCount();
		
		map.put("list1", list1);
		map.put("list2", list2);
		map.put("page", pageCount);
		return map;
	}
	
}
