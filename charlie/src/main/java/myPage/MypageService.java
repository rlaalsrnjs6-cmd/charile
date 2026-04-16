package myPage;

import java.util.List;

public class MypageService {
	MypageDAO dao = new MypageDAO();
	
	public List selectAll(MypageDTO dto) {
		List result = dao.selectData(dto);
		return result;
	}
}
