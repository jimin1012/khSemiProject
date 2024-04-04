package semi.project.game.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.project.game.board.model.service.BoardService;
import semi.project.game.board.model.vo.BoardLike;
import semi.project.game.board.model.vo.BoardView;
import semi.project.game.member.model.vo.Member;

@WebServlet("/view/*")
public class BoardViewLikeServlet extends HttpServlet {

	/**
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/view/").length());

		BoardService service = new BoardService();

		try {

			// 게시글 좋아요 수(boardLikeCount)
			if (command.equals("boardLikeCount")) {

				int boardNo = Integer.parseInt(req.getParameter("boardNo"));

				int boardLikeCount = service.boardLikeCount(boardNo);

				new Gson().toJson(boardLikeCount, resp.getWriter());

			}
			
			// 게시글 좋아요 확인 여부
			if(command.equals("boardLikeCheck")) {
				
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				HttpSession session = req.getSession();
				
				Member loginMember = (Member)session.getAttribute("loginMember");
				
				int memberNo = loginMember.getMemberNo();
				
				int boardLikeCheck = service.boardLikeCheck(boardNo, memberNo);
				
				resp.getWriter().print(boardLikeCheck);
				
			}

			// 게시글 좋아요 등록(추가)(+1)
			if (command.equals("boardLikeUpdate")) {

				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				HttpSession session = req.getSession();
				
				Member loginMember = (Member)session.getAttribute("loginMember");
				
				int memberNo = loginMember.getMemberNo();

				int result = service.boardLikeUpdate(boardNo, memberNo);

				resp.getWriter().print(result);

			}
			
			// 게시글 좋아요 취소(삭제)(-1)
			if (command.equals("boardLikeDelete")) {

				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				HttpSession session = req.getSession();
				
				Member loginMember = (Member)session.getAttribute("loginMember");
				
				int memberNo = loginMember.getMemberNo();

				int result = service.boardLikeDelete(boardNo, memberNo);

				resp.getWriter().print(result);

			}
			
			
			// 관리자용 게시글 반려(취소) 처리
			if(command.equals("cancel")) {
				
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				int result = service.boardCancel(boardNo);

				new Gson().toJson(result, resp.getWriter());
				
			}
			
			// 관리자용 게시글 삭제 처리
			if(command.equals("delete")) {
				
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				int result = service.boardDelete(boardNo);

				new Gson().toJson(result, resp.getWriter());
				
			}
			
			// 관리자가 신고된 게시글의 한에서만 삭제 및 목록삭제 버튼 노출 체크용
//			if(command.equals("reportCheck")) {
//				
//				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
//				
//				int result = service.reportCheck(boardNo);
//
//				resp.getWriter().print(result);
//				
//			}
			

		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
