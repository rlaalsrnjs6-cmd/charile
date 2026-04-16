package Material;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Material.MaterialDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentService2;

public class MaterialService extends ParentService2<MaterialDTO, CommonDTO> {

	MaterialDAO materialDAO = new MaterialDAO();

	@Override
	public Map selectDB(MaterialDTO dto, CommonDTO commonDTO) {
		

		commonDTO.setTableName(materialDAO.tableName());

		// 페이지에서 보여줄 항목 몇개인지 개수 리턴
		int totalCount = materialDAO.getTotalCount();

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
		// 생산관리에 있는 기존 DB만 select
		List list = materialDAO.selectDB(dto, commonDTO);
		System.out.println("서비스의 list: " + list);

		map.put("list", list); // list
		map.put("totalCount", totalCount);
		map.put("commonDTO", commonDTO); // common DTO

		return map;

	}
//////////////////////////////////////////////////////////////
	List<MaterialDTO> selectall(MaterialDTO dto){
		MaterialDAO dao = new MaterialDAO();
		List list = dao.selectall(dto);
		return list;
	}
	////////////////////////////////////////////////////////////
	@Override
	public MaterialDTO selectOne(MaterialDTO dto, CommonDTO commonDTO) {
		System.out.println("service selectOne : " + dto);
		return materialDAO.selectOne(dto, commonDTO);
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

	@Override
	public List selectJoinInfo() {
		System.out.println("select JoinInfo");
		return materialDAO.selectJoinInfo();
	}

}
