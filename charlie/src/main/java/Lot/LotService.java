package Lot;

import java.util.List;

import Defective.DefectiveDAO;
import Defective.DefectiveDTO;
import fileLibrary.ParentService;

public class LotService{

	List<LotDTO> select(LotDTO dto){
		LotDAO dao = new LotDAO();
		List list = dao.select(dto);
		return list;
	}
	
	int lotService(LotDTO dto){
		LotDAO dao = new LotDAO();
		int list = dao.lotDAO(dto);
		return list ;
	}

}
