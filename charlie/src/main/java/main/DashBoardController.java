package main;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/main")
public class DashBoardController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("통합 메인 컨트롤러 실행!!");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8;");

        // 1. 대시보드 데이터 가져오기 (기존 로직)
        MainService ms = new MainService();
        Map dMap = ms.select();
        
        // 2. 차트 데이터 가져오기 (차트 서비스 추가)
        ChartService cs = new ChartService();
        Map cMap = cs.selectMdm();
        
        // 3. ★ 중요: JSP에서 헷갈리지 않게 각각 다른 이름으로 setAttribute
        request.setAttribute("dashData", dMap);
        request.setAttribute("chartData", cMap);
        
        // 4. main.jsp로 이동
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
