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
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="order_num" value="${map.list[0].order_num}">
			<input type="hidden" name="prod_num" value="${map.list[0].prod_num}">
			<input type="hidden" name="mdm_num" value="${map.list[0].mdm_num}">
			<input type="hidden" name="empno" value="${map.list[0].empno}">
			<td>${map.list[0].order_num}</td>
			<td><input type="text" name="title" value="${map.list[0].title}"></td>
			<td>${map.list[0].prod_num}</td>
			<td><input type="text" name="target_quantity" value="${map.list[0].target_quantity}"></td>
			<td>${map.list[0].empno}</td>
			<td>${map.list[0].mdm_num}</td>
			<td><input type="text" name="status" value="${map.list[0].status}"></td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>