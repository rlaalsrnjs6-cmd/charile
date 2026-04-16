<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
</head>
<body>
<h1>mdm insert</h1>
<hr>

<form method="post" action="mdm">

	name: <input type="text" name="name" value="유기농 말차 가루"> <br>
	
	type: 
	<select name="type">
		<option value="assemble">반제품</option>
		<option value="product" selected>제품</option>
		<option value="material">재료</option>
		<option value="equip">장비</option>
	</select> <br>
	
	code: <input type="text" name="code" value="MAT-POW-01"> <br>
	
	quantity: <input type="text" name="quantity" value="50"> <br>
	
	unit:
	<select name="unit">
		<option value="g">g</option>
		<option value="kg" selected>kg</option>
		<option value="L">L</option>
		<option value="EA">EA</option>
	</select> <br>
	
	price: <input type="number" name="price" value="1200000"> 원<br>
	사용기한 : <input id="exp_date" type="date" name="exp_date" value="2027-04-15"> <br>
	
	canUse: <br>
	가능<input type="radio" name="canUse" value="Y" checked> <br>
	불가능<input type="radio" name="canUse" value="N"> <br>
	<hr>

	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>
	<a href="mdm?cmd=list">리스트페이지</a>
</body>
<script>
	
</script>
</html>