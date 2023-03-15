package fileupload;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class MyfileDAO extends JDBConnect{

	//db에 연결
	public MyfileDAO(ServletContext application) {
		super(application);
	}
	
	//새로운 게시물 입력
	public int insertFile(MyfileDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO myfile(" + " name, title cate, ofile, sfile) "+ " VALUES(" + " ?, ?, ?, ?, ?,)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getCate());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			
			applyResult = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("INSERT 중 예외 발생");
			e.printStackTrace();
		}
		
		
		return applyResult;
	}
}
