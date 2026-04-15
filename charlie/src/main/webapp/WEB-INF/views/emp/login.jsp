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
	<form method="post" action="check">
		<input type="hidden" name="mod" value="login">
		<input type="text" name="id" placeholder="id를 입력하세요" value="wqer1234"><br>
		<input type="password" name="pw" placeholder="pw를 입력하세요" value="Qwer1234@"><br>
		<input type="submit" value="로그인">		
	</form>
	<form method="get" action="charlie">
		<input type="hidden" name="mod" value="signin">
		<input type="submit" value="회원가입">	
	</form>
</body>
</html>