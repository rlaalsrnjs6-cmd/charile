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
	box-shadow: 0 0.2rem 0.5rem rgba(0,0,0,0.05); /* 헤더 입체감 */
}

/* 2. 유틸리티 메뉴 (#4B2C20 메인컬러) */
.ut-menu {
	width: 100%;
	background-color: #4B2C20;
	position: relative;
	z-index: 10;
}

.ut-list {
	display: flex;
	justify-content: flex-end;
	list-style: none;
	padding: 0.5rem 5%;
	gap: 1.5rem;
	margin: 0% !important; /* 요청하신 기존 important 유지 */
}

.ut-list a {
	color: #fff;
	font-size: 0.85rem;
	transition: color 0.2s;
	margin: 0%;
	text-decoration: none;
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
	background-color: #fff;
	position: relative;
	z-index: 10; /* 드롭다운 메뉴보다 위로 오도록 설정 */
}

.logo img {
	height: 3rem;
	width: auto;
	display: block;
	padding-left: 5%; /* 반응형 여백 */
}

.hd-nav {
	display: flex;
	align-items: center;
	gap: 2.5rem;
	list-style: none;
	font-size: 1.1rem;
	font-weight: 700;
	padding-right: 4%;
	margin: 0;
}

.hd-nav a {
	color: #333;
	transition: color 0.2s;
	text-decoration: none;
}

.hd-nav a:hover {
	color: #5C6BC0;
}

/* --- 햄버거 메뉴 및 드롭다운 UI --- */

/* 햄버거 버튼 디자인 */
.ham-btn {
	background: none;
	border: none;
	cursor: pointer;
	display: flex;
	flex-direction: column;
	gap: 0.4rem;
	padding: 0.5rem;
}

.ham-bar {
	width: 1.8rem;
	height: 0.2rem;
	background-color: #4B2C20;
	border-radius: 0.2rem;
	transition: all 0.3s ease;
}

/* 햄버거 버튼 X 애니메이션 (서브컬러 적용) */
.ham-btn.on .ham-bar:nth-child(1) {
	transform: translateY(0.6rem) rotate(45deg);
	background-color: #5C6BC0;
}
.ham-btn.on .ham-bar:nth-child(2) {
	opacity: 0;
}
.ham-btn.on .ham-bar:nth-child(3) {
	transform: translateY(-0.6rem) rotate(-45deg);
	background-color: #5C6BC0;
}

/* 스르륵 내려오는 드롭다운 (딜레이 해결 로직 적용) */
.drop-box {
	position: absolute;
	top: 100%;
	left: 0;
	width: 100%;
	background-color: #fafafa;
	/* max-height 딜레이 제거, transform과 opacity로 즉각적인 슬라이드 구현 */
	visibility: hidden;
	opacity: 0;
	transform: translateY(-1rem);
	transition: all 0.3s ease-in-out;
	z-index: 5;
	border-bottom: 0 solid #4B2C20;
	pointer-events: none; /* 숨어있을 때 클릭 방지 */
}

.drop-box.on {
	visibility: visible;
	opacity: 1;
	transform: translateY(0);
	border-bottom: 0.15rem solid #4B2C20;
	box-shadow: 0 0.5rem 1rem rgba(0,0,0,0.1);
	pointer-events: auto;
}

/* MES 환경에 맞춘 깔끔한 그리드 레이아웃 */
.drop-grid {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(12rem, 1fr));
	gap: 1.5rem;
	padding: 2rem 5%;
	list-style: none;
	margin: 0;
}

.drop-grid a {
	display: block;
	color: #444;
	font-weight: 600;
	font-size: 1.05rem;
	padding: 0.8rem 1rem;
	background: #fff;
	border: 0.1rem solid #eaeaea;
	border-radius: 0.3rem;
	transition: all 0.2s ease;
	text-decoration: none;
}

.drop-grid a:hover {
	color: #fff;
	background-color: #5C6BC0;
	border-color: #5C6BC0;
	transform: translateY(-0.1rem); /* 살짝 떠오르는 효과 */
}

/* --- 철칙 9: 페이징 스타일 --- */
.pg-wrap {
	display: flex;
	justify-content: center;
	gap: 0.5rem;
	margin-top: 2rem;
}
.pg-btn {
	padding: 0.5rem 1rem;
	color: #333;
	border: 0.1rem solid #ccc;
	text-decoration: none;
	border-radius: 0.2rem;
}
.pg-btn.on {
	font-weight: 900;
	color: #fff;
	background-color: #4B2C20;
	border-color: #4B2C20;
}

/* 4. 본문 영역 상단 여백 처리 */
.mes-cont {
	padding-top: 6.5rem; 
/* 	min-height: 100vh; */
}

/* 5. 반응형 */
@media (max-width : 768px) {
	.hd-bar {
		padding: 0.5rem 0;
	}
	.logo img {
		height: 2.2rem;
	}
	.hd-nav {
		gap: 1rem;
		font-size: 0.9rem;
	}
	.mes-cont {
		padding-top: 7.5rem;
	}
	.drop-grid {
		grid-template-columns: repeat(2, 1fr);
		padding: 1.5rem 5%;
		gap: 0.8rem;
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
						<li><a href="${pageContext.request.contextPath}/charlie?mod=logout">로그아웃</a></li>
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
				<a href="${pageContext.request.contextPath}/main"> 
					<img src="${pageContext.request.contextPath}/assets/img/logo-no-bg.png" alt="Charlie MES">
				</a>
			</h1>
			<ul class="hd-nav">
				<c:if test="${sessionScope.level < 3}">				
				</c:if>
				<li><a href="${pageContext.request.contextPath}/io">입출고이력</a></li>
				<li><a href="${pageContext.request.contextPath}/io?cmd=total">자재관리</a></li>
				<li><a href="${pageContext.request.contextPath}/order">작업지시서</a></li>
				<li><a href="${pageContext.request.contextPath}/production/management">생산관리</a></li>
				
				<li>
					<button class="ham-btn" id="hamBtn">
						<span class="ham-bar"></span>
						<span class="ham-bar"></span>
						<span class="ham-bar"></span>
					</button>
				</li>
			</ul>
		</div>
		
		<div class="drop-box" id="dropBox">
			<ul class="drop-grid">
				<c:if test="${sessionScope.level < 3}">
					<li><a href="${pageContext.request.contextPath}/select.report">보고서 게시판</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/notice/controller">공지사항</a></li>
				<li><a href="${pageContext.request.contextPath}/mdm">기준관리</a></li>
				<li><a href="${pageContext.request.contextPath}/lot">로트관리</a></li>
				<li><a href="${pageContext.request.contextPath}/defective">불량관리</a></li>
				<li><a href="${pageContext.request.contextPath}/process">공정관리</a></li>
				<li><a href="${pageContext.request.contextPath}/machinery">기계관리</a></li>
				<li><a href="${pageContext.request.contextPath}/warehouse">창고관리</a></li>
				<li><a href="${pageContext.request.contextPath}/bom">BOM</a></li>
			</ul>
		</div>
	</header>

	<div class="mes-cont">
		</div>

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const hamBtn = document.getElementById('hamBtn');
			const dropBox = document.getElementById('dropBox');

			// 햄버거 버튼 토글
			hamBtn.addEventListener('click', function(e) {
				e.stopPropagation(); 
				this.classList.toggle('on');
				dropBox.classList.toggle('on');
			});

			// 메뉴 밖 클릭 시 즉시 닫힘
			document.addEventListener('click', function(e) {
				if (!dropBox.contains(e.target) && !hamBtn.contains(e.target)) {
					hamBtn.classList.remove('on');
					dropBox.classList.remove('on');
				}
			});
		});
	</script>
</body>
</html>