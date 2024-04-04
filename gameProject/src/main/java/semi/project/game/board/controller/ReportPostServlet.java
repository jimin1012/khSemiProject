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

import semi.project.game.board.model.service.ReportPostService;
import semi.project.game.board.model.vo.ReportPost;

@WebServlet("/report/*")
public class ReportPostServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/report/").length());
		
		try {
			
			
			if(command.equals("post")) {
				
				int cp = 1;
				
				if (req.getParameter("cp") != null) {
					cp = Integer.parseInt(req.getParameter("cp"));
				}
				
				ReportPostService service = new ReportPostService();
				
				// 게시판 이름, 페이지네이션 객체, 게시글 목록을 한 번에 반호나 하는 Service 호출
				Map<String, Object> map = null;
				
				if(req.getParameter("key") == null) { // 일반 목록 조회
					map = service.reportPostList(cp);
					
				}else { // 검색 목록 조회
					String key = req.getParameter("key");
					String query = req.getParameter("query");
					
					map = service.searchReportPostList(cp, key, query);
					
				}
				
				req.setAttribute("map", map);
				
				String path = "/WEB-INF/views/boarder/reportPost.jsp";
				
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				
				dispatcher.forward(req, resp);
				
			}
			


		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}

	}

}
