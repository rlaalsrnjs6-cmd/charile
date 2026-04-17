<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Warehouse.WarehouseDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail page</title>
</head>
<body>

	<h1>Warehouse detail</h1>
	<hr>
	<a href="warehouse?cmd=list">뒤로</a> <br>
	<hr>
	
		warehouse_num : ${ warehouseDTO.warehouse_num } <br>
		wh_section : ${ warehouseDTO.wh_section } <br>
		floor_level : ${ warehouseDTO.floor_level } <br>
		temperature : ${ warehouseDTO.temperature } <br>
		humidity : ${ warehouseDTO.humidity } <br>
		wh_status_chk : ${ warehouseDTO.wh_status_chk } <br>
		wh_chk_date : ${ warehouseDTO.wh_chk_date } <br>
		
	
	<hr>
<%-- 	<a href="warehouse?cmd=modify&warehouse_num=${ warehouseDTO.warehouse_num }">수정</a> --%>
	<a href="warehouse?cmd=delete&warehouse_num=${ warehouseDTO.warehouse_num }">삭제</a>
	
</body>
</html>