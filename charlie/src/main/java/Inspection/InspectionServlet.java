@WebServlet("/inspection")
public class InspectionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InspectionDAO dao = new InspectionDAO();

        List<Map<String, Object>> list = dao.getList();

        request.setAttribute("list", list);

        request.getRequestDispatcher("inspection.jsp").forward(request, response);
    }
}