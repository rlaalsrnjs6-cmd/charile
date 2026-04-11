<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1>
		<tr>
			<th>qc번호</th>
			<th>로트번호</th>
			<th>qc부착날짜</th>
			<th>사원번호</th>
		</tr>
		<c:forEach var="q" items="${qc}">
			<tr>
				<td>${dto.ph_num}</td>
				<td><a href="http://localhost:8080/charlie/hygiene?ph_num=${dto.ph_num}&up=1">${dto.empno}</a></td>
				<td>${dto.body_temper}</td>
				<td>${dto.regist_time}</td>
				<td>${dto.washed}</td>
				<td>${dto.supervisor_chk}</td>
				<td>${dto.memo}</td>
			</tr>
		</c:forEach>
	</table>
	<form method="post" action="hygiene">
		<input type="hidden" name="mod" value="delete">
		<input type="submit" value="삭제">
	</form>
</body>
</html>