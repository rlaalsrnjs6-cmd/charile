<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Machinery.MachineryDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>machinery modify</title>
</head>
<body>
	<h1>machinery modify</h1>
	<hr>
	<a href="machinery?cmd=list">뒤로</a> <br>
	<hr>
	
	<form method="post" action="machinery">
	
		<input type="hidden" name="cmd" value="update">
		<input type="hidden" name="machinery_num" value="${ machineryDTO.machinery_num }">
		
		machinery_num : ${ machineryDTO.machinery_num } <br>
		
		machinery_type: 
		<select name="machinery_type">
			<c:if test="${ machineryDTO.machinery_type eq 'Mixer'}">
				<option value="Mixer" selected>혼합기</option>
			</c:if>
			<c:if test="${ machineryDTO.machinery_type ne 'Mixer'}">
				<option value="Mixer">혼합기</option>
			</c:if>
			<c:if test="${ machineryDTO.machinery_type eq 'Heater / Melter'}">
				<option value="Heater / Melter" selected>가열기(용해기)</option>
			</c:if>
			<c:if test="${ machineryDTO.machinery_type ne 'Heater / Melter'}">
				<option value="Heater / Melter">가열기(용해기)</option>
			</c:if>
			<c:if test="${ machineryDTO.machinery_type eq 'Cooler'}">
				<option value="Cooler" selected>냉각기</option>
			</c:if>
			<c:if test="${ machineryDTO.machinery_type ne 'Cooler'}">
				<option value="Cooler">냉각기</option>
			</c:if>
			<c:if test="${ machineryDTO.machinery_type eq 'Packing Machine'}">
				<option value="Packing Machine" selected>포장기</option>
			</c:if>
			<c:if test="${ machineryDTO.machinery_type ne 'Packing Machine'}">
				<option value="Packing Machine">포장기</option>
			</c:if>
		</select> <br>
		
		
		machinery_status: <br>
			동작 상태:  
			<c:if test="${ machineryDTO.machinery_status eq 'T'}">
				<input type="radio" name="machinery_status" value="T" checked>
			</c:if>
			<c:if test="${ machineryDTO.machinery_status ne 'T'}">
				<input type="radio" name="machinery_status" value="T">
			</c:if> <br>
			
			정지 상태:
			<c:if test="${ machineryDTO.machinery_status eq 'F'}">
				<input type="radio" name="machinery_status" value="F" checked>
			</c:if>
			<c:if test="${ machineryDTO.machinery_status ne 'F'}">
				<input type="radio" name="machinery_status" value="F">
			</c:if>
			<br>
	
		error_sign: 
	<select name="error_sign">
		<c:if test="${ machineryDTO.error_sign eq 'E01 Equipment failure'}">
			<option value="E01 Equipment failure" selected>1. 장비 이상 발생</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign ne 'E01 Equipment failure'}">
			<option value="E01 Equipment failure" >1. 장비 이상 발생</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign eq 'E01 Equipment failure'}">
			<option value="E02 Temp error" selected>2. 온도 이상</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign ne 'E01 Equipment failure'}">
			<option value="E02 Temp error" >2. 온도 이상</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign eq 'E03 Pressure error'}">
    		<option value="E03 Pressure error" selected>3. 압력 이상</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign ne 'E03 Pressure error'}">
    		<option value="E03 Pressure error">3. 압력 이상</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign eq 'E04 Sensor error'}">
    		<option value="E04 Sensor error" selected>4. 센서 오류</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign ne 'E04 Sensor error'}">
   			 <option value="E04 Sensor error">4. 센서 오류</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign eq 'E05 Power failure'}">
   			 <option value="E05 Power failure" selected>5. 전원 문제</option>
		</c:if>
		<c:if test="${ machineryDTO.error_sign ne 'E05 Power failure'}">
   			 <option value="E05 Power failure">5. 전원 문제</option>
		</c:if>
	</select> <br>
	
	m_action: 
	<select name="m_action">
		<c:if test="${ machineryDTO.m_action eq 'Stop the equipment'}">
			<option value="Stop the equipment" selected>1. 장비 정지</option>
		</c:if>
		<c:if test="${ machineryDTO.m_action ne 'Stop the equipment'}">
			<option value="Stop the equipment" >1. 장비 정지</option>
		</c:if>
		
		<c:if test="${ machineryDTO.m_action eq 'Check the error'}">
    		<option value="Check the error" selected>2. 에러 확인</option>
		</c:if>
		<c:if test="${ machineryDTO.m_action ne 'Check the error'}">
    		<option value="Check the error">2. 에러 확인</option>
		</c:if>

		<c:if test="${ machineryDTO.m_action eq 'Report issue'}">
    		<option value="Report issue" selected>3. 보고</option>
		</c:if>
		<c:if test="${ machineryDTO.m_action ne 'Report issue'}">
  		  <option value="Report issue">3. 보고</option>
		</c:if>

		<c:if test="${ machineryDTO.m_action eq 'Take action'}">
  		  <option value="Take action" selected>4. 조치 수행</option>
		</c:if>
		<c:if test="${ machineryDTO.m_action ne 'Take action'}">
    		<option value="Take action">4. 조치 수행</option>
		</c:if>
	</select> <br>
		
		 <input type="hidden" name="mdm_num" value="${machineryDTO.mdm_num}">
		 <input type="submit" value="수정"> <br>
		
	</form>
</body>
</html>