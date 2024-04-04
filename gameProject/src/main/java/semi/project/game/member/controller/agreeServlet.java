package semi.project.game.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;


@WebServlet("/agree")
public class agreeServlet extends HttpServlet{
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/agree.jsp";
		req.getRequestDispatcher(path).forward(req, resp);

	}
	

}
