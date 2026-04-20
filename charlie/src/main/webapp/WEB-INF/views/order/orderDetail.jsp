<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작업지시서 상세정보</title>
<style>
    /* 기본 초기화 */
    * { box-sizing: border-box; margin: 0; padding: 0; }
    a { text-decoration: none; color: inherit; }

    /* 전체 레이아웃 (20번 규칙: 푸터 하단 고정) */
    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        background-color: #f8f9fa;
    }

    /* 상세 컨테이너 (4번 규칙: 반응형 단위 사용) */
    .dt-wrap {
        width: 90%;
        max-width: 900px;
        margin: 5vh auto;
        background-color: #fff;
        border-top: 0.5rem solid #4B2C20; /* 5번 규칙: 메인 컬러 */
        border-radius: 8px;
        box-shadow: 0 10px 25px rgba(0,0,0,0.05);
        padding: 3rem;
        flex: 1;
    }

    /* 메인 타이틀 */
    .lb0 {
        display: block;
        font-weight: bold;
        color: #333;
        font-size: 1.6rem;
        margin-bottom: 2rem;
        border-bottom: 2px solid #4B2C20;
        padding-bottom: 0.8rem;
    }

    /* 항목 행 스타일 (12번 규칙: 텍스트 굵기 등 참고) */
    .dt-row {
        display: flex;
        border-bottom: 1px solid #eee;
        padding: 1rem 0.5rem;
        align-items: center;
    }

    .dt-lb {
        width: 10rem;
        font-weight: bold;
        color: #4B2C20;
        font-size: 1.05rem;
    }

    .dt-val {
        flex: 1;
        color: #333;
        font-size: 1rem;
        font-weight: 700; /* 참고 로직의 굵기 반영 */
    }

    /* 버튼 그룹 (21번 규칙: 버튼 위치 동일) */
    .btn-grp {
        display: flex;
        justify-content: flex-end;
        align-items: center;
        gap: 0.8rem;
        margin-top: 2.5rem;
        border-top: 1px solid #ddd;
        padding-top: 1.5rem;
    }

    /* 버튼 공통 */
    .btn-common {
        display: inline-block;
        padding: 0.7rem 1.8rem;
        border-radius: 4px;
        font-size: 1rem;
        font-weight: 600;
        cursor: pointer;
        transition: 0.2s ease-in-out;
    }

    /* 19번 규칙: 돌아가기 버튼 및 5번 규칙: 메인 컬러 */
    .btn-back, .btn-up {
        background-color: #4B2C20;
        color: #fff;
        border: 1px solid #4B2C20;
    }

    .btn-del {
        background-color: #fff;
        color: #4B2C20;
        border: 1px solid #4B2C20;
    }

    /* 6번 규칙: 서브 컬러 제한적 사용 (호버) */
    .btn-common:hover {
        background-color: #5C6BC0;
        border-color: #5C6BC0;
        color: #fff;
    }

    /* 모바일 반응형 */
    @media (max-width: 768px) {
        .dt-wrap { width: 95%; padding: 1.5rem; }
        .dt-row { flex-direction: column; align-items: flex-start; }
        .dt-lb { margin-bottom: 0.3rem; font-size: 0.9rem; }
        .btn-grp { flex-direction: column; }
        .btn-common { width: 100%; text-align: center; }
    }
</style>
</head>
<body>
    <div class="mat-all">
        <%-- 16번 규칙: 헤더/푸터 로직 포함 --%>
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
                <c:forEach var="o" items="${order}">
                	<span class="dt-val">${o.flow}</span>
                </c:forEach>
            </div>
          
            <div class="dt-row">
                <span class="dt-lb">공정내용</span>
                <c:forEach var="o" items="${order}">
                <span class="dt-val">${o.process_content}</span>
                 <span class="dt-val">${order[0].img_url}</span>
                </c:forEach>
            </div>
          
            <div class="dt-row">
                <span class="dt-lb">이미지</span>
                <span class="dt-val">${order[0].img_url}</span>
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

            <%-- 버튼 영역 --%>
            <div class="btn-grp">
                <%-- 19번 규칙: 목록 돌아가기 버튼 추가 --%>
                <a href="http://localhost:8080/charlie/order?mod=list" class="btn-common btn-back">목록으로</a>
                <a href="http://localhost:8080/charlie/order?order_num=${map.list[0].order_num}&mod=up" class="btn-common btn-up">수정</a>
                <a href="http://localhost:8080/charlie/order?order_num=${map.list[0].order_num}&mod=delete" class="btn-common btn-del">삭제</a>
            </div>
        </div>

        <jsp:include page="/footer.jsp" />
    </div>

    <script>
        /* 14번 규칙: 참고 페이지 JS 로직 반영 (삭제 확인) */
        const btnDel = document.querySelector(".btn-del");
        if (btnDel) {
            btnDel.addEventListener('click', (e) => {
                if(!confirm("해당 작업지시서를 정말로 삭제하시겠습니까?")) {
                    e.preventDefault();
                }
            });
        }

        /* 15번 규칙: 날짜 방어 로직 (디테일 페이지이나, 날짜 데이터 비교가 필요한 상황 대비 예시) */
        // 상세 페이지에서 바로 날짜를 수정하지는 않지만, 만약 비교 로직이 필요하다면 아래와 같이 처리 가능합니다.
        const workDate = "${map.list[0].work_date}";
        // 필요한 경우 추가적인 날짜 비교 로직 구현 가능
    </script>
</body>
</html>