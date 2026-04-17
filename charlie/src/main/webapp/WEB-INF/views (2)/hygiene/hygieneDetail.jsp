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
개인위생넘버 : ${hygiene[0].ph_num}<br>
사원번호 : ${hygiene[0].empno}<br>
체온 : ${hygiene[0].body_temper}<br>
등록시간 : ${hygiene[0].regist_time}<br>
위생체크 : ${hygiene[0].washed}<br>
책임자체크 : ${hygiene[0].supervisor_chk}<br>
메모 : ${hygiene[0].memo}<br>
<a href="http://localhost:8080/charlie/hygiene?ph_num=${hygiene[0].ph_num}&mod=up">수정</a>
<a href="http://localhost:8080/charlie/hygiene?ph_num=${hygiene[0].ph_num}&mod=delete">삭제</a>

</body>
</html>