package Bom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Lot.LotDAO;
import Lot.LotDTO;
import Mdm.MdmDAO;
import Mdm.MdmDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentService2;

public class BomService extends ParentService2<BomDTO, CommonDTO> {

	BomDAO bomDAO = new BomDAO();

	@Override
	public Map selectDB(BomDTO dto, CommonDTO commonDTO) {
		

		commonDTO.setTableName(bomDAO.tableName());

		// �럹�씠吏��뿉�꽌 蹂댁뿬以� �빆紐� 紐뉕컻�씤吏� 媛쒖닔 由ы꽩
		int totalCount = bomDAO.getTotalCount(dto, commonDTO);

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
		
		List list = bomDAO.selectDB(dto, commonDTO);
		
		System.out.println("�꽌鍮꾩뒪�쓽 list: " + list);

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

	public List<BomDTO> selectall(BomDTO dto){
		BomDAO dao = new BomDAO();
		List list = dao.selectall(dto);
		return list;
	}
	
	
	
	
	
}
