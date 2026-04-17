package charile_filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Emp.EmpDTO;

/**
 * Servlet Filter implementation class charile_filter
 */
@WebFilter("/*")
public class charile_filter implements Filter {

	/**
	 * Default constructor.
	 */
	public charile_filter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String path = req.getServletPath();

		boolean isMultipart = (req.getContentType() != null && req.getContentType().startsWith("multipart/form-data"));
		System.out.println(path);

		if (isExclude(path)) {
			chain.doFilter(request, response);
			System.out.println("�솗�씤�슜");
			return;
		} else {
			HttpSession session = req.getSession();

			String mod = isMultipart ? null : req.getParameter("mod");

			EmpDTO dto = new EmpDTO();
			Boolean login = (Boolean) session.getAttribute("login");
			String name = (String) session.getAttribute("name");
			Integer level = (Integer) session.getAttribute("level");
			System.out.println("�븘�꽣濡쒓렇�씤" + login);

			if ((login == null || login != true) && !isMultipart) {
				
				
				if("add".equals(mod)||"add".equals(dto.getMod())) {
					chain.doFilter(request, response);
				}
				resp.sendRedirect(req.getContextPath() + "/charlie");
				return;
				
			} else {
				if ("logout".equals(mod)) {
					session.invalidate();
				}
				chain.doFilter(request, response);
				return;
			}

		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private boolean isExclude(String path) {
		boolean result = false;
		if (path.equals("/WEB-INF/views/emp/login.jsp") || path.equals("/charlie") || path.equals("/check") ||
//			path.equals("/emp") ||
				path.equals("/WEB-INF/views/emp/emp_signin.jsp")) {
			result = true;
		}
		if (path.startsWith("/assets/")) {
	        result = true;
	    }
		return result;
	}

}
