package semi.project.game.gamelist.model.service;

import static semi.project.game.common.JDBCTemplate.Close;
import static semi.project.game.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import semi.project.game.gamelist.model.dao.GameListDAO;
import semi.project.game.gamelist.model.vo.GameList;

public class GameListService {

	private GameListDAO dao = new GameListDAO();

	
	/** 게임 목록 조회
	 * @return gameList
	 * @throws Exception
	 */
	public List<GameList> selectGameList() throws Exception{

		Connection conn = getConnection();
		
		List<GameList> gameList = dao.selectGameList(conn);
		
		Close(conn);
		
		return gameList;
	}
	
	


}
