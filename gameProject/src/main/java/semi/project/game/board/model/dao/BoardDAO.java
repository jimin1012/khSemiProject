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

import semi.project.game.board.model.vo.Board;
import semi.project.game.board.model.vo.BoardImage;
import semi.project.game.board.model.vo.BoardPagination;
import semi.project.game.board.model.vo.BoardView;
import semi.project.game.board.model.vo.ReportPost;


public class BoardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	/** xml파일 생성 구문
	 * 
	 */
	public BoardDAO() {
		try {
			
			prop = new Properties();
			
			String filePath = BoardDAO.class.getResource("/semi/project/game/sql/board-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 게시판 이름 조회 DAO
	 * @param conn
	 * @param type
	 * @return boardName
	 * @throws Exception
	 */
	public String selectBoardName(Connection conn, int kind) throws Exception {

		String boardName = null;

		try {
			String sql = prop.getProperty("selectBoardName");
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, kind);
// 							이거 타입에서 카인드로 수정했음 오류나면 확인해야함
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardName = rs.getString(1);
			}

		} finally {
			Close(rs);
			Close(pstmt);
		}

		return boardName;

	}
	

	/** 특정 게시판 전체 게시글 수 조회 DAO
	 * @param conn
	 * @param kind
	 * @return listCount
	 * @throws Exception
	 */
	public int selectAllBoardList(Connection conn, int kind) throws Exception {

		int listCount = 0;

		try {

			String sql = prop.getProperty("selectAllBoardList");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, kind);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}

		} finally {

			Close(rs);
			Close(pstmt);

		}

		return listCount;
	}

	
	
	/** 특정 게시판에서 일정한 범위의 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param kind
	 * @return
	 */
	public List<Board> selectKindBoardList(Connection conn, BoardPagination pagination, int kind)throws Exception {
		
		List<Board> boardList = new ArrayList<>();

		try {

			String sql = prop.getProperty("selectBoardList");

			// BETWEEN 구문에 들어갈 범위 계산
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, kind);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Board board = new Board();

				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setCategoryNo(rs.getInt("CATEGORY_NO"));
				board.setCategoryName(rs.getString("CATEGORY_NAME"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberName(rs.getString("MEMBER_NAME"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberProfile(rs.getString("MEMBER_PROFILE"));

				boardList.add(board);

			}

		} finally {

			Close(rs);
			Close(pstmt);

		}

		return boardList;
	}

	
	/** 특정 게시글 상세 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return view
	 * @throws Exception
	 */
	public BoardView selectBoardView(Connection conn, int boardNo) throws Exception{

		BoardView view = null;
		
		try {
			
			String sql = prop.getProperty("selectBoardView");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				view = new BoardView();
				
				view.setBoardNo(rs.getInt("BOARD_NO"));
				view.setBoardTitle(rs.getString("BOARD_TITLE"));
				view.setBoardContent(rs.getString("BOARD_CONTENT"));
				view.setBoardDate(rs.getString("BOARD_DATE"));
				view.setCategoryNo(rs.getInt("CATEGORY_NO"));
				view.setMemberNo(rs.getInt("MEMBER_NO"));
				view.setBoardStatus(rs.getString("BOARD_STATUS"));
				view.setCategoryName(rs.getString("CATEGORY_NAME"));
				view.setMemberName(rs.getString("MEMBER_NAME"));
				view.setMemberId(rs.getString("MEMBER_ID"));
//				view.setReportYn(rs.getString("REPORT_YN"));
//				view.setImageNo(rs.getInt("IMAGE_NO"));
//				view.setImagePath(rs.getString("IMAGE_PATH"));
				
			}

			
		}finally {
			
			Close(rs);
			Close(pstmt);
			
		}
		
		return view;
	}

	/**
	 * 게시글 좋아요 수 DAO
	 * @param conn
	 * @param boardNo
	 * @return boardLikeCount
	 * @throws Exception
	 */
	public int boardLikeCount(Connection conn, int boardNo) throws Exception{
		
		int boardLikeCount = 0;
		
		try {
			
			String sql = prop.getProperty("boardLikeCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardLikeCount = rs.getInt(1);
			}
			
		}finally {
			Close(rs);
			Close(pstmt);
		}
		
		return boardLikeCount;
	}
	
	/**
	 * 게시글 좋아요 판단 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return boardLikeCount
	 * @throws Exception
	 */
	public int boardLikeCheck(Connection conn, int boardNo, int memberNo) throws Exception{
		
		int boardLikeCheck = 0;
		
		try {
			
			String sql = prop.getProperty("boardLikeCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardLikeCheck = rs.getInt(1);
			}
			
			
		}finally {
			Close(rs);
			Close(pstmt);
		}
		
		return boardLikeCheck;
	}
	

	/**
	 * 게시글 좋아요 추가 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int boardLikeUpdate(Connection conn, int boardNo, int memberNo) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("boardLikeUpdate");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			Close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 게시글 좋아요 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int boardLikeDelete(Connection conn, int boardNo, int memberNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("boardLikeDelete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			Close(pstmt);
		}
		
		return result;
	}

	/** 다음 게시글 번호 조회 DAO
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{

		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		} finally {
			Close(rs);
			Close(pstmt);
		}
		
		return boardNo;
	}

	/** 게시글 작성 DAO
	 * @param conn
	 * @param view
	 * @param categoryNo
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, BoardView view, int categoryNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, view.getBoardNo());
			pstmt.setString(2, view.getBoardTitle());
			pstmt.setString(3, view.getBoardContent());
			pstmt.setInt(4, categoryNo);
			pstmt.setInt(5, view.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			Close(pstmt);
		}
		
		return result;
	}
 
	/** 게시글 이미지 삽입 DAO
	 * @param conn
	 * @param boardImage
	 * @return
	 * @throws Exception
	 */
	public int insertBoardImage(Connection conn, BoardImage boardImage) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertBoardImage");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardImage.getImagePath());
			pstmt.setInt(2, boardImage.getBoardNo());
			
			result = pstmt.executeUpdate();
		} finally {
			Close(pstmt);
		}
		
		
		return result;
	}

	/** 게시글 이미지 리스트 조회
	 * @param conn
	 * @param boardNo
	 * @return imageList
	 * @throws Exception
	 */
	public List<BoardImage> selectImageList(Connection conn, int boardNo) throws Exception{

		List<BoardImage> imageList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectImageList");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())	{
				BoardImage image = new BoardImage();
				image.setImageNo(rs.getInt(1));
				image.setImagePath(rs.getString(2));
				image.setBoardNo(rs.getInt(3));
				
				imageList.add(image);
			}
		} finally {
			Close(rs);
			Close(pstmt);
		}
		
		return imageList;
	}

	/** 게시글 수정 DAO
	 * @param conn
	 * @param view
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, BoardView view) throws Exception{
		
		int result =0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, view.getBoardTitle());
			pstmt.setString(2, view.getBoardContent());
			pstmt.setInt(3, view.getCategoryNo());
			pstmt.setInt(4, view.getBoardNo());
			
			result = pstmt.executeUpdate();
		} finally {
			Close(pstmt);
		}
		
		return result;
	}

	/** 게시글 이미지 수정
	 * @param conn
	 * @param boardImage
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardImage(Connection conn, BoardImage boardImage) throws Exception{

		int result =0;
		
		try {
			String sql = prop.getProperty("updateBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardImage.getImagePath());
			pstmt.setInt(2, boardImage.getBoardNo());
			pstmt.setInt(3, boardImage.getImageNo());
			
			result =pstmt.executeUpdate();
		} finally {
			Close(pstmt);
		}
		return result;
	}

	/** 게시글 이미지 삭제
	 * @param conn
	 * @param deleteList
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoardImage(Connection conn, String deleteList, int boardNo) throws Exception{
	
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoardImage")+deleteList+")";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		}finally {
			Close(pstmt);
		}
		return result;
	}

	/** 게시글 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @param kind
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception {
		
		int result =0;
		try {
			String sql = prop.getProperty("deleteBoard");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			Close(pstmt);
		}
		return result;
	}

	/**
	 * 게시글 신고 DAO
	 * @param conn
	 * @param report
	 * @return
	 * @throws Exception
	 */
	public int boardViewReport(Connection conn, ReportPost report) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("boardViewReport");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, report.getReportContent());
			pstmt.setInt(2, report.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			Close(pstmt);
		}
		return result;
		
	}

	
	/**
	 * 신고된 게시글 반려(취소) 처리 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int boardCancel(Connection conn, int boardNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("boardCancel");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			Close(pstmt);
		}
		return result;
	}

	/**
	 * 신고된 게시글 게시글 삭제하기 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int boardDelete(Connection conn, int boardNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("boardDelete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			Close(pstmt);
		}
		return result;
	}
	
	/**
	 * 검색해서 게시글 목록 띄우는 DAO
	 * @param conn
	 * @param kind
	 * @param condition
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoardList(Connection conn, BoardPagination pagination, int kind, String condition) throws Exception{
		
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			
			String sql = prop.getProperty("searchBoardList1") 
						+ condition 
						+ prop.getProperty("searchBoardList2");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start = (pagination.getCurrentPage() -1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, kind);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setCategoryNo(rs.getInt("CATEGORY_NO"));
				board.setCategoryName(rs.getString("CATEGORY_NAME"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberName(rs.getString("MEMBER_NAME"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				
				boardList.add(board);
				
			}
			
		}finally{
			
			Close(rs);
			Close(pstmt);
			
		}
		return boardList;
	}

	/**
	 * 검색한 게시글 숫자 조회 DAO
	 * @param conn
	 * @param kind
	 * @param condition
	 * @return listCountSearch
	 * @throws Exception
	 */
	public int selectAllBoardListSearch(Connection conn, int kind, String condition) throws Exception{
		
		int listCountSearch = 0;
		
		try {
			
			String sql = prop.getProperty("searchListCount") + condition;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, kind);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCountSearch = rs.getInt(1);
			}
			
		}finally {
			Close(rs);
			Close(pstmt);
		}
		
		return listCountSearch;
	}

	/**
	 * 관리자가 신고된 게시글의 한에서만 삭제 및 목록삭제 버튼 노출 체크용 DAO
	 * @param conn
	 * @param boardNo
	 * @return reportConfirm
	 * @throws Exception
	 */
	public int reportConfirm(Connection conn, int boardNo) throws Exception{
		
		int reportConfirm = 0;
		
		try {
			
			String sql = prop.getProperty("reportConfirm");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reportConfirm = rs.getInt(1);
			}
			
		}finally {
			Close(pstmt);
		}
		return reportConfirm;
	}


	

}
