package semi.project.game.gamelist.model.dao;

import static semi.project.game.common.JDBCTemplate.Close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.project.game.gamelist.model.vo.GameList;

public class GameListDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	/** xml파일 생성 구문
	 * 
	 */
	public GameListDAO() {
		try {
			
			prop = new Properties();
			
			String filePath = GameListDAO.class.getResource("/semi/project/game/sql/score-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/** 게임 목록 노출 DAO
	 * @param conn
	 * @return gameList
	 * @throws Exception
	 */
	public List<GameList> selectGameList(Connection conn) throws Exception{

		List<GameList> gameList = new ArrayList<>();

		try {

			String sql = prop.getProperty("gameList");

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				GameList game = new GameList();
				
				game.setGameNo(rs.getInt("GAME_NO"));
				game.setGameName(rs.getString("GAME_NAME"));
				game.setGameImg(rs.getString("GAME_IMG"));

				gameList.add(game);

			}
			
		} finally {

			Close(rs);
			Close(pstmt);

		}

		return gameList;
	}

}
