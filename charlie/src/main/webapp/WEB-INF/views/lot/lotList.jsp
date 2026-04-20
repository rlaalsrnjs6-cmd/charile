<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOT 목록 관리</title>
<style>
    /* [철칙 1, 2, 20] 기본 초기화 및 .mat-all 설정 */
    * { box-sizing: border-box; margin: 0; padding: 0; }
    a { text-decoration: none; color: inherit; }
    
    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    /* [철칙 4, 5] 메인 컨테이너 및 메인 컬러(#4B2C20) */
    .lot-all {
        width: 95vw;
        max-width: 1400px;
        margin: 3vh auto;
        flex: 1;
    }

    /* [철칙 3, 21] 상단 컨트롤 영역 (참고 페이지 스타일 이식) */
    .ctrl-box {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        gap: 1vw;
        margin-bottom: 1.5vh;
        padding: 1rem;
        background-color: #f9f9f9;
        border-radius: 0.5rem;
        border-top: 3px solid #4B2C20;
    }

    .sch-fm {
        display: flex;
        align-items: center;
        gap: 0.5rem;
    }

    .c-sel, .c-in {
        padding: 0.5rem;
        border: 1px solid #ddd;
        border-radius: 0.25rem;
        font-size: 1rem;
        height: 2.5rem;
        color: #333;
    }

    .c-in { width: 18vw; min-width: 150px; }

    /* [철칙 6] 버튼 스타일 (서브 컬러 #5C6BC0 호버 적용) */
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

    .btn-main:hover { background-color: #5C6BC0; }

    /* [철칙 12, 13, 17] 테이블 스타일 (참고 페이지 로직 동일 적용) */
    .tb-wrap { width: 100%; overflow-x: auto; }
    .lot-tb {
        width: 100%;
        border-collapse: collapse;
        text-align: center;
        border: 1px solid #eaeaea;
    }

    .lot-tb th {
        background-color: #4B2C20;
        color: #ffffff;
        padding: 1rem 0.5rem;
        font-size: 1.05rem;
        font-weight: 700;
    }

    .lot-tb td {
        padding: 1rem 0.5rem;
        border-bottom: 1px solid #eaeaea;
        font-weight: 700; /* 철칙 12: 글씨 굵기 참고 */
        color: #2c3e50; /* 철칙 17: 텍스트 컬러 참고 */
        font-size: 0.95rem;
    }

    /* 철칙 13: a태그 밑줄 색상 진하게 수정 (#ddd -> #2c3e50) 및 간격 동일화 */
    .lot-tb td a {
        text-decoration: underline;
        text-underline-position: under; 
        text-decoration-color: #2c3e50; /* 연하게 하지 않고 텍스트 컬러와 맞춰 진하게 수정 */
    }

    .lot-tb td a:hover { 
        color: #5C6BC0; 
        text-decoration-color: #5C6BC0; 
    }
    
    .lot-tb tbody tr:hover { background-color: #f1f3f5; }

    /* [철칙 9] 페이징 스타일 */
    .page-active {
        color: #ffffff !important;
        background-color: #4B2C20 !important;
        font-weight: 900 !important;
    }

    /* [철칙 4] 모바일 반응형 */
    @media (max-width: 768px) {
        .ctrl-box { flex-direction: column; align-items: stretch; }
        .c-in { width: 100%; }
        .lot-tb thead { display: none; }
        .lot-tb, .lot-tb tbody, .lot-tb tr, .lot-tb td { display: block; width: 100%; }
        .lot-tb tr { margin-bottom: 1rem; border: 1px solid #ddd; padding: 0.5rem; border-radius: 0.5rem; }
        .lot-tb td { display: flex; justify-content: space-between; text-align: right; border-bottom: 1px solid #eee; }
        .lot-tb td::before { content: attr(data-label); font-weight: bold; color: #4B2C20; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp" %>

    <div class="lot-all">
        <div class="ctrl-box">
            <form class="sch-fm" action="lot?mod=search" method="post" onsubmit="return validateSearch(this)">
                <select class="c-sel" name="search_select" id="searchSelect">
                    <option value="lot_num" ${param.search_select == 'lot_num' ? 'selected' : ''}>LOT번호</option>
                    <option value="order_num" ${param.search_select == 'order_num' ? 'selected' : ''}>작업지시번호</option>
                </select>
                <input class="c-in" name="search_content" id="searchContent" placeholder="검색어를 입력하세요" value="${param.search_content}">
                <input class="btn-main" type="submit" value="검색">
            </form>
        </div>

        <div class="tb-wrap">
            <table class="lot-tb">
                <thead>
                    <tr>
                        <th>lot번호</th>
                        <th>lot당제품개수</th>
                        <th>작업지시번호</th>
                        <th>품질체크전후번호</th>
                        <th>자재관리번호</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="l" items="${map.list}">
                        <tr>
                            <td data-label="lot번호">${l.lot_num}</td>
                            <td data-label="lot당제품개수">
                                <a href="lot?lot_num=${l.lot_num}&mod=detail">${l.lot_count}</a>
                            </td>
                            <td data-label="작업지시번호">${l.order_num}</td>
                            <td data-label="품질체크전후번호">${l.qc_chk}</td>
                            <td data-label="자재관리번호">${l.material_num}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div style="margin-top: 2vh; text-align: right;">
            	<a class="btn-main" href="lot?mod=add">작성하기</a>
        </div>

        <jsp:include page="/WEB-INF/views/paging.jsp" />
    </div>

    <%@ include file="/footer.jsp" %>
</div>

<script>
    function validateSearch(form) {
        return true; 
    }
</script>
</body>
</html>