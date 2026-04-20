<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기준관리 등록</title>
<style>
	/* 기본 초기화 */
/* 	* { */
/* 		box-sizing: border-box; */
/* 		margin: 0; */
/* 		padding: 0; */
/* 	} */

/* 	a { */
/* 		text-decoration: none; */
/* 		color: inherit; */
/* 	} */

/* 	/* 폼 컨테이너 (반응형: % 및 최대 너비 사용) */ */
/* 	.reg-wrap { */
/* 		width: 90%; */
/* 		max-width: 700px; */
/* 		margin: 3rem auto 6rem auto;  */
/* 		background-color: #fff; */
/* 		border-top: 3rem solid #4B2C20; /* 메인 컬러 */ */
/* 		border-radius: 6px; */
/* 		box-shadow: 0 4px 20px rgba(0,0,0,0.15);  */
/* 		padding: 3rem; */
/* 	} */

/* 	/* 타이틀 */ */
/* 	.reg-tit { */
/* 		font-size: 1.5rem; */
/* 		font-weight: 700; */
/* 		color: #333; */
/* 		margin-bottom: 2rem; */
/* 		border-bottom: 2px solid #eee; */
/* 		padding-bottom: 1rem; */
/* 	} */

/* 	/* 입력 행 */ */
/* 	.reg-row { */
/* 		display: flex; */
/* 		flex-direction: column; */
/* 		margin-bottom: 1.5rem; */
/* 	} */

/* 	/* 라벨 (메인 컬러) */ */
/* 	.reg-lb { */
/* 		font-weight: 600; */
/* 		color: #4B2C20; */
/* 		margin-bottom: 0.5rem; */
/* 		font-size: 1rem; */
/* 	} */

/* 	/* 인풋 및 셀렉트 공통 스타일 */ */
/* 	.reg-in { */
/* 		width: 100%; */
/* 		padding: 0.8rem; */
/* 		border: 1px solid #ddd; */
/* 		border-radius: 4px; */
/* 		font-size: 1rem; */
/* 		font-family: inherit;  */
/* 		transition: border-color 0.3s, box-shadow 0.3s; */
/* 	} */

/* 	/* 포커스 시 서브 컬러 반응 */ */
/* 	.reg-in:focus { */
/* 		outline: none; */
/* 		border-color: #5C6BC0;  */
/* 		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1);  */
/* 	} */

/* 	/* 라디오 그룹 스타일 */ */
/* 	.rd-grp { */
/* 		display: flex; */
/* 		gap: 2rem; */
/* 		padding: 0.5rem 0; */
/* 	} */

/* 	.rd-item { */
/* 		display: flex; */
/* 		align-items: center; */
/* 		gap: 0.5rem; */
/* 		cursor: pointer; */
/* 	} */

/* 	/* 버튼 그룹 */ */
/* 	.btn-grp { */
/* 		display: flex; */
/* 		justify-content: flex-end; */
/* 		gap: 1rem; */
/* 		margin-top: 3rem; */
/* 		border-top: 1px solid #eee; */
/* 		padding-top: 2rem; */
/* 	} */

/* 	/* 버튼 공통 */ */
/* 	.btn-sub, .btn-can { */
/* 		padding: 0.8rem 2.5rem; */
/* 		border-radius: 4px; */
/* 		font-size: 1rem; */
/* 		font-weight: 600; */
/* 		text-align: center; */
/* 		cursor: pointer; */
/* 		border: none; */
/* 		transition: all 0.2s; */
/* 	} */

/* 	/* 등록 버튼 (메인 컬러) */ */
/* 	.btn-sub { */
/* 		background-color: #4B2C20; */
/* 		color: #fff; */
/* 	} */

/* 	.btn-sub:hover { */
/* 		background-color: #5C6BC0; /* 서브 컬러 */ */
/* 	} */

/* 	/* 취소 버튼 (테두리 스타일) */ */
/* 	.btn-can { */
/* 		background-color: #fff; */
/* 		color: #4B2C20; */
/* 		border: 1px solid #4B2C20; */
/* 		display: flex; */
/* 		align-items: center; */
/* 		justify-content: center; */
/* 	} */

/* 	.btn-can:hover { */
/* 		color: #5C6BC0; */
/* 		border-color: #5C6BC0; */
/* 		background-color: #f8f9ff; */
/* 	} */

/* 	/* 태블릿/모바일 반응형 */ */
/* 	@media (max-width: 768px) { */
/* 		.reg-wrap { */
/* 			width: 95%; */
/* 			padding: 2rem 1.5rem; */
/* 		} */
/* 		.btn-grp { */
/* 			flex-direction: column-reverse;  */
/* 			gap: 0.8rem; */
/* 		} */
/* 		.btn-sub, .btn-can { */
/* 			width: 100%; */
/* 		} */
/* 	} */
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<h1>입고페이지</h1>
<div class="reg-wrap">
	<div class="reg-tit">입고 페이지</div>

	<form id="regForm" method="post" action="mdm">
		
		<div class="reg-row">
			<label class="reg-lb">양 (quantity)</label>
			<input type="text" name="quantity" class="reg-in" value="30">
		</div>

		<div class="reg-row">
			<label class="reg-lb">입/출고 (Type)</label>
			<select name="io_type" class="reg-in">
				<option value="입고">입고</option>
				<option value="출고">출고</option>
			</select>
		</div>

		<div class="reg-row">
			<label class="reg-lb">저장위치</label>
			<input type="text" name="storage_sec" class="reg-in" value="AAABBC001">
		</div>


		<div class="reg-row">
			<label class="reg-lb">사용기한</label>
			<input type="date" name="exp_date" class="reg-in">
		</div>

		<div class="reg-row">
			<label class="reg-lb">자재</label>
			   <select name="mdm_num" class="reg-in">
                    <c:forEach var="item" items="${ list }">
                        <option value="${ item.mdm_num }"> ${ item.code } : [${ item.name }] </option>
                    </c:forEach>
                </select>
		</div>

		<div class="btn-grp">
			<input type="hidden" name="cmd" value="insert">
			<a href="mdm?cmd=list" class="btn-can">등록취소</a>
			<button type="submit" class="btn-sub">등록하기</button>
		</div>
		
	</form>
</div>

<%@ include file="/footer.jsp" %>

<script>
	window.onload = function() {
		// 기존 날짜 설정 로직 유지
		let now = new Date();
		now.setMonth(now.getMonth() + 1);
		let year = now.getFullYear();
		let month = ("0" + (now.getMonth() + 1)).slice(-2);
		let day = ("0" + now.getDate()).slice(-2);
		let oneMonthLater = `${year}-${month}-${day}`;
		document.getElementById('exp_date').value = oneMonthLater;

		// 신규 추가: 유효성 검사 및 UX 로직
		const regForm = document.getElementById("regForm");
		const btnCan = document.querySelector(".btn-can");
		
		// 등록 버튼 클릭 이벤트
		regForm.addEventListener("submit", function(e) {
			const name = document.querySelector("input[name='name']");
			const code = document.querySelector("input[name='code']");
			const quantity = document.querySelector("input[name='quantity']");
			const price = document.querySelector("input[name='price']");

			// 명칭 확인
			if(name.value.trim() === "") {
				alert("명칭을 입력해주세요.");
				name.focus();
				e.preventDefault();
				return;
			}
			// 코드 확인
			if(code.value.trim() === "") {
				alert("코드를 입력해주세요.");
				code.focus();
				e.preventDefault();
				return;
			}
			// 수량 확인
			if(quantity.value.trim() === "" || isNaN(quantity.value)) {
				alert("수량을 올바른 숫자로 입력해주세요.");
				quantity.focus();
				e.preventDefault();
				return;
			}
			// 가격 확인
			if(price.value.trim() === "" || price.value < 0) {
				alert("가격을 정확히 입력해주세요.");
				price.focus();
				e.preventDefault();
				return;
			}

			if(!confirm("기준정보를 등록하시겠습니까?")) {
				e.preventDefault();
			}
		});

		// 취소 버튼 클릭 이벤트
		btnCan.addEventListener("click", function(e) {
			if(!confirm("작성 중인 내용이 저장되지 않습니다. 취소하시겠습니까?")) {
				e.preventDefault();
			}
		});
	}
</script>
</body>
</html>