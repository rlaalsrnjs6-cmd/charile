package Emp;

import java.util.List;

public class EmpService {
	List<EmpDTO> select(EmpDTO dto){
		EmpDAO dao = new EmpDAO();
		List list = dao.select(dto);
		return list;
	}

	int insert(EmpDTO dto){
		EmpDAO dao = new EmpDAO();
		List list = null;
		if("add".equals(dto.getMod())) {
		list = dao.select(dto);
		}
		int insert = -1;
		if(list.size()<1) {
			insert = dao.insert(dto);
		}
		return insert;
	}
}
