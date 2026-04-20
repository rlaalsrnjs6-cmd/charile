<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Warehouse.WarehouseDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>창고 정보 수정</title>
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

    #wh-mod-scope {
        width: 100%;
        padding: 2rem 0 5rem 0;
        flex: 1;
    }

    /* [2] 수정 폼 메인 컨테이너 (철칙 1, 4, 5) */
    #wh-mod-scope .mod-wrap {
        width: 90%;
        max-width: 800px;
        margin: 0 auto;
        background-color: #fff;
        border-top: 3rem solid #4B2C20;
        border-radius: 6px;
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
        padding: 3rem;
    }

    /* 페이지 타이틀 */
    #wh-mod-scope .mod-tit {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 2rem;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
    }

    /* [3] 항목 레이아웃 (철칙 12, 17, 23 준수) */
    #wh-mod-scope .mod-row {
        display: flex;
        flex-direction: column;
        margin-bottom: 1.5rem;
    }

    #wh-mod-scope .mod-lb {
        font-weight: 600;
        color: #4B2C20;
        margin-bottom: 0.6rem;
        font-size: 1.05rem;
    }

    /* 읽기 전용 및 인풋 텍스트 (철칙 23: 블랙 유지) */
    #wh-mod-scope .mod-txt, #wh-mod-scope .mod-in {
        font-size: 1rem;
        color: #000;
        padding: 0.8rem 1rem;
        border-radius: 4px;
        font-weight: 700;
    }

    #wh-mod-scope .mod-txt {
        background-color: #f9f9f9;
        border: 1px solid #eee;
    }

    #wh-mod-scope .mod-in {
        width: 100%;
        border: 1px solid #ddd;
        font-family: inherit;
        transition: border-color 0.3s, box-shadow 0.3s;
    }

    #wh-mod-scope .mod-in:focus {
        outline: none;
        border-color: #5C6BC0;
        box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
    }

    /* [4] 버튼 그룹 (철칙 21) */
    #wh-mod-scope .mod-btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        margin-top: 3rem;
        border-top: 1px solid #eee;
        padding-top: 2rem;
    }

    #wh-mod-scope .mod-btn {
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

    @media ( max-width : 768px) {
        #wh-mod-scope .mod-wrap { width: 95%; padding: 2rem 1.5rem; }
        #wh-mod-scope .mod-btn-grp { flex-direction: column-reverse; gap: 0.8rem; }
        #wh-mod-scope .mod-btn { width: 100%; padding: 1rem; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp"%>

    <div id="wh-mod-scope">
        <div class="mod-wrap">
            <div class="mod-tit">창고 정보 수정</div>

            <form id="modForm" method="post" action="warehouse">
                <input type="hidden" name="cmd" value="update">
                <input type="hidden" name="warehouse_num" value="${ warehouseDTO.warehouse_num }">

                <div class="mod-row">
                    <span class="mod-lb">창고 관리 번호</span>
                    <span class="mod-txt">${ warehouseDTO.warehouse_num }</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">창고 섹션 (Section)</span>
                    <input type="text" name="wh_section" value="${ warehouseDTO.wh_section }" class="mod-in">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">층 구분 (Floor Level)</span>
                    <input type="text" name="floor_level" value="${ warehouseDTO.floor_level }" class="mod-in">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">현재 온도 (Temperature)</span>
                    <input type="text" name="temperature" value="${ warehouseDTO.temperature }" class="mod-in" inputmode="decimal">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">현재 습도 (Humidity)</span>
                    <input type="text" name="humidity" value="${ warehouseDTO.humidity }" class="mod-in" inputmode="decimal">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">확인 상태 (Status)</span>
                    <select name="wh_status_chk" class="mod-in">
                        <option value="정상" ${ warehouseDTO.wh_status_chk eq '정상' ? 'selected' : ''}>정상</option>
                        <option value="점검필요" ${ warehouseDTO.wh_status_chk eq '점검필요' ? 'selected' : ''}>점검필요</option>
                        <option value="이상발생" ${ warehouseDTO.wh_status_chk eq '이상발생' ? 'selected' : ''}>이상발생</option>
                    </select>
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
    const tempIn = document.querySelector("input[name='temperature']");
    const humiIn = document.querySelector("input[name='humidity']");

    /* [실시간 방어] 숫자와 소수점(.)만 허용하는 정규표현식 로직 */
    const filterDecimal = (el) => {
        el.addEventListener("input", function() {
            // 숫자와 소수점 제외 제거
            this.value = this.value.replace(/[^0-9.]/g, "");
            // 소수점이 두 번 들어가는 것 방지
            const parts = this.value.split(".");
            if (parts.length > 2) {
                this.value = parts[0] + "." + parts.slice(1).join("");
            }
        });
    };

    filterDecimal(tempIn);
    filterDecimal(humiIn);

    /* 철칙 14: 유효성 검사 및 컨펌 */
    modForm.addEventListener('submit', (e) => {
        const section = document.querySelector("input[name='wh_section']");
        
        if (section.value.trim() === "") {
            alert("창고 섹션을 입력하세요.");
            e.preventDefault();
            section.focus();
            return;
        }

        if (tempIn.value.trim() === "" || humiIn.value.trim() === "") {
            alert("온도와 습도를 입력해주세요.");
            e.preventDefault();
            return;
        }

        if (confirm("창고 정보를 수정하시겠습니까?")) {
            alert("수정이 완료되었습니다.");
        } else {
            e.preventDefault(); 
        }
    });

    /* 철칙 21: 취소 버튼 */
    btn_can.addEventListener('click', () => {
        if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
            location.href = "warehouse?cmd=list";
        }
    });
</script>
</body>
</html>