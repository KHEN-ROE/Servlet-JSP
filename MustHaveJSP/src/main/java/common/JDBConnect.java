package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	//기본 생성자
	public JDBConnect() {
		try {
			//JDBC 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//DBdp 연결
			String url = "jdbc:mysql://localhost:3306/musthave";
			String id = "musthave";
			String pwd = "tiger";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//두 번째 생성자
	public JDBConnect(String driver, String url, String id, String pwd) {
			try {
				//JDBC 드라이버 로드
				Class.forName(driver);
				
				//DB에 연결
				con = DriverManager.getConnection(url, id, pwd);
				
				System.out.println("DB 연결 성공(인수 생성자 1)");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//세 번째 생성자
		public JDBConnect(ServletContext application) {
			try {
				//JDBC 드라이버 로드
				String driver = application.getInitParameter("MySQLDrive");
				Class.forName(driver);
				
				//DB에 연결
				String url = application.getInitParameter("MySQLUrl");
				String id = application.getInitParameter("MySQLId");
				String pwd = application.getInitParameter("MySQLPwd");
				con = DriverManager.getConnection(url, id, pwd);
				
				System.err.println("DB 연결 성공(인수 생성자 2)");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	
//	public static void main(String[] args) {
//		//테스트용 객체 생성
//		JDBConnect jdbconnect = new JDBConnect();
//	}
	
		//연결 해제(자원 반납)
		public void close() {
			try {
				if (rs!= null) rs.close();
				if (stmt != null) stmt.close();
				if (psmt != null) psmt.close();
				if (con != null) con.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	
		
}
