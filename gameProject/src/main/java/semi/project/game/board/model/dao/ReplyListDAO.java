package semi.project.game.board.model.dao;

import static semi.project.game.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.project.game.board.model.vo.ReplyList;

public class ReplyListDAO {

	// 필요한 변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	/**
	 * xml파일 생성 구문
	 * 
	 */
	public ReplyListDAO() {
		try {

			prop = new Properties();

			String filePath = ReplyListDAO.class.getResource("/semi/project/game/sql/reply-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 댓글 목록 노출 (비동기) DAO
	 * 
	 * @param conn
	 * @param boardNo
	 * @return replyList
	 * @throws Exception
	 */
	public List<ReplyList> selectReplyList(Connection conn, int boardNo) throws Exception {

		List<ReplyList> replyList = new ArrayList<ReplyList>();

		try {

			String sql = prop.getProperty("selectReplyList");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				ReplyList reply = new ReplyList();

				reply.setReplyNo(rs.getInt("REPLY_NO"));
				reply.setReplyContent(rs.getString("REPLY_CONTENT"));
				reply.setReplyDate(rs.getString("REPLY_DATE"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setMemberNo(rs.getInt("MEMBER_NO"));
				reply.setMemberName(rs.getString("MEMBER_NAME"));
				reply.setMemberProfile(rs.getString("MEMBER_PROFILE"));
				

				replyList.add(reply);
				
			}

		} finally {

			Close(rs);
			Close(pstmt);

		}

		return replyList;
	}

	
	/** 댓글 삭제 DAO (DB완전삭제)
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(Connection conn, int replyNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteReply");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			
			Close(pstmt);
			
		}
		return result;
	}

	/** 댓글 작성 DAO
	 * @param conn
	 * @param replyList
	 * @return result
	 * @throws Exception
	 */
	public int replyInsert(Connection conn, ReplyList replyList) throws Exception{
		int result =0;
		try {
			String sql = prop.getProperty("replyInsert");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, replyList.getReplyContent());
			pstmt.setInt(2, replyList.getBoardNo());
			pstmt.setInt(3, replyList.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			Close(pstmt);
		}
		return result;
	}

}
