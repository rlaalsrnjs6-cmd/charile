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

	name: <input type="text" name="name" value="카카오빈 (250g)"> <br>
	
	type: 
	<select name="type">
		<option value="assemble">반제품</option>
		<option value="product">제품</option>
		<option value="material">재료</option>
		<option value="equip">장비</option>
	</select> <br>
	
	code: <input type="text" name="code" value="AAABBC001"> <br>
	
	quantity: <input type="text" name="quantity" value="1"> <br>
	
	unit:
	<select name="unit">
		<option value="g">g</option>
		<option value="kg">kg</option>
		<option value="L">L</option>
		<option value="ea">ea</option>
	</select> <br>
	
	price: <input type="number" name="price" value="2000"> 원<br>
	사용기한 : <input id="exp_date" type="date" name="exp_date"> <br>
	
	can_use: <br>
	가능<input type="radio" name="can_use" value="Y" checked> <br>
	불가능<input type="radio" name="can_use" value="N"> <br>
	<hr>

	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>
	<a href="mdm?cmd=list">리스트페이지</a>
</body>
<script>
	window.onload = function() {
		
    // 현재 날짜
    let now = new Date();
    
    // 한 달 뒤
    now.setMonth(now.getMonth() + 1);
    
    // YYYY-MM-DD 형식으로 변환
    let year = now.getFullYear();
    let month = ("0" + (now.getMonth() + 1)).slice(-2);
    let day = ("0" + now.getDate()).slice(-2);
    
    let oneMonthLater = `${year}-${month}-${day}`;
    
    // input.value
    document.getElementById('exp_date').value = oneMonthLater;
	}
</script>
</html>