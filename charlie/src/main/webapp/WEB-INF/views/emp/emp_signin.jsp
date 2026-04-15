<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MES 회원가입</title>
<style>
	/* 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	/* 회원가입 전용 단독 배경 */
	body {
		background-color: #f4f6fa;
		display: flex;
		justify-content: center;
		align-items: center;
		min-height: 100vh; 
		font-family: inherit;
		padding: 2rem 0;
	}

	/* 회원가입 폼 컨테이너 */
	.sg-wrap {
		width: 90%;
		max-width: 550px;
		background-color: #fff;
		border-top: 3rem solid #4B2C20;
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.1); 
		padding: 3rem 2.5rem;
	}

	.log-logo {
		text-align: center;
		margin-bottom: 2.5rem;
	}

	.log-logo img {
		max-width: 180px;
		width: 100%;      
		height: auto;     
		display: inline-block;
	}

	.sg-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.2rem;
	}

	.sg-lb {
		font-weight: 600;
		color: #4B2C20;
		margin-bottom: 0.5rem;
		font-size: 1rem;
	}

	.sg-in {
		width: 100%;
		padding: 0.8rem 1rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		transition: border-color 0.3s, box-shadow 0.3s;
		font-family: inherit;
	}

	.sg-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15); 
	}

	.sg-err {
		color: #e53935;
		font-size: 0.85rem;
		margin-top: 0.4rem;
		min-height: 1rem;
	}

	.email-grp {
		display: flex;
		align-items: center;
		gap: 0.5rem;
	}

	@media (max-width: 480px) {
		.email-grp {
			flex-direction: column;
			align-items: flex-start;
		}
		.email-grp span {
			display: none;
		}
	}

	.btn-main, .btn-sub {
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

	.btn-main {
		background-color: #4B2C20;
		color: #fff;
		margin-top: 1.5rem;
		margin-bottom: 1rem;
	}

	.btn-main:hover {
		background-color: #5C6BC0;
	}

	.btn-sub {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
	}

	.btn-sub:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f8f9ff;
	}
</style>
</head>
<body>

	<div class="sg-wrap">
		<div class="log-logo">
			<img src="${pageContext.request.contextPath}/assets/img/logo-no-bg.png" alt="MES 로고">
		</div>

		<form method="post" action="emp" id="signForm" onsubmit="return validateForm()">
			<input type="hidden" name="mod" value="add">
			
			
			<div class="sg-row">
				<span class="sg-lb">아이디</span>
				<input type="text" id="id" name="id" class="sg-in" onblur="check_id()" placeholder="id" value="qwer23">
				<span id="sid" class="sg-err"></span>
			</div>
			
			<div class="sg-row">
				<span class="sg-lb">비밀번호</span>
				<input type="password" id="pw" name="pw" class="sg-in" onblur="check_pw()" placeholder="pw" value="Qwer237@">
				<span id="spw" class="sg-err"></span>
			</div>
			
			<div class="sg-row">
				<span class="sg-lb">비밀번호 재확인</span>
				<input type="password" id="rpw" name="rpw" class="sg-in" onblur="check_rpw()" placeholder="pw재확인" value="Qwer237@">
				<span id="srpw" class="sg-err"></span>
			</div>
			
			<div class="sg-row">
				<span class="sg-lb">이름</span>
				<input type="text" id="ename" name="ename" class="sg-in" placeholder="이름" value="서영">
			</div>
			
			<div class="sg-row">
				<span class="sg-lb">전화번호</span>
				<input type="text" id="tel" name="tel" class="sg-in" placeholder="번호" value="01078945612">
			</div>
			
			<div class="sg-row">
				<span class="sg-lb">주소</span>
				<input type="text" id="addr" name="addr" class="sg-in" placeholder="주소" value="경남 부산">
			</div>
			
			<div class="sg-row">
				<span class="sg-lb">이메일</span>
				<div class="email-grp">
					<input type="text" id="email" name="email" class="sg-in" onblur="check_email()" placeholder="이메일" value="qwehkj2">
					<input type="hidden" name="email2" value="@"> <span>@</span>
					<input type="text" id="domain" name="email3" class="sg-in">
					<select name="domain" id="domain_list" class="sg-in" onchange="setDomain()">
						<option name="gg" id="gg" value="">직접입력</option>
						<option name="naver" value="naver.com" checked>naver.com</option>
						<option name="google" value="google.com">google.com</option>
					</select>
				</div>
				<span id="semail" class="sg-err"></span>
			</div>
			
			<div class="sg-row">
				<span class="sg-lb">생년월일</span>
				<input type="date" name="birthday" class="sg-in">
			</div>

			<button type="submit" class="btn-main">회원가입</button>
			<button type="button" class="btn-sub" onclick="location.href='${pageContext.request.contextPath}/charlie'">로그인 페이지로 돌아가기</button>
		</form>
	</div>
	
	<script>
	function check_id() {
		let sid = document.querySelector('#sid');
		let check = /^[a-z][a-z0-9]{4,14}$/;
		let id = document.querySelector('#id').value.trim();
		if (!id || !check.test(id)) {
			sid.innerText = "첫글자는 소문자, 영소문자 숫자를 사용한 4글자이상 14이하의 id를 작성해주세요";
			return false;
		} else {
			sid.innerText = "";
			return true;
		}
	}

	function check_pw() {
		let spw = document.querySelector('#spw');
		let check = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,20}$/;
		let pw = document.querySelector('#pw').value;
		if (!pw || !check.test(pw)) {
			spw.innerText = "대문자 소문자 숫자 특수기호를 포함한 8글자 이상 20글자 이하로 작성해주세요";
			return false;
		} else {
			spw.innerText = "";
			return true;
		}
	}

	function check_rpw() {
		let srpw = document.querySelector('#srpw');
		let rpw = document.querySelector('#rpw').value;
		let pw = document.querySelector('#pw').value;
		if (pw !== rpw || rpw === "") {
			srpw.innerText = "입력하신 비밀번호와 다릅니다 확인해주세요";
			return false;
		} else {
			srpw.innerText = "";
			return true;
		}
	}

	function check_email() {
		let semail = document.querySelector('#semail');
		let email_val = document.querySelector('#email').value.trim();
		let domain_val = document.querySelector('#domain').value.trim();
		let full_email = email_val + "@" + domain_val;
		let check = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
		
		if (!email_val || !domain_val || !check.test(full_email)) {
			semail.innerText = "이메일 형식에 맞춰 작성하세요";
			return false;
		} else {
			semail.innerText = "";
			return true;
		}
	}

	function setDomain() {
		let domain_list = document.querySelector('#domain_list');
		let option = domain_list.options[domain_list.selectedIndex].value;
		let domain = document.querySelector('#domain');
		domain.value = option;
		if (option === '') {
			domain.readOnly = false;
			domain.value = '';
			domain.focus();
		} else {
			domain.readOnly = true; 
			domain.value = option;
		}
		check_email();
	}

	window.onload = function() {
		setDomain();
	};

	function validateForm() {
		// 사원번호 숫자 검사 및 빈칸 검사
		let empno = document.querySelector('#empno');
		if (empno.value.trim() === "") {
			alert("사원번호를 입력해주세요.");
			empno.focus();
			return false;
		}

		let isIdValid = check_id();
		let isPwValid = check_pw();
		let isRpwValid = check_rpw();
		let isEmailValid = check_email();
		
		if (!isIdValid) { alert("아이디 형식을 확인해주세요."); document.querySelector('#id').focus(); return false; }
		if (!isPwValid) { alert("비밀번호 형식을 확인해주세요."); document.querySelector('#pw').focus(); return false; }
		if (!isRpwValid) { alert("비밀번호 재확인이 일치하지 않습니다."); document.querySelector('#rpw').focus(); return false; }
		
		// 이메일 형식 미달 시 알림창 및 포커스 (요청사항 반영)
		if (!isEmailValid) { 
			alert("이메일 형식이 올바르지 않습니다."); 
			document.querySelector('#email').focus(); 
			return false; 
		}
		
		let birthday = document.querySelector('input[name="birthday"]');
		if (birthday.value === "") {
			alert("생년월일을 선택해주세요.");
			birthday.focus();
			return false;
		}
		
		return true;
	}
	</script>
</body>
</html>