<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<sapn>No</sapn> <span>${list[0].post_num }</span><br>
	<sapn>제목</sapn> <span>${list[0].title }</span><br>
	<sapn>작성날짜</sapn> <span>${list[0].write_time }</span><br>
	<sapn>작성자</sapn> <span>관리자</span><br>
	<sapn>내용</sapn> <span>${list[0].content }</span><br>
	
	<% 

	int level = (Integer)session.getAttribute("level");
	if(level == 1){
%>
	<form method="post" action="/charlie/noticeUpdate.jsp">
		<input type="hidden" name="post_num" value="${list[0].post_num }">
		<input type="hidden" name="title" value="${list[0].title }">
		<input type="hidden" name="content" value="${list[0].content }">
		<button type="submit">수정하기</button>
	</form>
	
	<form method="post" action="/charlie/NoticeDeleteController">
		<input type="hidden" name="post_num" value="${list[0].post_num }">
		<button type="submit">삭제하기</button>
	</form>
	<%} %>
	<%@ include file="footer.jsp" %>
</body>
</html>