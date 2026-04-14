package QC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO2;

public class QCService {
	// 수정
	Map select(QCDTO dto){
		
		QCDAO dao = new QCDAO();
		
		CommonDTO commonDTO = new CommonDTO();

        commonDTO.setTableName("qc");

        // 페이지에서 보여줄 항목 몇개인지 개수 리턴
        int totalCount = dao.getTotalCount();

        int size = commonDTO.getSize(); // 한 페이지에서 보여줄 개수
        int page = commonDTO.getPage(); // 시작 페이지

        int section = commonDTO.getSection(); // N 페이지씩 하기

        int start = 0, end = 0;

        // 페이지에서 보여줄 마지막 번호
        end = size * page;
        // 페이지에서 보여줄 시작 번호
        start = end - (size - 1);

        commonDTO.setEnd(end);
        commonDTO.setStart(start);
        Map map = new HashMap();
        // 생산관리에 있는 기존 DB만 select
        List<QCDTO> list = dao.select(dto);
        System.out.println("서비스의 list: " + list);

        map.put("list", list); // list
        map.put("totalCount", totalCount);
        map.put("commonDTO", commonDTO); // common DTO

        return map;
	}
//	public class QCService extends ParentDAO2<QCDTO, CommonDTO> {
//		List<QCDTO> select(QCDTO dto){
//			QCDAO dao = new QCDAO();
//			List list = dao.select(dto);
//		}
	
	
	int qcService(QCDTO dto){
		QCDAO dao = new QCDAO();
		int list = dao.qcDAO(dto);
		return list ;
	}


}
