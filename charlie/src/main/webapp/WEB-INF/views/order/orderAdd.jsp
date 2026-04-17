<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="order">
	<table border=1>
		<tr>
			<th>작업지시번호</th>
			<th>제목</th>
			<th>생산관리번호</th>
			<th>목표수량</th>
			<th>사원번호</th>
			<th>mdm</th>
			<th>상태</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="order_num"></td>
			<td><input type="text" name="title"></td>
			<td><input type="text" name="prod_num"></td>
			<td><input type="text" name="target_quantity"></td>
			<td><input type="text" name="empno"></td>
			<td><input type="text" name="mdm_num"></td>
			<td><input type="text" name="status"></td>
		</tr>
		<input type="submit" value="작성">
	</table>
	</form>
</body>
</html>