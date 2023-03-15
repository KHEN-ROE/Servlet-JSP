<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String saveDirectory = application.getRealPath("/Uploads"); //저장할 디렉터리
int maxPostSize = 1024 * 1000; // 파일 최대 크기(1mb)\
String encoding = "UTF-8"; //인코딩 방식

try {
	// 1. multipartrequest 객체생성
	MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding);
	
	// 2. 새로운 파일명 생성
	
	
}
catch(Exception e){
	e.printStackTrace();
	request.setAttribute("errorMessage", "파일 업로드 오류");
	request.getRequestDispatcher("FileUploadMain.jsp").forward(request, response);
}
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