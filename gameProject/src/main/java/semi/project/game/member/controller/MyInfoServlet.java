package semi.project.game.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;
import semi.project.game.member.model.vo.Score;

// 내 정보 조회 / 수정
@WebServlet("/myInfo")
public class MyInfoServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService service = new MemberService();
		
		HttpSession session = req.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 로그인된 회원 번호
		int memberNo = loginMember.getMemberNo();
		
		try {
			// 내 점수 조회
			List<Score> score = service.selectScore(memberNo);
			req.setAttribute("score", score);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("/WEB-INF/views/boarder/myInfo.jsp").forward(req, resp);
	}
	
}

