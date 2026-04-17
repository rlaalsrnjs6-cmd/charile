<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8;"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 게시판</title>

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

	/* 메인 컨테이너 (반응형 퍼센트 및 뷰포트 높이 사용) */
	.rp-all {
		width: 90%;
		max-width: 1200px;
		margin: 3rem auto;
		min-height: 70vh; /* 화면 높이에 비례한 최소 높이 */
	}

	/* 검색 폼 레이아웃 */
	.sch-fm {
		display: flex;
		justify-content: flex-end;
		gap: 0.5rem;
		margin-bottom: 1rem;
	}

	/* 검색 입력창 (퍼센트와 rem을 적절히 혼용) */
	.sch-in {
		width: 25%;
		min-width: 200px;
		padding: 0.5rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
	}

	/* 검색 버튼 (메인 컬러 적용) */
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

	/* 검색 버튼 호버 (서브 컬러 제한적 적용) */
	.btn-sch:hover {
		background-color: #5C6BC0;
	}

	/* 리포트 테이블 */
	.rp-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		margin-bottom: 1.5rem;
		font-weight: 700;
	}

	.rp-tb th, .rp-tb td {
		padding: 1rem 0; /* rem 단위 사용 */
		border-bottom: 1px solid #ddd;
	}

	/* 테이블 헤더 (메인 컬러) */
	.rp-tb th {
		background-color: #4B2C20;
		color: #fff;
		font-size: 1.1rem;
		font-weight: 700;
	}

	/* 행 오버 효과 (MES 환경에 맞는 직관적인 UX) */
	.rp-tb tr:hover {
		background-color: #f9f9f9;
	}

	/* 제목 링크 호버 (서브 컬러 적용) */
	.rp-tb td a:hover {
		color: #5C6BC0;
		font-weight: bold;
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
		.rp-tb th, .rp-tb td {
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

<div class="rp-all">
	
	<form class="sch-fm" action="${pageContext.request.contextPath}/select.report" method="get">
	    <input class="sch-in" type="text" name="selectTitle" placeholder="검색하실 제목을 입력하세요" value="${param.selectTitle}">
	    <button class="btn-sch" type="submit">검색</button>
	</form>

	<table class="rp-tb">
		<tr>
			<th>NO</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>작성날짜</th>
		</tr>

		<c:forEach var="i" items="${map.list }">
		<tr>
			<td>${i.post_num }</td>
			
			<td>
			<a href="${pageContext.request.contextPath}/detail.report?post_num=${i.post_num}">${i.title }</a>
			</td>
			
			<td>${i.ename }</td>
			<td>${i.write_time }</td>
		</tr>
		</c:forEach>
	</table>
	
	<% 

		if(level != null && level == 2){
	%>
	<a class="btn-wr" href="${pageContext.request.contextPath}/reportInsert.jsp">작성하기</a>
	<%} %>
</div>

<div class="pg-box">
	<c:if test="${map.startPage > 1}">
		<a href="${pageContext.request.contextPath}/select.report?page=${map.startPage - 1}">[이전]</a>
	</c:if>

	<c:forEach var="p" begin="${map.startPage}" end="${map.endPage}">
		<a href="${pageContext.request.contextPath}/select.report?page=${p}" class="${p == map.currentPage ? 'active' : ''}">${p}</a>
	</c:forEach>

	<c:if test="${map.endPage < map.totalPage}">
		<a href="${pageContext.request.contextPath}/select.report?page=${map.endPage + 1}">[다음]</a>
	</c:if>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>