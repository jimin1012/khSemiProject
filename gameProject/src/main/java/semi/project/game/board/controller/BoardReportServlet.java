package semi.project.game.board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.project.game.board.model.service.BoardService;
import semi.project.game.board.model.vo.ReportPost;

// 상세게시판에서 신고버튼 눌렀을 경우 신고테이블에 컬럼값 변경
@WebServlet("/view/report")
public class BoardReportServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			BoardService service = new BoardService();
			
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			String reportContent = req.getParameter("reportContent");
			
			ReportPost report = new ReportPost();
			report.setBoardNo(boardNo);
			report.setReportContent(reportContent);
			
			int result = service.boardViewReport(report);

			new Gson().toJson(result, resp.getWriter());


		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
	
	}
	
}
