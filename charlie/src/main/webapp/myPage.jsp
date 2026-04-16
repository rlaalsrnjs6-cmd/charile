<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8;");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Charlie 마이페이지</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	
<div id="default">
	<table>
		<tr>
			<th>사원번호</th>
			<th>이름</th>
			<th>아이디</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>이메일</th>
			<th>소속 부서</th>
			<th>입사일</th>
		</tr>

		<tr>
			<td>${list[0].empno }</td>
			<td>${list[0].ename }</td>
			<td>${list[0].id }</td>
			<td>${list[0].tel }</td>
			<td>${list[0].addr }</td>
			<td>${list[0].email }</td>
			<td>${list[0].dept }</td>
			<td>${list[0].hireDate }</td>
		</tr>
	</table>
	<button type="button" class="default-btn">회원정보 수정</button>
</div>	
	
	<div id="update">
	<form method="post" action="${pageContext.request.contextPath}/mypageuc">
	<span>사원번호</span><span>${list[0].empno }</span><br>
	<span>이름</span><span>${list[0].ename}</span><br>
	<span>아이디</span><span>${list[0].id }</span><br>
	<span>비밀번호</span><input type="password"><br>
	<span>비밀번호 재확인</span><input type="password"><br>
	<span>전화번호</span><input type="text" value="${list[0].tel }"><br>
	<span>주소</span><input type="text" value="${list[0].addr }"><br>
	<span>이메일</span><input type="text" value="${list[0].email }"><br>
	</form>
	</div>
	
	
	<%@ include file="footer.jsp"%>
</body>
</html>