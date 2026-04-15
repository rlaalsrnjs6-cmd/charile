<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MES 로그인</title>
<style>
	/* 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	/* 로그인 전용 단독 배경 (화면 전체 100vh 활용) */
	body {
		background-color: #f4f6fa;
		display: flex;
		justify-content: center;
		align-items: center;
		min-height: 100vh; 
		font-family: inherit;
	}

	/* 로그인 폼 컨테이너 */
	.log-wrap {
		width: 90%;
		max-width: 450px;
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 철칙 5: 메인 컬러 포인트 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.1); 
		padding: 3rem 2.5rem;
	}

	/* 로고 이미지 영역 (틀 깨짐 방지 완벽 적용) */
	.log-logo {
		text-align: center;
		margin-bottom: 2.5rem;
	}

	.log-logo img {
		max-width: 180px; /* 로고가 너무 커지는 것 방지 */
		width: 100%;      /* 부모 요소를 넘어가지 않도록 반응형 처리 */
		height: auto;     /* 비율 유지 */
		display: inline-block;
	}

	/* 입력 행 */
	.log-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.5rem;
	}

	/* 라벨 (메인 컬러) */
	.log-lb {
		font-weight: 600;
		color: #4B2C20;
		margin-bottom: 0.5rem;
		font-size: 1rem;
	}

	/* 인풋 공통 스타일 */
	.log-in {
		width: 100%;
		padding: 0.8rem 1rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	/* 포커스 시 서브 컬러 반응 (철칙 6 제한적 사용) */
	.log-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15); 
	}

	/* 버튼 공통 스타일 */
	.btn-log, .btn-sign {
		width: 100%;
		padding: 1rem;
		border-radius: 4px;
		font-size: 1.05rem;
		font-weight: bold;
		text-align: center;
		cursor: pointer;
		border: none;
		transition: all 0.3s;
	}

	/* 로그인 버튼 (메인 동작) */
	.btn-log {
		background-color: #4B2C20;
		color: #fff;
		margin-top: 1rem;
		margin-bottom: 1rem;
	}

	.btn-log:hover {
		background-color: #5C6BC0;
	}

	/* 회원가입 버튼 (서브 동작) */
	.btn-sign {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
	}

	.btn-sign:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f8f9ff;
	}

	/* 모바일 반응형 (여백 축소) */
	@media (max-width: 480px) {
		.log-wrap {
			padding: 2rem 1.5rem;
			border-top-width: 2rem;
		}
	}
</style>
</head>
<body>

	<div class="log-wrap">
		<div class="log-logo">
			<img src="${pageContext.request.contextPath}/assets/img/logo-no-bg.png" alt="MES 로고">
		</div>

		<form method="post" action="check" id="loginForm">
			<input type="hidden" name="mod" value="login">
			
			<div class="log-row">
				<span class="log-lb">아이디</span>
				<input type="text" name="id" class="log-in" placeholder="id를 입력하세요" value="admin123">
			</div>
			
			<div class="log-row">
				<span class="log-lb">비밀번호</span>
				<input type="password" name="pw" class="log-in" placeholder="pw를 입력하세요" value="Admin123@">
			</div>
			
			<button type="submit" class="btn-log">로그인</button>
		</form>

		<form method="get" action="charlie">
			<input type="hidden" name="mod" value="signin">
			<button type="submit" class="btn-sign">회원가입</button>	
		</form>
	</div>

<script>
	// 로그인 폼 유효성 검사 (빈칸 방지)
	const loginForm = document.getElementById("loginForm");
	const id_el = document.querySelector("input[name='id']");
	const pw_el = document.querySelector("input[name='pw']");

	loginForm.addEventListener('submit', (e) => {
		const id_val = id_el.value.trim();
		const pw_val = pw_el.value.trim();

		// 보더 색상 초기화
		id_el.style.borderColor = "";
		pw_el.style.borderColor = "";

		// 아이디 검사
		if (id_val === "") {
			alert("아이디를 입력해주세요.");
			e.preventDefault();
			id_el.focus();
			id_el.style.borderColor = "#4B2C20";
			return;
		}

		// 비밀번호 검사
		if (pw_val === "") {
			alert("비밀번호를 입력해주세요.");
			e.preventDefault();
			pw_el.focus();
			pw_el.style.borderColor = "#4B2C20";
			return;
		}
	});
</script>
</body>
</html>