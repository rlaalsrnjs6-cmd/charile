<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
작업지시번호 : ${order[0].order_num}<br>
제목 : ${order[0].title}<br>
작업실시날짜 : ${order[0].work_date}<br>
생산관리번호 : ${order[0].prod_num}<br>
목표수량 : ${order[0].target_quantity}<br>
사원번호 : ${order[0].empno}<br>
mdm번호 : ${order[0].mdm_num}<br>
상태 : ${order[0].status}<br>
<a href="http://localhost:8080/charlie/order?order_num=${order[0].order_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/order?order_num=${order[0].order_num}&mod=delete">삭제</a>
</body>
</html>