<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Warehouse.WarehouseDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
</head>
<body>
<h1>warehouse insert</h1>
<hr>

<form method="post" action="warehouse">

	SECTION: 
 	<select name="wh_section">
            <option value="RM-A01">RM-A01</option>
            <option value="RM-A02">RM-A02</option>
            <option value="RM-A03">RM-A03</option>
            <option value="FG-B01">FG-B01</option>
            <option value="FG-B02">FG-B02</option>
            <option value="FG-B03">FG-B03</option>
            <option value="FG-B04">FG-B04</option>
            <option value="CS-C01">CS-C01</option>
            <option value="CS-C02">CS-C02</option>
    </select> <br>
    
	FLOOR: 
	<select name="floor_level">
            <option value="B1">B1</option>
            <option value="B2">B2</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="F">F</option>
    </select> <br>
    
	온도 : <input type="number" step="0.1" name="temperature" value="12.5"> <br>
	습도 : <input type="number" step="0.1" name="humidity" value="32.1"> <br>
	
	
	정상: <input type="radio" name="wh_status_chk" value="Y" checked> 
    이상: <input type="radio" name="wh_status_chk" value="N"> <br>
	
	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>
	<a href="warehouse?cmd=list">리스트페이지</a>
</body>
</html>