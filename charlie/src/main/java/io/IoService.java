package io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fileLibrary.ParentService2;
import fileLibrary.CommonDTO;

public class IoService extends ParentService2<IoDTO, CommonDTO>{

	IoDAO ioDAO = new IoDAO();

	@Override
	public Map selectDB(IoDTO dto, CommonDTO commonDTO) {
		
		commonDTO.setTableName(ioDAO.tableName());		

		//페이지에서 보여줄 항목 몇개인지 개수 리턴
		int totalCount = ioDAO.getTotalCount(dto, commonDTO);
		
		int size = commonDTO.getSize(); // 한 페이지에서 보여줄 개수
		int page = commonDTO.getPage(); // 시작 페이지
		
		int section = commonDTO.getSection(); // N 페이지씩 하기 
		
		int start = 0
			, end = 0;
		
		//페이지에서 보여줄 마지막 번호
		end = size * page;
		//페이지에서 보여줄 시작 번호
		start = end - (size - 1);
				
		commonDTO.setEnd(end);
		commonDTO.setStart(start);
		Map map = new HashMap();
		//생산관리에 있는 기존 DB만 select
		List list = ioDAO.selectDB(dto, commonDTO);
		System.out.println("서비스의 list: " + list);
		

		
		map.put("list", list); // list 
		map.put("totalCount", totalCount);
		map.put("commonDTO", commonDTO); // common DTO
		
//		map.put("select1", ioDAO.selectCustom());
//		map.put("select2", ioDAO.selectCustom2());

		return map;
	}
	
	@Override
	public IoDTO selectOne(IoDTO dto, CommonDTO commonDTO) {
		System.out.println("service selectOne : " + dto);
		return ioDAO.selectOne(dto, commonDTO);
	}
	@Override
	public IoDTO insertDB(IoDTO dto) {
		System.out.println("service dto : " + dto);
		return ioDAO.insertDB(dto);
	}

	@Override
	public IoDTO modifyDB(IoDTO dto) {
		System.out.println("service dto : " + dto);
		return ioDAO.modifyDB(dto);
	}
 
	@Override
	public int deleteDB(IoDTO dto) {
		System.out.println("service dto : " + dto);
		return ioDAO.deleteDB(dto);
	}

	@Override
	public List selectJoinInfo() {
		return ioDAO.selectJoinInfo();
	}




}
