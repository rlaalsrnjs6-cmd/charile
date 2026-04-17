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
<title>Insert title here</title>
</head>
<body>
	<%-- 	<%@ include file="header.jsp"%> --%>

	<!-- 	전체 -->
	<div id="main-all">
		<!-- 	대시보드 -->
		<div class="dashboard">
			<div class="notice">
				<span>공정라인 정기점검 예정일</span><br>
				<c:forEach var="i" items="${map.dn}">
					<c:choose>
						<c:when test="${i.dsStatus == 1 }">
							<span>${i.dsLine }: ${i.dsDate }일</span>
							<span>점검</span>
							<br>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
			<div class="machine">
				<span>장비 제어 및 상태</span>
				<form method="post" action="">
					<label class="swith"> <span>${map.ls[0].lineName }</span>
					<div class="line-status1">
					<c:if test="${map.ls[0].lineStatus == 0}">
						<span>정지</span>
					</c:if>
					<c:if test="${map.ls[0].lineStatus == 1}">
						<span>가동 중</span>
					</c:if>
					
					</div>
					 
					<input type="checkbox" name="lb1" class="line-btn1">
					</label><br>
					
					 <label class="swith"> <span>${map.ls[1].lineName }</span>
					 <div class="line-status2">
					 <c:if test="${map.ls[1].lineStatus == 0}">
						<span>정지</span>
					</c:if>
					<c:if test="${map.ls[1].lineStatus == 1}">
						<span>가동 중</span>
					</c:if>
					</div>
					 <input	type="checkbox" name="lb2" class="line-btn2">
					</label><br>
					
					 <label class="swith"> <span>${map.ls[2].lineName }</span>
					<div class="line-status3">
					<c:if test="${map.ls[2].lineStatus == 0}">
						<span>정지</span>
					</c:if>
					<c:if test="${map.ls[2].lineStatus == 1}">
						<span>가동 중</span>
					</c:if>
					</div>
					 <input type="checkbox" name="lb3" class="line-btn3">
					</label><br>
				</form>	
			</div>
			
			<div class="personal-hygiene"></div>
			
			<div class="warehouse"></div>
		</div>


		<!-- 		차트 -->
		<div class="chart">
			<div class="sales"></div>
			<div class="production-management"></div>
		</div>


	</div>

	<%@ include file="footer.jsp"%>
</body>
</html>