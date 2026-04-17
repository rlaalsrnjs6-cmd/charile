package Bom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Mdm.MdmDAO;
import Mdm.MdmDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentService2;

public class BomService extends ParentService2<BomDTO, CommonDTO> {

	BomDAO bomDAO = new BomDAO();

	@Override
	public Map selectDB(BomDTO dto, CommonDTO commonDTO) {
		

		commonDTO.setTableName(bomDAO.tableName());

		// 페이지에서 보여줄 항목 몇개인지 개수 리턴
		int totalCount = bomDAO.getTotalCount();

		int size = commonDTO.getSize(); // 한 페이지에서 보여줄 개수
		int page = commonDTO.getPage(); // 시작 페이지

		int section = commonDTO.getSection(); // N 페이지씩 하기

		int start = 0, end = 0;

		// 페이지에서 보여줄 마지막 번호
		end = size * page;
		// 페이지에서 보여줄 시작 번호
		start = end - (size - 1);

		commonDTO.setEnd(end);
		commonDTO.setStart(start);
		Map map = new HashMap();
		
		List list = bomDAO.selectDB(dto, commonDTO);
		
		System.out.println("서비스의 list: " + list);

		map.put("list", list); // list
		map.put("totalCount", totalCount);
		map.put("commonDTO", commonDTO); // common DTO

		return map;

	}

	@Override
	public BomDTO selectOne(BomDTO dto, CommonDTO commonDTO) {
		System.out.println("service selectOne : " + dto);
		
		// SET QUERY
		commonDTO.setWhere("where tableA.bom_num = ?");
		BomDTO result = bomDAO.selectOne(dto, commonDTO);
		return result;
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

	@Override
	public List selectJoinInfo() {
		System.out.println("select JoinInfo");
		return bomDAO.selectJoinInfo();
	}

}
