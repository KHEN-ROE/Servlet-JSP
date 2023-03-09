package mission;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class JDBCDatabase {

	Connection con;
	
	//생성자
	public JDBCDatabase(String driverName) throws ClassNotFoundException {
		//드라이버 로드
		Class.forName(driverName);
	}
	
	//추상메소드
	public abstract void StudyStatement(int type) throws Exception;
	
	//일반메소드
	void StudyStatement2() throws Exception {
		//createStatement 하고
		Statement st = con.createStatement(); //createStatement는 connection 인터페이스의 추상메소드네?. Statement도 인터페이스고
		//쿼리 실행하고
		ResultSet rs = st.executeQuery("select num,title,content,id from board"); // 전부다 인터페이스의 추상메서드네? 어떻게 이게 작동되는 거지? 책을 읽어볼 것
		
		//커서프로세싱하고
		while(rs.next() ) {
			
			System.out.println(rs.getString("num")+","+
								rs.getString("title")+","+
								rs.getString("content")+","+
								rs.getString("id"));
		}
		
		rs.close();
		st.close();
	}

	void StudyStatement1() {
	}
}
