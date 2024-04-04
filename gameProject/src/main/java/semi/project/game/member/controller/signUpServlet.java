package semi.project.game.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;


@WebServlet("/signUp")
public class signUpServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/signUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/login";
		
		String memberId = req.getParameter("inputIdbox");
		String memberPw = req.getParameter("inputPwbox");
		String memberName = req.getParameter("inputNamebox");
		String memberEmail =req.getParameter("inputEmailbox");
		String memberBirthday = req.getParameter("inputDobbox");
		String managerYN = req.getParameter("managerYN");
		
		
		Member signMember = new Member();

		if(memberEmail != null) {
			signMember.setMemberEmail(memberEmail);
		}
		
		
		signMember.setMemberId(memberId);
		signMember.setMemberPw(memberPw);
		signMember.setMemberName(memberName);
		signMember.setMemberBirthday(memberBirthday);
		
		try {
			
			MemberService service = new MemberService();
			
			int result = service.signUp(signMember, managerYN);
									
			HttpSession session = req.getSession();
			
			if(result > 0 ) {
				session.setAttribute("message", "회원가입 성공");
			}else {
				session.setAttribute("message", "회원가입 실패");
			}
			
			
			resp.sendRedirect(req.getContextPath() + path);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
	}
		
		
}
