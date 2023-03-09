<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan main</title>
</head>
<body>
<%
	String sel = request.getParameter("gugudan");
	out.print("sel :" + sel);	//sel값에 type1, type2 넘어오는거 확인
	
	String col = request.getParameter("input");
	out.print("col :" + col);	//text에서 입력한 값이 넘어오는거 확인
	
	//request 내장 객체는 클라이언트의 요청 정보를 저장하는 반면, response객체는 요청에 대한 응답을 웹브라우저로 보내주는 역할 한다.
	if (sel.equals("type1")) response.sendRedirect("Gugudan1.jsp?dan="+col);
	else response.sendRedirect("Gugudan2.jsp?col="+col);

%>
</body>
</html>