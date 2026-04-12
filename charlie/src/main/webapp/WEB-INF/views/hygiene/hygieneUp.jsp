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
업데이트완 
	<table border=1>
		<tr>
			<th>개인위생넘버</th>
			<th>사원번호</th>
			<th>체온</th>
			<th>등록시간</th>
			<th>위생체크</th>
			<th>책임자체크</th>
			<th>메모</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="ph_num" value="${hygiene[0].ph_num}">
			<input type="hidden" name="empno" value="${hygiene[0].empno}">
			<td>${hygiene[0].ph_num}</td>
			<td>${hygiene[0].empno}</td>
			<td><input type="text" name="body_temper" value="${hygiene[0].body_temper}"></td>
			<td><input type="text" name="regist_time" value="${hygiene[0].regist_time}"></td>
			<td><input type="text" name="washed" value="${hygiene[0].washed}"></td>
			<td><input type="text" name="supervisor_chk" value="${hygiene[0].supervisor_chk}"></td>
			<td><input type="text" name="memo" value="${hygiene[0].memo}"></td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>