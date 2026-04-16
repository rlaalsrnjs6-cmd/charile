<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<style>
	/* 1. 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	/* 2. 메인 래퍼 (반응형 대응) */
	.mp-wrap {
		width: 90%;
		max-width: 900px;
		margin: 4rem auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20;
		border-radius: 8px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.08);
		padding: 3rem 2rem;
	}

	.mp-tit {
		font-size: 1.5rem;
		font-weight: 700;
		color: #333;
		margin-bottom: 2rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 3. 조회 모드 (Grid) */
	.info-grid {
		display: grid;
		grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
		gap: 1.5rem;
		margin-bottom: 2.5rem;
	}

	.it-box {
		background-color: #fcfcfc;
		border: 1px solid #eee;
		border-radius: 6px;
		padding: 1.2rem;
	}

	.it-lb {
		font-size: 0.95rem;
		font-weight: 700;
		color: #4B2C20;
		margin-bottom: 0.5rem;
	}

	.it-val {
		font-size: 1.05rem;
		color: #333;
		word-break: break-all;
		font-weight: 700;
	}

	/* 4. 폼 입력 모드 */
	.fm-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.5rem;
	}

	.fm-row-fix {
		display: flex;
		align-items: center;
		margin-bottom: 1.5rem;
		border-bottom: 1px dashed #eee;
		padding-bottom: 0.8rem;
	}
	
	.fm-row-fix .it-lb {
		min-width: 120px;
		margin-bottom: 0;
	}

	.in-box {
		width: 100%;
		padding: 0.8rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		transition: border-color 0.3s;
	}

	.in-box:focus {
		outline: none;
		border-color: #5C6BC0;
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1);
	}

	.err-msg {
		color: #d32f2f;
		font-size: 0.85rem;
		margin-top: 0.5rem;
		display: none;
	}

	.em-grp {
		display: flex;
		align-items: center;
		gap: 0.5rem;
	}

	.em-grp .in-box {
		flex: 1;
	}

	/* 5. 버튼 */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		gap: 1rem;
		margin-top: 2rem;
		border-top: 1px solid #eee;
		padding-top: 2rem;
	}

	.btn {
		padding: 0.8rem 2rem;
		border-radius: 4px;
		font-size: 1rem;
		font-weight: 600;
		cursor: pointer;
		border: none;
		transition: all 0.2s;
	}

	.btn-m {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-m:hover {
		background-color: #5C6BC0;
	}

	.btn-s {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
	}

	.btn-s:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f9fafe;
	}

	.d-none {
		display: none;
	}

	/* 반응형 */
	@media (max-width: 600px) {
		.mp-wrap {
			padding: 2rem 1rem;
		}
		.btn-grp {
			flex-direction: column-reverse;
		}
		.btn {
			width: 100%;
		}
		.em-grp {
			flex-direction: column;
		}
		.em-grp span {
			display: none;
		}
	}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	
	<div class="mp-wrap">
		<h2 class="mp-tit">마이페이지</h2>

		<div id="view-mode">
			<div class="info-grid">
				<div class="it-box">
					<div class="it-lb">사원번호</div>
					<div class="it-val">${list[0].empno}</div>
				</div>
				<div class="it-box">
					<div class="it-lb">이름</div>
					<div class="it-val">${list[0].ename}</div>
				</div>
				<div class="it-box">
					<div class="it-lb">아이디</div>
					<div class="it-val">${list[0].id}</div>
				</div>
				<div class="it-box">
					<div class="it-lb">소속 부서</div>
					<div class="it-val">${list[0].dept}</div>
				</div>
				<div class="it-box">
					<div class="it-lb">입사일</div>
					<div class="it-val">${list[0].hireDate}</div>
				</div>
				<div class="it-box">
					<div class="it-lb">전화번호</div>
					<div class="it-val">${list[0].tel}</div>
				</div>
				<div class="it-box">
					<div class="it-lb">이메일</div>
					<div class="it-val">${list[0].email}</div>
				</div>
				<div class="it-box" style="grid-column: 1 / -1;">
					<div class="it-lb">주소</div>
					<div class="it-val">${list[0].addr}</div>
				</div>
			</div>
			
			<div class="btn-grp">
				<button type="button" class="btn btn-m" id="btn-edit">회원정보 변경</button>
			</div>
		</div>	
		
		<div id="edit-mode" class="d-none">
			<form method="post" action="${pageContext.request.contextPath}/mypageuc" id="mp-form">
				
				<div class="fm-row-fix">
					<span class="it-lb">사원번호</span>
					<span class="it-val">${list[0].empno}</span>
					<input type="hidden" name="empno" value="${list[0].empno}">
				</div>
				<div class="fm-row-fix">
					<span class="it-lb">이름</span>
					<span class="it-val">${list[0].ename}</span>
				</div>
				<div class="fm-row-fix">
					<span class="it-lb">아이디</span>
					<span class="it-val">${list[0].id}</span>
				</div>

				<div class="fm-row">
					<label class="it-lb">비밀번호</label>
					<input type="password" class="in-box pw1" placeholder="비밀번호 입력 (변경하지 않을 시 비워두세요)">
					<span class="err-msg msg-pw1">대문자 소문자 숫자 특수기호를 포함한 8글자 이상 20글자 이하로 작성해주세요</span>
				</div>
				<div class="fm-row">
					<label class="it-lb">비밀번호 재확인</label>
					<input name="pw" type="password" class="in-box pw2" placeholder="비밀번호 재확인">
					<span class="err-msg msg-pw2">입력하신 비밀번호가 다릅니다 확인해주세요</span>
				</div>

				<div class="fm-row">
					<label class="it-lb">전화번호</label>
					<input name="tel" type="text" class="in-box tel" value="${list[0].tel}" maxlength="11" placeholder="- 없이 숫자 11자리 입력">
				</div>
				<div class="fm-row">
					<label class="it-lb">주소</label>
					<input type="text" name="addr" class="in-box addr" value="${list[0].addr}">
				</div>
				
				<c:set var="emailPart" value="${fn:split(list[0].email, '@')}" ></c:set>
				<div class="fm-row">
					<label class="it-lb">이메일</label>
					<div class="em-grp">
						<input type="text" name="email1" class="in-box email1" value="${emailPart[0]}">
						<span>@</span>
						<input type="text" name="email2" class="in-box email2" value="${emailPart[1]}">
					</div>
				</div>

				<div class="btn-grp">
					<button type="button" class="btn btn-s" id="btn-cancel">변경취소</button>
					<button type="submit" class="btn btn-m" id="btn-submit">변경하기</button>
				</div>
			</form>
		</div>
	</div>
	
	<%@ include file="footer.jsp"%>

<script>
	// DOM 선택
	const viewMode = document.getElementById('view-mode');
	const editMode = document.getElementById('edit-mode');
	const btnEdit = document.getElementById('btn-edit');
	const btnCancel = document.getElementById('btn-cancel');
	
	const form = document.getElementById('mp-form');
	const pw1 = document.querySelector('.pw1');
	const pw2 = document.querySelector('.pw2');
	const msgPw1 = document.querySelector('.msg-pw1');
	const msgPw2 = document.querySelector('.msg-pw2');

	const telInput = document.querySelector('.tel');
	const addrInput = document.querySelector('.addr');
	const email1Input = document.querySelector('.email1');
	const email2Input = document.querySelector('.email2');

	// 정규식
	const pwRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{8,20}$/;
	const telRegex = /^[0-9]{11}$/; // 숫자만 11자리
	const domainRegex = /^[a-zA-Z0-9-]+\.[a-zA-Z]{2,}$/; // 도메인 형식 (예: naver.com)

	// 모드 전환
	btnEdit.addEventListener('click', () => {
		viewMode.classList.add('d-none');
		editMode.classList.remove('d-none');
	});

	btnCancel.addEventListener('click', (e) => {
		e.preventDefault();
		if(confirm("수정을 취소하시겠습니까?")) {
			editMode.classList.add('d-none');
			viewMode.classList.remove('d-none');
			form.reset(); 
			msgPw1.style.display = 'none';
			msgPw2.style.display = 'none';
			pw1.style.borderColor = '#ddd';
			pw2.style.borderColor = '#ddd';
		}
	});

	// 실시간 비밀번호 검사
	pw1.addEventListener('input', () => {
		const val = pw1.value;
		if(val === '') {
			msgPw1.style.display = 'none';
			pw1.style.borderColor = '#ddd';
			return;
		}
		
		if(!pwRegex.test(val)) {
			msgPw1.style.display = 'block';
			pw1.style.borderColor = '#d32f2f';
		} else {
			msgPw1.style.display = 'none';
			pw1.style.borderColor = '#5C6BC0';
		}
		checkPasswordMatch();
	});

	pw2.addEventListener('input', checkPasswordMatch);

	function checkPasswordMatch() {
		if(pw2.value === '') {
			msgPw2.style.display = 'none';
			pw2.style.borderColor = '#ddd';
			return;
		}
		if(pw1.value !== pw2.value) {
			msgPw2.style.display = 'block';
			pw2.style.borderColor = '#d32f2f';
		} else {
			msgPw2.style.display = 'none';
			pw2.style.borderColor = '#5C6BC0';
		}
	}

	// 폼 제출 시 방어로직 (유효성 검사)
	form.addEventListener('submit', (e) => {
		// 1. 비밀번호 규정 체크
		if(pw1.value !== '' && !pwRegex.test(pw1.value)) {
			alert("비밀번호 규정에 맞게 작성해주세요.");
			pw1.focus();
			e.preventDefault();
			return;
		}
		
		// 2. 비밀번호 일치 체크
		if(pw1.value !== pw2.value) {
			alert("비밀번호가 일치하지 않습니다.");
			pw2.focus();
			e.preventDefault();
			return;
		}

		// 3. 전화번호 체크 (숫자만 11자리)
		const telVal = telInput.value.trim();
		if(!telRegex.test(telVal)) {
			alert("- 기호 없이 숫자 11자리만 입력해주세요.");
			telInput.focus();
			e.preventDefault();
			return;
		}

		// 4. 주소 빈칸 체크
		const addrVal = addrInput.value.trim();
		if(!addrVal) {
			alert("주소를 입력해주세요.");
			addrInput.focus();
			e.preventDefault();
			return;
		}

		// 5. 이메일 아이디 빈칸 체크
		const em1Val = email1Input.value.trim();
		if(!em1Val) {
			alert("이메일 아이디를 입력해주세요.");
			email1Input.focus();
			e.preventDefault();
			return;
		}

		// 6. 이메일 도메인 체크 (예: naver.com 등 . 포함 필수)
		const em2Val = email2Input.value.trim();
		if(!domainRegex.test(em2Val)) {
			alert("유효한 이메일 도메인을 입력해주세요. (예: naver.com)");
			email2Input.focus();
			e.preventDefault();
			return;
		}

		alert("회원정보 변경이 요청되었습니다.");
	});
</script>
</body>
</html>