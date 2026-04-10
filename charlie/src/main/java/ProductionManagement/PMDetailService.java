package ProductionManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PMDetailService {
	
	PMDetailDAO dao = new PMDetailDAO();
	
	//읽어옴
	public List loadPM(int prodNum) {
		List list = dao.selectPM(prodNum);
	return list;
	}
	
	//업데이트
	public int update(ProductionManagementDTO dto) {
		
		int result = dao.updatePM(dto);
		
		return result;
	}
	
}
