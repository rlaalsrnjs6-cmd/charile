<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="hygiene">
	<table border=1>
		<tr>
			<th>사원번호</th>
			<th>체온</th>
			<th>위생체크</th>
			<th>책임자체크</th>
			<th>메모</th>
		</tr>
		<%session.getAttribute("empno"); %>
		<%session.getAttribute("name"); %>
		<tr>
			<input type="hidden" name="mod" value="add">
			<input type="hidden" name="empno" value="${empno }">
			<input type="hidden" name="ename" value="${name }">
			<td>${name}(${empno})</td>
			<td><input type="text" name="body_temper"></td>
			<td>
				<select name="washed">
            			<option value="F">
               	 			F
             			</option>
            			<option value="T" selected>
               	 			T
             			</option>
    			</select>
    		</td>
			<td>
				<select name="supervisor_chk">
            			<option value="Y" selected>
               	 			Y
             			</option>
            			<option value="N">
               	 			N
             			</option>
    			</select>
    		</td>
			<td><input type="text" name="memo"></td>
		</tr>
	</table>
		<input type="submit" value="작성">
	</form>
</body>
</html>