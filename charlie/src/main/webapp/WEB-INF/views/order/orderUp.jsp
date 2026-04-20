<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작업지시서 수정</title>
<style>
    /* 기본 초기화 */
    * { box-sizing: border-box; margin: 0; padding: 0; }
    
    /* 20번 규칙: 푸터 하단 고정 */
    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        background-color: #f8f9fa;
    }

    /* 컨테이너 (4번 규칙: 반응형 단위 사용) */
    .mod-scope {
        width: 100%;
        padding: 2rem 0 5rem 0;
        flex: 1;
    }

    .mod-wrap {
        width: 90%;
        max-width: 800px;
        margin: 0 auto;
        background-color: #fff;
        border-top: 3rem solid #4B2C20; /* 5번 규칙: 메인 컬러 */
        border-radius: 6px;
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
        padding: 3rem;
    }

    /* 타이틀 */
    .mod-tit {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 2rem;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
    }

    /* 행 레이아웃 */
    .mod-row {
        display: flex;
        flex-direction: column;
        margin-bottom: 1.5rem;
    }

    .mod-lb {
        font-weight: 600;
        color: #4B2C20;
        margin-bottom: 0.6rem;
        font-size: 1.05rem;
    }

    /* 읽기 전용 텍스트 스타일 (12번 규칙: 굵기 반영) */
    .mod-txt {
        font-size: 1rem;
        color: #555;
        padding: 0.8rem 1rem;
        background-color: #f9f9f9;
        border-radius: 4px;
        border: 1px solid #eee;
        font-weight: 700;
    }

    /* 입력 폼 */
    .mod-in {
        width: 100%;
        padding: 0.8rem 1rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 1rem;
        transition: all 0.3s;
    }

    .mod-in:focus {
        outline: none;
        border-color: #5C6BC0; /* 6번 규칙: 서브 컬러 */
        box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
    }

    /* 버튼 그룹 */
    .mod-btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        margin-top: 3rem;
        border-top: 1px solid #eee;
        padding-top: 2rem;
    }

    .mod-btn {
        padding: 0.8rem 2.5rem;
        border-radius: 4px;
        font-size: 1rem;
        font-weight: 600;
        cursor: pointer;
        border: none;
        transition: all 0.2s;
        text-decoration: none;
        display: inline-block;
        text-align: center;
    }

    .btn-sub {
        background-color: #4B2C20;
        color: #fff;
    }

    .btn-can {
        background-color: #fff;
        color: #4B2C20;
        border: 1px solid #4B2C20;
    }

    .mod-btn:hover {
        background-color: #5C6BC0;
        border-color: #5C6BC0;
        color: #fff;
    }

    /* 반응형 */
    @media (max-width: 768px) {
        .mod-wrap { width: 95%; padding: 2rem 1.5rem; }
        .mod-btn-grp { flex-direction: column-reverse; }
        .mod-btn { width: 100%; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%-- 16번 규칙: 헤더 포함 --%>
    <%@ include file="/header.jsp"%>

    <div class="mod-scope">
        <div class="mod-wrap">
            <div class="mod-tit">작업지시서 수정</div>

            <form id="modForm" method="post" action="order">
                <input type="hidden" name="mod" value="up">
                <input type="hidden" name="order_num" value="${map.list[0].order_num}">
                <input type="hidden" name="work_date" value="${map.list[0].work_date}">

                <div class="mod-row">
                    <span class="mod-lb">작업지시번호</span>
                    <span class="mod-txt">${map.list[0].order_num}</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">제목</span>
                    <input type="text" name="work_order_title" value="${map.list[0].work_order_title}" class="mod-in">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">작업시작일자</span>
                    <span class="mod-txt">${map.list[0].work_date}</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">일일목표수량</span>
                    <input type="text" name="daily_target" value="${map.list[0].daily_target}" class="mod-in">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">담당 사원</span>
                    <select name="empno" class="mod-in">
                        <c:forEach var="e" items="${emp}">
                            <option value="${e.empno}" ${map.list[0].empno == e.empno ? 'selected' : ''}>
                                사원이름:${e.ename}, 사원번호:${e.empno}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">상태</span>
                    <select name="status" class="mod-in">
                        <option value="Y" ${map.list[0].status == 'Y' ? 'selected' : ''}>사용(Y)</option>
                        <option value="N" ${map.list[0].status == 'N' ? 'selected' : ''}>미사용(N)</option>
                    </select>
                </div>

                <div class="mod-btn-grp">
                    <%-- 취소 버튼 클래스 유지 --%>
                    <button type="button" class="mod-btn btn-can">취소</button>
                    <button type="submit" class="mod-btn btn-sub">수정하기</button>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="/footer.jsp"%>
</div>

<script>
    /* 14번 규칙: 참고 로직의 JS 로직(취소 확인 및 서브밋 검증) 완벽 반영 */
    const modForm = document.getElementById("modForm");
    const btn_can = document.querySelector(".btn-can");

    // 수정 제출 시 검증 로직
    modForm.addEventListener('submit', (e) => {
        const title_el = document.querySelector("input[name='work_order_title']");
        const target_el = document.querySelector("input[name='daily_target']");
        
        if (title_el.value.trim() === "") {
            alert("제목을 입력하세요.");
            e.preventDefault();
            title_el.focus();
            return;
        }
        
        if (target_el.value.trim() === "" || isNaN(target_el.value)) {
            alert("목표 수량을 올바른 숫자로 입력하세요.");
            e.preventDefault();
            target_el.focus();
            return;
        }

        if (confirm("해당 내용으로 작업지시서를 수정하시겠습니까?")) {
            alert("수정이 완료되었습니다.");
        } else {
            e.preventDefault();
        }
    });

    // 취소 버튼 클릭 시 로직 (참고 로직과 동일하게 수정)
    btn_can.addEventListener('click', () => {
        if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
            alert("취소되었습니다.");
            // 19번 규칙에 의거, 이전 목록(또는 상세) 페이지로 이동
            location.href = "order?order_num=${map.list[0].order_num}&mod=detail"; 
            // 또는 단순 뒤로가기를 원하시면 history.back(); 사용 가능합니다.
        }
    });
</script>
</body>
</html>