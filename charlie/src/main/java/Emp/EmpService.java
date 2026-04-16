package Emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fileLibrary.CommonDTO;

public class EmpService {
//	public List<EmpDTO> select(EmpDTO dto){
//		EmpDAO dao = new EmpDAO();
//		List list = dao.select(dto);
//		return list;
//	}

	public Map select(EmpDTO dto){
		CommonDTO pageing = new CommonDTO();
		EmpDAO dao = new EmpDAO();
		
		pageing.setTableName("emp");

        // 페이지에서 보여줄 항목 몇개인지 개수 리턴
        int totalCount = dao.getTotalCount();

        int size = pageing.getSize(); // 한 페이지에서 보여줄 개수
        int page = pageing.getPage(); // 시작 페이지

        int section = pageing.getSection(); // N 페이지씩 하기

        int start = 0, end = 0;

        // 페이지에서 보여줄 마지막 번호
        end = size * page;
        // 페이지에서 보여줄 시작 번호
        start = end - (size - 1);

        pageing.setStart(start);
        pageing.setEnd(end);
        Map map = new HashMap();
        // 생산관리에 있는 기존 DB만 select
        System.out.println("emp서비스mod: "+dto.getMod());
		System.out.println("emp서비스id: "+dto.getId());
		System.out.println("emp서비스pw: "+dto.getPw());
		System.out.println("emp서비스스타트: "+pageing.getStart());
		System.out.println("emp서비스엔드: "+pageing.getEnd());
        List<EmpDTO> list = dao.select(dto, pageing);
        System.out.println("서비스의 list: " + list);
        
        map.put("list", list); // list
        map.put("totalCount", totalCount);
        map.put("commonDTO", pageing); // common DTO

        return map;
	}
	
	int insert(EmpDTO dto){
		CommonDTO pageing = new CommonDTO();
		System.out.println("서비스 첫번쨰 인서트 확인");
		EmpDAO dao = new EmpDAO();
		List list = null;
		if("add".equals(dto.getMod())) {
		list = dao.select(dto,pageing);
		}
		int insert = -1;
		System.out.println("서비스 if 조건 확인: " + list.size());
		if(list.size()<1) {
			System.out.println("인서트 시작");
			insert = dao.insert(dto);
		}
		return insert;
	}
	int empService(EmpDTO dto){
		EmpDAO dao = new EmpDAO();
		int list = dao.empDAO(dto);
		return list ;
	}
}
