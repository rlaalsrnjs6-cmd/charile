<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="warehouse">
	<table border=1>
		<tr>
			<th>창고넘버</th>
			<th>섹션</th>
			<th>층구분</th>
			<th>온도</th>
			<th>습도</th>
			<th>정상체크</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="warehouse_num" value="${warehouse[0].warehouse_num}">
			<td>${warehouse[0].warehouse_num}</td>
			<td><input type="text" name="section" value="${warehouse[0].section}"></td>
			<td><input type="text" name="floor_level" value="${warehouse[0].floor_level}"></td>
			<td><input type="text" name="temperature" value="${warehouse[0].temperature}"></td>
			<td><input type="text" name="humidity" value="${warehouse[0].humidity}"></td>
			<td><input type="text" name="wh_status_chk" value="${warehouse[0].wh_status_chk}"></td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>