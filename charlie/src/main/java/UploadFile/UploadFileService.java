package UploadFile;

import java.util.List;


public class UploadFileService {
	List<UploadFileDTO> select(UploadFileDTO dto){
		UploadFileDAO dao = new UploadFileDAO();
		List list = dao.select(dto);
		return list;
	}
	
	int fileService(UploadFileDTO dto){
		UploadFileDAO dao = new UploadFileDAO();
		int list = dao.fileDAO(dto);
		return list ;
	}
}
