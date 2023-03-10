package membership;

import common.JDBConnect;

public class MemberDAO extends JDBConnect { //db에 연결, 쿼리문 실행, db에서 얻어온 결과를 dto 객체에 담아서 리턴
	//명시한 데이터베이스로의 연결이 완료된 MemberDAO 객체를 생성.
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw); //JDBConnect에 인자 전달. db에 연결할 준비 마침. 연걸은 loginProcess에서 DAO객체 생성하고 파라미터 이 생성자에 전달하면 생성됨
	}
	
	//명시한 아이디/패스워드와 일치하는 회원 정보 반환
	public MemberDTO getMemberDTO(String uid, String upass) { //LoginProcess에서 이 메소드 호출한다. loginForm에서 입력받은 정보임. 쿼리문에서 사용할 예정
		MemberDTO dto = new MemberDTO(); //회원 정보 DTO 객체 생성(메소드 쓰려고)
		String query = "SELECT * FROM member WHERE id=? AND pass=?";
		
		try {
			//쿼리 실행
			psmt = con.prepareStatement(query); // select * 이니까 모든 값 psmt에 저장
			psmt.setString(1, uid); // ? 에 파라미터로 받은 uid, upass 저장
			psmt.setString(2, upass);
			rs = psmt.executeQuery(); //rs에 id, pass와 일치하는 행의 모든 값 저장
			
			// 결과 처리
			if (rs.next()) {
				//쿼리 결과로 얻은 회원 정보를 DTO 객체에 저장
				dto.setId(rs.getString("id")); //"id"는 db에 있는 컬럼명. 컬럼에 해당하는 값을 setId 메소드에 저장
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
