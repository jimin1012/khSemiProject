package semi.project.game.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.board.model.service.BoardService;

@WebServlet("/delete")
public class BoardDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			int boardNo = Integer.parseInt(req.getParameter("no"));
			int kind = Integer.parseInt(req.getParameter("kind"));
			
			String message = null;
			String path = null;
			
			BoardService service = new BoardService();
			
			int result = service.deleteBoard(boardNo);
			
			if(result>0) {
				message = "게시글이 삭제되었습니다.";
				path = "BoardList?kind="+kind;
			}else {
				message = "게시글이 삭제에 실패했습니다.";
				path = req.getHeader("referer");
			}
			
			HttpSession session = req.getSession();
			
			session.setAttribute("message", message);
			resp.sendRedirect(path);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
	}
}
