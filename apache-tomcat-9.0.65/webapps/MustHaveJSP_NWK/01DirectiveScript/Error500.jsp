<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset=UTF-8">
<title>지시어 - erroPage, isErrorPage 속성</title>
</head>
<body>
<%
int myAge = Integer.parseInt(request.getParameter("age")) + 10;
out.println("10년 후 당신의 나이는 " + myAge + "입니다.");

String name = request.getParameter("name");
out.println("당신의 이름은 " + name + "입니다.");
%>
</body>
</html>