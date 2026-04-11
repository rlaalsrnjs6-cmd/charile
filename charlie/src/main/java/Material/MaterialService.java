package Material;

import java.util.List;

import Material.MaterialDAO;
import Material.MaterialDTO;
import fileLibrary.ParentService;

public class MaterialService extends ParentService<MaterialDTO>{

MaterialDAO materialDAO = new MaterialDAO();
	
	@Override
	public List selectDB(MaterialDTO dto, String cmd) {
		
		List list = materialDAO.selectDB(dto, cmd);
		
		return list;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return materialDAO.selectAll();
	}

	@Override
	public MaterialDTO insertDB(MaterialDTO dto) {
		System.out.println("service dto : " + dto);
		return materialDAO.insertDB(dto);
	}

	@Override
	public MaterialDTO modifyDB(MaterialDTO dto) {
		System.out.println("service dto : " + dto);
		return materialDAO.modifyDB(dto);
	}

	@Override
	public int deleteDB(MaterialDTO dto) {
		System.out.println("service dto : " + dto);
		
		return materialDAO.deleteDB(dto);
	}

}
