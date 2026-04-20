package Lot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Defective.DefectiveDAO;
import Defective.DefectiveDTO;
import WorkOrder.WorkOrderDAO;
import WorkOrder.WorkOrderDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentService;

public class LotService{

	Map select(LotDTO dto, CommonDTO pageing){
		LotDAO dao = new LotDAO();
		System.out.println("lot서비스 시작");

		pageing.setTableName("lot");

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
        System.out.println("lot서비스end: " + pageing.getEnd());
        System.out.println("lot서비스end: " + pageing.getStart());
        Map map = new HashMap();
        // 생산관리에 있는 기존 DB만 select
        System.out.println("lot서비스mod: " + dto.getMod());
        List<LotDTO> list = dao.select(dto, pageing);
        System.out.println("lot서비스의 list: " + list);

        map.put("list", list); // list
        map.put("totalCount", totalCount);
        map.put("commonDTO", pageing); // common DTO

        return map;
	}
	
	public List<LotDTO> selectall(LotDTO dto){
		LotDAO dao = new LotDAO();
		List list = dao.selectall(dto);
		return list;
	}
	public List<LotDTO> selectalll(LotDTO dto){
		LotDAO dao = new LotDAO();
		List list = dao.selectalll(dto);
		return list;
	}
	
	
	public int lotService(LotDTO dto){
		LotDAO dao = new LotDAO();
		WorkOrderDTO order = dao.selectallll(dto);
		int list = dao.lotDAO(dto, order);
		return list ;
	}
	
	public int lotQcService(LotDTO dto){
		LotDAO dao = new LotDAO();
		int list = dao.lotQcDAO(dto);
		return list ;
	}

}
