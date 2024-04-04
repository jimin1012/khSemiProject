package semi.project.game.gamelist.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.project.game.gamelist.model.service.GameListService;
import semi.project.game.gamelist.model.vo.GameList;
import semi.project.game.member.model.vo.Member;

@WebServlet("/gameList")
public class GameListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/game/gameList.jsp");

			HttpSession session = req.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");

			GameListService service = new GameListService();

			List<GameList> gameList = service.selectGameList();

			req.setAttribute("gameList", gameList);

			if (loginMember == null) {
				session.setAttribute("message", "로그인 후 이용가능합니다.");
				resp.sendRedirect(req.getContextPath());

			} else {
				dispatcher.forward(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}