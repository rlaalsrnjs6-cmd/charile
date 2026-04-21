<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8;"); 
%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>생산관리 목록</title>

<style>
	/* 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	a {
		text-decoration: none;
		color: inherit;
	}
		.a-title{
	text-decoration: underline;
	text-underline-offset: 5px;
	}

	/* 메인 컨테이너 (반응형: % 및 뷰포트 높이 사용) */
	.pm-all {
		width: 90%;
		max-width: 1200px;
		margin: 3rem auto;
		min-height: 77.5vh; /* 화면 높이에 비례한 최소 높이 */
	}

	/* 검색 폼 레이아웃 */
	.sch-fm {
		display: flex;
		justify-content: flex-end;
		gap: 0.5rem;
		margin-bottom: 1rem;
	}

	/* 검색 입력창 */
	.sch-in {
		width: 25%;
		min-width: 200px;
		padding: 0.5rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
	}

	/* 검색 버튼 (메인 컬러) */
	.btn-sch {
		background-color: #4B2C20;
		color: #fff;
		border: none;
		padding: 0.5rem 1.5rem;
		border-radius: 4px;
		font-size: 1rem;
		cursor: pointer;
		transition: background-color 0.3s;
	}

	/* 검색 버튼 호버 (서브 컬러 제한적 사용) */
	.btn-sch:hover {
		background-color: #5C6BC0;
	}

	/* 생산관리 테이블 */
	.pm-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		margin-bottom: 1.5rem;
	}

	.pm-tb th, .pm-tb td {
		padding: 1rem 0; /* rem 단위 사용 */
		border-bottom: 1px solid #ddd;
		font-weight: 700;
	}

	/* 테이블 헤더 (메인 컬러) */
	.pm-tb th {
		background-color: #4B2C20;
		color: #fff;
		font-size: 1.1rem;
	}

	/* 행 오버 효과 (MES 직관적 UX) */
	.pm-tb tr:hover {
		background-color: #f9f9f9;
	}

	/* 제목 링크 호버 (서브 컬러) */
	.pm-tb td a:hover {
		color: #5C6BC0;
	}

	/* 작성 버튼 */
	.btn-wr {
		display: inline-block;
		float: right;
		background-color: #4B2C20;
		color: #fff;
		padding: 0.5rem 1.5rem;
		border-radius: 4px;
		transition: background-color 0.3s;
	}

	.btn-wr:hover {
		background-color: #5C6BC0;
	}

	/* 페이징 컨테이너 */
	.pg-box {
		clear: both;
		display: flex;
		justify-content: center;
		gap: 0.5rem;
		padding-top: 1.5rem;
		padding-bottom: 3rem;
	}

	.pg-box a {
		display: inline-block;
		padding: 0.5rem 1rem;
		border: 1px solid #ddd;
		background-color: #fff;
		border-radius: 4px;
		transition: background-color 0.3s;
	}

	.pg-box a:hover {
		background-color: #f1f1f1;
	}

	/* 현재 페이지 활성화 (메인 컬러 및 굵기 적용) */
	.pg-box a.active {
		background-color: #4B2C20;
		color: #fff;
		font-weight: bold;
		border-color: #4B2C20;
	}

	/* 모바일/태블릿 반응형 처리 */
	@media (max-width: 768px) {
		.sch-fm {
			justify-content: center;
		}
		.sch-in {
			width: 60%;
		}
		.pm-tb th, .pm-tb td {
			padding: 0.8rem 0;
			font-size: 0.9rem;
		}
		.btn-wr {
			width: 100%;
			text-align: center;
			margin-top: 1rem;
		}
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="pm-all">
<h1 style="margin-bottom:2vh; color:#4B2C20;">생산관리</h1>
	<form class="sch-fm" action="${pageContext.request.contextPath}/production/management" method="get">
	    <input class="sch-in" type="text" name="selectTitle" placeholder="검색하실 제목을 입력하세요" value="${param.selectTitle}">
	    <button class="btn-sch" type="submit">검색</button>
	</form>

<%-- <form class="sch-fm" action="${pageContext.request.contextPath}/production/management" method="get"> --%>
<!--     <select class="sch-in" name="mdmNum"> -->
<!--         <option value="0">== 제품을 선택하세요 ==</option> -->
        
<%--         <c:forEach var="item" items="${productList}"> --%>
<%--             <option value="${item.mdmNum}" ${param.mdmNum == item.mdmNum ? 'selected' : ''}> --%>
<%--                 ${item.mdmName} --%>
<!--             </option> -->
<%--         </c:forEach> --%>
<!--     </select> -->
    
   
</form>
    

	<table class="pm-tb">
		<tr>
			<th>상태</th>
			<th>제목</th>
			<th>진행 현황</th>
		</tr>

		<c:forEach var="i" items="${map.List1}">
		<tr>
			<td>
				<c:if test="${i.target_quantity - i.currentCount == 0 }">완료</c:if>
				<c:if test="${i.target_quantity - i.currentCount != 0 }">진행중</c:if>
			</td>
			<td>
				<a href="${pageContext.request.contextPath}/PMDetailServlet?prod_num=${i.prod_num}" class="a-title">${i.title }</a>
			</td>
			<td>
				목표: ${i.target_quantity}<br>
				실적: ${i.currentCount } / 달성률: 
				<fmt:formatNumber value='${(i.currentCount * 100.0) / i.target_quantity}' pattern='0.0' />%
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<% 		 
		 if(level != null && level < 3){
	%>
	<a href="/charlie/ProductionManagementInsert.jsp" class="btn-wr">작성하기</a>
	<%} %>

	<div class="pg-box">
		<c:if test="${map.startPage > 1}">
			<a href="./management?page=${map.startPage - 1}">[이전]</a>
		</c:if>

		<c:forEach var="p" begin="${map.startPage}" end="${map.endPage}">
			<a href="./management?page=${p}" class="${p == map.currentPage ? 'active' : ''}">${p}</a>
		</c:forEach>

		<c:if test="${map.endPage < map.totalPage}">
			<a href="./management?page=${map.endPage + 1}">[다음]</a>
		</c:if>
	</div>

</div>

<%@ include file="footer.jsp" %>
</body>
</html>