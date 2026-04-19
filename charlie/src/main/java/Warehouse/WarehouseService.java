package Warehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Mdm.MdmDAO;
import Mdm.MdmDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentService2;

public class WarehouseService extends ParentService2<WarehouseDTO, CommonDTO> {

	WarehouseDAO warehouseDAO = new WarehouseDAO();

	@Override
	public Map selectDB(WarehouseDTO dto, CommonDTO commonDTO) {
		

		commonDTO.setTableName(warehouseDAO.tableName());

		// 페이지에서 보여줄 항목 몇개인지 개수 리턴
		int totalCount = warehouseDAO.getTotalCount();

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
		
		List list = warehouseDAO.selectDB(dto, commonDTO);
		
		System.out.println("서비스의 list: " + list);

		map.put("list", list); // list
		map.put("totalCount", totalCount);
		map.put("commonDTO", commonDTO); // common DTO
		
		map.put("select1", warehouseDAO.selectCustom());
		map.put("select2", warehouseDAO.selectCustom2());
		
		return map;

	}

	@Override
	public WarehouseDTO selectOne(WarehouseDTO dto, CommonDTO commonDTO) {
		System.out.println("service selectOne : " + dto);
		
		// SET QUERY
		commonDTO.setWhere("where tableA.warehouse_num = ?");
		WarehouseDTO result = warehouseDAO.selectOne(dto, commonDTO);
		return result;
	}
	@Override
	public WarehouseDTO insertDB(WarehouseDTO dto) {
		System.out.println("service dto : " + dto);
		return warehouseDAO.insertDB(dto);
	}

	@Override
	public WarehouseDTO modifyDB(WarehouseDTO dto) {
		System.out.println("service dto : " + dto);
		return warehouseDAO.modifyDB(dto);
	}
 
	@Override
	public int deleteDB(WarehouseDTO dto) {
		System.out.println("service dto : " + dto);
		return warehouseDAO.deleteDB(dto);
	}

	@Override
	public List selectJoinInfo() {
		System.out.println("select JoinInfo");
		return warehouseDAO.selectJoinInfo();
	}
	
//	public List selectCustom() {
//		return warehouseDAO.selectCustom();
//	}

}
