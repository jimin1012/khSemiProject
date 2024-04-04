package semi.project.game.gamelist.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static semi.project.game.common.JDBCTemplate.*;
public class SaveScoreDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	/** xml파일 생성 구문
	 * 
	 */
	public SaveScoreDAO() {
		try {
			
			prop = new Properties();
			
			String filePath = SaveScoreDAO.class.getResource("/semi/project/game/sql/score-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 게임 스코어 저장 DAO
	 * @param conn
	 * @param score
	 * @param memberNo
	 * @param gameNo
	 * @return result
	 * @throws Exception
	 */
	public int saveScore(Connection conn, int score, int gameNo, int memberNo) throws Exception{
		int result =0;
		
		try {
			
			String sql = prop.getProperty("saveScore");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, score);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, gameNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			Close(pstmt);
		}
		return result;
	}
	
	
}
