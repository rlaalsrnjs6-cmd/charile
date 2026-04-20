<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공정 상세 정보</title>
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

    hr {
        display: none;
    }

    /* [2] 상세 컨테이너 (철칙 1, 4, 5) */
    .dt-wrap {
        width: 90%;
        max-width: 900px;
        margin: 5vh auto;
        background-color: #fff;
        border-top: 3rem solid #4B2C20; /* 메인 컬러 */
        border-radius: 4px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        padding: 2.5rem;
        flex: 1; /* 콘텐츠가 적어도 푸터 하단 고정 */
    }

    /* [3] 타이틀 및 항목 스타일 (참고 로직 선 색상 및 텍스트 컬러 100% 일치) */
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
        border-bottom: 1px solid #eee; /* 참고 로직 td 구분선 동일화 */
        padding: 1.2rem 0;
        align-items: center;
    }

    .dt-lb {
        width: 10rem;
        font-weight: bold;
        color: #4B2C20; 
        font-size: 1.05rem;
    }

    /* 철칙 12, 17: 참고 로직의 td와 동일한 컬러(#333) 및 굵기(700) 적용 */
    .dt-val {
        flex: 1;
        color: #333; 
        font-size: 1rem;
        font-weight: 700;
    }

    /* 이미지 영역 (반응형 고려) */
    .process-img {
        width: 100%;
        max-width: 600px;
        height: 400px;
        background-size: contain;
        background-repeat: no-repeat;
        background-position: left center;
        border: 1px solid #eee;
        border-radius: 4px;
    }

    /* [4] 버튼 그룹 (철칙 21: 위치 및 스타일 동일화) */
    .btn-grp {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        gap: 0.8rem;
        margin-top: 2rem;
        border-top: 1px solid #ddd;
        padding-top: 1.5rem;
    }

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

    .btn-list, .btn-up {
        background-color: #4B2C20;
        color: #fff;
        border: 1px solid #4B2C20;
    }

    .btn-del {
        background-color: #fff;
        color: #4B2C20;
        border: 1px solid #4B2C20;
    }

    .btn-list:hover, .btn-up:hover {
        background-color: #5C6BC0;
        border-color: #5C6BC0;
    }

    .btn-del:hover {
        color: #5C6BC0;
        border-color: #5C6BC0;
        background-color: #f4f6fa;
    }

    /* [5] 모바일 반응형 (철칙 4) */
    @media (max-width: 768px) {
        .dt-wrap { width: 95%; padding: 1.5rem; }
        .dt-row { flex-direction: column; align-items: flex-start; }
        .dt-lb { margin-bottom: 0.5rem; color: #888; font-size: 0.9rem; }
        .process-img { height: 250px; }
        .btn-grp { flex-direction: column; width: 100%; }
        .btn-list, .btn-up, .btn-del { width: 100%; margin-bottom: 0.5rem; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp" %>

    <div class="dt-wrap">
        <span class="lb0">공정 상세 정보</span> 

        <div class="dt-row">
            <span class="dt-lb">공정 번호</span> 
            <span class="dt-val">${ processDTO.process_num }</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">공정 명칭</span> 
            <span class="dt-val">${ processDTO.name }</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">공정 코드</span> 
            <span class="dt-val">${ processDTO.code }</span>
        </div>

        <div class="dt-row">
            <span class="dt-lb">공정 이미지</span> 
            <div class="dt-val">
                <div class="process-img" style="background-image : url('${ processDTO.img_url }')"></div> 
            </div>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">공정 내용</span> 
            <span class="dt-val">${ processDTO.process_content }</span>
        </div>
        
        <div class="dt-row">
            <span class="dt-lb">공정 흐름(Flow)</span> 
            <span class="dt-val">${ processDTO.flow }</span>
        </div>

        <div class="dt-row">
            <span class="dt-lb">기준정보 번호</span> 
            <span class="dt-val">${ processDTO.mdm_num }</span>
        </div>

        <div class="btn-grp">
            <a href="process?cmd=list" class="btn-list">목록으로</a>
            <a href="process?cmd=modify&process_num=${ processDTO.process_num }" class="btn-up">수정</a>
            <a href="process?cmd=delete&process_num=${ processDTO.process_num }" class="btn-del">삭제</a>
        </div>
    </div>

    <%@ include file="/footer.jsp" %>
</div>

<script>
    /* 철칙 14: 삭제 컨펌 로직 추가 */
    const btnDel = document.querySelector(".btn-del");
    
    if (btnDel) {
        btnDel.addEventListener('click', (e) => {
            if(!confirm("정말로 해당 공정 정보를 삭제하시겠습니까?")) {
                e.preventDefault();
            }
        });
    }
</script>
</body>
</html>