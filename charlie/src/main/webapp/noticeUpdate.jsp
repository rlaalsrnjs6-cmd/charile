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
	<h1>공지사항 수정 페이지</h1>
	list  == ${list }
	list[0] == ${list[0] }
	<form method="post" action="/charlie/NoticeUpdateController">
		<input type="hidden" name="post_num" value="${param.post_num }">
		<span>제목</span> <input name="title" value="${param.title }">
		<span>내용</span> <input name="content" value="${param.content}">
		<button type="submit">수정하기</button>
	</form>
	<button type="button" onclick="history.back();">취소</button>
</body>
</html>