<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset=UTF-8">
<title>지시어 - erroPage, isErrorPage 속성</title>
</head>
<body>
<%
try {
	int myAge = Integer.parseInt(request.getParameter("age")) + 10;
	out.println("10년 후 당신의 나이는 " + myAge + "입니다.");
}
catch (Exception e) {
	out.println("에외 발생 : 매개변수 age가 null입니다.");
}
%>
</body>
</html>