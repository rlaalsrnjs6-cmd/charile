<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작업지시서 작성</title>
<style>
    /* 철칙 20: .mat-all 및 vh 설정으로 푸터 하단 고정 */
    * { box-sizing: border-box; margin: 0; padding: 0; }
    a { text-decoration: none; color: inherit; }
    
    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    /* 철칙 1, 4, 5: 간결한 클래스명, 반응형 단위, 메인 컬러(#4B2C20) */
    .reg-wrap {
        width: 90%;
        max-width: 800px;
        margin: 3rem auto 6rem auto; 
        background-color: #fff;
        border-top: 3rem solid #4B2C20;
        border-radius: 6px;
        box-shadow: 0 4px 20px rgba(0,0,0,0.15); 
        padding: 3rem;
        flex: 1; /* 콘텐츠 적어도 푸터 밀어냄 */
    }

    .reg-tit {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 2rem;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
    }

    .reg-row {
        display: flex;
        flex-direction: column;
        margin-bottom: 1.5rem;
    }

    .reg-lb {
        font-weight: 600;
        color: #4B2C20;
        margin-bottom: 0.5rem;
        font-size: 1rem;
    }

    .reg-in {
        width: 100%;
        padding: 0.8rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 1rem;
        transition: all 0.3s;
    }

    /* 철칙 6: 서브 컬러(#5C6BC0)는 포커스 시에만 제한적 사용 */
    .reg-in:focus {
        outline: none;
        border-color: #5C6BC0; 
        box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1); 
    }

    /* 철칙 21: 버튼 위치 및 디자인 참고 로직과 동일화 */
    .btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        margin-top: 3rem;
        border-top: 1px solid #eee;
        padding-top: 2rem;
    }

    .btn-sub, .btn-can {
        padding: 0.8rem 2.5rem;
        border-radius: 4px;
        font-size: 1rem;
        font-weight: 600;
        cursor: pointer;
        border: none;
        transition: all 0.2s;
    }

    .btn-sub { background-color: #4B2C20; color: #fff; }
    .btn-sub:hover { background-color: #5C6BC0; }
    
    .btn-can {
        background-color: #fff;
        color: #4B2C20;
        border: 1px solid #4B2C20;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .btn-can:hover {
        color: #5C6BC0;
        border-color: #5C6BC0;
        background-color: #f8f9ff;
    }

    /* 철칙 4: 반응형 미디어쿼리 */
    @media (max-width: 768px) {
        .reg-wrap { width: 95%; padding: 2rem 1.5rem; }
        .btn-grp { flex-direction: column-reverse; }
        .btn-sub, .btn-can { width: 100%; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp" %>

    <div class="reg-wrap">
        <div class="reg-tit">작업지시서 작성</div>

        <form id="orderForm" method="post" action="order">
            <input type="hidden" name="mod" value="add">
            
            <div class="reg-row">
                <label class="reg-lb">작업제목</label>
                <input type="text" name="work_order_title" class="reg-in" placeholder="제목을 입력하세요">
            </div>

            <div class="reg-row">
                <label class="reg-lb">생산품목</label>
                <select name="prod_num" class="reg-in">
                    <c:forEach var="p" items="${pm}">
                        <option value="${p.prod_num}">${p.title}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="reg-row">
                <label class="reg-lb">작업실시날짜 (시작일)</label>
                <input type="date" id="work_date" name="work_date" class="reg-in">
            </div>

            <div class="reg-row">
                <label class="reg-lb">일일목표수량</label>
                <input type="number" name="daily_target" class="reg-in" placeholder="수량을 입력하세요">
            </div>

            <div class="reg-row">
                <label class="reg-lb">담당 사원</label>
                <select name="empno" class="reg-in">
                    <c:forEach var="e" items="${emp}">
                        <option value="${e.empno}">${e.ename}(${e.empno})</option>
                    </c:forEach>
                </select>
            </div>

            <div class="reg-row">
                <label class="reg-lb">상태</label>
                <select name="status" class="reg-in">
                    <option value="Y">Y</option>
                    <option value="N">N</option>
                </select>
            </div>

            <div class="btn-grp">
                <a href="order?mod=list" class="btn-can" id="btnCancel">등록취소</a>
                <button type="submit" class="btn-sub">등록하기</button>
            </div>
        </form>
    </div>

    <%@ include file="/footer.jsp" %>
</div>

<script>
window.onload = function() {
    const orderForm = document.getElementById("orderForm");
    const btnCancel = document.getElementById("btnCancel");
    
    // 오늘 날짜 기본 설정
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('work_date').value = today;

    // 철칙 21: 취소 버튼 클릭 시 컨펌 창 (참고 로직 이식)
    btnCancel.addEventListener("click", function(e) {
        if(!confirm("작성 중인 내용이 저장되지 않습니다. 취소하시겠습니까?")) {
            e.preventDefault();
        }
    });

    // 철칙 14, 15: JS 로직 추가 및 날짜 방어
    orderForm.addEventListener("submit", function(e) {
        const title = document.querySelector("input[name='work_order_title']");
        const workDate = document.getElementById("work_date");
        const endDate = document.getElementById("end_date");
        const target = document.querySelector("input[name='daily_target']");

        if(!title.value.trim()) {
            alert("작업제목을 입력해주세요.");
            title.focus();
            e.preventDefault();
            return;
        }

        // 철칙 15: 시작일보다 종료일이 미래여야 함
        if(workDate.value && endDate.value) {
            if(workDate.value > endDate.value) {
                alert("작업완료기한은 실시날짜보다 이전일 수 없습니다.");
                endDate.focus();
                e.preventDefault();
                return;
            }
        }

        if(!target.value || target.value <= 0) {
            alert("목표 수량을 정확히 입력해주세요.");
            target.focus();
            e.preventDefault();
            return;
        }

        if(!confirm("작업지시서를 등록하시겠습니까?")) {
            e.preventDefault();
        }
    });
}
</script>
</body>
</html>