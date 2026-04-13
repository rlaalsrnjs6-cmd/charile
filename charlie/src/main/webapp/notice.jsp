<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8;"); %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>
<h1>공지사항 페이지</h1>

<div class="notice-all" >
<table border="1">
	<tr>
		<th>NO</th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>작성날짜</th>
	</tr>

	<c:forEach var="i" items="${map.list }">
	<tr>
		<td>${i.post_num }</td>
		
		<td>
		<a href="${pageContext.request.contextPath}/NoticedetailServlet?post_num=${i.post_num }">${i.title }</a>
		</td>
		
		<td>관리자</td>
		<td>${i.write_time }</td>
	</tr>
	</c:forEach>
</table>
<a href="/charlie/noticeInsert.jsp">작성하기</a>
</div>

	<div>
	<c:if test="${map.startPage > 1}">
		<a href="./controller?page=${map.startPage - 1}">[이전]</a>
	</c:if>

	<c:forEach var="p" begin="${map.startPage}" end="${map.endPage}">
		<a href="./controller?page=${p}" class="${p == map.currentPage ? 'active' : ''}">[${p}]</a>
	</c:forEach>

	<c:if test="${map.endPage < map.totalPage}">
		<a href="./controller?page=${map.endPage + 1}">[다음]</a>
	</c:if>
</div>
</body>
</html>