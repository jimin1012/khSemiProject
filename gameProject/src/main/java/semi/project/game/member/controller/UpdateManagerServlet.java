package semi.project.game.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.member.model.service.MemberService;

// 메니저 요청 처리
@WebServlet("/updateManager")
public class UpdateManagerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 메니저 가입을 요청한 회원의 번호
		int memberNo = Integer.parseInt( req.getParameter("no"));
		String manager = req.getParameter("manager");
		
		MemberService service = new MemberService();
		
		try {
			// 회원의 메니저 상태를 업데이트
			int result = service.updateManager(memberNo, manager);
			
			HttpSession session = req.getSession();
			
			String path = req.getContextPath()+"/memberList";
			
			if(result > 0) {
				session.setAttribute("message", "처리가 완료 되었습니다.");
			}else {
				session.setAttribute("message", "처리를 실패하였습니다.");
			}
			
			resp.sendRedirect(path);
			
		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
		
		
		
		
	}
	
}
