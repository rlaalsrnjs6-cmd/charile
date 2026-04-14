<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Machinery.MachineryDTO"%> 
<%@ page import="fileLibrary.CommonDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
</head>
<body>
<h1>machinery insert</h1>

<hr>


<form method="post" action="machinery">

	해당 장비: 
	<select name="mdm_num">
    	<c:forEach var="item" items="${list}">
      	  <option value="${item.mdm_num}" selected>
          	  ${item.name} (${item.mdm_num})
       	  </option>
    	</c:forEach>
	</select> <br>

	장비 타입: 
	<select name="machinery_type">
		<option value="Mixer" selected>혼합기</option>
		<option value="Heater / Melter">가열기(용해기)</option>
		<option value="Cooler">냉각기</option>
		<option value="Packing Machine">포장기</option>
	</select> <br>
	
	장비 상태: <br>
		동작: <input type="radio" name="machinery_status" value="T" selected>
		정지: <input type="radio" name="machinery_status" value="F" > <br>
	
	에러 내용: 
	<select name="error_sign">
		<option value="E01 Equipment failure" selected>1. 장비 이상 발생</option>
		<option value="E02 Temp error">2. 온도 이상</option>
		<option value="E03 Pressure error">3. 압력 이상</option>
		<option value="E04 Sensor error">4. 센서 오류</option>
		<option value="E05 Power failure">5. 전원 문제</option>
	</select> <br>
	
	조치 내용: 
	<select name="m_action">
		<option value="Stop the equipment" selected>1. 장비 정지</option>
		<option value="Check the error">2. 에러 확인</option>
		<option value="Report issue">3.보고</option>
		<option value="Take action">4. 조치 수행</option>
	</select> <br>
	
	
	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>
	<a href="machinery?cmd=list">리스트페이지</a>
</body>
</html>