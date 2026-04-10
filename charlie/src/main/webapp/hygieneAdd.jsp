<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="hygiene">
	<table border=1>
		<tr>
			<th>개인위생넘버</th>
			<th>사원번호</th>
			<th>체온</th>
			<th>위생체크</th>
			<th>책임자체크</th>
			<th>메모</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="ph_num"></td>
			<td><input type="text" name="empno"></td>
			<td><input type="text" name="body_temper"></td>
			<td><input type="text" name="washed"></td>
			<td><input type="text" name="supervisor_chk"></td>
			<td><input type="text" name="memo"></td>
		</tr>
		<input type="submit" value="작성">
		
	</table>
	</form>
</body>
</html>