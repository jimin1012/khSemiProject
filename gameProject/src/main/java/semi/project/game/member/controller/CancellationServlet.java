package semi.project.game.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;


// 회원 탈퇴 서블릿
@WebServlet("/cancellation")
public class CancellationServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Member loginMember = (Member) req.getSession().getAttribute("loginMember");
		
		String memberPw = req.getParameter("checkPw");
		
		HttpSession session = req.getSession();
		
		MemberService service = new MemberService();
		
		try {
			
			
			String memberPwCheck = service.cancellationPw(loginMember.getMemberNo());
			
			String message = null;
			String path = null;
			
			if(memberPw.equals(memberPwCheck)) { // 입력된 비밀번호와 DB에 저장된 번호가 일치하는지 확인
				
				// 회원 탈퇴 진행
				int result = service.cancellation(loginMember);
				
				if(result > 0) { // 탈퇴 성공 시
					message = "회원 탈퇴에 성공했습니다.";
					session.setAttribute("message", message);
					path = "index.jsp";
					
					// 탈퇴 성공 후 로그아웃
					session.setAttribute("loginMember", null);
				}else { // 탈퇴 실패 시 
					message = "탈퇴 실패";
					session.setAttribute("message", message);
					path = req.getHeader("referer");
				}
			}else { // 입력된 비밀번호가 일치하지 않을 시
				session.setAttribute("message", "비밀번호가 일치하지 않습니다.");
				path = req.getHeader("referer");
			}
			resp.sendRedirect(path);
		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
		
	}
	
}
