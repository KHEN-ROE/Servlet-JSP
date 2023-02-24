package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCClientMySQL {

	public static void main(String[] args) throws Exception {
		
		//MySQL 접속을 위한 JDCB 드라이버 로드 - 
		Class.forName("com.mysql.cj.jdbc.Driver"); //class명 Class의 static 메소드 forName 호출(객체생성없이 사용)
		
		//MySQL Database 연결 객체 생성, 마찬가지로 DriverManager 클래스의 스태틱 메소드 호출. db가 있는 위치(ip명으로 접근가능 내 ip;10.125.121.209)/db명/유저명/pw
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "musthave", "tiger");
		
		//질의를 위한 객체 생성하여 con 참조변수에 있는 정보 저장
		Statement st = con.createStatement(); //어떤 데이터베이스든 드라이버만 제대로 로드하면 이런 인터페이스 가져와 쓸 수 있다
		//Statement 인터페이스의 createStatemnet 메소드 호출
		
		//SQL 질의
		ResultSet rs = st.executeQuery("select Name,Continent,Population,HeadOfState from country");
		
		//질의 결과 Parsing - cursor processing 커서가 맨처음에 db의 빈공간을 가리키다가 rs.next호출하면 첫번째 레코드 값이 리모트해서 전송해서 저장된다
		while(rs.next()) {
			for(int i=1; i<=4; i++) { // result set은 0이 아닌 1부터 시작한다. 이유없음
				if(i != 1) System.out.print(",");
				System.out.print(rs.getString(i)); //i번째 값 가져옴
			}
			System.out.println();
		}
		
		//생성된 객체 연결을 모두 해제
		rs.close();
		st.close();
		con.close();

	}

}
