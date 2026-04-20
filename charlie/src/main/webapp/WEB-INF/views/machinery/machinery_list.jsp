<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Machinery.MachineryDTO"%> 
<%@ page import="fileLibrary.CommonDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기계 관리 목록</title>
<style>
    /* [1] 기본 초기화 및 .mat-all 설정 (철칙 1, 2, 20) */
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }
    
    a {
        text-decoration: none;
        color: inherit;
    }

    hr {
        display: none;
    }

    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    /* [2] 메인 컨테이너 (철칙 4, 5) */
    .mach-all {
        width: 95vw;
        max-width: 1400px;
        margin: 3vh auto;
        flex: 1; /* 푸터 하단 고정 보조 */
    }

    /* [3] 상단 컨트롤 영역 (철칙 3, 5, 22) */
    .ctrl-box {
        display: flex;
        justify-content: flex-start;
        align-items: center;
        gap: 1vw;
        margin-bottom: 1.5vh;
        padding: 1rem;
        background-color: #f9f9f9;
        border-radius: 0.5rem;
        border-top: 3px solid #4B2C20;
    }

    .flt-fm {
        display: flex;
        align-items: center;
        gap: 0.5rem;
    }

    /* Select 및 버튼 스타일 (철칙 5, 6) */
    .c-sel {
        padding: 0.5rem;
        border: 1px solid #ddd;
        border-radius: 0.25rem;
        font-size: 1rem;
        height: 2.5rem;
        color: #333;
        min-width: 150px;
    }

    .btn-main {
        background-color: #4B2C20;
        color: #ffffff;
        border: none;
        height: 2.5rem;
        padding: 0 1.5rem;
        border-radius: 0.25rem;
        font-size: 1rem;
        font-weight: 700;
        cursor: pointer;
        transition: background-color 0.2s ease;
        display: inline-flex;
        align-items: center;
        justify-content: center;
    }

    .btn-main:hover {
        background-color: #5C6BC0;
    }

    .btn-wr {
        float: right;
        margin-top: 2vh;
    }

    /* [4] 테이블 스타일 (철칙 12, 13, 17) */
    .tb-wrap {
        width: 100%;
        overflow-x: auto;
    }

    .mach-tb {
        width: 100%;
        border-collapse: collapse;
        text-align: center;
        border: 1px solid #eaeaea;
    }

    .mach-tb th {
        background-color: #4B2C20;
        color: #ffffff;
        padding: 1rem 0.5rem;
        font-size: 1.05rem;
        font-weight: 700;
    }

    /* 철칙 12, 17: td 글씨 굵기 및 컬러 참고 로직과 동일화 */
    .mach-tb td {
        padding: 1rem 0.5rem;
        border-bottom: 1px solid #eaeaea;
        font-weight: 700;
text-underline-offset: 5px;
        font-size: 0.95rem;
    }

    /* 철칙 13: a태그 밑줄 색상 진하게 및 거리 동일화 */
    .mach-tb td a {
        text-decoration: underline;
        text-underline-position: under;
        text-decoration-color: #2c3e50;
    }

    .mach-tb td a:hover {
        color: #5C6BC0;
        text-decoration-color: #5C6BC0;
    }

    .mach-tb tbody tr:nth-child(even) { background-color: #fcfcfc; }
    .mach-tb tbody tr:hover { background-color: #f1f3f5; }

    /* [5] 페이징 스타일 (철칙 9) */
    .page-active {
        color: #ffffff !important;
        background-color: #4B2C20 !important;
        font-weight: 900 !important;
    }

    /* [6] 모바일 반응형 (철칙 4) */
    @media (max-width: 768px) {
        .ctrl-box { flex-direction: column; align-items: stretch; }
        .c-sel, .btn-main { width: 100%; }
        .mach-tb thead { display: none; }
        .mach-tb, .mach-tb tbody, .mach-tb tr, .mach-tb td { display: block; width: 100%; }
        .mach-tb tr { margin-bottom: 1rem; border: 1px solid #ddd; padding: 0.5rem; border-radius: 0.5rem; }
        .mach-tb td { display: flex; justify-content: space-between; text-align: right; border-bottom: 1px solid #eee; padding: 0.8rem 0.5rem; }
        .mach-tb td::before { content: attr(data-label); font-weight: bold; color: #4B2C20; text-align: left; }
        .btn-wr { width: 100%; text-align: center; float: none; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp" %>

    <div class="mach-all">
        <div class="ctrl-box">
            <form class="flt-fm" action="machinery?cmd=search" method="post">
                <select class="c-sel" name="selectName">
                    <option value="" ${empty param.selectName ? 'selected' : ''}> 전체보기 </option>
                    <c:forEach var="item" items="${ map.select1 }"> 
                        <option value="${item.name}" ${param.selectName == item.name ? 'selected' : ''}>${ item.name }</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn-main">장비검색</button>
            </form>
        </div>

        <div class="tb-wrap">
            <table class="mach-tb">
                <thead>
                    <tr>
                        <th>등록 번호</th>
                        <th>해당 장비</th>
                        <th>장비 타입</th>
                        <th>장비 상태</th>
                        <th>에러 내용</th>
                        <th>조치 내용</th>
                        <th>등록 시간</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${ map.list }">
                        <tr>
                            <td data-label="등록 번호">${ row.machinery_num }</td>
                            <td data-label="해당 장비">
                                <a href="machinery?cmd=detail&machinery_num=${ row.machinery_num }">
                                    ${ row.mdm_num } : ${ row.name }
                                </a>
                            </td>
                            <td data-label="장비 타입">${ row.machinery_type }</td>
                            <td data-label="장비 상태">${ row.machinery_status }</td>
                            <td data-label="에러 내용">${ row.error_sign }</td>
                            <td data-label="조치 내용">${ row.m_action }</td>
                            <td data-label="등록 시간">${ row.m_log_time }</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <a href="machinery?cmd=insertPage" class="btn-main btn-wr">등록하기</a>

        <jsp:include page="/WEB-INF/views/paging.jsp" />
    </div>

    <%@ include file="/footer.jsp" %>
</div>

<script>
    /* 철칙 14, 22: 검색 로직 유지 및 상태값 체크 */
    function validateSearch(form) {
        // 셀렉트 박스 변경 후 검색창 비우기 등 철칙 22 관련 로직 보완 시 사용
        return true; 
    }
</script>
</body>
</html>