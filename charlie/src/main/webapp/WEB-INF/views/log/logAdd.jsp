<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="log">
	<table border=1>
		<tr>
			<th>log_번호</th>
			<th>구분</th>
			<th>lot번호</th>
			<th>mdm번호</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="log_num"></td>
			<td><input type="text" name="io_type"></td>
			<td><input type="text" name="lot_num"></td>
			<td><input type="text" name="mdm_num"></td>
		</tr>
		<input type="submit" value="작성">
	</table>
</form>
</body>
</html>