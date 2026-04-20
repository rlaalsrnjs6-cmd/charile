package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartService {
	ChartDAO dao = new ChartDAO();
	
	public Map selectMdm() {
		Map map = new HashMap();
		
		
		List product  = dao.selectProduct();	
		int sal = dao.sal();
		
		List material = dao.selectMaterial();
		List assemble = dao.selectAssemble();
		ChartDTO dto = dao.selectTotalAchievement();
		int defective = dao.selectdefective();
		
		map.put("product", product);
		map.put("sal", sal);
		map.put("material", material);
		map.put("assemble", assemble);
		map.put("dto", dto);
		map.put("def", defective);

		return map;
	}
}
