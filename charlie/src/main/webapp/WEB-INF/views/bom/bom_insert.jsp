<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Bom.BomDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOM 등록</title>
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

    /* 철칙 20: 푸터 하단 고정 */
    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    /* 철칙 8: 기존 hr 숨김 처리 */
    hr {
        display: none;
    }

    /* [2] 폼 컨테이너 (철칙 1, 4, 5) */
    .reg-wrap {
        width: 90%;
        max-width: 700px;
        margin: 3rem auto 6rem auto; 
        background-color: #fff;
        border-top: 3rem solid #4B2C20; /* 메인 컬러 */
        border-radius: 6px;
        box-shadow: 0 4px 20px rgba(0,0,0,0.15); 
        padding: 3rem;
        flex: 1; /* 콘텐츠 적어도 푸터 하단 고정 보조 */
    }

    /* 타이틀 */
    .reg-tit {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 2rem;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
    }

    /* 입력 행 */
    .reg-row {
        display: flex;
        flex-direction: column;
        margin-bottom: 1.5rem;
    }

    /* 라벨 (메인 컬러 및 철칙 12 굵기 참고) */
    .reg-lb {
        font-weight: 600;
        color: #4B2C20;
        margin-bottom: 0.5rem;
        font-size: 1rem;
    }

    /* 인풋 및 셀렉트 (철칙 23: 폰트 블랙 유지) */
    .reg-in {
        width: 100%;
        padding: 0.8rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 1rem;
        font-family: inherit; 
        color: #000; /* 철칙 23 */
        transition: border-color 0.3s, box-shadow 0.3s;
    }

    /* 철칙 6: 포커스 시 서브 컬러 반응 */
    .reg-in:focus {
        outline: none;
        border-color: #5C6BC0; 
        box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.1); 
    }

    /* [3] 버튼 그룹 (철칙 21: 위치 동일화) */
    .btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        margin-top: 3rem;
        border-top: 1px solid #eee;
        padding-top: 2rem;
    }

    /* 버튼 공통 */
    .btn-sub, .btn-can {
        padding: 0.8rem 2.5rem;
        border-radius: 4px;
        font-size: 1rem;
        font-weight: 600;
        text-align: center;
        cursor: pointer;
        border: none;
        transition: all 0.2s;
    }

    /* 등록 버튼 (메인 컬러) */
    .btn-sub {
        background-color: #4B2C20;
        color: #fff;
    }

    .btn-sub:hover {
        background-color: #5C6BC0;
    }

    /* 취소 버튼 (테두리 스타일) */
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

    /* 철칙 4: 태블릿/모바일 반응형 */
    @media (max-width: 768px) {
        .reg-wrap { width: 95%; padding: 2rem 1.5rem; }
        .btn-grp { flex-direction: column-reverse; gap: 0.8rem; }
        .btn-sub, .btn-can { width: 100%; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp" %>

    <div class="reg-wrap">
        <div class="reg-tit">BOM 등록</div>

        <form id="bomForm" method="post" action="bom">
            <input type="hidden" name="cmd" value="insert">
            
            <div class="reg-row">
                <label class="reg-lb">기준 제품 (Target Product)</label>
                <select name="target_mdm_num" class="reg-in">
                    <c:forEach var="item" items="${ list }">
                        <c:if test="${ item.type eq 'product' }">
                            <option value="${ item.mdm_num }"> ${ item.name } </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="reg-row">
                <label class="reg-lb">요구 재재료 (Required Material)</label>
                <select name="mdm_num" class="reg-in">
                    <c:forEach var="item" items="${ list }">
                        <c:if test="${ item.type eq 'assemble' or item.type eq 'material' }">
                            <option value="${ item.mdm_num }">[ ${ item.code } ] : ${ item.name }</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="reg-row">
                <label class="reg-lb">요구량 (Required Weight)</label>
                <input type="text" name="required_weight" class="reg-in" value="20000" inputmode="decimal">
            </div>

            <div class="btn-grp">
                <a href="bom?cmd=list" class="btn-can">등록취소</a>
                <button type="submit" class="btn-sub">등록하기</button>
            </div>
            
        </form>
    </div>

    <%@ include file="/footer.jsp" %>
</div>

<script>
    window.onload = function() {
        const bomForm = document.getElementById("bomForm");
        const btnCan = document.querySelector(".btn-can");
        const weightIn = document.querySelector("input[name='required_weight']");

        /* [실시간 방어] 요구량 숫자/소수점 실시간 정제 */
        weightIn.addEventListener("input", function() {
            this.value = this.value.replace(/[^0-9.]/g, "");
            const parts = this.value.split(".");
            if (parts.length > 2) {
                this.value = parts[0] + "." + parts.slice(1).join("");
            }
        });

        /* 철칙 14: 유효성 검사 및 컨펌 */
        bomForm.addEventListener("submit", function(e) {
            if(weightIn.value.trim() === "" || isNaN(weightIn.value)) {
                alert("요구량을 올바른 숫자로 입력해주세요.");
                weightIn.focus();
                e.preventDefault();
                return;
            }

            if(!confirm("BOM 정보를 등록하시겠습니까?")) {
                e.preventDefault();
            }
        });

        /* 철칙 21: 취소 버튼 컨펌 (참고 로직 동일 적용) */
        btnCan.addEventListener("click", function(e) {
            if(!confirm("작성 중인 내용이 저장되지 않습니다. 취소하시겠습니까?")) {
                e.preventDefault();
            }
        });
    }
</script>
</body>
</html>