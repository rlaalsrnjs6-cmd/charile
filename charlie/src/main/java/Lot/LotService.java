package Lot;

import java.util.List;

import Defective.DefectiveDAO;
import Defective.DefectiveDTO;
import fileLibrary.ParentService;

public class LotService{

	public List<LotDTO> select(LotDTO dto){
		LotDAO dao = new LotDAO();
		List list = dao.select(dto);
		return list;
	}
	
	public int lotService(LotDTO dto){
		LotDAO dao = new LotDAO();
		int list = dao.lotDAO(dto);
		return list ;
	}
	
	public int lotQcService(LotDTO dto){
		LotDAO dao = new LotDAO();
		int list = dao.lotQcDAO(dto);
		return list ;
	}

}
