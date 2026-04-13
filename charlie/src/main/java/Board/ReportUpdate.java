package Board;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Board.Command;
import Board.BoardDTO;
import Board.ReportService;

public class ReportUpdate implements Command {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		String savePath = "C:\\charlie_upload";
	    
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setFileSizeMax(10 * 1024 * 1024); // 10MB
	    System.out.println("reportUpdate 액션 2");
	    try {
	        List<FileItem> items = upload.parseRequest(request);
	        
	        // 1. DTO와 파일명을 담을 변수 준비
	        BoardDTO dto = new BoardDTO();
	        String newFileName = null;
	        int postNum = 0;
	        System.out.println("reportUpdate 액션 1");
	        // 2. 폼 필드와 파일 필드 분리 처리
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                // 일반 텍스트 데이터 (title, content, postNum 등)
	                String name = item.getFieldName();
	                String value = item.getString("utf-8");
	                System.out.println("reportUpdate 액션 3");
	                if (name.equals("postNum")) dto.setPost_num(Integer.parseInt(value)); 
	                else if (name.equals("title")) dto.setTitle(value);
	                else if (name.equals("content")) dto.setContent(value);
	            } else {
	                // 파일 데이터
	                if (item.getSize() > 0) {
	                	System.out.println("reportUpdate 액션 4");
	                    newFileName = new File(item.getName()).getName();
	                    File storeFile = new File(savePath + File.separator + newFileName);
	                    item.write(storeFile); // 파일 저장
	                    dto.setUrl(newFileName);
	                }
	            }
	        }

	        System.out.println("reportUpdate 액션 5");
	        ReportService sv = new ReportService();
	        int result = sv.updateReport(dto);


	        System.out.println("reportUpdate 액션 6");
	        return "select.report";

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("reportUpdate 액션 7");
	        return "error.jsp";
	    }
		
		
		}
}
