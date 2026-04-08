package charile_filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class charile_filter
 */
@WebFilter("/charile/filter")
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

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private boolean isExclude(String path) {
		boolean result = false;
		if(
			path.equals("WEB-INF/views/emp/login.jsp") ||
			path.equals("WEB-INF/views/emp/emp_signin.jsp")
			) {
			result = true;
		}
		return result;
	}
	
	
}
