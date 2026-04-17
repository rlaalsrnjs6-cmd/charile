package ProductionManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import WorkOrder.WorkOrderDAO;
import WorkOrder.WorkOrderDTO;
import WorkOrder.WorkOrderService;

public class ProductionManagementService {
	ProductionManagementDAO dao = new ProductionManagementDAO();

	
	public Map loadPM(ProductionManagementDTO dto){ 

		
		//�럹�씠吏��뿉�꽌 蹂댁뿬以� �빆紐� 紐뉕컻�씤吏� 媛쒖닔 由ы꽩
		int pageCount = dao.getTotalCount(dto);
		
//		System.out.println(list1);

		
		int size = dto.getSize(); // �븳 �럹�씠吏��뿉�꽌 蹂댁뿬以� 媛쒖닔
		int page = dto.getPage(); // �떆�옉 �럹�씠吏�
		
		//�럹�씠吏��뿉�꽌 蹂댁뿬以� 留덉�留� 踰덊샇
		int end = size * page;
		
		//�럹�씠吏��뿉�꽌 蹂댁뿬以� �떆�옉 踰덊샇
		int start = end - (size - 1);
		
		dto.setStart(start);
		dto.setEnd(end);
		
			// totalPage: �쟾泥� �럹�씠吏� 媛쒖닔
		
				// pageCount / size瑜� double���엯�쑝濡� 留뚮뱾怨� 洹� 媛믪쓣 �삱由� 泥섎━ �븯怨�(Math.ceil)
				//洹� 媛믪쓣 int濡� �삎蹂��솚 �빐�꽌 totalPage�뿉 �떞�뒗�떎
				int totalPage = (int) Math.ceil((double) pageCount / size);
				
				//�떆�옉�젏 李얘린 : (�쁽�옱 �럹�씠吏� -1 / 5 ) * 5 + 1;
				int startPage = ((page - 1) / 10) * 10 + 1;
				
				//醫낆젏 李얘린 
				int endPage = startPage + 9;

				if (endPage > totalPage) {
					endPage = totalPage;
				}
				
				Map map = new HashMap();
				//�깮�궛愿�由ъ뿉 �엳�뒗 湲곗〈 DB留� select
				List list1 = dao.selectPage(dto);
				System.out.println("�꽌鍮꾩뒪�쓽 list1: " + list1);
				
				// �쟾泥대ぉ�몴, 留뚮뱺 媛쒖닔, �궓�� �닔�웾 select
//				List list2 = dao.selectData();
				
				map.put("List1", list1);

				map.put("page", pageCount);
				
				map.put("startPage", startPage); // �떆�옉 �럹�씠吏� 紐뉖쾲�씤吏�
				map.put("endPage", endPage);	 // 醫낆젏 �럹�씠吏� 紐뉖쾲�씤吏�
				map.put("totalPage", totalPage); //�쟾泥� �럹�씠吏� 媛쒖닔
				map.put("currentPage", page); //�쁽�옱 �럹�씠吏�媛� �뜲�씠�꽣
				
		return map;
	}
	
	public List<ProductionManagementDTO> selectall(ProductionManagementDTO dto){
		ProductionManagementDAO dao = new ProductionManagementDAO();
		List list = dao.selectall(dto);
		return list;
	}
	
	// insert
	public int insert(ProductionManagementDTO dto) {
		
		System.out.println("servlet�쓽 insert吏꾩엯");
		int result = dao.insertData(dto);
		
		return result;
	}
	
}
