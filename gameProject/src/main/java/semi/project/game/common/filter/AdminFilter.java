package semi.project.game.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.member.model.vo.Member;

@WebFilter(filterName = "adminFilter", urlPatterns = {"/memberList","/report/*"})
public class AdminFilter extends HttpFilter implements Filter {
       
    public void init(FilterConfig fConfig) throws ServletException {
    	System.out.println("관리자 필터 생성");
	}
	public void destroy() {
		System.out.println("필터가 변경되어 파괴 -> 이후 재생성");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String message = null;
		
		if(loginMember != null) {
			String managerYn = loginMember.getManagerYn();
			if(!managerYn.equals("Y")) {
				message = "관리자만 이용가능합니다. 어딜 보시는거죠? ^오^";
				session.setAttribute("message", message);
				((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath());
				return ;
			}
		}
		
		
		chain.doFilter(request, response);
	}

	

}
