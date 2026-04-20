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

    /* 20. 전체 레이아웃 (푸터 하단 고정 및 min-height vh) */
    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        background-color: #fcfcfc;
    }

    /* 4. 상세 컨테이너 (반응형 단위 rem, %, vh 사용) */
    .dt-wrap {
        width: 95%;
        max-width: 50rem;
        margin: 5vh auto;
        background-color: #fff;
        border: 1px solid #e0e0e0;
        border-top: 0.5rem solid #4B2C20; /* 5. 메인 컬러 */
        border-radius: 0.5rem;
        box-shadow: 0 4px 15px rgba(0,0,0,0.06);
        padding: 2.5rem;
        flex: 1;
    }

    /* 타이틀 레이아웃 */
    .lb0 {
        display: block;
        font-weight: 800;
        color: #212121;
        font-size: 1.5rem;
        margin-bottom: 2rem;
        padding-bottom: 1rem;
        border-bottom: 2px solid #4B2C20;
    }

    /* 3. 항목 행 스타일 (UI/UX 고려하여 행간 최적화) */
    .dt-row {
        display: flex;
        border-bottom: 1px solid #f2f2f2;
        padding: 1.2rem 0.5rem;
        align-items: center;
    }

    .dt-lb {
        width: 9rem;
        font-weight: 700;
        color: #4B2C20; /* 메인 컬러 */
        font-size: 0.95rem;
    }

    /* 23. 테이블 내부 폰트 블랙 유지 */
    .dt-val {
        flex: 1;
        color: #000;
        font-size: 1rem;
        font-weight: 700;
    }

    /* 이미지 배경 처리 로직 (1번 피드백 반영) */
    .img-view {
        width: 100%;
        max-width: 22rem;
        aspect-ratio: 16 / 9; /* 반응형 높이 조절 */
        background-color: #f9f9f9;
        background-image: url('${order[0].img_url}');
        background-size: cover;
        background-position: center;
        border-radius: 4px;
        border: 1px solid #ddd;
        margin-top: 0.5rem;
    }

    /* 21. 버튼 그룹 (위치 유지) */
    .btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 0.7rem;
        margin-top: 3rem;
        padding-top: 1.5rem;
        border-top: 1px solid #eee;
    }

    /* 1. 버튼 공통 (짧은 클래스명) */
    .btn-c {
        padding: 0.7rem 1.6rem;
        border-radius: 4px;
        font-size: 0.95rem;
        font-weight: 600;
        cursor: pointer;
        transition: 0.2s ease;
        text-align: center;
    }

    /* 5, 6, 19. 버튼 컬러 정책 (메인/서브 컬러 사용) */
    .b-back { background-color: #4B2C20; color: #fff; border: 1px solid #4B2C20; }
    .b-up { background-color: #5C6BC0; color: #fff; border: 1px solid #5C6BC0; }
    .b-del { background-color: #fff; color: #4B2C20; border: 1px solid #4B2C20; }

    .btn-c:hover { opacity: 0.85; transform: translateY(-1px); }

    /* 4. 모바일 반응형 처리 */
    @media (max-width: 768px) {
        .dt-wrap { padding: 1.5rem; }
        .dt-row { flex-direction: column; align-items: flex-start; }
        .dt-lb { margin-bottom: 0.5rem; width: 100%; }
        .btn-grp { flex-direction: column; }
        .btn-c { width: 100%; }
    }
</style>
</head>
<body>
    <div class="mat-all">
        <%-- 16. 헤더 포함 --%>
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
                        ${o.flow}${!st.last ? ' &gt; ' : ''}
                    </c:forEach>
                </div>
            </div>

            <div class="dt-row">
                <span class="dt-lb">공정내용</span>
                <div class="dt-val">
                    <c:forEach var="o" items="${order}">
                        <p style="margin-bottom: 0.3rem;">${o.process_content}</p>
                        <%-- 2. 불필요하게 반복되던 img_url 텍스트 제거 --%>
                    </c:forEach>
                </div>
            </div>

            <div class="dt-row" style="align-items: flex-start;">
                <span class="dt-lb">이미지</span>
                <div class="dt-val">
                    <%-- 이미지 경로를 백그라운드로 표시 --%>
                    <div class="img-view"></div>
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
                <span class="dt-val">${order[0].status}</span>
            </div>

            <%-- 버튼 영역 (19, 21. 돌아가기 버튼 및 위치 준수) --%>
            <div class="btn-grp">
                <a href="http://localhost:8080/charlie/order?mod=list" class="btn-c b-back">목록으로</a>
                <a href="http://localhost:8080/charlie/order?order_num=${map.list[0].order_num}&mod=up" class="btn-c b-up">수정</a>
                <a href="http://localhost:8080/charlie/order?order_num=${map.list[0].order_num}&mod=delete" class="btn-c b-del">삭제</a>
            </div>
        </div>

        <%-- 16. 푸터 포함 --%>
        <jsp:include page="/footer.jsp" />
    </div>

    <script>
        /* 14. 삭제 확인 로직 */
        const btnDel = document.querySelector(".b-del");
        if (btnDel) {
            btnDel.addEventListener('click', (e) => {
                if(!confirm("해당 작업지시서를 정말로 삭제하시겠습니까?")) {
                    e.preventDefault();
                }
            });
        }

        /* 15. 날짜 방어 로직 예시 (디테일 페이지 참조용) */
        const workDateStr = "${map.list[0].work_date}";
        if(workDateStr) {
            console.log("현재 작업지시 날짜: " + workDateStr);
        }
    </script>
</body>
</html>