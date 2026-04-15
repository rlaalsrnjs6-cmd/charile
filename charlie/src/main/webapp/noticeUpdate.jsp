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
<%@ include file="header.jsp" %>

	<form method="post" action="/charlie/NoticeUpdateController">
		<input type="hidden" name="post_num" value="${param.post_num }"><br>
		<span>제목</span> <input name="title" value="${param.title }"><br>
		<span>내용</span> <input name="content" value="${param.content}"><br>
		<button type="submit">수정하기</button>
	</form>
	<button type="button" onclick="history.back();">취소</button>
<%@ include file="footer.jsp" %>
</body>
</html>