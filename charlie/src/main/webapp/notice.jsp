<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
request.setCharacterEncoding("utf-8");
response.setContentType("text/html; charset=utf-8;"); %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

	/* 메인 컨테이너 (반응형 퍼센트 및 최대 너비 지정) */
	.notice-all {
		width: 90%;
		max-width: 1200px;
		margin: 3rem auto;
		min-height: calc(100vh - 249px);
	}

	/* 공지사항 테이블 */
	.nt-tb {
		width: 100%;
		border-collapse: collapse;
		text-align: center;
		margin-bottom: 1.5rem;
	}

	.nt-tb th, .nt-tb td {
		padding: 1rem 0; /* rem 단위 사용 */
		border-bottom: 1px solid #ddd;
		font-weight: 700;
	}

	/* 메인 컬러 적용 (#4B2C20) */
	.nt-tb th {
		background-color: #4B2C20;
		color: #fff;
		font-size: 1.1rem;
	}

	/* 행 오버 효과 (UX 가독성) */
	.nt-tb tr:hover {
		background-color: #f9f9f9;
	}

	/* 서브 컬러 제한적 적용 (#5C6BC0) */
	.nt-tb td a:hover {
		color: #5C6BC0;
		font-weight: bold;
	}

	/* 작성 버튼 레이아웃 */
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
	}

	.pg-box a:hover {
		background-color: #f1f1f1;
	}

	/* 현재 페이지 활성화 (9번째 철칙: 굵은 글씨 및 색상 변경) */
	.pg-box a.active {
		background-color: #4B2C20;
		color: #fff;
		font-weight: bold;
		border-color: #4B2C20;
	}

	/* 모바일/태블릿 반응형 */
	@media (max-width: 768px) {
		.nt-tb th, .nt-tb td {
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

<div class="notice-all" >
<h1 style="margin-bottom:2vh; color:#4B2C20;">공지사항</h1>
<table class="nt-tb">
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
		<a href="${pageContext.request.contextPath}/NoticedetailServlet?post_num=${i.post_num }" class="a-title">${i.title }</a>
		</td>
		
		<td>관리자</td>
		<td>${i.write_time }</td>
	</tr>
	</c:forEach>
</table>
<% 
	if(level == 1){
%>
<a href="/charlie/noticeInsert.jsp" class="btn-wr">작성하기</a>
<%} %>
</div>

	<div class="pg-box">
	<c:if test="${map.startPage > 1}">
		<a href="./controller?page=${map.startPage - 1}">[이전]</a>
	</c:if>

	<c:forEach var="p" begin="${map.startPage}" end="${map.endPage}">
		<a href="./controller?page=${p}" class="${p == map.currentPage ? 'active' : ''}">${p}</a>
	</c:forEach>

	<c:if test="${map.endPage < map.totalPage}">
		<a href="./controller?page=${map.endPage + 1}">[다음]</a>
	</c:if>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>