<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="defective">
	<table border=1>
		<tr>
			<th>불량번호</th>
			<th>불량카테고리</th>
			<th>개수</th>
			<th>qc번호</th>
			<th>불량조치방법</th>
			<th>mdm번호</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="defective_num" value="${defective[0].defective_num}">
			<input type="hidden" name="qc_num" value="${defective[0].qc_num}">
			<input type="hidden" name="mdm_num" value="${defective[0].mdm_num}">
			<td>${defective[0].defective_num}</td>
			<td><input type="text" name="category" value="${defective[0].category}"></td>
			<td><input type="text" name="count" value="${defective[0].count}"></td>
			<td>${defective[0].qc_num}</td>
			<td><input type="text" name="action" value="${defective[0].action}"></td>
			<td>${defective[0].mdm_num}</td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>