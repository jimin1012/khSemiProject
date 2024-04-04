package semi.project.game.board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.project.game.board.model.service.BoardService;


@WebServlet("/BoardList")
public class BoardListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			int kind = Integer.parseInt( req.getParameter("kind") );
			
			int cp = 1;
			
			if(req.getParameter("cp") != null) {
				cp = Integer.parseInt( req.getParameter("cp") );
			}
			
			BoardService service = new BoardService();
			
			// 게시판 이름, 페이지네이션 객체, 게시글 목록을 한 번에 반호나 하는 Service 호출
			Map<String, Object> map = null;
			
			if(req.getParameter("key") == null) { // 일반 목록 조회
				map = service.selectKindBoardList(kind, cp);
				
			}else { // 검색 목록 조회
				String key = req.getParameter("key");
				String query = req.getParameter("query");
				
				map = service.searchKindBoardList(kind, cp, key, query);
			}
			
			// request 범위로 map 세팅
			req.setAttribute("map", map);
			
			String path = "/WEB-INF/views/boarder/boardList.jsp";
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			
			dispatcher.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
		
	}
	
}
