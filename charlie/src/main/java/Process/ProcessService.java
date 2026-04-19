package Process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Process.ProcessDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentService2;

public class ProcessService extends ParentService2<ProcessDTO, CommonDTO> {

	ProcessDAO processDAO = new ProcessDAO();

	@Override
	public Map selectDB(ProcessDTO dto, CommonDTO commonDTO) {
		

		commonDTO.setTableName(processDAO.tableName());

		// 페이지에서 보여줄 항목 몇개인지 개수 리턴
		int totalCount = processDAO.getTotalCount();

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
		
		List list = processDAO.selectDB(dto, commonDTO);
		System.out.println("서비스의 list: " + list);

		map.put("list", list); // list
		map.put("totalCount", totalCount);
		map.put("commonDTO", commonDTO); // common DTO

		return map;

	}

	@Override
	public ProcessDTO selectOne(ProcessDTO dto, CommonDTO commonDTO) {
		System.out.println("service selectOne : " + dto);
		
		// SET QUERY
		commonDTO.setWhere("WHERE tableA.process_num = ?");
		ProcessDTO result = processDAO.selectOne(dto, commonDTO);
		return result;
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

	@Override
	public List selectJoinInfo() {
		System.out.println("select JoinInfo");
		return processDAO.selectJoinInfo();
	}

}
