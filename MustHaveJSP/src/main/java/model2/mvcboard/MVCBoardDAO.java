package model2.mvcboard;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class MVCBoardDAO extends JDBConnect {
	
	public MVCBoardDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// db 연결
	public MVCBoardDAO(ServletContext application) {
		super(application);
	}
	
	

	// db에 있는 데이터 가져옴
	// 검색 조건에 맞는 게시물의 개수 반환
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		// 쿼리문 준비
		String query = "SELECT COUNT(*) FROM mvcboard";
		
		//검색 조건이 있다면 where절로 추가
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement(); // 쿼리문 실행
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1); // 검색된 게시물 개수 저장
			
		}
		catch(Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount; // 게시물 개수를 서블릿으로 반환
	}
	
	// db에 있는 데이터 가져와서 DTO에 저장. dto에 저장된 데이터를 이제 List.jsp에서 보여줄 거임
	// 검색 조건에 맞는 게시물 목록을 반환(페이징 기능 지원).
	public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		
		//쿼리문 준비
		String query = "SELECT * FROM mvcboard";
		
		//검색조건 추가
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += " ORDER BY Idx DESC LIMIT ?,?"; //LIMIT FROM, TO
		
		try {
			psmt = con.prepareStatement(query);//쿼리문 준비
			psmt.setInt(1, (int)map.get("start")-1);
			psmt.setInt(2, (int)map.get("pageSize"));
			
			// 쿼리문 실행
			rs = psmt.executeQuery(); 
			
			// 반환된 게시물 목록을 List 컬렉션에 추가
			while(rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO(); //dto 메소드 이용하기 위해 객체 생성
				
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getString(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto); //리스트에 dto 추가
				
			}
		}
		catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return board; //목록 반환
	}
	
	//게시글 데이터를 받아 db에 추가합니다(파일 업로드 지원)
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0; // result 무슨 의미? 왜 선언?
		try {
			String query = "INSERT INTO mvcboard (name, title, content, ofile, sfile, pass) VALUES (?,?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			result = psmt.executeUpdate(); //왜 result에 저장?
			
		}
		catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 주어진 일련번호에 해당하는 게시물을 dto에 담아 반환
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO(); //DTO객체 생성
		String query = "SELECT * FROM mvcboard WHERE idx=?"; // 쿼리문 템플릿 준비
		try {
			psmt = con.prepareStatement(query); // 쿼리문 준비
			psmt.setString(1, idx); // 인파라미터 설정. 여기까지 쿼리문 준비
			rs = psmt.executeQuery(); // 쿼리문 실행하여 db에 있는 데이터 가져옴. 그걸 rs에 저장
			
			//rs에 저장된 데이터들을 dto에 저장. dto에 저장된 데이터들은 이후 view.jsp에서 메소드 오버라이딩하여 사용하고 화면에 뿌려줄 예정
			if(rs.next()) {
				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getString(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
		}
		catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		
		
		
		return dto;
	}
	
	//주어진 일련번호에 해당하는 게시물의 조회수 1 증가 시킴
	public void updateVisitCount(String idx) {
		String query = "UPDATE mvcboard SET visitcount=visitcount+1 WHERE idx=?";
		try {
			psmt = con.prepareStatement(query); //쿼리문 준비
			psmt.setString(1, idx); // 인파라미터 설정. 여기까지가 쿼리문 준비
			psmt.executeQuery(); // 쿼리문 실행. 근데 왜 rs에 저장 안하는가? dto에 저장할 필요 없으니까?
			
		} catch (SQLException e) {
			System.out.println("게시물 조회 수 증가 중 예외 발생");
			e.printStackTrace();
		}
		
		
	}
}