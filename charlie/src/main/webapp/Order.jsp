<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
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
	
	<SELECT>
		<option name="total" value="전체">전체</option>
		<option name="am" value="오전">오전</option>
		<option name="pm" value="오후">오후</option>
	</SELECT>
	
	<input type="text" name="search" placeholder="제목/날짜/작성자 검색">
	
	<table border=1>
		<tr>
			<th>no</th>
			<th>제목</th>
			<th>날짜</th>
			<th>작성자</th>
			<th>상태</th>
		</tr>
		<c:forEach var="order" items="${order}">
			<tr>
				<td>${order.order_num}</td>
				<td>${order.work_order_title}</td>
				<td>${order.work_date}</td>
				<td>${order.ename}</td>
				<td>${order.status}</td>
			</tr>
		</c:forEach>
	</table>
	<form method="post" action="order">
		<input type="hidden" name="mod" value="write">
		<input type="submit" value="작성">
	</form>

</body>
</html>