<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작업지시서 상세정보</title>
<style>
    /* 1. 기본 초기화 */
    * { box-sizing: border-box; margin: 0; padding: 0; }
    a { text-decoration: none; color: inherit; }

    /* 전체 레이아웃 */
    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        background-color: #fcfcfc;
    }

    /* 상세 컨테이너 */
    .dt-wrap {
        width: 95%;
        max-width: 50rem;
        margin: 5vh auto;
        background-color: #fff;
        border: 1px solid #e0e0e0;
        border-top: 0.5rem solid #4B2C20;
        border-radius: 0.5rem;
        box-shadow: 0 4px 15px rgba(0,0,0,0.06);
        padding: 2.5rem;
        flex: 1;
    }

    .lb0 {
        display: block;
        font-weight: 800;
        color: #212121;
        font-size: 1.5rem;
        margin-bottom: 2rem;
        padding-bottom: 1rem;
        border-bottom: 2px solid #4B2C20;
    }

    .dt-row {
        display: flex;
        border-bottom: 1px solid #f2f2f2;
        padding: 1.2rem 0.5rem;
        align-items: center;
    }

    .dt-lb {
        width: 9rem;
        font-weight: 700;
        color: #4B2C20;
        font-size: 0.95rem;
    }

    .dt-val {
        flex: 1;
        color: #000;
        font-size: 1rem;
        font-weight: 700;
    }

    /* 이미지 그리드 시스템 (9개 이미지 출력용) */
    .img-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(140px, 1fr));
        gap: 12px;
        width: 100%;
        margin-top: 0.5rem;
    }

    .img-item {
        width: 100%;
        aspect-ratio: 1 / 1;
        object-fit: cover;
        border-radius: 6px;
        border: 1px solid #ddd;
        background-color: #f9f9f9;
        transition: transform 0.2s;
    }

    .img-item:hover {
        transform: scale(1.05);
        z-index: 10;
    }

    /* 버튼 스타일 */
    .btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 0.7rem;
        margin-top: 3rem;
        padding-top: 1.5rem;
        border-top: 1px solid #eee;
    }

    .btn-c {
        padding: 0.7rem 1.6rem;
        border-radius: 4px;
        font-size: 0.95rem;
        font-weight: 600;
        cursor: pointer;
        transition: 0.2s ease;
        text-align: center;
    }

    .b-back { background-color: #4B2C20; color: #fff; border: 1px solid #4B2C20; }
    .b-up { background-color: #5C6BC0; color: #fff; border: 1px solid #5C6BC0; }
    .b-del { background-color: #fff; color: #4B2C20; border: 1px solid #4B2C20; }

    .btn-c:hover { opacity: 0.85; transform: translateY(-1px); }

    /* 반응형 */
    @media (max-width: 768px) {
        .dt-wrap { padding: 1.5rem; }
        .dt-row { flex-direction: column; align-items: flex-start; }
        .dt-lb { margin-bottom: 0.5rem; width: 100%; }
        .btn-grp { flex-direction: column; }
        .btn-c { width: 100%; }
        .img-grid { grid-template-columns: repeat(auto-fill, minmax(100px, 1fr)); }
    }
</style>
</head>
<body>
    <div class="mat-all">
        <jsp:include page="/header.jsp" />

        <div class="dt-wrap">
            <span class="lb0">작업지시 상세정보</span>

            <div class="dt-row">
                <span class="dt-lb">작업지시번호</span>
                <span class="dt-val">${order[0].order_num}</span>
            </div>

            <div class="dt-row">
                <span class="dt-lb">제목</span>
                <span class="dt-val">${order[0].work_order_title}</span>
            </div>

            <div class="dt-row">
                <span class="dt-lb">코드</span>
                <span class="dt-val">${order[0].code}</span>
            </div>

            <div class="dt-row">
                <span class="dt-lb">단위</span>
                <span class="dt-val">${order[0].unit}</span>
            </div>

            <div class="dt-row">
                <span class="dt-lb">만들양</span>
                <span class="dt-val">${order[0].required_weight}</span>
            </div>

            <div class="dt-row">
                <span class="dt-lb">과정</span>
                <div class="dt-val">
                    <c:forEach var="o" items="${order}" varStatus="st">
                        ${o.flow}<c:if test="${!st.last}"> &gt; </c:if>
                    </c:forEach>
                </div>
            </div>

            <div class="dt-row">
                <span class="dt-lb">공정내용</span>
                <div class="dt-val">
                    <c:forEach var="o" items="${order}">
                        <p style="margin-bottom: 0.3rem;">• ${o.process_content}</p>
                    </c:forEach>
                </div>
            </div>

            <div class="dt-row" style="align-items: flex-start; border-bottom: none;">
                <span class="dt-lb">작업 이미지</span>
                <div class="dt-val">
                    <div class="img-grid">
                        <c:forEach var="o" items="${order}">
                            <c:if test="${not empty o.img_url}">
                                <img src="${o.img_url}" class="img-item" alt="공정 이미지" onerror="this.src='https://placehold.co/150x150?text=No+Image'">
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="dt-row">
                <span class="dt-lb">작업실시날짜</span>
                <span class="dt-val">${order[0].work_date}</span>
            </div>

            <div class="dt-row">
                <span class="dt-lb">목표수량</span>
                <span class="dt-val">${order[0].daily_target}</span>
            </div>

            <div class="dt-row">
                <span class="dt-lb">사원번호</span>
                <span class="dt-val">${order[0].empno}</span>
            </div>

            <div class="dt-row">
                <span class="dt-lb">상태</span>
                <span class="dt-val" style="color: #5C6BC0;">${order[0].status}</span>
            </div>

            <div class="btn-grp">
                <a href="http://localhost:8080/charlie/order?mod=list" class="btn-c b-back">목록으로</a>
                <%-- map.list[0]가 비어있을 수 있으므로 order[0] 기준으로 링크 설정 --%>
                <a href="http://localhost:8080/charlie/order?order_num=${order[0].order_num}&mod=up" class="btn-c b-up">수정</a>
                <a href="http://localhost:8080/charlie/order?order_num=${order[0].order_num}&mod=delete" class="btn-c b-del">삭제</a>
            </div>
        </div>

        <jsp:include page="/footer.jsp" />
    </div>

    <script>
        const btnDel = document.querySelector(".b-del");
        if (btnDel) {
            btnDel.addEventListener('click', (e) => {
                if(!confirm("해당 작업지시서를 정말로 삭제하시겠습니까?")) {
                    e.preventDefault();
                }
            });
        }
    </script>
</body>
</html>