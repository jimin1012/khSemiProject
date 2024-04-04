package semi.project.game.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import semi.project.game.board.model.service.BoardService;
import semi.project.game.board.model.vo.BoardImage;
import semi.project.game.board.model.vo.BoardView;
import semi.project.game.common.MyRenamePolicy;
import semi.project.game.member.model.vo.Member;





@WebServlet("/write")
public class BoardWriteServlet extends HttpServlet{
	// 여기서 게시글 작성인지 수정인지는 mode를 읽어와서 판단함
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String mode = req.getParameter("mode");	// insert / update 구분
			
			
			int kind = Integer.parseInt(req.getParameter("kind"));
			
			// insert는 별도 처리 없이 jsp로 위임
			// update는 기존 게시글 내용을 조회하는 처리가 필요
			if(mode.equals("update")) { // 수정	
				
				BoardView view = new BoardService().selectBoardView(Integer.parseInt(req.getParameter("boardNo")));
				HttpSession session = req.getSession();
				Member loginMember = (Member)session.getAttribute("loginMember");
				
				if(loginMember.getMemberNo() == view.getMemberNo()) {
					view.setBoardContent(view.getBoardContent().replaceAll("<br>", "\n"));
					req.setAttribute("view", view);
				}else {
					req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
				}
				
			}
			
			String path ="/WEB-INF/views/boarder/boardWrite.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int maxSize = 1024 * 1024 * 100; // 업로드 최대 용량 (100MB)
			
			HttpSession session = req.getSession(); // session 얻어오는 것은 지장없음(사용가능)
			
			String root = session.getServletContext().getRealPath("/"); //webapp 폴더까지 경로
			String folderPath = "/resources/images/board/"; // 파일 저장 폴더 경로
			String filePath = root + folderPath;
			String encoding = "UTF-8"; // 파라미터 중 파일제외 파라미터(문자열)의 인코딩 지정
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			
			Enumeration<String> files = mpReq.getFileNames();
			List<BoardImage> imageList = new ArrayList<>();
			
			while(files.hasMoreElements()) {//다음 요소가 있으면 true
				String name= files.nextElement(); // 다음 요소(name 속성 값)을 얻어옴
				
//				String original = mpReq.getOriginalFileName(name); // 원본 파일명
				String imagePath = mpReq.getFilesystemName(name); // 변경된 파일명
				
				if(imagePath != null) {//업로드된 파일이 있을 경우
									  // 현재 files에서 얻어온 name 속성을 이용해
									  // 원본 또는 변경을 얻어왔을 때 그 값이 null이 아닌 경우
					// 이미지 정보를 담은 객체(BoardImage)를 생성
					BoardImage image = new BoardImage();
					image.setImagePath(folderPath+imagePath);
					imageList.add(image); // 리스트에 추가
					
				}// if문 끝
			}// while문 끝
			
			String boardTitle = mpReq.getParameter("boardTitle");
			String boardContent = mpReq.getParameter("boardContent");
			int categoryNo =  Integer.parseInt(mpReq.getParameter("boardCategory"));// 셀렉트 박스
			
			
			Member loginMember = (Member)session.getAttribute("loginMember");
			int memberNo = loginMember.getMemberNo(); // 회원 번호
			
			BoardView view = new BoardView();
			
			view.setBoardTitle(boardTitle);
			view.setBoardContent(boardContent);
			view.setMemberNo(memberNo);
			
			BoardService service = new BoardService();
			String mode =mpReq.getParameter("mode");// hidden
			if(mode.equals("insert")) { // 삽입
				int boardNo = service.insertBoard(view,imageList,categoryNo);
				
				String path =null;
				
				if(boardNo > 0) { // 성공
					session.setAttribute("message", "게시글이 등록되었습니다.");
					path = "view?no="+boardNo+"&kind="+categoryNo;
				}else {//실패
					session.setAttribute("message", "게시글 등록 실패");
					path = "write?mode="+mode+"&kind="+categoryNo;
				}
				resp.sendRedirect(path);
			}
			
			if(mode.equals("update")) { // 수정
				
				int boardNo = Integer.parseInt(mpReq.getParameter("boardNo"));
				int kind = Integer.parseInt(mpReq.getParameter("kind"));
				int cp = Integer.parseInt(mpReq.getParameter("cp"));
				String deleteList = mpReq.getParameter("deleteList");
				
				view.setBoardNo(boardNo);
				view.setCategoryNo(categoryNo);
				int result = service.updateBoard(view,imageList,deleteList);
				
				
				String path= null;
				String message = null;
				
				if(result>0) {
					path = "view?no="+boardNo+"&kind="+categoryNo+"&cp="+cp;
					message = "게시글이 수정되었습니다.";
				}else {
					path = req.getHeader("referer");
					message = "게시글 수정 실패";
				}
				session.setAttribute("message", message);
				resp.sendRedirect(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/views/boarder/error.jsp").forward(req, resp);
		}
		
	}
}
