package Board;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder; // 인코딩용 추가
import javax.servlet.http.HttpServletRequest; // 이거 필수
import javax.servlet.http.HttpServletResponse; // 이거 필수
public class ReportDownloadAction implements Command {

@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
	
	// 1. 파라미터로 넘어온 파일 이름 받기
    String fileName = request.getParameter("fileName");
    String savePath = "C:\\charlie_upload"; // 파일 저장 경로
    
    // 2. 실제 파일 객체 생성
    File file = new File(savePath + File.separator + fileName);

    // 3. 파일 읽기 스트림과 응답 쓰기 스트림 준비 (try-with-resources로 자동 close)
    try (FileInputStream in = new FileInputStream(file);
         OutputStream out = response.getOutputStream()) {

        // 4. 브라우저가 다운로드 창을 띄우게 설정 (헤더 세팅)
        // 파일명에 한글이나 공백이 있으면 깨지므로 인코딩 필수
        String encodedName = java.net.URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        
        response.setContentType("application/octet-stream"); // 8비트 바이너리 데이터라는 뜻
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedName + "\"");
        response.setHeader("Content-Length", String.valueOf(file.length()));

        // 5. 파일 데이터를 버퍼 단위로 읽어서 브라우저로 전송
        byte[] buffer = new byte[4096]; // 4KB 단위로 쪼개서 보냄
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        
        out.flush(); // 남은 데이터 밀어내기
        
        // 중요: 다운로드는 파일 데이터를 직접 응답했으므로 페이지 이동(forward)을 하면 안 됨
        return null; 

    } catch (Exception e) {
        System.out.println("다운로드 중 에러 발생!");
        e.printStackTrace();
        return "error.jsp"; // 에러 발생 시에만 이동 경로 반환
    }
	
}

}
