package semi.project.game.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.project.game.board.model.service.ReplyListService;
import semi.project.game.board.model.vo.ReplyList;


@WebServlet("/reply/*")
public class ReplyListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring(  (contextPath + "/reply/").length()  );
		
		ReplyListService service = new ReplyListService();
		
		try {
			
			// 댓글 목록 노출(selectReplyList)
			if(command.equals("selectReplyList")) {
				
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				
				List<ReplyList> replyList = service.selectReplyList(boardNo);
				
				new Gson().toJson(replyList, resp.getWriter());
				
			}
			
			// 댓글 삭제
			if(command.equals("delete")) {
				
				int replyNo = Integer.parseInt(req.getParameter("replyNo"));

				int result = service.deleteReply(replyNo);
				
				resp.getWriter().print(result);
				
			}
			
			//댓글 등록
			if(command.equals("insert")) {
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));
				int memberNo = Integer.parseInt(req.getParameter("loginMemberNo"));
				String rContent = req.getParameter("replyContent");
				
				
				ReplyList replyList = new ReplyList();
				
				replyList.setBoardNo(boardNo);
				replyList.setMemberNo(memberNo);
				replyList.setReplyContent(rContent);
				
				int result = service.replyInsert(replyList);
				
				resp.getWriter().print(result);
			}
			
			
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
