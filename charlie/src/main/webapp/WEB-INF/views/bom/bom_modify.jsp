<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Bom.BomDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BOM 정보 수정</title>
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

    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    /* 철칙 8: 기존 hr 숨김 처리 */
    hr {
        display: none;
    }

    #bom-mod-scope {
        width: 100%;
        padding: 2rem 0 5rem 0;
        flex: 1; /* 콘텐츠 적어도 푸터 하단 고정 */
    }

    /* [2] 수정 폼 메인 컨테이너 (철칙 1, 4, 5) */
    #bom-mod-scope .mod-wrap {
        width: 90%;
        max-width: 800px;
        margin: 0 auto;
        background-color: #fff;
        border-top: 3rem solid #4B2C20; /* 메인 컬러 */
        border-radius: 6px;
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
        padding: 3rem;
    }

    /* 페이지 타이틀 */
    #bom-mod-scope .mod-tit {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 2rem;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
    }

    /* [3] 항목 레이아웃 (철칙 12, 17, 23 준수) */
    #bom-mod-scope .mod-row {
        display: flex;
        flex-direction: column;
        margin-bottom: 1.5rem;
    }

    #bom-mod-scope .mod-lb {
        font-weight: 600;
        color: #4B2C20; /* 메인 컬러 */
        margin-bottom: 0.6rem;
        font-size: 1.05rem;
    }

    /* 읽기 전용 및 인풋 텍스트 (철칙 23: 블랙 유지) */
    #bom-mod-scope .mod-txt, #bom-mod-scope .mod-in {
        font-size: 1rem;
        color: #000; /* 철칙 23: 폰트 블랙 유지 */
        padding: 0.8rem 1rem;
        border-radius: 4px;
        font-weight: 700; /* 철칙 12: 굵기 반영 */
    }

    #bom-mod-scope .mod-txt {
        background-color: #f9f9f9;
        border: 1px solid #eee;
    }

    #bom-mod-scope .mod-in {
        width: 100%;
        border: 1px solid #ddd;
        font-family: inherit;
        transition: border-color 0.3s, box-shadow 0.3s;
    }

    /* 철칙 6: 포커스 시 서브 컬러 반응 */
    #bom-mod-scope .mod-in:focus {
        outline: none;
        border-color: #5C6BC0;
        box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
    }

    /* [4] 버튼 그룹 (철칙 21: 위치 동일화) */
    #bom-mod-scope .mod-btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        margin-top: 3rem;
        border-top: 1px solid #eee;
        padding-top: 2rem;
    }

    #bom-mod-scope .mod-btn {
        padding: 0.8rem 2.5rem;
        border-radius: 4px;
        font-size: 1rem;
        font-weight: 600;
        text-align: center;
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
    }
    .btn-can:hover {
        color: #5C6BC0;
        border-color: #5C6BC0;
        background-color: #f8f9ff;
    }

    /* 철칙 4: 반응형 미디어쿼리 */
    @media ( max-width : 768px) {
        #bom-mod-scope .mod-wrap { width: 95%; padding: 2rem 1.5rem; }
        #bom-mod-scope .mod-btn-grp { flex-direction: column-reverse; gap: 0.8rem; }
        #bom-mod-scope .mod-btn { width: 100%; padding: 1rem; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp"%>

    <div id="bom-mod-scope">
        <div class="mod-wrap">
            <div class="mod-tit">BOM 정보 수정</div>

            <form id="modForm" method="post" action="bom">
                <input type="hidden" name="cmd" value="update">
                <input type="hidden" name="bom_num" value="${ bomDTO.bom_num }">
                <input type="hidden" name="mdm_num" value="${ bomDTO.mdm_num }">
                <input type="hidden" name="target_mdm_num" value="${ bomDTO.target_mdm_num }">

                <div class="mod-row">
                    <span class="mod-lb">BOM 번호</span>
                    <span class="mod-txt">${ bomDTO.bom_num }</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">재료명 / 코드</span>
                    <span class="mod-txt">${ bomDTO.name } (${ bomDTO.code })</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">요구 제품</span>
                    <span class="mod-txt">${ bomDTO.target_name }</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">요구량 (Required Weight)</span>
                    <div style="display: flex; align-items: center; gap: 0.5rem;">
                        <input type="text" name="required_weight" value="${ bomDTO.required_weight }" class="mod-in" inputmode="decimal">
                        <span style="font-weight: 700; color: #000;">${ bomDTO.unit }</span>
                    </div>
                </div>

                <div class="mod-btn-grp">
                    <button type="button" class="mod-btn btn-can">수정취소</button>
                    <button type="submit" class="mod-btn btn-sub">수정하기</button>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="/footer.jsp"%>
</div>

<script>
    const modForm = document.getElementById("modForm");
    const btn_can = document.querySelector(".btn-can");
    const weightIn = document.querySelector("input[name='required_weight']");

    /* [실시간 방어] 요구량 숫자 및 소수점 실시간 정제 로직 */
    weightIn.addEventListener("input", function() {
        this.value = this.value.replace(/[^0-9.]/g, "");
        const parts = this.value.split(".");
        if (parts.length > 2) {
            this.value = parts[0] + "." + parts.slice(1).join("");
        }
    });

    /* 철칙 14: 유효성 검사 및 컨펌 이식 */
    modForm.addEventListener('submit', (e) => {
        if (weightIn.value.trim() === "" || isNaN(weightIn.value)) {
            alert("요구량을 올바른 숫자로 입력해주세요.");
            e.preventDefault();
            weightIn.focus();
            return;
        }

        if (confirm("해당 내용으로 BOM 정보를 수정하시겠습니까?")) {
            alert("수정이 완료되었습니다.");
        } else {
            e.preventDefault(); 
        }
    });

    /* 철칙 21: 취소 버튼 컨펌 로직 (참고 로직 동일 적용) */
    btn_can.addEventListener('click', () => {
        if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
            location.href = "bom?cmd=list";
        }
    });
</script>
</body>
</html>