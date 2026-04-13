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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String path = req.getServletPath();
		
		//종한 로직임 건들면 안돼~
		boolean isMultipart = (req.getContentType() != null && req.getContentType().startsWith("multipart/form-data"));
		System.out.println(path);
		
		//로그인x들어갈수있음
		if(isExclude(path)) {
			chain.doFilter(request, response);
		} else {//로그인 해야 들어갈수있음
			HttpSession session = req.getSession();
			
//			String mod = req.getParameter("mod");
			//파일 업로드 하는 것 때문에 수정 했어 파일 업로드 하는 상황일 때는
			//getParameter 하지 말라는거야
			String mod = isMultipart ? null : req.getParameter("mod");
			
			Boolean login = (Boolean) session.getAttribute("login");
			String name = (String) session.getAttribute("name");
			Integer level = (Integer) session.getAttribute("level");
			System.out.println("필터로그인"+login);
<<<<<<< HEAD
//			if((login!=null && login==true) || "login".equals(mod) || "add".equals(mod)) { 
//				chain.doFilter(request, response);
//			}
			if((login == null || login != true) && !isMultipart) {
=======
			if("logout".equals(mod)) {
			session.invalidate();
			}
			if(login == null || login != true) {
>>>>>>> 2df4fe03f01664f121c1ea45d5ea6548305ade63
				System.out.println("로그인 후 이용하세요");
				resp.sendRedirect("charlie");
			}else {
				chain.doFilter(request, response);
			}
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
//			path.equals("/emp") ||
			path.equals("/WEB-INF/views/emp/emp_signin.jsp")
			) {
			result = true;
		}
		return result;
	}
	
	
}
