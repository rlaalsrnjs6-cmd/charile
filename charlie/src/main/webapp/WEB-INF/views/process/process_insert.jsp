<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert page</title>
</head>
<body>
<h1>process insert</h1>
<hr>
img url 등록 시 보이게 끔

<form method="post" action="process">

	process_content: <br> 
	<textarea name="process_content">들어갈 공정 내용</textarea>  <br>
	flow: <input type="number" name="flow" value="1">  <br>
	img_url: <input type="text" name="img_url" 
		value="https://cdn.discordapp.com/attachments/1486907574929330306/1492024217951277128/-removebg-preview.png?ex=69d9d356&is=69d881d6&hm=2c9ba62f625cab2e2f5b0bfb3a8f1c968fece496ad67ce4ff2624dddacc4c21f&
		"> <br>
		mdm_num:
    <select name="mdm_num">
        <c:forEach var="item" items="${ list }">
            <option value="${ item.mdm_num }">${ item.name } : ${ item.code }</option>
        </c:forEach>
    </select> <br>
		
	<input type="hidden" name="cmd" value="insert">
	<input type="submit" value="등록">
	
</form>
	<a href="process?cmd=list">리스트페이지</a>
</body>
</html>