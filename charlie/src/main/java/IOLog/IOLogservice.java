package IOLog;

import java.util.List;


public class IOLogservice {
	List<IOLogDTO> select(IOLogDTO dto){
		IOLogDAO dao = new IOLogDAO();
		List list = dao.select(dto);
		return list;
	}
	
	int logService(IOLogDTO dto){
		IOLogDAO dao = new IOLogDAO();
		int list = dao.logDAO(dto);
		return list ;
	}
}
