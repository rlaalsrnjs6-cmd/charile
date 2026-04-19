<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Material.MaterialDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자재관리 상세페이지</title>
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

	/* 기존 텍스트 기반 레이아웃에 있던 hr 숨김 처리 */
	hr {
		display: none;
	}

	/* 상세 컨테이너 (반응형: %, max-width, vh, rem 활용) */
	.dt-wrap {
		width: 90%;
		max-width: 50rem;
		margin: 5vh auto;
		background-color: #fff;
		border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
		border-radius: 0.25rem;
		box-shadow: 0 0.25rem 0.75rem rgba(0,0,0,0.1);
		padding: 2.5rem;
	}

	/* 메인 타이틀 */
	.lb0 {
		display: block;
		font-weight: bold;
		color: #333;
		font-size: 1.8rem;
		margin-bottom: 1.5rem;
		border-bottom: 0.125rem solid #eee;
		padding-bottom: 1rem;
	}

	/* 항목 행 (Flexbox) */
	.dt-row {
		display: flex;
		border-bottom: 0.0625rem solid #eee;
		padding: 1.2rem 0;
		align-items: center;
	}

	/* 항목 라벨 (메인 컬러) */
	.dt-lb {
		width: 8rem;
		font-weight: bold;
		color: #4B2C20; 
		font-size: 1.05rem;
	}

	/* 항목 값 (참고 로직과 동일한 굵기 및 색상) */
	.dt-val {
		flex: 1;
		color: #333;
		font-size: 1rem;
		font-weight: 700;
	}

	/* 버튼 그룹 영역 */
	.btn-grp {
		display: flex;
		justify-content: flex-end;
		align-items: center;
		gap: 0.8rem;
		margin-top: 2rem;
		border-top: 0.0625rem solid #ddd;
		padding-top: 1.5rem;
	}

	/* 버튼 공통 스타일 */
	.btn-list, .btn-del {
		display: inline-block;
		text-align: center;
		padding: 0.6rem 1.5rem;
		border-radius: 0.25rem;
		font-size: 1rem;
		font-weight: 600;
		cursor: pointer;
		transition: all 0.3s ease;
	}

	/* 목록 버튼 (메인 컬러 기반) */
	.btn-list {
		background-color: #4B2C20;
		color: #fff;
		border: 0.0625rem solid #4B2C20;
	}

	/* 삭제 버튼 (서브 동작: 배경 비우고 테두리만 메인 컬러) */
	.btn-del {
		background-color: #fff;
		color: #4B2C20;
		border: 0.0625rem solid #4B2C20;
	}

	/* 호버 액션 (서브 컬러 제한적 적용) */
	.btn-list:hover {
		background-color: #5C6BC0;
		border-color: #5C6BC0;
	}

	.btn-del:hover {
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
		.btn-grp {
			flex-direction: column;
			width: 100%;
		}
		.btn-list, .btn-del {
			width: 100%; 
			margin-bottom: 0.5rem;
		}
	}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="dt-wrap">
	<span class="lb0">자재관리 상세정보</span> 

	<div class="dt-row">
		<span class="dt-lb">자재관리번호</span> 
		<span class="dt-val">${ materialDTO.material_num }</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">코드</span> 
		<span class="dt-val">${ materialDTO.code }</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">자재명</span> 
		<span class="dt-val">${ materialDTO.name }</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">자재량</span> 
		<span class="dt-val">${ materialDTO.area_quantity } ${ materialDTO.unit }</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">자재위치</span> 
		<span class="dt-val">${ materialDTO.wh_section }</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">층 구분</span> 
		<span class="dt-val">${ materialDTO.floor_level }</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">기준관리번호</span> 
		<span class="dt-val">${ materialDTO.mdm_num }</span>
	</div>
	
	<div class="dt-row">
		<span class="dt-lb">창고번호</span> 
		<span class="dt-val">${ materialDTO.warehouse_num }</span>
	</div>

	<div class="btn-grp">
		<a href="material?cmd=list" class="btn-list">목록으로</a>
		<a href="material?cmd=delete&material_num=${ materialDTO.material_num }" class="btn-del">삭제</a>
	</div>
</div>

<%@ include file="/footer.jsp" %>

<script>
	// 삭제 버튼 이벤트 (UX 향상)
	const btnDel = document.querySelector(".btn-del");
	
	if (btnDel) {
		btnDel.addEventListener('click', (e) => {
			if(confirm("정말로 해당 자재 정보를 삭제하시겠습니까?")) {
				// 확인을 누르면 정상적으로 a태그의 href로 이동 (삭제 로직 수행)
			} else {
				// 취소 시 기본 동작(이동) 막기
				e.preventDefault();
			}
		});
	}
</script>
</body>
</html>