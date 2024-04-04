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
import semi.project.game.board.model.vo.ReportPost;
import semi.project.game.board.model.vo.ReportPostPagination;

public class ReportPostDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	/**
	 * xml파일 생성 구문
	 * 
	 */
	public ReportPostDAO() {
		try {

			prop = new Properties();

			String filePath = ReportPostDAO.class.getResource("/semi/project/game/sql/reportpost-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 신고된 게시글 목록 숫자 DAO
	 * 
	 * @param conn
	 * @param cp
	 * @return reportPostListCount
	 * @throws Exception
	 */
	public int reportPostListCount(Connection conn, int cp) throws Exception {

		int reportPostListCount = 0;

		try {

			String sql = prop.getProperty("reportPostListCount");

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				reportPostListCount = rs.getInt(1);
			}

		} finally {
			Close(rs);
			Close(pstmt);
		}

		return reportPostListCount;
	}

	/**
	 * 신고된 게시글 목록 조회
	 * 
	 * @param conn
	 * @param reportPostPagination
	 * @param cp
	 * @return boardList
	 * @throws Exception
	 */
	public List<ReportPost> selectReportPostList(Connection conn, ReportPostPagination reportPostPagination, int cp)
			throws Exception {

		List<ReportPost> selectReportPostList = new ArrayList<ReportPost>();

		try {

			String sql = prop.getProperty("selectReportPostList");

			// BETWEEN 구문에 들어갈 범위 계산
			int start = (reportPostPagination.getCurrentPage() - 1) * reportPostPagination.getLimit() + 1;
			int end = start + reportPostPagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ReportPost reportPost = new ReportPost();

				reportPost.setBoardNo(rs.getInt("BOARD_NO"));
				reportPost.setBoardTitle(rs.getString("BOARD_TITLE"));
				reportPost.setBoardContent(rs.getString("BOARD_CONTENT"));
				
				reportPost.setMemberName(rs.getString("MEMBER_NAME"));
				
				reportPost.setReportYn(rs.getString("REPORT_YN"));
				reportPost.setReportContent(rs.getString("REPORT_CONTENT"));
				reportPost.setReportNo(rs.getInt("REPORT_NO"));
				
//				BOARD_NO, BOARD_TITLE, BOARD_CONTENT,
//			    REPORT_NO, REPORT_CONTENT, REPORT_YN, MEMBER_NAME

				selectReportPostList.add(reportPost);

			}
			

		} finally {

			Close(rs);
			Close(pstmt);

		}

		return selectReportPostList;
	}

	/**
	 * 신고된 게시글 숫자 조회
	 * @param conn
	 * @param condition
	 * @return listCountSearch
	 * @throws Exception
	 */
	public int searchReportPostCount(Connection conn, String condition) throws Exception{
		
		int listCountSearch = 0;
		
		try {
			
			String sql = prop.getProperty("searchReportPostCount");
			
			pstmt = conn.prepareStatement(sql);
			
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
	 * 신고된 게시글 중 검색해서 게시글 목록 띄우는 DAO
	 * @param conn
	 * @param reportPostPagination
	 * @param condition
	 * @return searchReportPostList
	 * @throws Exception
	 */
	public List<ReportPost> searchReportPostList(Connection conn, ReportPostPagination reportPostPagination, String condition) throws Exception{
		
		List<ReportPost> searchReportPostList = new ArrayList<ReportPost>();
		
		try {
			
			String sql = prop.getProperty("searchReportPostList1") 
						+ condition 
						+ prop.getProperty("searchReportPostList2");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start = (reportPostPagination.getCurrentPage() -1) * reportPostPagination.getLimit() + 1;
			int end = start + reportPostPagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReportPost reportPost = new ReportPost();
				
				reportPost.setReportNo(rs.getInt("REPORT_NO"));
				reportPost.setReportContent(rs.getString("REPORT_CONTENT"));
				reportPost.setReportYn(rs.getString("REPORT_YN"));
				reportPost.setReportYn(rs.getString("BOARD_TITLE"));
				reportPost.setBoardContent(rs.getString("BOARD_CONTENT"));
				reportPost.setBoardTitle(rs.getString("BOARD_TITLE"));
				reportPost.setBoardNo(rs.getInt("BOARD_NO"));
				reportPost.setMemberNo(rs.getInt("MEMBER_NO"));
				reportPost.setMemberName(rs.getString("MEMBER_NAME"));
				
				searchReportPostList.add(reportPost);
				
			}
			
		}finally{
			
			Close(rs);
			Close(pstmt);
			
		}
		
		return searchReportPostList;
	}


}
