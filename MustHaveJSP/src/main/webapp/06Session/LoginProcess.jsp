<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//로그인 폼으로부터 받은 아이디와 패스워드
String userId = request.getParameter("user_id");
String userPwd = request.getParameter("user_pw");

// web.xml에서 가져온 데이터베이스 연결 정보
String MySQLDriver = application.getInitParameter("MySQLDriver");
String MySQLURL = application.getInitParameter("MySQLUrl");
String MySQLId = application.getInitParameter("MySQLId");
String MySQLPwd = application.getInitParameter("MySQLPwd");

// 회원 테이블 DAO를 통해 회원 정보 DTO 획득
MemberDAO dao = new MemberDAO(MySQLDriver, MySQLURL, MySQLId, MySQLPwd); //db 정보를 dao 생성자에 전달하여 db에 연결함.
MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd); //메소드호출하여 loginForm에서 받은 정보 전달
dao.close();

//로그인 성공 여부에 따른 처리
if (memberDTO.getId() != null) {
	//로그인 성공
	session.setAttribute("UserId", memberDTO.getId());
	session.setAttribute("UserName", memberDTO.getName());
	response.sendRedirect("LoginForm.jsp");
}
else{
	//로그인 실패
 	request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
 	request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
}

//로그인 성공 여부에 따른 처리 - 테스트용
// if(userId.equals("musthave") && userPwd.equals("1234")) { //스트링 타입 비교하므로 equals 사용
// 	//로그인 성공
// 	session.setAttribute("UserId", userId);
// 	session.setAttribute("UserName", "홍길동");
// 	response.sendRedirect("LoginForm.jsp");
// }
// else {
// 	//로그인 실패
// 	request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
// 	request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
// }

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>