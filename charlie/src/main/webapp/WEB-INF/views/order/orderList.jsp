<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
    <%@ page import="java.util.*"%>
<%@ page import="fileLibrary.CommonDTO"%>
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
	<%@ include file="/header.jsp" %>
	<SELECT>
		<option name="total" value="전체">전체</option>
		<option name="am" value="오전">오전</option>
		<option name="pm" value="오후">오후</option>
	</SELECT>
	
	<input type="text" name="search" placeholder="제목/날짜/작성자 검색">
	
	<table border=1>
		<tr>
			<th>No.</th>
			<th>제목</th>
			<th>날짜</th>
			<th>작성자 사원번호</th>
			<th>상태</th>
		</tr>
		<c:forEach var="o" items="${map.list}">
			<tr>
				<td>${o.order_num}</td>
				<td>${o.prod_num}</td>
				<td><a href="http://localhost:8080/charlie/order?order_num=${o.order_num}&mod=detail">${o.title}</td>
				<td>${o.work_start}</td>
				<td>${o.work_end}</td>
				<td>${o.content}</td>
				<td>${o.daily_target}</td>
				<td>${o.empno}</td>
				<td>${o.mdm_num}</td>
				<td>${o.ename}</td>
				<td>${o.status}</td>
			</tr>
		</c:forEach>
	</table>
	<a href = "http://localhost:8080/charlie/order?mod=add">작성</a>
	
	<jsp:include page="/WEB-INF/views/paging.jsp" />
	<%@ include file="/footer.jsp" %>
</body>
</html>