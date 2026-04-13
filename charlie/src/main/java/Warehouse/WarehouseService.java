package Warehouse;

import java.util.List;

import Lot.LotDAO;
import Lot.LotDTO;

public class WarehouseService {
	List<WarehouseDTO> select(WarehouseDTO dto){
		WarehouseDAO dao = new WarehouseDAO();
		List list = dao.select(dto);
		return list;
	}
	
	int warehouseService(WarehouseDTO dto){
		WarehouseDAO dao = new WarehouseDAO();
		int list = dao.warehouseDAO(dto);
		return list ;
	}
}
