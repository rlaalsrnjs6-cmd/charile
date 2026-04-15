<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//요청의 한글 깨짐 방지
	request.setCharacterEncoding("utf-8");
	//응답의 한글 깨짐 방지
	response.setContentType("text/html; charset=utf-8;"); 
%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>생산관리 작성</title>
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

	/* 폼 컨테이너 (반응형: % 및 최대 너비 사용) */
	.wr-wrap {
		width: 90%;
		max-width: 800px;
		margin: 3rem auto 6rem auto; 
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.1); 
		padding: 3rem;
	}

	/* 타이틀 */
	.wr-tit {
		font-size: 1.5rem;
		font-weight: 700;
		color: #333;
		margin-bottom: 2rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 입력 행 */
	.wr-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.8rem;
	}

	/* 라벨 (메인 컬러) */
	.wr-lb {
		font-weight: 600;
		color: #4B2C20;
		margin-bottom: 0.6rem;
		font-size: 1.05rem;
	}

	/* 인풋 공통 스타일 */
	.wr-in {
		width: 100%;
		padding: 0.8rem 1rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit; 
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	/* 포커스 시 서브 컬러 반응 */
	.wr-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15); 
	}

	/* 라디오 버튼 그룹 특화 스타일 */
	.rd-grp {
		display: flex;
		flex-wrap: wrap;
		gap: 1.5rem;
		padding: 0.5rem 0;
	}

	.rd-lb {
		display: flex;
		align-items: center;
		gap: 0.4rem;
		font-size: 1rem;
		cursor: pointer;
	}

	.rd-lb input[type="radio"] {
		accent-color: #4B2C20; /* 라디오 버튼 선택 색상을 메인 컬러로 */
		width: 1.1rem;
		height: 1.1rem;
		cursor: pointer;
	}

	/* 버튼 그룹 */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		gap: 1rem;
		margin-top: 3rem;
		border-top: 1px solid #eee;
		padding-top: 2rem;
	}

	/* 버튼 공통 */
	.btn-sub, .btn-can {
		padding: 0.8rem 2.5rem;
		border-radius: 4px;
		font-size: 1rem;
		font-weight: 600;
		text-align: center;
		cursor: pointer;
		border: none;
		transition: all 0.2s;
	}

	/* 등록 버튼 (메인) */
	.btn-sub {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-sub:hover {
		background-color: #5C6BC0;
	}

	/* 취소 버튼 (서브) */
	.btn-can {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
	}

	.btn-can:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f8f9ff;
	}

	/* 태블릿/모바일 반응형 */
	@media (max-width: 768px) {
		.wr-wrap {
			width: 95%;
			padding: 2rem 1.5rem;
			margin: 2rem auto 4rem auto;
		}
		.btn-grp {
			flex-direction: column-reverse; 
			gap: 0.8rem;
		}
		.btn-sub, .btn-can {
			width: 100%;
			padding: 1rem; 
		}
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>

	<div class="wr-wrap">
		<div class="wr-tit">생산관리 작성</div>

		<form method="post" action="PMinsetServlet">
			
			<div class="wr-row">
				<span class="wr-lb">품목 명 (MDM Code)</span>
				<div class="rd-grp">
					<label class="rd-lb">
						<input type="radio" name="mdm_num" value="1" checked="checked"> mdm code1
					</label>
					<label class="rd-lb">
						<input type="radio" name="mdm_num" value="2"> mdm code2
					</label>
					<label class="rd-lb">
						<input type="radio" name="mdm_num" value="33"> mdm code33
					</label>
				</div>
			</div>
			
			<div class="wr-row">
				<span class="wr-lb">목표 생산량</span> 
				<input type="number" name="taget_quantity" class="wr-in" placeholder="목표 생산량을 입력하세요">
			</div>
			
			<div class="wr-row">
				<span class="wr-lb">제목</span> 
				<input type="text" name="title" class="wr-in" placeholder="생산관리 제목을 입력하세요">
			</div>
			
			<div class="wr-row">
				<span class="wr-lb">시작일</span> 
				<input type="date" name="workStart" class="wr-in">
			</div>
			
			<div class="wr-row">
				<span class="wr-lb">만료일</span> 
				<input type="date" name="workEnd" class="wr-in">
			</div>
			
			<div class="btn-grp">
				<button type="button" class="btn-can">취소</button>
				<button type="submit" class="btn-sub">작성하기</button>
			</div>
			
		</form>
	</div>

<%@ include file="footer.jsp" %>

<script>
	const btn_can = document.querySelector(".btn-can");
	const btn_sub = document.querySelector(".btn-sub");
	
	// 요소명 직접 타겟팅
	const qty_el = document.querySelector("input[name='taget_quantity']");
	const title_el = document.querySelector("input[name='title']");
	const start_el = document.querySelector("input[name='workStart']");
	const end_el = document.querySelector("input[name='workEnd']");
	
	// 작성하기 버튼 이벤트 (유효성 및 날짜 논리 검사)
	btn_sub.addEventListener('click', (e) => {
		const qty_val = qty_el.value.trim();
		const title_val = title_el.value.trim();
		const start_val = start_el.value;
		const end_val = end_el.value;
		
		// 1. 보더 색상 초기화
		qty_el.style.borderColor = "";
		title_el.style.borderColor = "";
		start_el.style.borderColor = "";
		end_el.style.borderColor = "";
		
		// 2. 목표 생산량 검사
		if (qty_val === "" || qty_val <= 0) {
			alert("올바른 목표 생산량을 입력하세요.");
			e.preventDefault();
			qty_el.focus();
			qty_el.style.borderColor = "#4B2C20";
			return;
		}

		// 3. 제목 검사
		if (title_val === "") {
			alert("제목을 입력하세요.");
			e.preventDefault();
			title_el.focus();
			title_el.style.borderColor = "#4B2C20";
			return;
		} 

		// 4. 시작일 미입력 검사
		if (start_val === "") {
			alert("시작일을 지정해주세요.");
			e.preventDefault();
			start_el.focus();
			start_el.style.borderColor = "#4B2C20";
			return;
		}

		// 5. 만료일 미입력 검사
		if (end_val === "") {
			alert("만료일을 지정해주세요.");
			e.preventDefault();
			end_el.focus();
			end_el.style.borderColor = "#4B2C20";
			return;
		}

		// 6. 시작일과 만료일 날짜 선후관계 방어 로직 (요청사항 적용)
		const startDate = new Date(start_val);
		const endDate = new Date(end_val);

		if (startDate > endDate) {
			alert("만료일이 시작일보다 빠를 수 없습니다. 날짜를 다시 확인해주세요.");
			e.preventDefault();
			end_el.focus();
			end_el.style.borderColor = "#e53935"; // 에러 시각화
			return;
		}
		
		alert("작성이 완료되었습니다.");
	});
	
	// 작성 취소 버튼 이벤트
	btn_can.addEventListener('click', (e) => {
		if (confirm("정말 작성을 취소하시겠습니까?")) {
			alert("취소되었습니다.");
			history.back(); 
		} else {
			e.preventDefault(); 
		}
	});
</script>
</body>
</html>