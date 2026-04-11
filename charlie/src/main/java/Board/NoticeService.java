package Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ProductionManagement.ProductionManagementDTO;

public class NoticeService {
	
	NoticeDAO dao = new NoticeDAO();
	
	public Map loadNotice(BoardDTO dto) {
		
//		System.out.println("noticeService의 loadNotice() 실행");
//		List list = noticeDAO.selectNotice();
//		
//		return list;
		
		
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
						List list = dao.selectNotice(dto);
//						System.out.println("서비스의 list1: " + list1);
						

						
						map.put("list", list);

						map.put("page", pageCount);
						
						map.put("startPage", startPage); // 시작 페이지 몇번인지
						map.put("endPage", endPage);	 // 종점 페이지 몇번인지
						map.put("totalPage", totalPage); //전체 페이지 개수
						map.put("currentPage", page); //현재 페이지가 데이터
						
				return map;
			}
			
}
