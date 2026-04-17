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
<style>
/* [전역 고정 레이아웃] 철칙 2, 4 준수 */
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
}

/* 1. 헤더 고정 (상단 박제) */
#hd-wrap {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	z-index: 5000; /* 최상단 유지 */
	background-color: #fff;
}

/* 2. 유틸리티 메뉴 (#4B2C20 메인컬러) */
.ut-menu {
	width: 100%;
	background-color: #4B2C20;
}

.ut-list {
	display: flex;
	justify-content: flex-end;
	list-style: none;
	padding: 0.5rem 5%;
	gap: 1.5rem;
	margin: 0% !important;
}

.ut-list a {
	color: #fff;
	font-size: 0.85rem;
	transition: color 0.2s;
	margin: 0%;
}
/* 서브컬러 #5C6BC0 제한적 사용 */
.ut-list a:hover {
	color: #5C6BC0;
}

/* 3. 메인 네비게이션 바 */
.hd-bar {
	width: 100%;
	border-bottom: 0.15rem solid #4B2C20;
	display: flex;
	align-items: center;
	justify-content: space-between;
	/* 		padding: 0% 1% ; */
	background-color: #fff;
}

.logo img {
	height: 3rem;
	width: auto;
	display: block;
}

.hd-nav {
	display: flex;
	gap: 2.5rem;
	list-style: none;
	font-size: 1.1rem;
	font-weight: 700;
	padding-right: 4%;
}

.hd-nav a {
	color: #333;
	transition: color 0.2s;
}

.hd-nav a:hover {
	color: #5C6BC0;
}

/* 4. 본문 영역 자동 여백 처리 (핵심)
	   헤더가 fixed이므로 본문이 가려지지 않도록 상단 여백을 미리 확보함.
	   다른 팀원들이 사용할 공통 클래스임.
	*/
.mes-cont {
	padding-top: 4.2rem;
	min-height: 100vh;
}

/* 반응형 (철칙 4 준수) */
@media ( max-width : 768px) {
	.hd-bar {
		flex-direction: column;
		gap: 0.8rem;
	}
	.hd-nav {
		gap: 1rem;
		font-size: 0.9rem;
	}
	.mes-cont {
		padding-top: 7rem;
	}
}
</style>
</head>
<body>
	<%
	// 세션 및 권한 체크 (기존 로직 보존)
	Boolean isLogin = (Boolean) session.getAttribute("login");
	Integer level = (Integer) session.getAttribute("level");
	if (level == null)
		level = 9; // 비로그인 기본값
	%>

	<header id="hd-wrap">
		<div class="ut-menu">
			<ul class="ut-list">
				<c:choose>
					<c:when test="${sessionScope.login == true }">
						<c:if test="${sessionScope.level == 1}">
							<li><a href="${pageContext.request.contextPath}/emp">사원목록</a></li>
						</c:if>
						<li><a href="${pageContext.request.contextPath}/mypage">마이페이지</a></li>
						<li><a
							href="${pageContext.request.contextPath}/charlie?mod=logout">로그아웃</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="">로그인</a></li>
						<li><a href="">회원가입</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>

		<div class="hd-bar">
			<h1 class="logo">
				<a href="${pageContext.request.contextPath}/main"> <img
					src="${pageContext.request.contextPath}/assets/img/logo-no-bg.png"
					alt="Charlie MES">
				</a>
			</h1>
			<ul class="hd-nav">

				<li><a href="${pageContext.request.contextPath}/log">입출고이력</a></li>
				<li><a href="${pageContext.request.contextPath}/defective">불량관리</a></li>
				<li><a href="${pageContext.request.contextPath}/lot">로트관리</a></li>
				<li><a href="${pageContext.request.contextPath}/mdm">기준관리</a></li>
				<li><a href="${pageContext.request.contextPath}/material">자재관리</a></li>
				<li><a href="${pageContext.request.contextPath}/order">작업지시서</a></li>
				<li><a
					href="${pageContext.request.contextPath}/production/management">생산관리</a></li>
				<li><a href="${pageContext.request.contextPath}/qc">품질관리</a></li>
				<li><a
					href="${pageContext.request.contextPath}/notice/controller">공지사항</a></li>
				<c:if test="${sessionScope.level < 3}">
					<li><a href="${pageContext.request.contextPath}/select.report">리포트</a></li>
				</c:if>
			</ul>
		</div>
	</header>

	<div class="mes-cont">