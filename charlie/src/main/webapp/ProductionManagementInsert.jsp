<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%		//요청의 한글 깨짐 방지
	request.setCharacterEncoding("utf-8");
	//응답의 한글 깨짐 방지
	response.setContentType("text/html; charset=utf-8;"); %>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>ProductionManagement inset 페이지</h1>
	
	<form method="post" action="PMinsetServlet">
		
		<div class="mdm-select">
		<label>
			<input type="radio" name="mdm_num" value="1" checked="checked">mdm code1
		</label>
		
		<label>
			<input type="radio" name="mdm_num" value="2">mdm code2
		</label>
		
		<label>
			<input type="radio" name="mdm_num" value="33">mdm code33
		</label>
		</div>
		
		<span>목표 생산량</span> <input name="taget_quantity" type="text"><br>
		<span>제목</span> <input name="title" type="text"><br>
		<span>시작일</span> <input name="workStart" type="date"><br>
		<sapn>만료일</sapn> <input name="workEnd" type="date"><br>
		<button type="submit">작성하기</button>
	</form>
</body>
</html>