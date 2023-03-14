<%@page import="model1.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model1.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//board 데이터 출력 미션 나중에 다시 
	
	//dao 객체 생성. db에 연결. db에 있는 데이터를 출력할 예정.
	BoardDAO dao = new BoardDAO(application);

	List<BoardDTO> aList = dao.selectList(null);

	request.setAttribute("board", aList);
%>
<!DOCTYPE html>
<html>
<head> <!-- Board 게시판의 모든 데이터 출력하는 미션  -->
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1>
	<% for (int idx = 0; idx <aList.size(); idx++) {
		pageContext.setAttribute("idx", idx);
	%>	
		<tr>	
			<td>${ board[idx].num }</td>
			<td>${ board[idx].title }</td>
			<td>${ board[idx].content }</td>
			<td>${ board[idx].postdate }</td>
			<td>${ board[idx].id }</td>
			<td>${ board[idx].visitcount }</td>			
		</tr>
	<%
	}
	%>
</table>
</body>
</html>