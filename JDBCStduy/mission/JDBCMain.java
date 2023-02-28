package edu.mission;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import edu.pnu.JDBCClientH2;

public class JDBCMain {
	
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
	
	public static void main(String[] args) {
		JDBCMain cli = new JDBCMain();

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
