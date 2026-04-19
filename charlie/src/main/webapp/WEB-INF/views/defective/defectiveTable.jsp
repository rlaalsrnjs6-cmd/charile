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
<c:forEach var="d" items="${map.list}">
			<tr>
				<td>${d.defective_num}</td>
				<td><a href="http://localhost:8080/charlie/defective?defective_num=${d.defective_num}&mod=detail">${d.category}</td>
				<td>${d.count}</td>
				<td>${d.action}</td>
				<td>${d.lot_num}</td>
			</tr>
		</c:forEach>
</body>
</html>