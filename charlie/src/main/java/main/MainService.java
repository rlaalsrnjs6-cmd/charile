package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainService {
	MainDAO dao = new MainDAO();

	//대시보드에서 라인 점검 및 설비 가져오기 DS: dash_board 테이블
	public Map select() {
		Map map = new HashMap();
		
		//dash_notice selelct
		List dn = dao.loadDn();
		
		//lineStatus select
		List ls = dao.loadLs();
		
		//warehiuse select
		List wh = dao.loadWh();
		
		map.put("dn", dn);//dash_notice
		map.put("ls", ls);//lineStatus
		map.put("wh", wh);//warehouse
		return map;
	}
	
	public int lineUpdate(MainDTO dto) {
		
		int result = dao.lineUpdateStatus(dto);
		return result;
	}
	
	
}
