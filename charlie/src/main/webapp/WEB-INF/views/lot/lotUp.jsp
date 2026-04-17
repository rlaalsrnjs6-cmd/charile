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
			<th>lot당제품개수</th>
			<th>품질체크전후번호</th>
			<th>자재관리번호</th>
			<th>작업지시번호</th>
		</tr>
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="lot_num" value="${lot[0].lot_num}">
			<input type="hidden" name="order_num" value="${lot[0].order_num}">
			<input type="hidden" name="material_num" value="${lot[0].material_num}">
			<td><input type="text" name="lot_count" value="${lot[0].lot_count}"></td>
			<td>
    			<select name="qc_chk">
            			<option value="Y">
							Y
             			</option>
            			<option value="N">
							N
             			</option>
    			</select>
    		</td>
			<td>${lot[0].material_num}</td>
			<td>${lot[0].order_num}</td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>