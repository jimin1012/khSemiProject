package semi.project.game.member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import semi.project.game.common.MyRenamePolicy;
import semi.project.game.member.model.service.MemberService;
import semi.project.game.member.model.vo.Member;

// 내 정보 수정
@WebServlet("/updateMyInfo")
public class UpdateMyInfoServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int maxSize = 1024 * 1024 * 100; // 업로드 최대 용량 (100MB)
		
		HttpSession session = req.getSession(); // session 얻어오는 것은 지장없음(사용가능)
		String root = session.getServletContext().getRealPath("/"); 
		String folderPath = "/resources/images/member/"; // 파일 저장 폴더 경로
		String folderPath2 = "/resources/images/"; // 파일 저장 폴더 경로
		String filePath = root + folderPath;
		
		String encoding = "UTF-8";
		
		MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
		
		// 정보 수정을 위해 loginMember의 memberNo를 불러옴
		Member loginMember = (Member)session.getAttribute("loginMember");
		int memberNo = loginMember.getMemberNo();
		int flag = Integer.parseInt(mpReq.getParameter("flag")); // 프로필 이미지 삭제 여부를 가져옴 기본값 1
		String profileImage = null;
		
		// 이미지 설정 부분
		if(mpReq.getFilesystemName("profile") != null) {
			profileImage = folderPath + mpReq.getFilesystemName("profile"); 
		}else {
			if(flag == 0) { // 프로필 이미지를 기본값으로 되돌리는지 판단 flag의 기본값 1
				profileImage = folderPath2 + "프로필 기본값.png";
			}else {
				profileImage = loginMember.getProfileImg();
			}
		}
		
		
		String memberName = mpReq.getParameter("memberName");
		String memberNewPw = mpReq.getParameter("memberNewPw");
		
		Member mem = new Member();
		
		mem.setProfileImg(profileImage);
		mem.setMemberName(memberName);
		mem.setMemberPw(memberNewPw);
		
		
		mem.setMemberNo(memberNo);
		
		
		try {
			
			MemberService service = new MemberService();
			
			if(memberNewPw.equals("")) {
				int result = service.updateMyInfo(mem);
				
				if(result > 0) {
					
					loginMember.setMemberName(memberName);
					loginMember.setProfileImg(profileImage);
					
					session.setAttribute("message", "정보 수정 완료!");
				}else {
					session.setAttribute("message", "정보 수정 실패");
				}
				
			}else {
				int result = service.updateMyInfoPw(mem);
				
				if(result > 0) {
					loginMember.setMemberName(memberName);
					loginMember.setProfileImg(profileImage);
					loginMember.setMemberPw(memberNewPw);
					
					session.setAttribute("message", "정보 수정 완료(비밀번호 포함)");
				}else {
					session.setAttribute("message", "정보 수정 실패");
				}
				
			}
			
			
			req.setAttribute("loginMember", loginMember);
			
			resp.sendRedirect(req.getContextPath()+"/myInfo");
		
		}catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}

	}

}
