package semi.project.game.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;


@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberService service = new MemberService();
		
		int kind = 1;
		
		String memberName = req.getParameter("query");
		
		if(req.getParameter("kind") != null) {
			
			if(req.getParameter("kind") != "") {
				kind = Integer.parseInt(req.getParameter("kind"));
			}
		}
		
		int cp = 1;
		
		if(req.getParameter("cp") != null){
			if(req.getParameter("cp") != "") {
				cp = Integer.parseInt( req.getParameter("cp") );
			}
		}
		
		
		
		try {
			
			Map<String, Object> map;
			
			if(memberName != null) {
				if(!memberName.equals("")) {
					map = service.selectMemberName(cp, memberName);
				}else {
					map = service.selectMember(cp, kind);
				}
			}else {
				map = service.selectMember(cp, kind);
			}
			
			req.setAttribute("map", map);
			
			req.getRequestDispatcher("/WEB-INF/views/member/memberList.jsp").forward(req, resp);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
		
		
	}
	
	
}
