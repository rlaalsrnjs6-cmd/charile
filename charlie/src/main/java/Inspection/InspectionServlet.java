@WebServlet("/inspection")
public class InspectionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String date = request.getParameter("date");

        if (status == null) status = "전체";

        InspectionService service = new InspectionService();
        List<InspectionDTO> list = service.list(name, status, date);

        request.setAttribute("list", list);

        request.getRequestDispatcher("inspection.jsp").forward(request, response);
    }
}