<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
사원번호 : ${map.list[0].empno}<br>
사원이름 : ${map.list[0].ename}<br>
사원등급 : ${map.list[0].emp_level}<br>
전화번호 : ${map.list[0].tel}<br>
급여 : ${map.list[0].sal}<br>
주소 : ${map.list[0].addr}<br>
생년월일 : ${map.list[0].birthday}<br>
이메일 : ${map.list[0].email}<br>
상태 : ${map.list[0].status}<br>
<a href="http://localhost:8080/charlie/emp?empno=${map.list[0].empno}&mod=up">수정</a>
</body>
</html>