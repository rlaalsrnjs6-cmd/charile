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
			<th>qc부착날짜</th>
			<th>사원번호</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="qc_num" value="${qc[0].qc_num}">
			<input type="hidden" name="lot_num" value="${qc[0].lot_num}">
			<td>${qc[0].qc_num}</td>
			<td>${qc[0].lot_num}</td>
			<td><input type="text" name="qc_date" value="${qc[0].qc_date}"></td>
			<td><input type="text" name="empno" value="${qc[0].empno}"></td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
	<script>
			
	</script>
</body>
</html>