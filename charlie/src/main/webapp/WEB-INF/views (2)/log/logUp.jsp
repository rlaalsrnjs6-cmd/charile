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
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="log_num" value="${log[0].log_num}">
			<input type="hidden" name="lot_num" value="${log[0].lot_num}">
			<input type="hidden" name="mdm_num" value="${log[0].mdm_num}">
			<td>${log[0].log_num}</td>
			<td><input type="text" name="io_type" value="${log[0].io_type}"></td>
			<td>${log[0].lot_num}</td>
			<td>${log[0].mdm_num}</td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>