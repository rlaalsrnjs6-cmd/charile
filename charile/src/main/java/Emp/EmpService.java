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
		int list = dao.insert(dto);
		return list;
	}
}
