package semi.project.game.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Score;

// 랭킹 조회
@WebServlet("/rank")
public class RankServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberService service = new MemberService();
		
		
		int category = 0;
		
		// 카테고리를 선택했는지 확인 선택안했다면 기본값 0
		if(req.getParameter("category") != null) {
			category = Integer.parseInt(req.getParameter("category"));	
		}
		
		
		try {
			if(category == 0) { // 카테고리가 기본값 일 경우
				
				List<Score> scoreList = service.firstSelectScore(); 
				
				if(scoreList != null) { // 조회 성공 시
					HttpSession score = req.getSession();
					score.setAttribute("scoreList", scoreList);
					req.getRequestDispatcher("WEB-INF/views/boarder/rank.jsp").forward(req, resp);
				}
			}else { // 카테고리가 선택 되었을 경우
				List<Score> scoreList = service.secondSelectScore(category);
				
				new Gson().toJson(scoreList, resp.getWriter());
			}
			
			
			 
		}catch (Exception e) {
			 e.printStackTrace();
			 req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
		
		
		
	}

}
