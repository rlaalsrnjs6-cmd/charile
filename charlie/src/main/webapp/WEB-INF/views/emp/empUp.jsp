<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원정보 수정</title>
<style>
	/* 기본 초기화 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	/* 사원 수정 페이지 전용 스코프 */
	#emp-mod-scope {
		width: 100%;
		padding: 5vh 0;
	}

	/* 수정 폼 메인 컨테이너 */
	#emp-mod-scope .mod-wrap {
		width: 90vw;
		max-width: 800px;
		margin: 0 auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
		padding: 3rem 5%;
	}

	/* 페이지 타이틀 */
	#emp-mod-scope .mod-tit {
		font-size: 1.5rem;
		font-weight: 700;
		color: #333;
		margin-bottom: 2rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 입력 항목 행 */
	#emp-mod-scope .mod-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.5rem;
	}

	/* 항목 라벨 */
	#emp-mod-scope .mod-lb {
		font-weight: 600;
		color: #4B2C20;
		margin-bottom: 0.6rem;
		font-size: 1.05rem;
	}

	/* 읽기 전용 텍스트 */
	#emp-mod-scope .mod-txt {
		font-size: 1rem;
		color: #555;
		padding: 0.8rem 1rem;
		background-color: #f9f9f9;
		border-radius: 4px;
		border: 1px solid #eee;
	}

	/* 입력 인풋 스타일 */
	#emp-mod-scope .mod-in {
		width: 100%;
		padding: 0.8rem 1rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit;
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	#emp-mod-scope .mod-in:focus {
		outline: none;
		border-color: #5C6BC0;
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
	}

	/* 라디오 그룹 레이아웃 (UX 개선) */
	#emp-mod-scope .mod-rd-grp {
		display: flex;
		gap: 2rem;
		padding: 0.5rem 0;
	}

	#emp-mod-scope .mod-rd-item {
		display: flex;
		align-items: center;
		gap: 0.5rem;
		cursor: pointer;
		font-size: 1rem;
		color: #333;
	}

	/* 버튼 그룹 */
	#emp-mod-scope .mod-btn-grp {
		display: flex;
		justify-content: flex-end;
		gap: 1rem;
		margin-top: 3rem;
		border-top: 1px solid #eee;
		padding-top: 2rem;
	}

	/* 버튼 공통 스타일 */
	#emp-mod-scope .mod-btn {
		padding: 0.8rem 2.5rem;
		border-radius: 4px;
		font-size: 1rem;
		font-weight: 600;
		text-align: center;
		cursor: pointer;
		border: none;
		transition: all 0.2s;
	}

	/* 수정하기 버튼 (메인 컬러) */
	#emp-mod-scope .btn-sub {
		background-color: #4B2C20;
		color: #fff;
	}

	#emp-mod-scope .btn-sub:hover {
		background-color: #5C6BC0;
	}

	/* 취소 버튼 */
	#emp-mod-scope .btn-can {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
	}

	#emp-mod-scope .btn-can:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f8f9ff;
	}

	/* 모바일 반응형 대응 */
	@media (max-width: 768px) {
		#emp-mod-scope .mod-wrap {
			width: 95vw;
			padding: 2rem 1.5rem;
		}
		#emp-mod-scope .mod-btn-grp {
			flex-direction: column-reverse;
			gap: 0.8rem;
		}
		#emp-mod-scope .mod-btn {
			width: 100%;
			padding: 1rem;
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<div id="emp-mod-scope">
	<div class="mod-wrap">
		<div class="mod-tit">사원정보 수정</div>
		
		<form id="modForm" method="post" action="emp">
			<input type="hidden" name="mod" value="up">
			<input type="hidden" name="empno" value="${map.list[0].empno}">
			<input type="hidden" name="tel" value="${map.list[0].tel}">
			<input type="hidden" name="email" value="${map.list[0].email}">
			<input type="hidden" name="addr" value="${map.list[0].addr}">
			<input type="hidden" name="birthday" value="${map.list[0].birthday}">

			<div class="mod-row">
				<span class="mod-lb">사원번호</span>
				<span class="mod-txt">${map.list[0].empno}</span>
			</div>
			
			<div class="mod-row">
				<span class="mod-lb">이름</span>
				<input type="text" name="ename" value="${map.list[0].ename}" class="mod-in">
			</div>
			
			<div class="mod-row">
				<span class="mod-lb">사원등급</span>
				<input type="text" name="emp_level" value="${map.list[0].emp_level}" class="mod-in">
			</div>
			
			<div class="mod-row">
				<span class="mod-lb">전화번호</span>
				<span class="mod-txt">${map.list[0].tel}</span>
			</div>
			
			<div class="mod-row">
				<span class="mod-lb">급여</span>
				<input type="text" name="sal" value="${map.list[0].sal}" class="mod-in">
			</div>
			
			<div class="mod-row">
				<span class="mod-lb">주소</span>
				<span class="mod-txt">${map.list[0].addr}</span>
			</div>
			
			<div class="mod-row">
				<span class="mod-lb">생년월일</span>
				<span class="mod-txt">${map.list[0].birthday}</span>
			</div>
			
			<div class="mod-row">
				<span class="mod-lb">이메일</span>
				<span class="mod-txt">${map.list[0].email}</span>
			</div>
			
			<div class="mod-row">
				<span class="mod-lb">상태</span>
				<div class="mod-rd-grp">
					<label class="mod-rd-item">
						<input type="radio" name="status" value="Y" ${map.list[0].status == 'Y' ? 'checked' : ''}>
						Y
					</label>
					<label class="mod-rd-item">
						<input type="radio" name="status" value="N" ${map.list[0].status == 'N' ? 'checked' : ''}>
						N
					</label>
				</div>
			</div>

			<div class="mod-btn-grp">
				<button type="button" class="mod-btn btn-can">취소</button>
				<button type="submit" class="mod-btn btn-sub">수정하기</button>
			</div>
		</form>
	</div>
</div>

<%@ include file="/footer.jsp" %>

<script>
	const modForm = document.getElementById("modForm");
	const btn_can = document.querySelector(".btn-can");

	const ename_el = document.querySelector("input[name='ename']");
	const emp_level_el = document.querySelector("input[name='emp_level']");
	const sal_el = document.querySelector("input[name='sal']");

	modForm.addEventListener('submit', (e) => {
		const ename_val = ename_el.value.trim();
		const emp_level_val = emp_level_el.value.trim();
		const sal_val = sal_el.value.trim();
		
		// 라디오 버튼 선택 여부 확인을 위한 요소 취득
		const status_checked = document.querySelector("input[name='status']:checked");
		
		if (ename_val === "") {
			alert("이름을 입력하세요.");
			e.preventDefault();
			ename_el.focus();
			return;
		}
		if (emp_level_val === "") {
			alert("사원등급을 입력하세요.");
			e.preventDefault();
			emp_level_el.focus();
			return;
		}
		if (sal_val === "" || isNaN(sal_val)) {
			alert("급여를 올바른 숫자 형태로 입력하세요.");
			e.preventDefault();
			sal_el.focus();
			return;
		}
		// 라디오 버튼 유효성 검증
		if (!status_checked) {
			alert("상태(Y/N)를 선택하세요.");
			e.preventDefault();
			return;
		}

		if (confirm("해당 내용으로 사원정보를 수정하시겠습니까?")) {
			// 정상 제출 처리
		} else {
			e.preventDefault(); 
		}
	});

	btn_can.addEventListener('click', () => {
		if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
			history.back(); 
		}
	});
</script>
</body>
</html>