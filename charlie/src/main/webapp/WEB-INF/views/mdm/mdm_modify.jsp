<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Mdm.MdmDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mdm modify</title>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

#mdm-mod-scope * {
	box-sizing: border-box;
}

#mdm-mod-scope {
	width: 100%;
	padding: 2rem 0 5rem 0; /* 헤더 여백(.mes-cont)과 자연스럽게 이어지도록 처리 */
}

/* 수정 폼 메인 컨테이너 */
#mdm-mod-scope .mod-wrap {
	width: 90%;
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
	border-top: 3rem solid #4B2C20; /* 메인 컬러 */
	border-radius: 6px;
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
	padding: 3rem;
}

/* 페이지 타이틀 */
#mdm-mod-scope .mod-tit {
	font-size: 1.5rem;
	font-weight: 700;
	color: #333;
	margin-bottom: 2rem;
	border-bottom: 2px solid #eee;
	padding-bottom: 1rem;
}

/* 입력 항목 행 */
#mdm-mod-scope .mod-row {
	display: flex;
	flex-direction: column;
	margin-bottom: 1.5rem;
}

/* 라벨 (메인 컬러) */
#mdm-mod-scope .mod-lb {
	font-weight: 600;
	color: #4B2C20;
	margin-bottom: 0.6rem;
	font-size: 1.05rem;
}

/* 읽기 전용 텍스트 (관리번호, 날짜 등) */
#mdm-mod-scope .mod-txt {
	font-size: 1rem;
	color: #555;
	padding: 0.8rem 1rem;
	background-color: #f9f9f9;
	border-radius: 4px;
	border: 1px solid #eee;
}

/* 인풋 및 셀렉트 공통 스타일 */
#mdm-mod-scope .mod-in {
	width: 100%;
	padding: 0.8rem 1rem;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 1rem;
	font-family: inherit;
	transition: border-color 0.3s, box-shadow 0.3s;
}

/* 포커스 시 서브 컬러 반응 */
#mdm-mod-scope .mod-in:focus {
	outline: none;
	border-color: #5C6BC0;
	box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
}

/* 라디오 그룹 (UX 개선) */
#mdm-mod-scope .mod-rd-grp {
	display: flex;
	gap: 2rem;
	padding: 0.5rem 0;
}

#mdm-mod-scope .mod-rd-item {
	display: flex;
	align-items: center;
	gap: 0.5rem;
	cursor: pointer;
	font-size: 1rem;
}

/* 버튼 그룹 */
#mdm-mod-scope .mod-btn-grp {
	display: flex;
	justify-content: flex-end;
	gap: 1rem;
	margin-top: 3rem;
	border-top: 1px solid #eee;
	padding-top: 2rem;
}

/* 버튼 공통 스타일 */
#mdm-mod-scope .mod-btn {
	padding: 0.8rem 2.5rem;
	border-radius: 4px;
	font-size: 1rem;
	font-weight: 600;
	text-align: center;
	cursor: pointer;
	border: none;
	transition: all 0.2s;
	text-decoration: none; /* a태그 용 */
}

/* 수정하기 버튼 */
#mdm-mod-scope .btn-sub {
	background-color: #4B2C20;
	color: #fff;
}

#mdm-mod-scope .btn-sub:hover {
	background-color: #5C6BC0;
}

/* 취소 버튼 */
#mdm-mod-scope .btn-can {
	background-color: #fff;
	color: #4B2C20;
	border: 1px solid #4B2C20;
}

#mdm-mod-scope .btn-can:hover {
	color: #5C6BC0;
	border-color: #5C6BC0;
	background-color: #f8f9ff;
}

/* 태블릿 및 모바일 반응형 대응 */
@media ( max-width : 768px) {
	#mdm-mod-scope .mod-wrap {
		width: 95%;
		padding: 2rem 1.5rem;
	}
	#mdm-mod-scope .mod-btn-grp {
		flex-direction: column-reverse;
		gap: 0.8rem;
	}
	#mdm-mod-scope .mod-btn {
		width: 100%;
		padding: 1rem;
	}
}
</style>
</head>
<body>
	<%@ include file="/header.jsp"%>

	<h1 style="display: none;">mdm modify</h1>
	<hr style="display: none;">
	<a href="mdm?cmd=list" style="display: none;">뒤로</a>
	<hr style="display: none;">

	<div id="mdm-mod-scope">
		<div class="mod-wrap">
			<div class="mod-tit">기준정보 수정</div>

			<form id="modForm" method="post" action="mdm">

				<input type="hidden" name="mdm_num" value="${ mdmDTO.mdm_num }">
				<input type="hidden" name="cmd" value="update">

				<div class="mod-row">
					<span class="mod-lb">관리번호</span> <span class="mod-txt">${ mdmDTO.mdm_num }</span>
				</div>

				<div class="mod-row">
					<span class="mod-lb">코드</span> <input type="text" name="code"
						value="${ mdmDTO.code }" class="mod-in">
				</div>

				<div class="mod-row">
					<span class="mod-lb">이름</span> <input type="text" name="name"
						value="${ mdmDTO.name }" class="mod-in">
				</div>

				<div class="mod-row">
					<span class="mod-lb">단위</span> <select name="unit" class="mod-in">
						<option value="g" ${mdmDTO.unit == 'g' ? 'selected' : ''}>g</option>
						<option value="kg" ${mdmDTO.unit == 'kg' ? 'selected' : ''}>kg</option>
						<option value="L" ${mdmDTO.unit == 'L' ? 'selected' : ''}>L</option>
						<option value="ea" ${mdmDTO.unit == 'EA' ? 'selected' : ''}>EA</option>
					</select>
				</div>

				<div class="mod-row">
					<span class="mod-lb">수량</span> <input type="text" name="quantity"
						value="${ mdmDTO.quantity }" class="mod-in">
				</div>

				<div class="mod-row">
					<span class="mod-lb">타입</span> <select name="type" class="mod-in">
						<option value="assemble"
							${mdmDTO.type == 'assemble' ? 'selected' : ''}>반제품</option>
						<option value="product"
							${mdmDTO.type == 'product' ? 'selected' : ''}>제품</option>
						<option value="material"
							${mdmDTO.type == 'material' ? 'selected' : ''}>재료</option>
						<option value="equip" ${mdmDTO.type == 'equip' ? 'selected' : ''}>장비</option>
					</select>
				</div>

				<div class="mod-row">
					<span class="mod-lb">가격</span> <input type="text" name="price"
						value="${ mdmDTO.price }" class="mod-in">
				</div>

				<div class="mod-row">
					<span class="mod-lb">입고날짜</span> <span class="mod-txt">${ mdmDTO.received_date }</span>
				</div>

				<div class="mod-row">
					<span class="mod-lb">사용기한</span> <span class="mod-txt">${ mdmDTO.exp_date }</span>
				</div>

				<div class="mod-row">
					<span class="mod-lb">가용확인</span>
					<div class="mod-rd-grp">
						<label class="mod-rd-item"> <input type="radio"
							name="can_use" value="Y" ${mdmDTO.canUse == 'Y' ? 'checked' : ''}>
							가능
						</label> <label class="mod-rd-item"> <input type="radio"
							name="can_use" value="N" ${mdmDTO.canUse == 'N' ? 'checked' : ''}>
							불가능
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

	<%@ include file="/footer.jsp"%>

	<script>
	const modForm = document.getElementById("modForm");
	const btn_can = document.querySelector(".btn-can");

	const code_el = document.querySelector("input[name='code']");
	const name_el = document.querySelector("input[name='name']");
	const qty_el = document.querySelector("input[name='quantity']");
	const price_el = document.querySelector("input[name='price']");

	modForm.addEventListener('submit', (e) => {
		const code_val = code_el.value.trim();
		const name_val = name_el.value.trim();
		const qty_val = qty_el.value.trim();
		const price_val = price_el.value.trim();
		
		if (code_val === "") {
			alert("코드를 입력하세요.");
			e.preventDefault();
			code_el.focus();
			return;
		}
		if (name_val === "") {
			alert("이름을 입력하세요.");
			e.preventDefault();
			name_el.focus();
			return;
		}
		if (qty_val === "" || isNaN(qty_val)) {
			alert("수량을 올바른 숫자로 입력하세요.");
			e.preventDefault();
			qty_el.focus();
			return;
		}
		if (price_val === "" || isNaN(price_val)) {
			alert("가격을 올바른 숫자로 입력하세요.");
			e.preventDefault();
			price_el.focus();
			return;
		}

		if (confirm("해당 내용으로 기준정보를 수정하시겠습니까?")) {
			alert("수정이 완료되었습니다.");
		} else {
			e.preventDefault(); 
		}
	});

	btn_can.addEventListener('click', () => {
		if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
			alert("취소되었습니다.");
			location.href = "mdm?cmd=list";
		}
	});
</script>
</body>
</html>