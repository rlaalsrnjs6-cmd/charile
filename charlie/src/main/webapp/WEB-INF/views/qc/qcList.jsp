<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	<table border=1>
		<tr>
			<th>qc번호</th>
			<th>로트번호</th>
			<th>qc부착날짜</th>
			<th>사원번호</th>
			<th>사원이름</th>
			<th>로트당제품개수</th>
			<th>qc체크</th>
		</tr>
		<c:forEach var="q" items="${qc}">
			<tr>
				<td>${q.qc_num}</td>
				<td><a href="http://localhost:8080/charlie/qc?qc_num=${q.qc_num}&mod=detail">${q.lot_num}</td>
				<td>${q.qc_date}</td>
				<td>${q.empno}</td>
				<td>${q.ename}</td>
				<td>${q.lot_count}</td>
				<td>${q.qc_chk}</td>
			</tr>
		</c:forEach>
	</table>
	<a href = "http://localhost:8080/charlie/qc?mod=add">작성</a>
	<%@ include file="/footer.jsp" %>
	
</body>
</html>