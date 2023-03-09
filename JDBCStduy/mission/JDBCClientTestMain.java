package mission;

public class JDBCClientTestMain {

	public static void main(String[] args) throws Exception {

		JDBCDatabase jd1 = new JDBCDatabaseH2("jdbc:h2:tcp://localhost/~/musthave", "sa", ""); //H2 관련 정보 전달

		jd1.StudyStatement(1);
		jd1.StudyStatement(2);
		 
	}

}