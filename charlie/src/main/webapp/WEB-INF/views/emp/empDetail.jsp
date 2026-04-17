<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
사원번호 : ${emp[0].empno}<br>
사원이름 : ${emp[0].ename}<br>
사원등급 : ${emp[0].emp_level}<br>
전화번호 : ${emp[0].tel}<br>
급여 : ${emp[0].sal}<br>
주소 : ${emp[0].addr}<br>
생년월일 : ${emp[0].birthday}<br>
이메일 : ${emp[0].email}<br>
상태 : ${emp[0].status}<br>
<a href="http://localhost:8080/charlie/emp?empno=${emp[0].empno}&mod=up">수정</a>
</body>
</html>