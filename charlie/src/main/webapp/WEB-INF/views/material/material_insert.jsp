<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Material.MaterialDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자재 등록</title>
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

	/* 메인 컨테이너 - 최소 높이 설정 (철칙 4번 및 요청사항 반영) */
	.mat-all {
		min-height: 84.2vh; /* 화면 높이에 맞춰 유동적으로 조절 */
		display: flex;
		flex-direction: column;
		justify-content: center;
		padding: 2rem 0;
	}

	/* 폼 컨테이너 (반응형: % 및 최대 너비 사용) */
	.reg-wrap {
		width: 90%;
		max-width: 700px;
		margin: 0 auto; 
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 */
		border-radius: 6px;
		box-shadow: 0 4px 20px rgba(0,0,0,0.15); 
		padding: 3rem;
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

	/* 셀렉트 공통 스타일 */
	.reg-in {
		width: 100%;
		padding: 0.8rem;
		border: 1px solid #ddd;
		border-radius: 4px;
		font-size: 1rem;
		font-family: inherit; 
		transition: border-color 0.3s, box-shadow 0.3s;
	}

	/* 포커스 시 서브 컬러 반응 */
	.reg-in:focus {
		outline: none;
		border-color: #5C6BC0; 
		box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1); 
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

	/* 등록 버튼 (메인 컬러) */
	.btn-sub {
		background-color: #4B2C20;
		color: #fff;
	}

	.btn-sub:hover {
		background-color: #5C6BC0; /* 서브 컬러 */
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

	/* 태블릿/모바일 반응형 */
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
<%@ include file="/header.jsp" %>

<div class="mat-all">
	<div class="reg-wrap">
		<div class="reg-tit">자재 등록</div>
	
		<form id="regForm" method="post" action="material">
			
			<div class="reg-row">
				<label class="reg-lb">섹션/층구분</label>
				<select name="warehouse_num" class="reg-in">
					<c:forEach var="item" items="${map.whList}">
						<option value="${item.warehouse_num}" selected>
							${item.wh_section} / ${item.floor_level}
						</option>
					</c:forEach>
				</select>
			</div>
	
			<div class="reg-row">
				<label class="reg-lb">품목</label>
				<select name="mdm_num" class="reg-in">
					<c:forEach var="item" items="${map.mdmList}">
						<option value="${item.mdm_num}" selected>
							${item.code} / ${item.name}
						</option>
					</c:forEach>
				</select>
			</div>
	
			<div class="btn-grp">
				<input type="hidden" name="cmd" value="insert">
				<a href="material?cmd=list" class="btn-can">취소</a>
				<button type="submit" class="btn-sub">작성하기</button>
			</div>
			
		</form>
	</div>
</div>

<%@ include file="/footer.jsp" %>

<script>
	window.onload = function() {
		const regForm = document.getElementById("regForm");
		const btnCan = document.querySelector(".btn-can");
		
		// 등록 전 유효성 및 의사 확인 (철칙 준수)
		regForm.addEventListener("submit", function(e) {
			const warehouse = document.querySelector("select[name='warehouse_num']");
			const mdm = document.querySelector("select[name='mdm_num']");

			if(!warehouse || warehouse.value.trim() === "") {
				alert("섹션/층구분 정보가 없습니다.");
				e.preventDefault();
				return;
			}
			
			if(!mdm || mdm.value.trim() === "") {
				alert("품목 정보가 없습니다.");
				e.preventDefault();
				return;
			}

			if(!confirm("자재를 등록하시겠습니까?")) {
				e.preventDefault();
			}
		});

		// 취소 버튼 경고창
		btnCan.addEventListener("click", function(e) {
			if(!confirm("작성 중인 내용이 사라집니다. 취소하시겠습니까?")) {
				e.preventDefault();
			}
		});
	}
</script>
</body>
</html>