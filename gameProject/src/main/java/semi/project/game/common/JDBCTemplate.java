package semi.project.game.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	
	// 필드
	private static Connection conn; // 초기값 null

	
	public static Connection getConnection() {

		try {
			
			Context initContext = new InitialContext();
			
			// servers -> context.xml 파일 찾기
			Context envContext = (Context) initContext.lookup("java:/comp/env");
		
			DataSource ds = (DataSource) envContext.lookup("jdbc/oracle"); 
			
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {

		}

		return conn;
	}
	
	public static void Close(Connection conn) {
		try {
			if(conn !=null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
	public static void Close(Statement stmt) {
		try {
			if(stmt !=null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
	// ResultSet 반환메소드
	public static void Close(ResultSet rs) {
		try {
			if(rs !=null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}
	}

	// 트랜잭션제어 commit 메소드
	public static void commit(Connection conn) {
		try {

			if(conn !=null && !conn.isClosed()) {
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();		
		}
	}
	
	// 트랜잭션제어 rollback 메소드
		public static void rollback(Connection conn) {
			try {

				if(conn !=null && !conn.isClosed()) {
					conn.rollback();
				}

			} catch (Exception e) {
				e.printStackTrace();		
			}
		}
}
