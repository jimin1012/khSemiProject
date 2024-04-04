package semi.project.game.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;

@WebServlet("/idFind")
public class IdFindServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		String MemberName = req.getParameter("memberName");
		String memberBirth = req.getParameter("memberBirth");
		
		
		try {
		
			MemberService service = new MemberService();
			
			
			List<Member> list = service.selectMemberId(MemberName,memberBirth);
			
			if(list != null) {
				new Gson().toJson(list, resp.getWriter());
			}
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
