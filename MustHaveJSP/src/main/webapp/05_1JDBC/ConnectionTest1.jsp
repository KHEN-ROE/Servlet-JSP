<%@ page import="common.JDBConnect1" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	
	<%
	JDBConnect1 jdbc5 = new JDBConnect1();
	jdbc5.close();
	%>
</body>
</html>