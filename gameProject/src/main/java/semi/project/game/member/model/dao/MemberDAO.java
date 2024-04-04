package semi.project.game.member.model.dao;

import static semi.project.game.common.JDBCTemplate.*;

import semi.project.game.board.model.vo.BoardPagination;
import semi.project.game.member.model.vo.Member;
import semi.project.game.member.model.vo.Score;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author user1
 *
 */
public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;

	// 기본 생성자를 통해 prop 생성(xml파일에서 sql 세팅 가능하게)
	public MemberDAO() {
		try {
			prop = new Properties();

			String filePath = MemberDAO.class.getResource("/semi/project/game/sql/member-sql.xml").getPath();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 로그인 DAO
	 * @param conn
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public Member login(Connection conn, Member member) throws Exception{
		Member loginMember = null; // 결과 저장용 변수

		try {

			//SQL 얻어오기
			String sql = prop.getProperty("login");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());

			rs = pstmt.executeQuery();

			if(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				String memberName = rs.getString("MEMBER_NAME");
				String memberId = rs.getString("MEMBER_ID");
				String memberProfile = rs.getString("MEMBER_PROFILE");
				String managerYn = rs.getString("MANAGER_YN");


				loginMember = new Member();
				loginMember.setMemberNo(memberNo);
				loginMember.setMemberName(memberName);
				loginMember.setMemberId(memberId);
				loginMember.setProfileImg(memberProfile);
				loginMember.setManagerYn(managerYn);

			}


		}finally {
			Close(rs);
			Close(pstmt);
		}

		return loginMember;
	}


	/** 아이디 찾기 DAO
	 * @param conn
	 * @param memberName
	 * @param memberBirth
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectMemberId(Connection conn, String memberName, String memberBirth) throws Exception{

		List<Member> list = new ArrayList<>();

		try {
			//SQL 얻어오기
			String sql = prop.getProperty("idFind");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,memberName);
			pstmt.setString(2, memberBirth);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Member member = new Member();
				String memberId = rs.getString(1);

				member.setMemberId(memberId);
				list.add(member);
			}
		} finally {
			Close(rs);
			Close(pstmt);
		}

		return list;

	}

	/** 내 정보 수정 DAO
	 * @param mem
	 * @param conn
	 * @return res
	 * @throws Exception
	 */
	public int updateMyInfo(Member mem, Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("updateMyInfo");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberName());
			pstmt.setString(2, mem.getProfileImg());
			pstmt.setInt(3, mem.getMemberNo());

			result = pstmt.executeUpdate();

		}finally {
			Close(pstmt);
		}


		return result;
	}

	public int selectMemberPw(Connection conn, String memberId, String memberBirth) throws Exception{
		int result =0;

		try {
			String sql = prop.getProperty("pwFind");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,memberId);
			pstmt.setString(2, memberBirth);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}
		} finally {
			Close(rs);
			Close(pstmt);
		}

		return result;
	}

	public int updateMemberPw(Connection conn, String memberId, String memberBirth, String memberPw) throws Exception{

		int result =0;

		try {

			String sql = prop.getProperty("pwUpdate");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberPw);
			pstmt.setString(2,memberId);
			pstmt.setString(3, memberBirth);

			result = pstmt.executeUpdate();

		}finally {
			Close(pstmt);
		}

		return result;
	}

	/** 내 정보 조회(게임별 스코어) DAO
	 * @param memberNo
	 * @param conn
	 * @return score
	 * @throws Exception
	 */
	public List<Score> selectScore(int memberNo, Connection conn) throws Exception{
		List<Score> score = new ArrayList<Score>();

		try {

			// 게임 최고점을 불러오기 위한 SQL문
			String sql = prop.getProperty("selectScoreMax");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				if(rs.getInt("GAME_NO") == 5) { 
					// 게임 번호가 5번, 6번은 제일 낮은 점수가 최고점
					// 그래서 MAX 불러오는 SQL 중단시키기 위함
					break;
				}

				Score s = new Score();

				s.setScore(rs.getInt(1));
				s.setGameName(rs.getString(2));
				s.setGameNo(rs.getInt(3));
				score.add(s);
			}

			// 게임 최소점을 불러오기 위한 SQL문
			// 게임번호 5번, 6번 : 업다운, 숫자야구
			// 최소점이 하이스코어
			sql = prop.getProperty("selectScoreMin");

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Score s = new Score();

				s.setScore(rs.getInt(1));
				s.setGameName(rs.getString(2));
				s.setGameNo(rs.getInt(3));
				score.add(s);
			}




		}finally {
			Close(rs);
			Close(pstmt);
		}


		return score;
	}

	/** 내 정보 수정(비밀번호 포함) DAO
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMyInfoPw(Connection conn, Member mem) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("updateMyInfoPw");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mem.getMemberName());
			pstmt.setString(2, mem.getProfileImg());
			pstmt.setString(3, mem.getMemberPw());
			pstmt.setInt(4, mem.getMemberNo());

			result = pstmt.executeUpdate();

		}finally {
			Close(pstmt);
		}

		return result;
	}

	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param loginMember
	 * @return result 
	 * @throws Exception
	 */
	public int cancellation(Connection conn, Member loginMember) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("cancellation");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, loginMember.getMemberNo());

			result = pstmt.executeUpdate();

		}finally {
			Close(pstmt);
		}

		return result;
	}

	public String cancellationPw(Connection conn, int memberNo) throws Exception{
		String memberPw = null;

		try {
			String sql = prop.getProperty("cancellationPw");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				memberPw = rs.getString(1);
			}

		}finally {
			Close(rs);
			Close(pstmt);
		}

		return memberPw;
	}

	/** 랭킹 조회(기본으로 보여줄 랭킹)
	 * @param conn
	 * @return score
	 * @throws Exception
	 */
	public List<Score> firstSelectScore(Connection conn) throws Exception{
		List<Score> scoreList = new ArrayList<>();

		try {
			String sql = prop.getProperty("firstSelectScore");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Score so = new Score();

				so.setGameName(rs.getString("GAME_NAME"));
				so.setMemberName(rs.getString("MEMBER_NAME"));
				so.setScore(rs.getInt("SCORE"));
				so.setProfile(rs.getString("MEMBER_PROFILE"));

				scoreList.add(so);
			}
		}finally {
			Close(rs);
			Close(pstmt);
		}

		return scoreList;
	}

	/** 랭킹 조회(카테고리 입력 후)
	 * @param conn
	 * @param category
	 * @return scoreList
	 * @throws Exception
	 */
	public List<Score> secondSelectScore(Connection conn, int category) throws Exception{
		List<Score> scoreList = new ArrayList<>();

		try {

			String sql = null;
			if(category > 4) {
				sql = prop.getProperty("secondSelectScoreMin");
			}else {
				sql = prop.getProperty("secondSelectScoreMax");
			}

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, category);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Score so = new Score();

				so.setGameName(rs.getString("GAME_NAME"));
				so.setMemberName(rs.getString("MEMBER_NAME"));
				so.setScore(rs.getInt("SCORE"));
				so.setProfile(rs.getString("MEMBER_PROFILE"));

				scoreList.add(so);
			}

		}finally {

		}

		return scoreList;
	}

	/** 회원가입
	 * @param conn
	 * @param signMember
	 * @param managerYN 
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member signMember, String managerYN)throws Exception {

		int result = 0;

		try {
			String sql = null;
			if(managerYN == null) {
				sql = prop.getProperty("signUp");
			}else {
				sql = prop.getProperty("signUpManager");
			}


			pstmt = conn.prepareStatement(sql);


			pstmt.setString(1, signMember.getMemberName());
			pstmt.setString(2, signMember.getMemberId());
			pstmt.setString(3, signMember.getMemberEmail());
			pstmt.setString(4, signMember.getMemberPw());
			pstmt.setString(5, signMember.getMemberBirthday());

			result = pstmt.executeUpdate();

		}finally {

			Close(pstmt);


		}


		return result;
	}

	/** 저장된 점수 조회 DAO
	 * @param memberNo
	 * @param conn
	 * @return 
	 * @throws Exception
	 */
	public List<Score> selectSaveScore(int memberNo, Connection conn) throws Exception{
		List<Score> score = new ArrayList<>();

		try {

			String sql = prop.getProperty("selectSaveScore");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				Score so = new Score();

				so.setScore(rs.getInt(1));
				so.setGameNo(rs.getInt(2));

				score.add(so);
			}

		}finally {
			Close(rs);
			Close(pstmt);
		}

		return score;
	}

	/** 아이디 중복체크 DAO
	 * @param conn
	 * @param userId
	 * @return result
	 * @throws Exception
	 */
	public int DuplicateCheck(Connection conn, String userId) throws Exception{

		int result = 0;

		try {

			String sql = prop.getProperty("DuplicateCheck");

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}

		} finally {
			Close(rs);
			Close(pstmt);
		}

		return result;
	}

	/** 회원 목록 조회 DAO
	 * @param conn 
	 * @param pagination 
	 * @return mem 
	 * @throws Exception
	 */
	public List<Member> selectMember(Connection conn, BoardPagination pagination) throws Exception{
		List<Member> mem = new ArrayList<>();

		try {

			String sql = prop.getProperty("selectMember");
			
			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {

				Member m = new Member();

				
				m.setMemberNo(rs.getInt(2));
				m.setMemberName(rs.getString(3));
				m.setMemberEmail(rs.getString(4));
				m.setStatusYn(rs.getString(5));
				m.setMemberBirthday(rs.getString(6));
				m.setManagerYn(rs.getString(7));
				m.setMemberId(rs.getString("MEMBER_ID"));

				mem.add(m);
			}

		}finally {
			Close(rs);
			Close(pstmt);
		}

		return mem;
	}

	/** 회원 목록 카운트 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int selectMemberCount(Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("selectMemberCount");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}

		}finally {
			Close(rs);
			Close(pstmt);
		}

		return result;
	}

	/** 관리자 요청을 한 회원 목록 조회 DAO
	 * @param conn
	 * @param pagination 
	 * @return MEM
	 * @throws Exception
	 */
	public List<Member> selectMemberRequest(Connection conn, BoardPagination pagination) throws Exception{
		List<Member> mem = new ArrayList<>();

		try {
			String sql = prop.getProperty("selectMemberRequest");

			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {

				Member m = new Member();

				m.setMemberNo(rs.getInt(2));
				m.setMemberName(rs.getString(3));
				m.setMemberEmail(rs.getString(4));
				m.setStatusYn(rs.getString(5));
				m.setMemberBirthday(rs.getString(6));
				m.setManagerYn(rs.getString(7));
				m.setMemberId(rs.getString("MEMBER_ID"));
				
				mem.add(m);
			}

		}finally {
			Close(rs);
			Close(pstmt);
		}

		return mem;
	}

	/** 관리자 요청을 한 회원 목록 카운트 DAO
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int selectMemberCount2(Connection conn) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("selectMemberCount2");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}

		}finally {
			Close(rs);
			Close(pstmt);
		}

		return result;
	}

	/** 매니저 업데이트 DAO
	 * @param conn
	 * @param memberNo
	 * @param manager
	 * @return result
	 * @throws Exception
	 */
	public int updateManager(Connection conn, int memberNo, String manager) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateManager");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, manager);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			Close(pstmt);
		}
		
		return result;
	}

	/** 이름으로 회원 조회 목록 카운트
	 * @param conn
	 * @param memberName 
	 * @return result
	 * @throws Exception
	 */
	public int selectMemberCount3(Connection conn, String memberName) throws Exception{
		int result = 0;

		try {
			String sql = prop.getProperty("selectMemberCount3");

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberName);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = rs.getInt(1);
			}

		}finally {
			Close(rs);
			Close(pstmt);
		}

		return result;
	}

	/** 회원 목록에서 이름으로 검색 DAO
	 * @param conn
	 * @param pagination
	 * @param memberName
	 * @return mem
	 * @throws Exception
	 */
	public List<Member> selectMemberName(Connection conn, BoardPagination pagination, String memberName) throws Exception{
		List<Member> mem = new ArrayList<>();

		try {
			String sql = prop.getProperty("selectMemberName");

			int start = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberName);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {

				Member m = new Member();

				m.setMemberNo(rs.getInt(2));
				m.setMemberName(rs.getString(3));
				m.setMemberEmail(rs.getString(4));
				m.setStatusYn(rs.getString(5));
				m.setMemberBirthday(rs.getString(6));
				m.setManagerYn(rs.getString(7));
				m.setMemberId(rs.getString("MEMBER_ID"));

				mem.add(m);
			}

		}finally {
			Close(rs);
			Close(pstmt);
		}

		return mem;
	}




}
