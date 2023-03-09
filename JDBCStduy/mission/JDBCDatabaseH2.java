package mission;

import java.sql.DriverManager;

public class JDBCDatabaseH2 extends JDBCDatabase { //JDBCDatabase 인터페이스 구현했기 때문에, mysql, h2 정보 다 갖게 됨. 그걸 메인에서 객체 생성
	
	public JDBCDatabaseH2(String url, String usr, String pwd) throws Exception {
		//부모 클래스의 생성자 호출하고 파라미터 전달
		super("org.h2.Driver"); //h2드라이버 로드
		//db에 연결
		con = DriverManager.getConnection(url, usr, pwd);
	}

	@Override
	public void StudyStatement(int type) throws Exception {

		System.out.println("StudyStatement:" + type);
		if (type == 1)	StudyStatement1();	// board
		else			StudyStatement2();	// memeber
	}



}