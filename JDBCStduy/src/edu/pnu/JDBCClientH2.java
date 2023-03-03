package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBCClientH2 {

	//주석을 달아볼 것
	
	//statement : 파라미터 없이 spl문 출력
	//preparestatement : 파라미터필요함. 언제? 조건이 붙을 때. sql에서 where 뒤에 조건 붙을 떄 
	public int printColumnName(ResultSet rs) throws Exception {
		ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		
		StringBuilder sb = new StringBuilder("#");
		for (int i = 1 ; i <= count ; i++) {
			sb.append("," + meta.getColumnName(i));
		}
		System.out.println(sb);
		System.out.println("-".repeat(sb.length()));
		
		return count;
	}
	
	public void StudyStatement(Connection con) throws Exception {
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from board");

		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) {
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}

public void StudyStatement2(Connection con) throws Exception {
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from member");

		int colCount = printColumnName(rs);
		int rowCount = 1;
		while(rs.next()) {
			for(int i = 1 ; i <= colCount ; i++) {
				if (i == 1)	System.out.print((rowCount++) + ",");
				else		System.out.print(",");
				System.out.print(rs.getString(i));
			}
			System.out.println();
		}
		rs.close();
		st.close();
	}
	
	//엔트리 포인트
	public static void main(String[] args) throws Exception  {
		//메소드 사용하려고 스스로 객체생성
		JDBCClientH2 cli = new JDBCClientH2();

		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//커넥션 가져옴
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/musthave", "musthave", "tiger");) {
			
			System.out.println("<=== StudyStatement ===>");
			//메소드호출하면서 파라미터로 앞에서 만든 Connection 객체 전달
			cli.StudyStatement(con);
			System.out.println();

			System.out.println("<=== StudyStatement ===>");
			cli.StudyStatement2(con);
		}		
	}
}
