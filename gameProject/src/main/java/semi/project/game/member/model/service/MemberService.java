package semi.project.game.member.model.service;

import static semi.project.game.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import semi.project.game.board.model.vo.BoardPagination;
import semi.project.game.member.model.dao.MemberDAO;
import semi.project.game.member.model.vo.Member;
import semi.project.game.member.model.vo.Score;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();

	/** 로그인 서비스
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public Member login(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		Member loginMember = dao.login(conn,member);
		
		
		Close(conn);
		return loginMember;
	}


	/** 내 정보 수정 서비스
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMyInfo(Member mem) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateMyInfo(mem, conn);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		Close(conn);
		
		return result;
	}


	/** 아이디 찾기 서비스
	 * @param memberName
	 * @param memberBirth
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectMemberId(String memberName, String memberBirth) throws Exception{
		
		Connection conn = getConnection();
		
		
		List<Member> list = dao.selectMemberId(conn,memberName,memberBirth);
		
		Close(conn);
		return list;
	}


	/** 비밀번호 찾기 서비스( 비밀번호변경)
	 * @param memberId
	 * @param memberBirth
	 * @return
	 * @throws Exception
	 */
	public int selectMemberPw(String memberId, String memberBirth) throws Exception{
		int result =0;
		Connection conn = getConnection();
		
		result = dao.selectMemberPw(conn,memberId,memberBirth); // 셀렉트 문인데 int 가져오게함
		
		Close(conn);
		return result;
	}

 
	/** 비밀번호 재수정 서비스( 비밀번호변경)
	 * @param MemberId
	 * @param memberBirth
	 * @param memberPw
	 * @return result
	 * @throws Exception
	 */
	public int updateMemberPw(String MemberId, String memberBirth, String memberPw) throws Exception{
		int result =0;
		Connection conn = getConnection();
		result = dao.updateMemberPw(conn,MemberId,memberBirth,memberPw);
		
		if(result>0) commit(conn);
		else  rollback(conn);
			
		Close(conn);
		
		return result;
	}


	/** 내 정보 조회(게임 별 스코어) 서비스
	 * @param memberNo
	 * @return score
	 * @throws Exception
	 */
	public List<Score> selectScore(int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Score> score = dao.selectScore(memberNo, conn);
		
		Close(conn);
		
		return score;
	}


	/** 내 정보 수정(비밀번호 포함) Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMyInfoPw(Member mem) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateMyInfoPw(conn, mem);
		
		if(result > 0 )commit(conn);
		else rollback(conn);
		
		Close(conn);
		
		return result;
	}


	/** 회원 탈퇴 Service
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int cancellation(Member loginMember) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.cancellation(conn, loginMember);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		Close(conn);
		
		return result;
	}


	/** 회원 탈퇴를 위한 비밀번호 조회 Service
	 * @param memberNo
	 * @return memberPw
	 * @throws Exception
	 */
	public String cancellationPw(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		String memberPw = dao.cancellationPw(conn, memberNo);
		
		Close(conn);
		
		return memberPw;
	}


	/** 랭킹 조회(기본으로 보여줄 랭킹)
	 * @return score
	 * @throws Exception
	 */
	public List<Score> firstSelectScore() throws Exception{
		Connection conn = getConnection();
		
		List<Score> scoreList = dao.firstSelectScore(conn);
		
		Close(conn);
		
		return scoreList;
	}


	/** 랭킹 조회(카테고리 입력 후)
	 * @param category
	 * @return scoreList
	 * @throws Exception
	 */
	public List<Score> secondSelectScore(int category) throws Exception{
		
		Connection conn = getConnection();
		
		List<Score> scoreList = dao.secondSelectScore(conn, category);
		
		Close(conn);
		
		return scoreList;
	}


	/** 회원가입 Service
	 * @param signMember
	 * @param managerYN 
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member signMember, String managerYN) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, signMember, managerYN);
		
		if(result > 0)  commit(conn);
		else          rollback(conn);
		
		Close(conn);
		
		
		return result;
	}


	/** 저장된 점수 조회
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public List<Score> selectSaveScore(int memberNo) throws Exception{
		Connection conn = getConnection();
		
		List<Score> score = dao.selectSaveScore(memberNo, conn);
		
		Close(conn);
		
		return score;
	}


	/** 아이디 중복체크 Service
	 * @param userId
	 * @return result
	 * @throws Exception
	 */
	public int DuplicateCheck(String userId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.DuplicateCheck(conn,userId);
		
		Close(conn);
		
		return result;
	}


	/** 회원 목록 조회 Service
	 * @param cp 
	 * @param kind 
	 * @param memberName 
	 * @return mem
	 * @throws Exception
	 */
	public Map<String, Object> selectMember(int cp, int kind) throws Exception{
		Connection conn = getConnection();
		int listCount = 0;
		List<Member> mem = null;
		// 페이지네이션
		BoardPagination pagination = null;
		
		if(kind == 2) {
			listCount = dao.selectMemberCount2(conn); 
			pagination = new BoardPagination(cp, listCount);
			mem = dao.selectMemberRequest(conn, pagination);
		}else {
			// 페이지 갯수를 구하기 위해 회원의 수 조회
			listCount = dao.selectMemberCount(conn);
			pagination = new BoardPagination(cp, listCount);
			mem = dao.selectMember(conn, pagination);
		}
		// Map 객체에 저장
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("memberList", mem);
		
		Close(conn);
		
		return map;
	}


	/** 매니저 업데이트 Service
	 * @param memberNo
	 * @param manager
	 * @return result
	 * @throws Exception
	 */
	public int updateManager(int memberNo, String manager) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateManager(conn, memberNo, manager);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		Close(conn);
		
		return result;
	}


	/** 이름으로 회원 조회
	 * @param cp
	 * @param memberName
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectMemberName(int cp, String memberName) throws Exception{
		
		Connection conn = getConnection();
		
		int listCount = 0;
		
		List<Member> mem = null;
		
		// 페이지네이션
		BoardPagination pagination = null;
		
		
		listCount = dao.selectMemberCount3(conn, memberName);
		
		pagination = new BoardPagination(cp, listCount);
		
		mem = dao.selectMemberName(conn, pagination, memberName);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("memberList", mem);
		
		Close(conn);
		
		return map;
	}


	

}
