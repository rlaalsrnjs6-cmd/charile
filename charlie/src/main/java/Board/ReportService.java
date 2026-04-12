package Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
	
	ReportDAO dao = new ReportDAO();
	
	//select 로직
	public Map selectList(BoardDTO dto) {
		
		//페이지에서 보여줄 항목 몇개인지 개수 리턴
				int pageCount = dao.getTotalCount();
				
//				System.out.println(list1);

				
				int size = dto.getSize(); // 한 페이지에서 보여줄 개수
				int page = dto.getPage(); // 시작 페이지
				
				//페이지에서 보여줄 마지막 번호
				int end = size * page;
				
				//페이지에서 보여줄 시작 번호
				int start = end - (size - 1);
				
				dto.setStart(start);
				dto.setEnd(end);
				
					// totalPage: 전체 페이지 개수
				
						// pageCount / size를 double타입으로 만들고 그 값을 올림 처리 하고(Math.ceil)
						//그 값을 int로 형변환 해서 totalPage에 담는다
						int totalPage = (int) Math.ceil((double) pageCount / size);
						
						//시작점 찾기 : (현재 페이지 -1 / 5 ) * 5 + 1;
						int startPage = ((page - 1) / 5) * 5 + 1;
						
						//종점 찾기 
						int endPage = startPage + 4;

						if (endPage > totalPage) {
							endPage = totalPage;
						}
						
						Map map = new HashMap();
						//생산관리에 있는 기존 DB만 select
						List list = dao.selectAll(dto);
						System.out.println("서비스의 list1: " + list);
						
						// 전체목표, 만든 개수, 남은 수량 select
//						List list2 = dao.selectData();
						
						map.put("list", list);

						map.put("page", pageCount);
						
						map.put("startPage", startPage); // 시작 페이지 몇번인지
						map.put("endPage", endPage);	 // 종점 페이지 몇번인지
						map.put("totalPage", totalPage); //전체 페이지 개수
						map.put("currentPage", page); //현재 페이지가 데이터
						
				return map;
		
	}
}
