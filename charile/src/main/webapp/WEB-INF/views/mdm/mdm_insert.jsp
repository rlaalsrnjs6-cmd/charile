<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
</head>
<body>
<h1>기준관리 insert</h1>
<hr>
<form method="post" action="">

	name: <input type="text" name="name" value="카카오빈 (250g)"> <br>
	
	type: 
	<select name="type">
		<option value="semi">반제품</option>
		<option value="Prod">제품</option>
		<option value="raw">재료</option>
		<option value="equip">장비</option>
	</select> <br>
	
	code: <input type="text" name="code" value="AAABBC001"> <br>
	
	unit:
	<select name="unit">
		<option value="g">g</option>
		<option value="L">L</option>
		<option value="ea">정</option>
	</select> <br>
	
	price: <input type="number" name="price" value="2000"> 원<br>
	<hr>

	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>

</body>
</html>