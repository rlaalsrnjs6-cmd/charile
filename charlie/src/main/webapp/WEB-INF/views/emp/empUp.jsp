<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="emp">
	<table border=1>
		<tr>
			<th>사원번호</th>
			<th>이름</th>
			<th>사원등급</th>
			<th>전화번호</th>
			<th>급여</th>
			<th>주소</th>
			<th>생년월일</th>
			<th>이메일</th>
			<th>상태</th>
		</tr>
		
		<tr>
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="empno" value="${emp[0].empno}">
			<input type="hidden" name="tel" value="${emp[0].tel}">
			<input type="hidden" name="email" value="${emp[0].email}">
			<input type="hidden" name="addr" value="${emp[0].addr}">
			<input type="hidden" name="birthday" value="${emp[0].birthday}">
			<td>${emp[0].empno}</td>
			<td><input type="text" name="ename" value="${emp[0].ename}"></td>
			<td><input type="text" name="emp_level" value="${emp[0].emp_level}"></td>
			<td>${emp[0].tel}</td>
			<td><input type="text" name="sal" value="${emp[0].sal}"></td>
			<td>${emp[0].addr}</td>
			<td>${emp[0].birthday}</td>
			<td>${emp[0].email}</td>
			<td><input type="text" name="status" value="${emp[0].status}"></td>
		</tr>
		<input type="submit" value="수정">
	</table>
	</form>
</body>
</html>