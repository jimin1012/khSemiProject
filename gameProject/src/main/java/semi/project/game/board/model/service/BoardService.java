package semi.project.game.board.model.service;


import static semi.project.game.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import semi.project.game.board.model.dao.BoardDAO;
import semi.project.game.board.model.vo.Board;
import semi.project.game.board.model.vo.BoardImage;
import semi.project.game.board.model.vo.BoardLike;
import semi.project.game.board.model.vo.BoardPagination;
import semi.project.game.board.model.vo.BoardView;
import semi.project.game.board.model.vo.ReportPost;
import semi.project.game.common.Util;
public class BoardService {

	private BoardDAO dao = new BoardDAO();

	
	/** 게시글 목록 조회(노출) Service
	 * @param kind
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectKindBoardList(int kind, int cp) throws Exception{
		
		Connection conn = getConnection();
		
		// 1. 게시판 이름 조회 DAO 호출
		String boardName = dao.selectBoardName(conn, kind);
		
		// 2-1. 특정 게시판 전체 게시글 수 조회 DAO 호출
		int listCount = dao.selectAllBoardList(conn, kind);
		
		// 2-2. 전체 게시글 수 + 현재 페이지(cp)를 이용해 페이지네이션(pagination) 객체 생성
		BoardPagination pagination = new BoardPagination(cp, listCount);
		
		// 3. 게시글 목록 조회
		List<Board> boardList = dao.selectKindBoardList(conn, pagination, kind);
		
		// 4. Map 객체를 생성하여 1,2,3 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<>();
		
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		
		Close(conn);
		
		
		return map;
	}


	/** 특정 게시글 상세 조회 Service
	 * @param boardNo
	 * @return view
	 * @throws Exception
	 */
	public BoardView selectBoardView(int boardNo) throws Exception{

		Connection conn = getConnection();
		
		BoardView view = dao.selectBoardView(conn, boardNo);
		
		int reportConfirm = dao.reportConfirm(conn, boardNo);
		
		if(view != null) { // 게시글 상세 조회 결과가 있을 경우 + 신고된 게시글일 경우
			
			List<BoardImage> imageList = dao.selectImageList(conn, boardNo);
		
			view.setImageList(imageList);
			
			view.setReportConfirm(reportConfirm);
			
			
		}
		
		return view;
	}


	
	/**
	 * 게시글 좋아요 수 Service
	 * @param boardNo
	 * @return boardLikeCount
	 * @throws Exception
	 */
	public int boardLikeCount(int boardNo) throws Exception{

		Connection conn = getConnection();
		
		int boardLikeCount = dao.boardLikeCount(conn, boardNo);
		
		Close(conn);
		
		return boardLikeCount;
	}
	
	/**
	 * 게시글 좋아요 확인 Service
	 * @param boardNo
	 * @param memberNo
	 * @return boardLikeCheck
	 * @throws Exception
	 */
	public int boardLikeCheck(int boardNo, int memberNo) throws Exception{

		Connection conn = getConnection();
		
		int boardLikeCheck = dao.boardLikeCheck(conn, boardNo, memberNo);
		
		Close(conn);
		
		return boardLikeCheck;
	}

	/**
	 * 게시글 좋아요 추가 Service
	 * @param boardNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int boardLikeUpdate(int boardNo, int memberNo) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.boardLikeUpdate(conn, boardNo, memberNo);
		
		if(result > 0 ) commit(conn);
	    else          rollback(conn);
		
		Close(conn);
		
		return result;
	}
	
	
	/**
	 * 게시글 좋아요 삭제 Service
	 * @param boardNo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int boardLikeDelete(int boardNo, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.boardLikeDelete(conn, boardNo, memberNo);
		
		if(result > 0 ) commit(conn);
	    else          rollback(conn);
		
		Close(conn);
		
		return result;
	}


	/** 게시글 작성(insert) 서비스
	 * @param view
	 * @param imageList
	 * @param categoryNo
	 * @return boardNo
	 * @throws Exception
	 */
	public int insertBoard(BoardView view, List<BoardImage> imageList, int categoryNo) throws Exception {

		Connection conn = getConnection();
		
		int boardNo = dao.nextBoardNo(conn);
		
		view.setBoardNo(boardNo);
		//XSS 방지 처리(제목/내용)
		view.setBoardTitle(Util.XSSHandling(view.getBoardTitle()));
		view.setBoardContent(Util.XSSHandling(view.getBoardContent()));
		
		// 개행문자 처리(내용)
		view.setBoardContent(Util.newLineHandling(view.getBoardContent()));
		
		int result = dao.insertBoard(conn,view,categoryNo);
		
		if(result > 0) {
			
			for (BoardImage boardImage : imageList) {
				boardImage.setBoardNo(boardNo); // 게시글번호세팅
				
				result = dao.insertBoardImage(conn,boardImage);
				
				if(result == 0) {
					break;
				}
			}// for문끝
		}// if문 끝
		
		if(result > 0) {
			commit(conn);
		}else { // 2,3번에서 한번이라도 실패한 경우
			rollback(conn);
			boardNo = 0; //게시글 번호를 0으로 바꿔서 실패했음을 컨트롤러로 전달
		}
		
		Close(conn);
		return boardNo;
	}


	/** 게시글 수정 Service
	 * @param view
	 * @param imageList
	 * @param deleteList
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(BoardView view, List<BoardImage> imageList, String deleteList) throws Exception {

		int result = 0;
		Connection conn = getConnection();
		
		view.setBoardTitle(Util.XSSHandling(view.getBoardTitle()));
		view.setBoardContent(Util.XSSHandling(view.getBoardContent()));
		
		view.setBoardContent(Util.newLineHandling(view.getBoardContent()));
		
		result = dao.updateBoard(conn,view);
		
		if(result>0) {
			
			for (BoardImage boardImage : imageList) {
				boardImage.setBoardNo(view.getBoardNo());
				
				result = dao.updateBoardImage(conn,boardImage);
				
				if(result == 0) {
					result = dao.insertBoardImage(conn, boardImage);
				}
			}
			
			if(!deleteList.equals("")) {
				result = dao.deleteBoardImage(conn,deleteList,view.getBoardNo());
			}
			
		}
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		Close(conn);
		return result;
	}

	/** 게시글 삭제
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn,boardNo);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		Close(conn);
		return result;
	}


	/**
	 * 게시글 신고 Service
	 * @param report
	 * @return result
	 * @throws Exception
	 */
	public int boardViewReport(ReportPost report) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.boardViewReport(conn, report);
		
		if(result>0) commit(conn);
		else		 rollback(conn);
		
		Close(conn);
		return result;
	}


	/**
	 * 신고된 게시글 목록에서제거(취소) 처리 Service
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int boardCancel(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.boardCancel(conn, boardNo);
		
		if(result > 0 ) commit(conn);
	    else          rollback(conn);
		
		Close(conn);
		
		return result;
	}


	/**
	 * 신고된 게시글 게시글 삭제하기 Service
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public int boardDelete(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.boardDelete(conn, boardNo);
		
		if(result > 0 ) commit(conn);
	    else          rollback(conn);
		
		Close(conn);
		
		return result;
	}
	
	/**
	 * 게시글 검색 해서 목록에 노출하기 Service
	 * @param kind
	 * @param cp
	 * @param key
	 * @param query
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchKindBoardList(int kind, int cp, String key, String query) throws Exception{

		Connection conn = getConnection();
		
		String boardName = dao.selectBoardName(conn, kind);
		
		String condition = null;
		
		switch(key) {
			case "tc" : condition = " AND (BOARD_TITLE LIKE '%"+ query +"%' OR BOARD_CONTENT LIKE '%" + query + "%') "; break;
			case "w" : condition = " AND MEMBER_NAME LIKE '%" + query + "%' "; break;
			case "t" : condition = " AND BOARD_TITLE LIKE '%" + query + "%' "; break;
		}
		
		int listCountSearch = dao.selectAllBoardListSearch(conn, kind, condition);

		// 3-2. 전체 게시글 수 + 현재 페이지(cp)를 이용해 페이지네이션(pagination) 객체 생성
		BoardPagination pagination = new BoardPagination(cp, listCountSearch);

		// 4. 특정 게시판에서 조건을 만족하는 게시글 목록 조회
		List<Board> boardList = dao.searchBoardList(conn, pagination, kind, condition);

		// 5. Map 객체를 생성하여 1,2,3 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<>();

		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		Close(conn);
		
		return map;
		
	}

}
