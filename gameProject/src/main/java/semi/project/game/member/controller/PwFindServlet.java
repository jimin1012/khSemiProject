package semi.project.game.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;

@WebServlet("/pwFind")
public class PwFindServlet extends HttpServlet{
	
	//get 그리고 post랑 공유하려고 여기에 선언해놈
	String MemberId = null; 
	String memberBirth = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberId = req.getParameter("memberId");
		memberBirth = req.getParameter("memberBirth");
		
		try {
			MemberService service = new MemberService();
			// 여기는 유저 아이디랑 유저 생일 비교해서 존재하면 비밀번호 재설정 가능하게 하려고 검사하는 부분
			int result = service.selectMemberPw(MemberId,memberBirth);
			
			resp.getWriter().print(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String memberPw = req.getParameter("userPw");
		
		try {
			MemberService service = new MemberService();
			// 유저 비밀번호 재설정 하는 부분
			int result = service.updateMemberPw(MemberId,memberBirth,memberPw);
			HttpSession session = req.getSession();
			
			if(result>0) {
				session.setAttribute("message", "비밀번호가 수정되었습니다.");
				resp.sendRedirect(req.getContextPath()+"/login");
			}else {
				session.setAttribute("message", "비밀번호 수정이 되지 않았습니다.");
				resp.sendRedirect("idpwFind");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
		
	}
}
