package Machinery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Mdm.MachineryDTO;
import Mdm.MdmDTO;
import fileLibrary.CommonDTO;
import fileLibrary.ParentService2;

public class MachineryService extends ParentService2<MachineryDTO, CommonDTO>{

	MachineryDAO machineryDAO = new MachineryDAO();

	@Override
	public Map selectDB(MachineryDTO dto, CommonDTO commonDTO) {

		@Override
		public Map selectDB(MachineryDTO dto, CommonDTO commonDTO) {
			
			commonDTO.setTableName(machineryDAO.tableName());		

			//페이지에서 보여줄 항목 몇개인지 개수 리턴
			int totalCount = machineryDAO.getTotalCount();
			
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
			List list = machineryDAO.selectDB(dto, commonDTO);
			System.out.println("서비스의 list: " + list);
			

			
			map.put("list", list); // list 
			map.put("totalCount", totalCount);
			map.put("commonDTO", commonDTO); // common DTO

			return map;
			
	}

		@Override
		public MdmDTO selectOne(MdmDTO dto, CommonDTO commonDTO) {
			System.out.println("service selectOne : " + dto);
			return machineryDAO.selectOne(dto, commonDTO);
		}
		@Override
		public MdmDTO insertDB(MdmDTO dto) {
			System.out.println("service dto : " + dto);
			return machineryDAO.insertDB(dto);
		}

		@Override
		public MdmDTO modifyDB(MdmDTO dto) {
			System.out.println("service dto : " + dto);
			return machineryDAO.modifyDB(dto);
		}
	 
		@Override
		public int deleteDB(MdmDTO dto) {
			System.out.println("service dto : " + dto);
			return machineryDAO.deleteDB(dto);
		}
	
	}

	

}
