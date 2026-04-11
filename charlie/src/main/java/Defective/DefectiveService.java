package Defective;

import java.util.List;


public class DefectiveService {
		List<DefectiveDTO> select(DefectiveDTO dto){
			DefectiveDAO dao = new DefectiveDAO();
			List list = dao.select(dto);
			return list;
		}
		
		int defectiveService(DefectiveDTO dto){
			DefectiveDAO dao = new DefectiveDAO();
			int list = dao.defectiveDAO(dto);
			return list ;
		}
}
