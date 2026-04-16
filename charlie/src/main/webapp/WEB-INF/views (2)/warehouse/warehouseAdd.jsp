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
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="warehouse_num"></td>
			<td><input type="text" name="section"></td>
			<td><input type="text" name="floor_level"></td>
			<td><input type="text" name="temperature"></td>
			<td><input type="text" name="humidity"></td>
			<td><input type="text" name="wh_status_chk"></td>
		</tr>
		<input type="submit" value="작성">
	</table>
</form>
</body>
</html>