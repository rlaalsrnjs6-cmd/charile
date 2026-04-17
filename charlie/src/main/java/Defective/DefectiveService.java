package Defective;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Lot.LotDAO;
import Lot.LotDTO;
import fileLibrary.CommonDTO;


public class DefectiveService {
	Map select(DefectiveDTO dto, CommonDTO pageing){
		DefectiveDAO dao = new DefectiveDAO();
		System.out.println("defective서비스 시작");

		pageing.setTableName("defective");

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
        System.out.println("Defective서비스end: " + pageing.getEnd());
        System.out.println("Defective서비스start: " + pageing.getStart());
        Map map = new HashMap();
        // 생산관리에 있는 기존 DB만 select
        System.out.println("Defective서비스mod: " + dto.getMod());
        List<DefectiveDTO> list = dao.select(dto, pageing);
        System.out.println("Defective서비스의 list: " + list);

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
	
	int defectiveService(DefectiveDTO dto){
			DefectiveDAO dao = new DefectiveDAO();
			int list = dao.defectiveDAO(dto);
			return list ;
		}
}
