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
<table border=1>
		<tr>
			<th>개인위생넘버</th>
			<th>사원번호</th>
			<th>체온</th>
			<th>등록시간</th>
			<th>위생체크</th>
			<th>책임자체크</th>
			<th>메모</th>
		</tr>
		<c:forEach var="dto" items="${hygiene}">
			<tr>
				<td>${dto.ph_num}</td>
				<td><a href="http://localhost:8080/charlie/hygiene?ph_num=${dto.ph_num}&mod=detail">${dto.empno}</a></td>
				<td>${dto.body_temper}</td>
				<td>${dto.regist_time}</td>
				<td>${dto.washed}</td>
				<td>${dto.supervisor_chk}</td>
				<td>${dto.memo}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="http://localhost:8080/charlie/hygiene?mod=add">작성</a>
<!-- 	<form method="post" action="hygiene"> -->
<!-- 		<input type="hidden" name="mod" value="detele.jsp"> -->
<!-- 		<input type="submit" value="삭제"> -->
<!-- 	</form> -->
</body>
</html>