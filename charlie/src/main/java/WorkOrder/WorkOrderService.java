package WorkOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bom.BomDTO;
import ProductionManagement.ProductionManagementDTO;
import fileLibrary.CommonDTO;
import io.IoDTO;

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
	 
	public List<WorkOrderDTO> selectall(WorkOrderDTO dto){
		WorkOrderDAO dao = new WorkOrderDAO();
		List list = dao.selectall(dto);
		return list;
	}
	public List<WorkOrderDTO> selectalll(WorkOrderDTO dto){
		WorkOrderDAO dao = new WorkOrderDAO();
		List list = dao.selectalll(dto);
		return list;
	}
	
	WorkOrderDTO orderService(WorkOrderDTO dto){
		ProductionManagementDTO pmDTO = new ProductionManagementDTO();
		WorkOrderDAO dao = new WorkOrderDAO();
		WorkOrderDTO list = dao.orderDAO(dto);
		System.out.println("dljkdsalkjsdahlisaf"+dto.getProd_num());
		ProductionManagementDTO pm = dao.selectMdm(dto);
		System.out.println("pmpmpmpmpmpm:"+pm);
		System.out.println("실행실행실행실행:::"+dto.getMod());
		if ("add".equals(dto.getMod())) {
			System.out.println("들어옴들어옴들어옴들어옴");
			IoDTO ioDTO = new IoDTO();
			ioDTO.setMdm_num(pm.getMdm_num()); // 제품 mdm_num
			System.out.println("실행2실행2실행2");
			List bomList = dao.selectBom(ioDTO); // bom mdm_num 필요량
			System.out.println("실행3실행3실행3:"+bomList);
			for( int i=0; i<bomList.size(); i++ ) {
				System.out.println("실행4실4실행4");
				System.out.println("인서트인서트인서트인서트인서트:::::"+bomList.get(i)); 
				BomDTO bomDTO = (BomDTO)bomList.get(i);
//				bomDTO.getMdm_num(); 재료
//				bomDTO.getRequired_weight(); 필요량!
				
				dao.ioInsert(dto, bomDTO);
				
			}
		}
		return list ;
	}
}
