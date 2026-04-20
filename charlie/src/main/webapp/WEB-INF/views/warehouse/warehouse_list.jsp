<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Warehouse.WarehouseDTO"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고 관리 목록</title>
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
    .wh-all {
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
        flex-wrap: wrap;
        gap: 1vw;
        margin-bottom: 1.5vh;
        padding: 1rem;
        background-color: #f9f9f9;
        border-radius: 0.5rem;
        border-top: 3px solid #4B2C20; /* 메인 컬러 */
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
        background-color: #5C6BC0; /* 서브 컬러 */
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

    .wh-tb {
        width: 100%;
        border-collapse: collapse;
        text-align: center;
        border: 1px solid #eaeaea;
    }

    .wh-tb th {
        background-color: #4B2C20;
        color: #ffffff;
        padding: 1rem 0.5rem;
        font-size: 1.05rem;
        font-weight: 700;
    }

    /* 철칙 12, 17: td 글씨 스타일 참고 로직 동일화 */
    .wh-tb td {
        padding: 1rem 0.5rem;
        border-bottom: 1px solid #eaeaea;
        font-weight: 700;
/*         color: #2c3e50; */
        font-size: 0.95rem;
    }

    /* 철칙 13: a태그 밑줄 및 오프셋 동일화 */
    .wh-tb td a {
        text-decoration: underline;
        text-underline-offset: 5px; /* 참고 로직 오프셋 이식 */
    }

    .wh-tb td a:hover {
        color: #5C6BC0;
    }

    .wh-tb tbody tr:nth-child(even) { background-color: #fcfcfc; }
    .wh-tb tbody tr:hover { background-color: #f1f3f5; }

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
        .wh-tb thead { display: none; }
        .wh-tb, .wh-tb tbody, .wh-tb tr, .wh-tb td { display: block; width: 100%; }
        .wh-tb tr { margin-bottom: 1rem; border: 1px solid #ddd; padding: 0.5rem; border-radius: 0.5rem; }
        .wh-tb td { display: flex; justify-content: space-between; text-align: right; border-bottom: 1px solid #eee; padding: 0.8rem 0.5rem; }
        .wh-tb td::before { content: attr(data-label); font-weight: bold; color: #4B2C20; text-align: left; }
        .btn-wr { width: 100%; text-align: center; float: none; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp" %>

    <div class="wh-all">
        <div class="ctrl-box">
            <form class="flt-fm" action="warehouse?cmd=search" method="post">
                <select class="c-sel" name="selectName">
                    <option value="" ${empty param.selectName ? 'selected' : ''}> 전체보기 </option>
                    <c:forEach var="item" items="${ map.select1 }"> 
                        <option value="${item.wh_section}" ${param.selectName == item.wh_section ? 'selected' : ''}>${ item.wh_section }</option>
                    </c:forEach>
                </select>
                
                <select class="c-sel" name="selectChk">
                    <option value="" ${empty param.selectChk ? 'selected' : ''}> 확인상태 </option>
                    <c:forEach var="item" items="${ map.select2 }">  
                        <option value="${item.wh_status_chk}" ${param.selectChk == item.wh_status_chk ? 'selected' : ''}>${ item.wh_status_chk }</option>
                    </c:forEach>
                </select>
                
                <button type="submit" class="btn-main">분류검색</button>
            </form>
        </div>

        <div class="tb-wrap">
            <table class="wh-tb">
                <thead>
                    <tr>
                        <th>확인날짜</th>
                        <th>섹션</th>
                        <th>온도</th>
                        <th>습도</th>
                        <th>층구분</th>
                        <th>확인상태</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${ map.list }">
                        <tr>
                            <td data-label="확인날짜">${ row.wh_chk_date }</td>
                            <td data-label="섹션">
                                <a href="warehouse?cmd=detail&warehouse_num=${ row.warehouse_num }">
                                    ${ row.wh_section }
                                </a>
                            </td>
                            <td data-label="온도">${ row.temperature }°C</td>
                            <td data-label="습도">${ row.humidity }%</td>
                            <td data-label="층구분">${ row.floor_level }F</td>
                            <td data-label="확인상태">${ row.wh_status_chk }</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <a href="warehouse?cmd=insertPage" class="btn-main btn-wr">등록하기</a>

        <jsp:include page="/WEB-INF/views/paging.jsp" />
    </div>

    <%@ include file="/footer.jsp" %>
</div>

<script>
    /* 철칙 14, 22: 검색 상태 유지 및 전체조회 로직 */
    document.addEventListener('DOMContentLoaded', function() {
        // 필요 시 정규표현식 등 추가 로직 작성
    });
</script>
</body>
</html>