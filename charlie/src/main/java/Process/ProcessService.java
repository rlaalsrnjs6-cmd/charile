package Process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bom.BomDAO;
import Bom.BomDTO;
import Lot.LotDAO;
import Process.ProcessDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentService2;

public class ProcessService extends ParentService2<ProcessDTO, CommonDTO> {

	ProcessDAO processDAO = new ProcessDAO();

	@Override
	public Map selectDB(ProcessDTO dto, CommonDTO commonDTO) {
		

		commonDTO.setTableName(processDAO.tableName());

		// �럹�씠吏��뿉�꽌 蹂댁뿬以� �빆紐� 紐뉕컻�씤吏� 媛쒖닔 由ы꽩
		int totalCount = processDAO.getTotalCount(dto, commonDTO);

		int size = commonDTO.getSize(); // �븳 �럹�씠吏��뿉�꽌 蹂댁뿬以� 媛쒖닔
		int page = commonDTO.getPage(); // �떆�옉 �럹�씠吏�

		int section = commonDTO.getSection(); // N �럹�씠吏��뵫 �븯湲�

		int start = 0, end = 0;

		// �럹�씠吏��뿉�꽌 蹂댁뿬以� 留덉�留� 踰덊샇
		end = size * page;
		// �럹�씠吏��뿉�꽌 蹂댁뿬以� �떆�옉 踰덊샇
		start = end - (size - 1);

		commonDTO.setEnd(end);
		commonDTO.setStart(start);
		Map map = new HashMap();
		
		List list = processDAO.selectDB(dto, commonDTO);
		System.out.println("�꽌鍮꾩뒪�쓽 list: " + list);

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

	public List<ProcessDTO> selectall(ProcessDTO dto){
		ProcessDAO dao = new ProcessDAO();
		List list = dao.selectall(dto);
		return list;
	}
	
	
	
}
