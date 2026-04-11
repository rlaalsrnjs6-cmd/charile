package QC;

import java.util.List;

import Emp.EmpDAO;
import Emp.EmpDTO;
import PersonalHygiene.PersonalHygieneDAO;
import PersonalHygiene.PersonalHygieneDTO;

public class QCService {
	List<QCDTO> select(QCDTO dto){
		QCDAO dao = new QCDAO();
		List list = dao.select(dto);
		return list;
	}
	
	int qcService(QCDTO dto){
		QCDAO dao = new QCDAO();
		int list = dao.qcDAO(dto);
		return list ;
	}
}
