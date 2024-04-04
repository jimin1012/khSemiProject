package semi.project.game.gamelist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import semi.project.game.gamelist.model.service.SaveScoreService;
import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;
import semi.project.game.member.model.vo.Score;


@WebServlet("/saveScore")
public class SaveScoreServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 유저 점수 실제로 저장하기 위한 GET 메소드
		
		int score = Integer.parseInt(req.getParameter("score"));
		int gameNo = Integer.parseInt(req.getParameter("gameNo"));
		
		
		try {
			HttpSession session = req.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
			
			int memberNo = loginMember.getMemberNo();
			
			SaveScoreService service = new SaveScoreService(); 
			
			int result = service.saveScore(score,gameNo,memberNo);
			
			// result는 ajax로 보내서 거기서 비교할거임 1인지 0인지
			resp.getWriter().print(result);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 지금 유저가 저장하려는 점수가 이미 DB에 있는 점수랑 같은 지 비교하기 위한 메소드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int memberNo = Integer.parseInt(req.getParameter("memberNo"));
		int gameNo = Integer.parseInt(req.getParameter("gameNo"));
	
		try {
			MemberService service = new MemberService();
			// 지금 로그인 한 유저의 모든 게임 기록을 가져옴
			List<Score> score = service.selectSaveScore(memberNo);
			List<Integer> sArr = new ArrayList<>();
			
			// 유저의 기록 중 DB에서 보낸 게임에 관한 점수만 다시 호출
			for (int i = 0; i<score.size(); i++) {
				if(score.get(i).getGameNo() == gameNo) {
					sArr.add(score.get(i).getScore()); 
				}
			}
			// 아직 DB에 점수가 하나도 없어서 비교할 점수가 없다면 -1 리턴
			if(sArr.size() ==0) {
				sArr.add(-1);
			}
			
			
			new Gson().toJson(sArr,resp.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
	}
}
