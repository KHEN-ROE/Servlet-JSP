package fileupload;

import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class MyfileDAO extends JDBConnect{

	public MyfileDAO() {
		
	}
	
	//db에 연결
	public MyfileDAO(ServletContext application) {
		super(application);
	}
	
	//파일 목록 반환
	public List<MyfileDTO> myFileList() {
		List<MyfileDTO> fileList = new Vector<MyfileDTO>();
		
		//쿼리문 작성
		String query = "SELECT * FROM myfile ORDER BY IDX DESC";
		try {
			psmt = con.prepareStatement(query); //쿼리 준비
			rs = psmt.executeQuery(); // 쿼리 실행
			
			while(rs.next()) { //목록 안의 파일 수만큼 반복
				//dto에 저장
				MyfileDTO dto = new MyfileDTO();
				dto.setIdx(rs.getString(1)); //
				dto.setName(rs.getString(2)); //데이터 베이스의 데이터 가져와서 dto객체에 저장. 1은 첫번째 열 의미. 근데 db에 언제 저장? 밑에 insertFile 메소드에서. 
				dto.setTitle(rs.getString(3));
				dto.setCate(rs.getString(4));
				dto.setOfile(rs.getString(5));
				dto.setSfile(rs.getString(6));
				dto.setPostdate(rs.getString(7));
				
				fileList.add(dto); // 목록에 추가
			}
			
		}
		catch(Exception e) {
			System.out.println("select 시 예외 발생");
			e.printStackTrace();
		}
		
		return fileList; //목록 반환
	}
	
	//새로운 게시물 입력
	public int insertFile(MyfileDTO dto) {
		int applyResult = 0;
		try {
			String query = "INSERT INTO myfile(name, title, cate, ofile, sfile) VALUES(?, ?, ?, ?, ?)"; //db에 데이터 저장
			
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