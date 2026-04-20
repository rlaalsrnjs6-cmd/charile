<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공정관리 목록</title>
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

    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    hr {
        display: none;
    }

    /* [2] 메인 컨테이너 (철칙 4, 5) */
    .proc-all {
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

    .proc-tb {
        width: 100%;
        border-collapse: collapse;
        text-align: center;
        border: 1px solid #eaeaea;
    }

    .proc-tb th {
        background-color: #4B2C20;
        color: #ffffff;
        padding: 1rem 0.5rem;
        font-size: 1.05rem;
        font-weight: 700;
    }

    /* 철칙 12, 17: td 글씨 굵기 및 컬러 참고 로직과 동일화 */
    .proc-tb td {
        padding: 1rem 0.5rem;
        border-bottom: 1px solid #eaeaea;
        font-weight: 700;
/*         color: #2c3e50; */
        font-size: 0.95rem;
    }

    /* 철칙 13: a태그 밑줄 색상 진하게 및 거리 동일화 */
    .proc-tb td a {
        text-decoration: underline;
        text-underline-position: under;
/*         text-decoration-color: #2c3e50; */
    }

    .proc-tb td a:hover {
        color: #5C6BC0;
        text-decoration-color: #5C6BC0;
    }

    .proc-tb tbody tr:nth-child(even) { background-color: #fcfcfc; }
    .proc-tb tbody tr:hover { background-color: #f1f3f5; }

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
        .proc-tb thead { display: none; }
        .proc-tb, .proc-tb tbody, .proc-tb tr, .proc-tb td { display: block; width: 100%; }
        .proc-tb tr { margin-bottom: 1rem; border: 1px solid #ddd; padding: 0.5rem; border-radius: 0.5rem; }
        .proc-tb td { display: flex; justify-content: space-between; text-align: right; border-bottom: 1px solid #eee; }
        .proc-tb td::before { content: attr(data-label); font-weight: bold; color: #4B2C20; text-align: left; }
        .btn-wr { width: 100%; text-align: center; float: none; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp" %>

    <div class="proc-all">
        <div class="ctrl-box">
            <form class="flt-fm" action="process?cmd=search" method="post">
                <select class="c-sel" name="selectName">
                    <option value="" ${empty param.selectName ? 'selected' : ''}> 전체보기 </option>
                    <c:forEach var="join" items="${ joinList }"> 
                        <option value="${join.name}" ${param.selectName == join.name ? 'selected' : ''}>${ join.name }</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn-main">공정검색</button>
            </form>
        </div>

        <div class="tb-wrap">
            <table class="proc-tb">
                <thead>
                    <tr>
                        <th>NAME</th>
                        <th>CODE</th>
                        <th>PROCESS</th>
                        <th>FLOW</th>
                        <th>MDM NUMBER</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${ map.list }">
                        <tr>
                            <td data-label="NAME">${ row.name }</td>
                            <td data-label="CODE">${ row.code }</td>
                            <td data-label="PROCESS">
                                <a href="process?cmd=detail&process_num=${ row.process_num }">
                                    ${ row.process_content }
                                </a>
                            </td>
                            <td data-label="FLOW">${ row.flow }</td>
                            <td data-label="MDM NUMBER">${ row.mdm_num }</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <a href="process?cmd=insertPage" class="btn-main btn-wr">등록하기</a>

        <jsp:include page="/WEB-INF/views/paging.jsp" />
    </div>

    <%@ include file="/footer.jsp" %>
</div>

<script>
    /* 철칙 14, 22: 검색 로직 유지 및 보완 */
    document.addEventListener('DOMContentLoaded', function() {
        // 셀렉트 박스 변경 시 검색창 비우기 등 철칙 22 관련 UX 로직 필요 시 추가
    });
</script>
</body>
</html>