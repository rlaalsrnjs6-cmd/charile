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

public class ReportInsertAction implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        System.out.println("11111111");
        Integer empno = (Integer) session.getAttribute("empno");
        if (empno == null) return "login.report";
        System.out.println("222222222222");

        // 1. 설정
        String savePath = "C:\\charlie_upload";
        File uploadDir = new File(savePath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        BoardDTO dto = new BoardDTO();
        dto.setEmpno(empno);

        // 2. Apache Commons FileUpload 처리
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(10 * 1024 * 1024); // 파일 개별 10MB 제한

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    // 일반 파라미터 처리 (title, content, category 등)
                    String fileNameAttr = item.getFieldName();
                    String value = item.getString("UTF-8");

                    if (fileNameAttr.equals("title")) dto.setTitle(value);
                    else if (fileNameAttr.equals("content")) dto.setContent(value);
                    else if (fileNameAttr.equals("category")) dto.setCategory(value);

                } else {
                    // 파일 처리
                    String originName = item.getName(); // 원본 파일명
                    if (item.getFieldName().equals("file") && originName != null && !originName.isEmpty()) {
                        // 중복 방지를 위해 파일명 가공 (간단하게 앞에 시간을 붙임)
                        String saveName =  originName;
                        File storeFile = new File(savePath + File.separator + saveName);
                        
                        item.write(storeFile); // 실제 저장
                        dto.setUrl(saveName);  // DB 저장용 파일명
                    } else {
                        dto.setUrl("no_file");
                    }
                }
            }

            // 3. Service 호출
            ReportService sv = new ReportService();
            int result = sv.insertReport(dto);
            if (result > 0) {
                // [성공] 등록이 잘 됐을 때
                System.out.println("데이터 등록 성공! 목록으로 이동합니다.");
                return "select.report"; 
            } else {
                // [실패] 쿼리는 실행됐으나 반영된 행이 0일 때
                System.out.println("데이터 등록 실패 (반영된 행 없음)");
                request.setAttribute("msg", "등록에 실패했습니다.");
                return "write.report"; // 다시 쓰기 페이지로
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("인서트 이후 셀렉트 페이지로 이동");
        return "select.report";
    }
}