package QC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Lot.LotDAO;
import Lot.LotDTO;
import fileLibrary.CommonDTO;

public class QCService {

	Map select(QCDTO dto, CommonDTO pageing){
		
		QCDAO dao = new QCDAO();
		

		pageing.setTableName("qc");

        int totalCount = dao.getTotalCount();

        int size = pageing.getSize(); 
        int page = pageing.getPage(); 

        int section = pageing.getSection(); 

        int start = 0, end = 0;

       
        end = size * page;
       
        start = end - (size - 1);

        pageing.setStart(start);
        pageing.setEnd(end);
        Map map = new HashMap();
        // �깮�궛愿�由ъ뿉 �엳�뒗 湲곗〈 DB留� select
        List<QCDTO> list = dao.select(dto, pageing);
        
        map.put("list", list); // list
        map.put("totalCount", totalCount);
        map.put("commonDTO", pageing); // common DTO

        return map;
	}
	public List<QCDTO> selectall(QCDTO dto){
		QCDAO dao = new QCDAO();
		List list = dao.selectall(dto);
		return list;
	}
	
	int qcService(QCDTO dto){
		QCDAO dao = new QCDAO();
		int list = dao.qcDAO(dto);
		return list ;
	}


}
