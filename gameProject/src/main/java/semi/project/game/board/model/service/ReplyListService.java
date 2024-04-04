package semi.project.game.board.model.service;

import static semi.project.game.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import semi.project.game.board.model.dao.ReplyListDAO;
import semi.project.game.board.model.vo.ReplyList;
import semi.project.game.common.Util;

public class ReplyListService {
	
	private ReplyListDAO dao = new ReplyListDAO();

	
	/** 댓글 목록 노출 (비동기) Service
	 * @param boardNo
	 * @return	replyList
	 * @throws Exception
	 */
	public List<ReplyList> selectReplyList(int boardNo) throws Exception{

		Connection conn = getConnection();
		
		List<ReplyList> replyList = dao.selectReplyList(conn, boardNo);
		
		Close(conn);
		
		return replyList;
	}


	/** 댓글 삭제 Service (DB완전삭제)
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn, replyNo);
		
		if(result > 0 ) commit(conn);
	    else          rollback(conn);
		
		Close(conn);
		
		
		return result;
	
	}


	/** 댓글 작성 서비스
	 * @param replyList
	 * @return
	 * @throws Exception
	 */
	public int replyInsert(ReplyList replyList) throws Exception{

		Connection conn = getConnection();
		
		replyList.setReplyContent(Util.XSSHandling(replyList.getReplyContent()));
		
		replyList.setReplyContent(Util.newLineHandling(replyList.getReplyContent()));
		int result = dao.replyInsert(conn,replyList);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		Close(conn);
		return result;
	}

}
