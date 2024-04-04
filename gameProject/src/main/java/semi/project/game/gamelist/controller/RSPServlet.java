package semi.project.game.gamelist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.member.model.vo.Member;


@WebServlet("/game/rsp")
public class RSPServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Member loginMember =  (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			session.setAttribute("message", "로그인 후 이용 가능합니다.");
			resp.sendRedirect(req.getContextPath()+"/login");
		}else {
			req.getRequestDispatcher("/WEB-INF/views/game/RSP.jsp").forward(req, resp);
		}
		
		
	}
	
}
