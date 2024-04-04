package semi.project.game.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.member.model.vo.Member;


// 게시글 등록, 수정, 삭제
// 댓글 등록, 수정, 삭제
// 마이페이지 관련
@WebFilter(filterName = "loginFilter", urlPatterns = {"/gameList","/BoardList","/view","/delete","/write","/report/post","/game/*","/cancellation","/myInfo","/rank","/memberList"})
public class LoginFilter extends HttpFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("로그인 필터 생성");
	}
	
	public void destroy() {
		System.out.println("로그인 필터가 변경되어 파괴 -> 이후 재생성");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 클라이언트 요청 시 로그인 여부를 확인
		// -> 로그인이 되어있지 않으면 메인페이지로 리다이렉트
		// 	-> 로그인 후 이용해주세요. 알림창 띄우기
		//
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		String message = null;
		if(loginMember == null) {
			message = "로그인 후 이용해주세요.";
			session.setAttribute("message", message);
			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/login");
			
			return ;
		}
		

		// 다음 필터 호출, Servlet으로 이동
		chain.doFilter(request, response);
	}
}
