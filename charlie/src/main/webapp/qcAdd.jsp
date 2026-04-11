<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="qc">
	<table border=1>
		<tr>
			<th>qc번호</th>
			<th>로트번호</th>
			<th>사원번호</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="qc_num"></td>
			<td><input type="text" name="lot_num"></td>
			<td><input type="text" name="empno"></td>
		</tr>
		<input type="submit" value="작성">
	</table>
	</form>
</body>
</html>