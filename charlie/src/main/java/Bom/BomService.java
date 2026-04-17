package Bom;

import java.util.List;

import fileLibrary.ParentService;

public class BomService extends ParentService<BomDTO> {

	BomDAO bomDAO = new BomDAO();
	
	@Override
	public List selectDB(BomDTO dto, String cmd) {
		System.out.println("service dto : " + dto);
		System.out.println("service cmd : " + cmd);
		
		List list = bomDAO.selectDB(dto, cmd);
		
		return list;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return bomDAO.selectAll();
	}

	@Override
	public BomDTO insertDB(BomDTO dto) {
		System.out.println("service dto : " + dto);
		return bomDAO.insertDB(dto);
	}

	@Override
	public BomDTO modifyDB(BomDTO dto) {
		System.out.println("service dto : " + dto);
		return bomDAO.modifyDB(dto);
	}

	@Override
	public int deleteDB(BomDTO dto) {
		System.out.println("service dto : " + dto);
		
		return bomDAO.deleteDB(dto);
	}



}
