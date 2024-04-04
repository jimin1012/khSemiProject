package semi.project.game.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;
import semi.project.game.member.model.vo.Score;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("password");
		
		Member member = new Member();
		
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		try {
			MemberService service = new MemberService();
			
			Member loginMember = service.login(member);
			
			HttpSession session = req.getSession();
			
			if(loginMember != null) {
				
				session.setAttribute("loginMember", loginMember);
				
				resp.sendRedirect(req.getContextPath());
				
			}else {
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
				resp.sendRedirect("login");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
	}
}
