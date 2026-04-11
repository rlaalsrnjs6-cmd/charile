package Mdm;

import java.util.List;

import fileLibrary.ParentService2;
import fileLibrary.TestDTO;

public class MdmServiceTest extends ParentService2<MdmDTO, TestDTO>{

	MdmDAOtest mdmDAO = new MdmDAOtest();

	@Override
	public List selectDB(MdmDTO dto, TestDTO testDTO) {
		System.out.println("service dto : " + dto);
		List list = mdmDAO.selectDB(dto, testDTO);
		if(list.size() > 0) {
			return list;
		} else { return mdmDAO.selectAll(); } 
	}
	
	@Override
	public List selectAll() {
		return mdmDAO.selectAll();
	}

	@Override
	public MdmDTO insertDB(MdmDTO dto) {
		System.out.println("service dto : " + dto);
		return mdmDAO.insertDB(dto);
	}

	@Override
	public MdmDTO modifyDB(MdmDTO dto) {
		System.out.println("service dto : " + dto);
		return mdmDAO.modifyDB(dto);
	}
 
	@Override
	public int deleteDB(MdmDTO dto) {
		System.out.println("service dto : " + dto);
		return mdmDAO.deleteDB(dto);
	}


}
