<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Warehouse.WarehouseDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고관리 등록</title>
<style>
	/* [철칙 1, 2, 4, 20] 기본 초기화 및 하단 고정 설정 */
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}

	a {
		text-decoration: none;
		color: inherit;
	}

	/* [철칙 20] 컨텐츠가 적어도 푸터가 하단에 위치하도록 설정 */
	.mat-all {
		min-height: 100vh;
		display: flex;
		flex-direction: column;
	}

	/* [철칙 8] 기존 hr은 스타일링을 위해 제거 또는 숨김 처리 */
	hr {
		display: none;
	}

	/* 폼 컨테이너 (반응형 단위 사용) */
	.reg-wrap {
		width: 90%;
		max-width: 700px;
		margin: 3rem auto 6rem auto; 
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* [철칙 5] 메인 컬러 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.15); 
		padding: 3rem;
		flex: 1; /* 컨텐츠 영역 확장 */
	}

	/* [철칙 3] 타이틀 스타일 */
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

	/* [철칙 5, 12, 23] 라벨 및 텍스트 설정 */
	.reg-lb {
		font-weight: 600; /* 참고 로직 글씨 굵기 반영 */
		color: #4B2C20;
		margin-bottom: 0.5rem;
		font-size: 1rem;
	}

	/* 인풋 및 셀렉트 (철칙 23: 폰트 색상 블랙 유지) */
	.reg-in {
		width: 100%;
		padding: 0.8rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit; 
		color: #000; 
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	/* [철칙 6] 포커스 시 서브 컬러 반응 */
	.reg-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1); 
	}

	/* 라디오 그룹 */
	.rd-grp {
		display: flex;
		gap: 2rem;
		padding: 0.5rem 0;
	}

	.rd-item {
		display: flex;
		align-items: center;
		gap: 0.5rem;
		cursor: pointer;
		color: #000;
		font-weight: 600;
	}

	/* [철칙 21] 버튼 그룹 위치 일치 */
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

	/* 등록 버튼 (메인 컬러) */
	.btn-sub {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-sub:hover {
		background-color: #5C6BC0; /* [철칙 6] 서브 컬러 활용 */
	}

	/* 취소 버튼 (테두리 스타일) */
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

	/* [철칙 4] 태블릿/모바일 반응형 (px 최소화) */
	@media (max-width: 768px) {
		.reg-wrap {
			width: 95%;
			padding: 2rem 1.5rem;
		}
		.btn-grp {
			flex-direction: column-reverse; 
			gap: 0.8rem;
		}
		.btn-sub, .btn-can {
			width: 100%;
		}
	}
</style>
</head>
<body>
<div class="mat-all">
	<%@ include file="/header.jsp" %>

	<div class="reg-wrap">
		<div class="reg-tit">창고 등록</div>

		<form id="regForm" method="post" action="warehouse">
			<input type="hidden" name="cmd" value="insert">

			<div class="reg-row">
				<label class="reg-lb">구역 (Section)</label>
				<select name="wh_section" class="reg-in">
					<option value="RM-A01">RM-A01</option>
					<option value="RM-A02">RM-A02</option>
					<option value="RM-A03">RM-A03</option>
					<option value="FG-B01">FG-B01</option>
					<option value="FG-B02">FG-B02</option>
					<option value="FG-B03">FG-B03</option>
					<option value="FG-B04">FG-B04</option>
					<option value="CS-C01">CS-C01</option>
					<option value="CS-C02">CS-C02</option>
				</select>
			</div>

			<div class="reg-row">
				<label class="reg-lb">층수 (Floor)</label>
				<select name="floor_level" class="reg-in">
					<option value="B1">B1</option>
					<option value="B2">B2</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="F">F</option>
				</select>
			</div>

			<div class="reg-row">
				<label class="reg-lb">온도 (Temperature)</label>
				<input type="number" step="0.1" name="temperature" class="reg-in" value="12.5">
			</div>

			<div class="reg-row">
				<label class="reg-lb">습도 (Humidity)</label>
				<input type="number" step="0.1" name="humidity" class="reg-in" value="32.1">
			</div>

			<div class="reg-row">
				<label class="reg-lb">상태 (Status)</label>
				<div class="rd-grp">
					<label class="rd-item">
						<input type="radio" name="wh_status_chk" value="Y" checked> 정상
					</label>
					<label class="rd-item">
						<input type="radio" name="wh_status_chk" value="N"> 이상
					</label>
				</div>
			</div>

			<div class="btn-grp">
				<a href="warehouse?cmd=list" class="btn-can">등록취소</a>
				<button type="submit" class="btn-sub">등록하기</button>
			</div>
			
		</form>
	</div>

	<%@ include file="/footer.jsp" %>
</div>

<script>
	// [철칙 14] 참고 페이지의 JS 로직 반영 및 유효성 검사
	window.onload = function() {
		const regForm = document.getElementById("regForm");
		const btnCan = document.querySelector(".btn-can");
		
		regForm.addEventListener("submit", function(e) {
			const temp = document.querySelector("input[name='temperature']");
			const humi = document.querySelector("input[name='humidity']");

			// 온도 확인 (철칙 15와 유사한 방어 로직 적용)
			if(temp.value.trim() === "" || isNaN(temp.value)) {
				alert("온도를 정확한 숫자로 입력해주세요.");
				temp.focus();
				e.preventDefault();
				return;
			}

			// 습도 확인
			if(humi.value.trim() === "" || isNaN(humi.value)) {
				alert("습도를 정확한 숫자로 입력해주세요.");
				humi.focus();
				e.preventDefault();
				return;
			}

			// [철칙 10] 한 번에 제대로 작동하도록 최종 컨펌
			if(!confirm("창고 정보를 등록하시겠습니까?")) {
				e.preventDefault();
			}
		});

		// 취소 버튼 컨펌 (참고 로직 적용)
		btnCan.addEventListener("click", function(e) {
			if(!confirm("작성 중인 내용이 저장되지 않습니다. 취소하시겠습니까?")) {
				e.preventDefault();
			}
		});
	}
</script>
</body>
</html>