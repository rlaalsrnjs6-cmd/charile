<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="lot">
	<table border=1>
		<tr>
			<th>lot번호</th>
			<th>lot당제품개수</th>
			<th>작업지시번호</th>
			<th>품질체크전후번호</th>
			<th>자재관리번호</th>
			<th>mdm번호</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" name="lot_num"></td>
			<td><input type="text" name="lot_count"></td>
			<td><input type="text" name="order_num"></td>
			<td><input type="text" name="qc_chk"></td>
			<td><input type="text" name="material_num"></td>
			<td><input type="text" name="mdm_num"></td>
		</tr>
		<input type="submit" value="작성">
	</table>
</form>
</body>
</html>