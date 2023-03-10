<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
</head>
<body>
	<jsp:include page="../Common/Link.jsp"/>
	<h2>로그인 페이지</h2>
	<span style="color: red; font-size: 1.2em;">
		<%= request.getAttribute("LoginErrMsg") == null? //리퀘스트 영역에 요청
				"" : request.getAttribute("LoginErrMsg")%>
	</span>
	<%
	if (session.getAttribute("UserId") == null) { //로그인 상태 확인
		//로그아웃 상태. 서블릿에서 html파일 만들어주는데, 로그아웃 상태면 아래 if 문 안에 코드 실행
	%>
	<script>
	function validateFome(form) {
		if (!form.user_id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (form.user_pw.value == "") {
			alert("패스워드를 입력하세요.");
			return false;
		}
	}
	</script>
	<form action="LoginProcess.jsp" method="post" name="loginFrm"
		onsubmit="return validateForm(this);">
		아이디 : <input type="text" name="user_id" /> <br />
		패스워드 : <input type="password" name="user_pw" /> <br />
		<input type="submit" value="로그인하기" /> <!-- submit 하면 loginProcess 페이지로 이동 -->
	</form>
	<%
	} else {
	%>
		<%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다. <br />
		<a href="Logout.jsp">[로그아웃]</a>
	<%
	}
	%>
</body>
</html>