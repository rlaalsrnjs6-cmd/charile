package IOLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Defective.DefectiveDAO;
import Defective.DefectiveDTO;
import Lot.LotDAO;
import Lot.LotDTO;
import fileLibrary.CommonDTO;


public class IOLogservice {
	Map select(IOLogDTO dto, CommonDTO pageing){
		IOLogDAO dao = new IOLogDAO();
		System.out.println("log서비스 시작");

		pageing.setTableName("io_log");

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
        System.out.println("log서비스end: " + pageing.getEnd());
        System.out.println("log서비스start: " + pageing.getStart());
        Map map = new HashMap();
        // 생산관리에 있는 기존 DB만 select
        System.out.println("log서비스mod: " + dto.getMod());
        List<IOLogDTO> list = dao.select(dto, pageing);
        System.out.println("log서비스의 list: " + list);

        map.put("list", list); // list
        map.put("totalCount", totalCount);
        map.put("commonDTO", pageing); // common DTO

        return map;
	}
		
	public List<IOLogDTO> selectall(IOLogDTO dto){
		IOLogDAO dao = new IOLogDAO();
		List list = dao.selectall(dto);
		return list;
	}
	
	int logService(IOLogDTO dto){
		System.out.println("로그딜리트서비스");
		IOLogDAO dao = new IOLogDAO();
		int list = dao.logDAO(dto);
		return list ;
	}
}
