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

/**
 * Servlet Filter implementation class charile_filter
 */
//@WebFilter("*")
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String path = req.getServletPath();
		System.out.println(path);
		//로그인x들어갈수있음
		if(isExclude(path)) {
			chain.doFilter(request, response);
		} else {//로그인 해야 들어갈수있음
			HttpSession session = req.getSession();
			String mod = req.getParameter("mod");
			Boolean login = (Boolean) session.getAttribute("login");
			String name = (String) session.getAttribute("name");
			Integer level = (Integer) session.getAttribute("level");
			System.out.println("필터로그인"+login);
			if((login!=null && login==true) || "login".equals(mod) || "add".equals(mod)) { 
				chain.doFilter(request, response);
			}
			if(login == null || login != true) {
				System.out.println("로그인 후 이용하세요");
				resp.sendRedirect("charlie");
			}else {
				chain.doFilter(request, response);
			}
//			if() {
//				
//			}
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private boolean isExclude(String path) {
		boolean result = false;
		if(
			path.equals("/WEB-INF/views/emp/login.jsp") ||
			path.equals("/charlie") ||
			path.equals("/check") ||
			path.equals("/emp") ||
			path.equals("/WEB-INF/views/emp/emp_signin.jsp")
			) {
			result = true;
		}
		return result;
	}
	
	
}
