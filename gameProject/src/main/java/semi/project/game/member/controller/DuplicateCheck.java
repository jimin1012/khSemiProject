package semi.project.game.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.project.game.member.model.service.MemberService;

@WebServlet("/DuplicateCheck")
public class DuplicateCheck extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userId = req.getParameter("userId");
		
		try {
			
			MemberService service = new MemberService();
			// 아이디 중복체크
			int result = service.DuplicateCheck(userId);
			
			resp.getWriter().print(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
	}
		
}
