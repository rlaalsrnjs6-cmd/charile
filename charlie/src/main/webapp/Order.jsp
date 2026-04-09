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
	
	${order}
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


</body>
</html>