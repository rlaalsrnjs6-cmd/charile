<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="check">
		<input type="hidden" name="mod" value="login">
		<input type="text" name="id" placeholder="id를 입력하세요" value="rlaalsrnjs1"><br>
		<input type="text" name="pw" placeholder="pw를 입력하세요" value="Rlaalsrnjs1@"><br>
		<input type="submit" value="로그인">		
	</form>
	<a href="emp_signin.jsp">회원가입</a>
</body>
</html>