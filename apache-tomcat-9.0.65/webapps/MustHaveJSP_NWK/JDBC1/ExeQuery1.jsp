<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect1"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>world db 쿼리 실행</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
<%
	//DB에 연결
	JDBConnect1 jdbc = new JDBConnect1();
	
	//쿼리문 생성
	String sql = "select count(name) as 'English 사용국가 수' from country join countrylanguage on country.code = countrylanguage.countrycode where language='english'";
	Statement stmt = jdbc.con.createStatement();
	
	//쿼리 수행
	ResultSet rs = stmt.executeQuery(sql);
	
	//결과 확인(웹 페이지에 출력)
%>

<table class="table table-striped">
<% while(rs.next()) { %> <thead><tr><td> 영어 사용 국가수 </td></tr></thead>
	<tr><td><%= rs.getInt(1) %></td></tr>
<%
}
%>
</table>
<%
	//연결 닫기
// 	jdbc.close();
%>
</body>
</html>