package ProductionManagement;

import java.util.List;

public class ProductionManagementService {
	ProductionManagementDAO dao = new ProductionManagementDAO();
	
	//생산관리에 있는 기존 DB만 select
	public List<ProductionManagementDTO> loadPM(){ 
		
		List list = dao.selectPM();
		return list;
	}
	
	// 전체목표, 만든 개수, 남은 수량 select
	public List<ProductionManagementDTO> loadData(){
		
		List list = dao.selectData();
		
		return list;
	}
	
	
	
}
