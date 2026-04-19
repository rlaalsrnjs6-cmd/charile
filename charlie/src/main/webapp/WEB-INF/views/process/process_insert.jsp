<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공정 정보 등록</title>
<style>
	/* [1] 기본 초기화 및 철칙 20 (.mat-all) */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	a {
		text-decoration: none;
		color: inherit;
	}

	.mat-all {
		min-height: 100vh;
		display: flex;
		flex-direction: column;
	}

	/* [2] 폼 컨테이너 (철칙 1, 4, 5) */
	.reg-wrap {
		width: 90%;
		max-width: 700px;
		margin: 3rem auto 6rem auto; 
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.15); 
		padding: 3rem;
		flex: 1; /* 콘텐츠가 적어도 푸터를 하단으로 밀어냄 */
	}

	/* 타이틀 */
	.reg-tit {
		font-size: 1.5rem;
		font-weight: 700;
		color: #333;
		margin-bottom: 2rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	/* 입력 행 */
	.reg-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.5rem;
	}

	/* 라벨 (메인 컬러) */
	.reg-lb {
		font-weight: 600;
		color: #4B2C20;
		margin-bottom: 0.5rem;
		font-size: 1rem;
	}

	/* 인풋 및 셀렉트 공통 스타일 */
	.reg-in {
		width: 100%;
		padding: 0.8rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit; 
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	/* [3] 철칙 6: 포커스 시 서브 컬러(#5C6BC0) 적용 */
	.reg-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1); 
	}

	/* [4] 버튼 그룹 (철칙 21: 위치 및 스타일 동일화) */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		gap: 1rem;
		margin-top: 3rem;
		border-top: 1px solid #eee;
		padding-top: 2rem;
	}

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

	.btn-sub {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-sub:hover {
		background-color: #5C6BC0;
	}

	.btn-can {
		background-color: #fff;
		color: #4B2C20;
		border: 1px solid #4B2C20;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.btn-can:hover {
		color: #5C6BC0;
		border-color: #5C6BC0;
		background-color: #f8f9ff;
	}

	/* 철칙 4: 태블릿/모바일 반응형 */
	@media (max-width: 768px) {
		.reg-wrap { width: 95%; padding: 2rem 1.5rem; }
		.btn-grp { flex-direction: column-reverse; gap: 0.8rem; }
		.btn-sub, .btn-can { width: 100%; }
	}
</style>
</head>
<body>
<div class="mat-all">
	<%@ include file="/header.jsp" %>

	<div class="reg-wrap">
		<div class="reg-tit">공정 정보 등록</div>

		<form id="procForm" method="post" action="process">
			<input type="hidden" name="cmd" value="insert">
			
			<div class="reg-row">
				<label class="reg-lb">공정 내용 (Process Content)</label>
				<textarea name="process_content" class="reg-in" style="height: 120px; resize: none;">부품 A와 B를 결합 후 고정 볼트 체결</textarea>
			</div>

			<div class="reg-row">
				<label class="reg-lb">공정 흐름 (Flow - 숫자만 입력)</label>
				<input type="text" name="flow" class="reg-in" value="1" inputmode="numeric">
			</div>

			<div class="reg-row">
				<label class="reg-lb">이미지 URL (Image URL)</label>
				<input type="text" name="img_url" class="reg-in" value="https://cdn.discordapp.com/attachments/1486907574929330306/1492024217951277128/-removebg-preview.png">
			</div>

			<div class="reg-row">
				<label class="reg-lb">기준정보 연결 (MDM Link)</label>
				<select name="mdm_num" class="reg-in">
					<c:forEach var="item" items="${ list }">
						<option value="${ item.mdm_num }">${ item.name } : ${ item.code }</option>
					</c:forEach>
				</select>
			</div>

			<div class="btn-grp">
				<a href="process?cmd=list" class="btn-can">등록취소</a>
				<button type="submit" class="btn-sub">등록하기</button>
			</div>
			
		</form>
	</div>

	<%@ include file="/footer.jsp" %>
</div>

<script>
	window.onload = function() {
		const procForm = document.getElementById("procForm");
		const btnCan = document.querySelector(".btn-can");
		const flowInput = document.querySelector("input[name='flow']");

		/* [실시간 방어] flow 필드 숫자 전용 정규표현식 적용 */
		flowInput.addEventListener("input", function() {
			this.value = this.value.replace(/[^0-9]/g, "");
		});

		/* 철칙 14: 유효성 검사 및 최종 확인 */
		procForm.addEventListener("submit", function(e) {
			const content = document.querySelector("textarea[name='process_content']");
			
			if(content.value.trim() === "") {
				alert("공정 내용을 입력해주세요.");
				content.focus();
				e.preventDefault();
				return;
			}

			if(flowInput.value.trim() === "") {
				alert("공정 흐름 번호를 입력해주세요.");
				flowInput.focus();
				e.preventDefault();
				return;
			}

			if(!confirm("공정 정보를 등록하시겠습니까?")) {
				e.preventDefault();
			}
		});

		/* 철칙 21: 취소 버튼 컨펌 로직 (참고 로직 동일 적용) */
		btnCan.addEventListener("click", function(e) {
			if(!confirm("작성 중인 내용이 저장되지 않습니다. 취소하시겠습니까?")) {
				e.preventDefault();
			}
		});
	}
</script>
</body>
</html>