<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8;");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>notice insert 페이지</h1>
	
	<form method="post" action="/charlie/NoticeInsertController">
		<span>제목</span> <input type="text" name="title"><br>
		<span>내용</span> <input type="text" name="content"><br>
		<a href="notice/controller">작성 취소</a><br>
		<button>등록하기</button><br>
	</form>
	
</body>
</html>