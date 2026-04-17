@WebServlet("/inspection")
public class InspectionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String status = request.getParameter("status");
        String date = request.getParameter("date");
        String pageStr = request.getParameter("page");

        int page = 1;
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }

        if (status == null) {
            status = "all";
        }

        InspectionService service = new InspectionService();
        request.setAttribute("list", service.list(status, date, page));

        request.getRequestDispatcher("inspection.jsp").forward(request, response);
    }
}