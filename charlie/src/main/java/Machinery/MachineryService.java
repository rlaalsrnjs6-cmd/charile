package Machinery;

import java.util.List;

import Machinery.MachineryDAO;
import Machinery.MachineryDTO;
import fileLibrary.ParentService;

public class MachineryService extends ParentService<MachineryDTO>{

	MachineryDAO machineryDAO = new MachineryDAO();
	
	@Override
	public List selectDB(MachineryDTO dto, String cmd) {
		
		List list = machineryDAO.selectDB(dto, cmd);
		
		return list;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return machineryDAO.selectAll();
	}

	@Override
	public MachineryDTO insertDB(MachineryDTO dto) {
		System.out.println("service dto : " + dto);
		return machineryDAO.insertDB(dto);
	}

	@Override
	public MachineryDTO modifyDB(MachineryDTO dto) {
		System.out.println("service dto : " + dto);
		return machineryDAO.modifyDB(dto);
	}

	@Override
	public int deleteDB(MachineryDTO dto) {
		System.out.println("service dto : " + dto);
		
		return machineryDAO.deleteDB(dto);
	}
	

}
