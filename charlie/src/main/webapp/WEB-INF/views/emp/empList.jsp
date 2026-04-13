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
			<th>사원번호</th>
			<th>이름</th>
			<th>id</th>
			<th>pw</th>
			<th>사원등급</th>
			<th>전화번호</th>
			<th>급여</th>
			<th>주소</th>
			<th>상태</th>
		</tr>
		
		<c:forEach var="e" items="${emp}">
			<tr>
				<td>${e.empno}</td>
				<td><a href="http://localhost:8080/charlie/emp?empno=${e.empno}&mod=detail">${e.ename}</td>
				<td>${e.id}</td>
				<td>${e.pw}</td>
				<td>${e.emp_level}</td>
				<td>${e.tel}</td>
				<td>${e.sal}</td>
				<td>${e.addr}</td>
				<td>${e.status}</td>
			</tr>
		</c:forEach>
		
	</table>
<%@ include file="/footer.jsp" %>
</body>
</html>