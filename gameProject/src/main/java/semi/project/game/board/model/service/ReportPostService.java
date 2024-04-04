package semi.project.game.board.model.service;

import static semi.project.game.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import semi.project.game.board.model.dao.ReportPostDAO;
import semi.project.game.board.model.vo.Board;
import semi.project.game.board.model.vo.BoardPagination;
import semi.project.game.board.model.vo.ReportPost;
import semi.project.game.board.model.vo.ReportPostPagination;

public class ReportPostService {

	// DAO호출을 위한 객체 생성
	private ReportPostDAO dao = new ReportPostDAO();

	/**
	 * 신고된 게시글 목록 노출 Service
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> reportPostList(int cp) throws Exception {

		// 연결을 위한 커넥션 생성
		Connection conn = getConnection();
		
		// 신고된 게시글 목록 수 (DAO호출)
		int reportPostListCount = dao.reportPostListCount(conn, cp);
		
		// 신고게시글 목록을 위한 페이지네이션 객체 생성
		ReportPostPagination reportPostPagination = new ReportPostPagination(cp, reportPostListCount);
		
		// 신고된 게시글 목록 노출
		List<ReportPost> selectReportPostList = dao.selectReportPostList(conn, reportPostPagination, cp);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("reportPostListCount", reportPostListCount);
		map.put("reportPostPagination", reportPostPagination);
		map.put("selectReportPostList", selectReportPostList);

		return map;
	}

	/** 
	 * 신고 게시글 검색으로 목록 불러오기
	 * @param cp
	 * @param key
	 * @param query
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchReportPostList(int cp, String key, String query) throws Exception{

		Connection conn = getConnection();
		
		String condition = null;
		
		switch(key) {
			case "t" : condition = " AND REPORT_CONTENT LIKE '%" + query + "%' "; break;
		}
		
		int listCountSearch = dao.searchReportPostCount(conn, condition);

		ReportPostPagination reportPostPagination = new ReportPostPagination(cp, listCountSearch);

		List<ReportPost> searchReportPostList = dao.searchReportPostList(conn, reportPostPagination, condition);

		Map<String, Object> map = new HashMap<>();

		map.put("pagination", reportPostPagination);
		map.put("searchReportPostList", searchReportPostList);
		
		Close(conn);
		
		return map;
	}



}
