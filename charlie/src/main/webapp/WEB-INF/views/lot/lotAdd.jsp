<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOT 등록</title>
<style>
	/* 철칙 1, 2, 20: 기본 초기화 및 푸터 하단 고정 설정 */
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

	/* 철칙 4, 5: 반응형 컨테이너 및 메인 컬러 적용 */
	.reg-wrap {
		width: 90%;
		max-width: 700px;
		margin: 3rem auto 6rem auto; 
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.15); 
		padding: 3rem;
		flex: 1;
	}

	.reg-tit {
		font-size: 1.5rem;
		font-weight: 700;
		color: #333;
		margin-bottom: 2rem;
		border-bottom: 2px solid #eee;
		padding-bottom: 1rem;
	}

	.reg-row {
		display: flex;
		flex-direction: column;
		margin-bottom: 1.5rem;
	}

	.reg-lb {
		font-weight: 600;
		color: #4B2C20;
		margin-bottom: 0.5rem;
		font-size: 1rem;
	}

	.reg-in {
		width: 100%;
		padding: 0.8rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit; 
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	.reg-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1); 
	}

	/* 철칙 21: 버튼 그룹 */
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
		<div class="reg-tit">LOT 등록</div>

		<form id="lotForm" method="post" action="lot">
			<input type="hidden" name="mod" value="add">
			
			<div class="reg-row">
				<label class="reg-lb">LOT당 제품개수</label>
				<select name="order_num" class="reg-in">
					<c:forEach var="o" items="${order}">
						<option value="${o.order_num}">${o.work_order_title}(${o.order_num})</option>
					</c:forEach>
				</select>
			</div>

			<div class="reg-row">
				<label class="reg-lb">품질체크전후</label>
				<select name="qc_chk" class="reg-in">
					<option value="Y">Y</option>
					<option value="N">N</option>
				</select>
			</div>


			<div class="btn-grp">
				<a href="lot?mod=list" class="btn-can">등록취소</a>
				<button type="submit" class="btn-sub">등록하기</button>
			</div>
			
		</form>
	</div>

	<%@ include file="/footer.jsp" %>
</div>

<script>
	window.onload = function() {
		const lotForm = document.getElementById("lotForm");
		const btnCan = document.querySelector(".btn-can");
		const countInput = document.querySelector("input[name='lot_count']");

		/* [신규 추가] 정규표현식을 이용한 실시간 입력 제한 로직 (철칙 14 대응) */
		countInput.addEventListener("input", function(e) {
			// 숫자가 아닌 문자를 모두 제거
			this.value = this.value.replace(/[^0-9]/g, "");
		});
		
		lotForm.addEventListener("submit", function(e) {
			// 공백 체크
			if(countInput.value.trim() === "") {
				alert("제품 개수를 입력해주세요.");
				countInput.focus();
				e.preventDefault();
				return;
			}

			if(!confirm("LOT 정보를 등록하시겠습니까?")) {
				e.preventDefault();
			}
		});

		/* 철칙 21: 취소 컨펌 */
		btnCan.addEventListener("click", function(e) {
			if(!confirm("작성 중인 내용이 저장되지 않습니다. 취소하시겠습니까?")) {
				e.preventDefault();
			}
		});
	}
</script>
</body>
</html>