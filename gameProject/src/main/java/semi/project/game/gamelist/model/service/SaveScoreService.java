package semi.project.game.gamelist.model.service;

import semi.project.game.gamelist.model.dao.SaveScoreDAO;
import static semi.project.game.common.JDBCTemplate.*;

import java.sql.Connection;

public class SaveScoreService {
	private SaveScoreDAO dao = new SaveScoreDAO();

	/** 게임 스코어 저장 서비스
	 * @param score
	 * @param gameNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int saveScore(int score, int gameNo, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.saveScore(conn,score,gameNo,memberNo);
		
		if(result>0) commit(conn);
		else  rollback(conn);
		
		Close(conn);
		return result;
	}
	
	
}
