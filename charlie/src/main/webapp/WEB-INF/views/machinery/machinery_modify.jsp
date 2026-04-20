<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Machinery.MachineryDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기계 정보 수정</title>
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

    /* [2] 수정 폼 메인 컨테이너 (철칙 1, 4, 5) */
    #mach-mod-scope {
        width: 100%;
        padding: 2rem 0 5rem 0;
        flex: 1; /* 콘텐츠 적어도 푸터 하단 고정 */
    }

    #mach-mod-scope .mod-wrap {
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
    #mach-mod-scope .mod-tit {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 2rem;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
    }

    /* [3] 항목 레이아웃 (철칙 12, 17 준수) */
    #mach-mod-scope .mod-row {
        display: flex;
        flex-direction: column;
        margin-bottom: 1.5rem;
    }

    /* 라벨 (메인 컬러) */
    #mach-mod-scope .mod-lb {
        font-weight: 600;
        color: #4B2C20;
        margin-bottom: 0.6rem;
        font-size: 1.05rem;
    }

    /* 읽기 전용 텍스트 (철칙 12: 굵기 700 / 철칙 17: 컬러 #555) */
    #mach-mod-scope .mod-txt {
        font-size: 1rem;
        color: #555;
        padding: 0.8rem 1rem;
        background-color: #f9f9f9;
        border-radius: 4px;
        border: 1px solid #eee;
        font-weight: 700;
    }

    /* 인풋 및 셀렉트 공통 스타일 */
    #mach-mod-scope .mod-in {
        width: 100%;
        padding: 0.8rem 1rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 1rem;
        font-family: inherit;
        transition: border-color 0.3s, box-shadow 0.3s;
    }

    /* 철칙 6: 포커스 시 서브 컬러 반응 */
    #mach-mod-scope .mod-in:focus {
        outline: none;
        border-color: #5C6BC0;
        box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
    }

    /* 라디오 그룹 (참고 로직 UX 이식) */
    #mach-mod-scope .mod-rd-grp {
        display: flex;
        gap: 2rem;
        padding: 0.5rem 0;
    }

    #mach-mod-scope .mod-rd-item {
        display: flex;
        align-items: center;
        gap: 0.5rem;
        cursor: pointer;
        font-size: 1rem;
    }

    /* [4] 버튼 그룹 (철칙 21: 위치 동일화) */
    #mach-mod-scope .mod-btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        margin-top: 3rem;
        border-top: 1px solid #eee;
        padding-top: 2rem;
    }

    #mach-mod-scope .mod-btn {
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
        #mach-mod-scope .mod-wrap { width: 95%; padding: 2rem 1.5rem; }
        #mach-mod-scope .mod-btn-grp { flex-direction: column-reverse; gap: 0.8rem; }
        #mach-mod-scope .mod-btn { width: 100%; padding: 1rem; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp"%>

    <div id="mach-mod-scope">
        <div class="mod-wrap">
            <div class="mod-tit">기계 정보 수정</div>

            <form id="modForm" method="post" action="machinery">
                <input type="hidden" name="cmd" value="update">
                <input type="hidden" name="machinery_num" value="${ machineryDTO.machinery_num }">
                <input type="hidden" name="mdm_num" value="${machineryDTO.mdm_num}">

                <div class="mod-row">
                    <span class="mod-lb">장비 번호</span>
                    <span class="mod-txt">${ machineryDTO.machinery_num }</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">장비 타입</span>
                    <select name="machinery_type" class="mod-in">
                        <option value="Mixer" ${ machineryDTO.machinery_type eq 'Mixer' ? 'selected' : ''}>혼합기</option>
                        <option value="Heater / Melter" ${ machineryDTO.machinery_type eq 'Heater / Melter' ? 'selected' : ''}>가열기(용해기)</option>
                        <option value="Cooler" ${ machineryDTO.machinery_type eq 'Cooler' ? 'selected' : ''}>냉각기</option>
                        <option value="Packing Machine" ${ machineryDTO.machinery_type eq 'Packing Machine' ? 'selected' : ''}>포장기</option>
                    </select>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">동작 상태</span>
                    <div class="mod-rd-grp">
                        <label class="mod-rd-item">
                            <input type="radio" name="machinery_status" value="T" ${ machineryDTO.machinery_status eq 'T' ? 'checked' : ''}> 동작 (T)
                        </label>
                        <label class="mod-rd-item">
                            <input type="radio" name="machinery_status" value="F" ${ machineryDTO.machinery_status eq 'F' ? 'checked' : ''}> 정지 (F)
                        </label>
                    </div>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">에러 코드 (Error Sign)</span>
                    <select name="error_sign" class="mod-in">
                        <option value="E01 Equipment failure" ${ machineryDTO.error_sign eq 'E01 Equipment failure' ? 'selected' : ''}>1. 장비 이상 발생</option>
                        <option value="E02 Temp error" ${ machineryDTO.error_sign eq 'E02 Temp error' ? 'selected' : ''}>2. 온도 이상</option>
                        <option value="E03 Pressure error" ${ machineryDTO.error_sign eq 'E03 Pressure error' ? 'selected' : ''}>3. 압력 이상</option>
                        <option value="E04 Sensor error" ${ machineryDTO.error_sign eq 'E04 Sensor error' ? 'selected' : ''}>4. 센서 오류</option>
                        <option value="E05 Power failure" ${ machineryDTO.error_sign eq 'E05 Power failure' ? 'selected' : ''}>5. 전원 문제</option>
                    </select>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">조치 방법 (Action)</span>
                    <select name="m_action" class="mod-in">
                        <option value="Stop the equipment" ${ machineryDTO.m_action eq 'Stop the equipment' ? 'selected' : ''}>1. 장비 정지</option>
                        <option value="Check the error" ${ machineryDTO.m_action eq 'Check the error' ? 'selected' : ''}>2. 에러 확인</option>
                        <option value="Report issue" ${ machineryDTO.m_action eq 'Report issue' ? 'selected' : ''}>3. 보고</option>
                        <option value="Take action" ${ machineryDTO.m_action eq 'Take action' ? 'selected' : ''}>4. 조치 수행</option>
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

    /* 철칙 14: 참고 로직의 유효성 검사 및 컨펌 이식 */
    modForm.addEventListener('submit', (e) => {
        if (confirm("해당 내용으로 기계 정보를 수정하시겠습니까?")) {
            alert("수정이 완료되었습니다.");
        } else {
            e.preventDefault(); 
        }
    });

    /* 철칙 21: 취소 버튼 컨펌 로직 (참고 로직 동일 적용) */
    btn_can.addEventListener('click', () => {
        if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
            location.href = "machinery?cmd=list";
        }
    });
</script>
</body>
</html>