package Process;

import java.util.List;

import fileLibrary.ParentService;

public class ProcessService extends ParentService<ProcessDTO>{

	ProcessDAO processDAO = new ProcessDAO();
	
	@Override
	public List selectDB(ProcessDTO dto, String cmd) {
		
		List list = processDAO.selectDB(dto, cmd);
		
		return list;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return processDAO.selectAll();
	}

	@Override
	public ProcessDTO insertDB(ProcessDTO dto) {
		System.out.println("service dto : " + dto);
		return processDAO.insertDB(dto);
	}

	@Override
	public ProcessDTO modifyDB(ProcessDTO dto) {
		System.out.println("service dto : " + dto);
		return processDAO.modifyDB(dto);
	}

	@Override
	public int deleteDB(ProcessDTO dto) {
		System.out.println("service dto : " + dto);
		
		return processDAO.deleteDB(dto);
	}


}
