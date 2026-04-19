<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Warehouse.WarehouseDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고 상세 정보</title>
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

    /* 철칙 8: 기존 hr 숨김 처리 */
    hr {
        display: none;
    }

    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    /* [2] 상세 컨테이너 (철칙 1, 4, 5) */
    .dt-wrap {
        width: 90%;
        max-width: 800px;
        margin: 5vh auto;
        background-color: #fff;
        border-top: 3rem solid #4B2C20; /* 메인 컬러 포인트 */
        border-radius: 4px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        padding: 2.5rem;
        flex: 1; /* 콘텐츠 적어도 푸터 하단 고정 */
    }

    /* [3] 타이틀 및 항목 스타일 (참고 로직 선 색상 일치) */
    .lb0 {
        display: block;
        font-weight: bold;
        color: #333;
        font-size: 1.8rem;
        margin-bottom: 1.5rem;
        border-bottom: 2px solid #eee; /* 참고 로직 구분선 */
        padding-bottom: 1rem;
    }

    .dt-row {
        display: flex;
        border-bottom: 1px solid #eee; /* 참고 로직 구분선 동일화 */
        padding: 1.2rem 0;
        align-items: center;
    }

    /* 항목 라벨 (메인 컬러) */
    .dt-lb {
        width: 10rem;
        font-weight: bold;
        color: #4B2C20; 
        font-size: 1.05rem;
    }

    /* 항목 값 (철칙 12, 17, 23 준수: 굵기 700 및 블랙 컬러 고정) */
    .dt-val {
        flex: 1;
        color: #000; /* 철칙 23: 폰트 컬러 블랙 유지 */
        font-size: 1rem;
        font-weight: 700; /* 철칙 12: 굵기 참고 */
    }

    /* [4] 버튼 그룹 영역 (철칙 21: 위치 동일화) */
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
    .btn-list, .btn-up, .btn-del {
        display: inline-block;
        text-align: center;
        padding: 0.6rem 1.5rem;
        border-radius: 4px;
        font-size: 1rem;
        font-weight: 600;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    /* 목록/수정 버튼 (메인 컬러 기반) */
    .btn-list, .btn-up {
        background-color: #4B2C20;
        color: #fff;
        border: 1px solid #4B2C20;
    }

    /* 삭제 버튼 (서브 동작) */
    .btn-del {
        background-color: #fff;
        color: #4B2C20;
        border: 1px solid #4B2C20;
    }

    /* 호버 액션 (철칙 6: 서브 컬러 제한적 적용) */
    .btn-list:hover, .btn-up:hover {
        background-color: #5C6BC0;
        border-color: #5C6BC0;
    }

    .btn-del:hover {
        color: #5C6BC0;
        border-color: #5C6BC0;
        background-color: #f4f6fa;
    }

    /* [5] 모바일 반응형 대응 (철칙 4) */
    @media (max-width: 768px) {
        .dt-wrap { width: 95%; padding: 1.5rem; }
        .dt-row { flex-direction: column; align-items: flex-start; }
        .dt-lb { margin-bottom: 0.5rem; color: #888; font-size: 0.9rem; }
        .btn-grp { flex-direction: column; width: 100%; }
        .btn-list, .btn-up, .btn-del { width: 100%; margin-bottom: 0.5rem; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp" %>

    <div class="dt-wrap">
        <span class="lb0">창고 상세 정보</span> 

        <div class="dt-row">
            <span class="dt-lb">창고 관리 번호</span> 
            <span class="dt-val">${ warehouseDTO.warehouse_num }</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">창고 섹션</span> 
            <span class="dt-val">${ warehouseDTO.wh_section }</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">층 구분</span> 
            <span class="dt-val">${ warehouseDTO.floor_level }F</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">현재 온도</span> 
            <span class="dt-val">${ warehouseDTO.temperature }°C</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">현재 습도</span> 
            <span class="dt-val">${ warehouseDTO.humidity }%</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">확인 상태</span> 
            <span class="dt-val">${ warehouseDTO.wh_status_chk }</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">최종 확인 일자</span> 
            <span class="dt-val">${ warehouseDTO.wh_chk_date }</span>
        </div>

        <div class="btn-grp">
            <a href="warehouse?cmd=list" class="btn-list">목록으로</a>
            <a href="warehouse?cmd=modify&warehouse_num=${ warehouseDTO.warehouse_num }" class="btn-up">수정</a>
            <a href="warehouse?cmd=delete&warehouse_num=${ warehouseDTO.warehouse_num }" class="btn-del">삭제</a>
        </div>
    </div>

    <%@ include file="/footer.jsp" %>
</div>

<script>
    /* 철칙 14: 삭제 컨펌 로직 (참고 로직 이식) */
    const btnDel = document.querySelector(".btn-del");
    
    if (btnDel) {
        btnDel.addEventListener('click', (e) => {
            if(!confirm("정말로 해당 창고 기록을 삭제하시겠습니까?")) {
                e.preventDefault();
            }
        });
    }
</script>
</body>
</html>