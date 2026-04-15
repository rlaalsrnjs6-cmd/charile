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
<title>생산관리 상세</title>
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

	/* 상세 컨테이너 (반응형: % 및 최대 너비 사용) */
	.dt-wrap {
		width: 90%;
		max-width: 900px;
		margin: 3rem auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
		border-radius: 4px;
		box-shadow: 0 2px 10px rgba(0,0,0,0.15); /* !important 오류 제거 및 그림자 조정 */
		padding: 2.5rem;
	}

	/* 메인 타이틀 */
	.lb0 {
		display: block;
		font-weight: bold;
		color: #333;
		font-size: 1.8rem;
		margin-bottom: 1.5rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 항목 행 (Flexbox) */
	.dt-row {
		display: flex;
		border-bottom: 1px solid #eee;
		padding: 1.2rem 0;
		align-items: center;
	}

	/* 항목 라벨 */
	.dt-lb {
		width: 8rem; /* 라벨 길이 통일 */
		font-weight: bold;
		color: #4B2C20; /* 메인 컬러 */
		font-size: 1.05rem;
	}

	/* 항목 값 */
	.dt-val {
		flex: 1;
		color: #333;
		font-size: 1rem;
		font-weight: 700;
	}

	/* 입력 폼 공통 스타일 (수정 모드용) */
	.up-in {
		width: 100%;
		max-width: 300px; /* MES 환경에 맞춰 인풋창 길이 제한 */
		padding: 0.6rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		transition: border-color 0.3s;
	}

	/* 포커스 시 서브 컬러 반응 (제한적 사용) */
	.up-in:focus {
		outline: none;
		border-color: #5C6BC0;
	}

	/* 버튼 그룹 영역 */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		align-items: center;
		gap: 0.8rem;
		margin-top: 2rem;
		border-top: 1px solid #ddd;
		padding-top: 1.5rem;
	}

	/* 버튼 공통 스타일 */
	.btn {
		display: inline-block;
		text-align: center;
		padding: 0.6rem 1.5rem;
		border: none;
		border-radius: 4px;
		font-size: 1rem;
		cursor: pointer;
		transition: all 0.3s;
		font-weight: 600;
	}

	/* 메인 동작 버튼 (수정, 저장, 목록) */
	.btn-main {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-main:hover {
		background-color: #5C6BC0;
	}

	/* 서브 동작 버튼 (삭제, 취소 - 테두리만) */
	.btn-line {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20; 
	}

	.btn-line:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f4f6fa;
	}

	/* 모바일 반응형 대응 */
	@media (max-width: 768px) {
		.dt-wrap {
			width: 95%;
			padding: 1.5rem;
		}
		.dt-row {
			flex-direction: column;
			align-items: flex-start;
		}
		.dt-lb {
			margin-bottom: 0.5rem;
			color: #888;
			font-size: 0.9rem;
		}
		.up-in {
			max-width: 100%;
		}
		.btn-grp {
			flex-direction: column;
			width: 100%;
		}
		.btn-grp form, .btn-grp .btn {
			width: 100%; 
			margin-bottom: 0.5rem;
		}
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="dt-wrap">
	<span class="lb0">생산관리 상세페이지</span>

	<div id="select">
		<div class="dt-row"><span class="dt-lb">제목</span> <span class="dt-val">${result[0].title }</span></div>
		<div class="dt-row"><span class="dt-lb">품목 명</span> <span class="dt-val">${result[0].mdmName}</span></div>
		<div class="dt-row"><span class="dt-lb">목표 총량</span> <span class="dt-val">${result[0].target_quantity}</span></div>
		<div class="dt-row"><span class="dt-lb">목표 달성량</span> <span class="dt-val">${result[0].currentCount}</span></div>
		<div class="dt-row"><span class="dt-lb">남은 목표</span> <span class="dt-val">${result[0].remainCount}</span></div>
		<div class="dt-row"><span class="dt-lb">달성률</span> <span class="dt-val"><fmt:formatNumber value='${(result[0].currentCount * 100.0) / result[0].target_quantity}' pattern='0.0' />%</span></div>
		<div class="dt-row"><span class="dt-lb">시작일</span> <span class="dt-val">${result[0].work_start}</span></div>
		<div class="dt-row"><span class="dt-lb">마감일</span> <span class="dt-val">${result[0].work_end}</span></div>
		
		<div class="btn-grp">
			<a href="${pageContext.request.contextPath}/production/management" class="btn btn-main">목록으로</a>
			
			<% 
				if(level != null && level < 3){
			%>
			<button type="button" class="btn btn-main PMD-btn-update">수정하기</button>
			
			<form method="post" action="/charlie/PMDelete" style="margin: 0;">
				<input type="hidden" name="prod_num" value="${result[0].prod_num }">
				<button type="submit" class="btn btn-line btn-del">삭제</button>
			</form>
			<%} %>
		</div>
	</div>

	<div id="update" style="display: none;">
		<form method="post" action="PMDetailUpdateServlet" onsubmit="return validateForm()">
			<input type="hidden" name="prod_num" value="${result[0].prod_num }">
			
			<div class="dt-row">
				<span class="dt-lb">제목</span>
				<span class="dt-val"><input name="title" id="title" class="up-in PMD-input" type="text" value="${result[0].title }"></span>
			</div>
			
			<div class="dt-row">
				<span class="dt-lb">품목 명</span>
				<span class="dt-val">${result[0].mdmName}</span>
			</div>
			
			<div class="dt-row">
				<span class="dt-lb">목표 총량</span>
				<span class="dt-val"><input name="targetAll" id="value2" class="up-in PMD-input" type="number" value="${result[0].target_quantity}"></span>
			</div>
			
			<div class="dt-row"><span class="dt-lb">목표 달성량</span><span class="dt-val">${result[0].currentCount}</span></div>
			<div class="dt-row"><span class="dt-lb">남은 목표</span><span class="dt-val">${result[0].remainCount}</span></div>
			<div class="dt-row"><span class="dt-lb">달성률</span><span class="dt-val"><fmt:formatNumber value='${(result[0].currentCount * 100.0) / result[0].target_quantity}' pattern='0.0' />%</span></div>
			
			<div class="dt-row">
				<span class="dt-lb">시작일</span>
				<span class="dt-val"><input name="startDate" class="up-in PMD-input" type="date" value="${result[0].work_start}"></span>
			</div>
			
			<div class="dt-row">
				<span class="dt-lb">마감일</span>
				<span class="dt-val"><input name="endDate" class="up-in PMD-input" type="date" value="${result[0].work_end}"></span>
			</div>
			
			<div class="btn-grp">
				<button type="button" class="btn btn-line PMD-btn-cancel">취소</button>
				<button type="submit" class="btn btn-main PMD-btn-save">저장하기</button>
			</div>
		</form>
	</div>
	
</div>

<%@ include file="footer.jsp" %>
	
<script>
	// 폼 유효성 검사 로직
	function validateForm() {
		const title_el = document.getElementById("title");
		const value2_el = document.getElementById("value2");
		
		const title = title_el.value.trim();
		const value2 = value2_el.value;
		
		// 보더 색상 초기화
		title_el.style.borderColor = "";
		value2_el.style.borderColor = "";
		
		if (title === "") {
			alert("제목을 입력해주세요.");
			title_el.focus();
			title_el.style.borderColor = "#4B2C20";
			return false;
		}
		
		if (value2 === "" || value2 <= 0) {
			alert("올바른 목표 수량을 입력해주세요.");
			value2_el.focus();
			value2_el.style.borderColor = "#4B2C20";
			return false;
		}
		
		alert("저장되었습니다.");
		return true;
	}
	
	window.addEventListener('load', () => {
		// 컨테이너
		const select = document.querySelector("#select");
		const update = document.querySelector("#update");
		
		// 버튼
		const btn_update = document.querySelector(".PMD-btn-update");
		const btn_cancel = document.querySelector(".PMD-btn-cancel");
		const btn_del = document.querySelector(".btn-del");
		
		// 수정하기 모드 진입
		if (btn_update) {
			btn_update.addEventListener('click', () => {
				update.style.display = "block";
				select.style.display = "none";
			});
		}
		
		// 수정 취소 및 보기 모드 복귀
		if (btn_cancel) {
			btn_cancel.addEventListener('click', () => {
				if(confirm("수정을 취소하시겠습니까?")) {
					update.style.display = "none";
					select.style.display = "block";
				}
			});
		}
		
		// 삭제 버튼 안전 장치 (리포트 페이지 JS 참고)
		if (btn_del) {
			btn_del.addEventListener('click', (e) => {
				if(confirm("정말로 해당 생산관리를 삭제하시겠습니까?")) {
					alert("삭제되었습니다.");
				} else {
					e.preventDefault();
				}
			});
		}
	});
</script>
</body>
</html>