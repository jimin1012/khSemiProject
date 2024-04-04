package semi.project.game.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.project.game.board.model.service.BoardService;
import semi.project.game.board.model.vo.BoardLike;
import semi.project.game.board.model.vo.BoardView;
import semi.project.game.board.model.vo.ReplyList;

@WebServlet("/view")
public class BoardViewServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// 파라미터 중 게시글 번호(no) 얻어오기
			int boardNo = Integer.parseInt(req.getParameter("no"));
			
			BoardService service = new BoardService();
			
			BoardView view = service.selectBoardView(boardNo);
			
//			// 게시글 상세조회된 내용이 있을 경우 댓글 목록 조회
//			if(view != null) {
//				List<Reply> rList = new ReplyService().selectReplyList(boardNo);
//				req.setAttribute("rList", rList);
//			}
			
			req.setAttribute("view", view);
			
			String path = "/WEB-INF/views/boarder/boardView.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			
			dispatcher.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
