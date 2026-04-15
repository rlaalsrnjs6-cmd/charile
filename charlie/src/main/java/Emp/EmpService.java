package Emp;

import java.util.List;

import Defective.DefectiveDAO;
import Defective.DefectiveDTO;

public class EmpService {
	public List<EmpDTO> select(EmpDTO dto){
		EmpDAO dao = new EmpDAO();
		List list = dao.select(dto);
		return list;
	}

	int insert(EmpDTO dto){
		System.out.println("서비스 첫번쨰 인서트 확인");
		EmpDAO dao = new EmpDAO();
		List list = null;
		if("add".equals(dto.getMod())) {
		list = dao.select(dto);
		}
		int insert = -1;
		System.out.println("서비스 if 조건 확인: " + list.size());
		if(list.size()<1) {
			System.out.println("인서트 시작");
			insert = dao.insert(dto);
		}
		return insert;
	}
	int empService(EmpDTO dto){
		EmpDAO dao = new EmpDAO();
		int list = dao.empDAO(dto);
		return list ;
	}
}
