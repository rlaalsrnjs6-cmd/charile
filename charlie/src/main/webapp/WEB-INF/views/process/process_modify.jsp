<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="Process.ProcessDTO"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공정 정보 수정</title>
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
    #proc-mod-scope {
        width: 100%;
        padding: 2rem 0 5rem 0;
        flex: 1; /* 콘텐츠 적어도 푸터 하단 고정 */
    }

    #proc-mod-scope .mod-wrap {
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
    #proc-mod-scope .mod-tit {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 2rem;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
    }

    /* [3] 항목 레이아웃 (철칙 12, 17 준수) */
    #proc-mod-scope .mod-row {
        display: flex;
        flex-direction: column;
        margin-bottom: 1.5rem;
    }

    /* 라벨 (메인 컬러) */
    #proc-mod-scope .mod-lb {
        font-weight: 600;
        color: #4B2C20;
        margin-bottom: 0.6rem;
        font-size: 1.05rem;
    }

    /* 읽기 전용 텍스트 (철칙 12: 굵기 700 / 철칙 17: 컬러 #555) */
    #proc-mod-scope .mod-txt {
        font-size: 1rem;
        color: #555;
        padding: 0.8rem 1rem;
        background-color: #f9f9f9;
        border-radius: 4px;
        border: 1px solid #eee;
        font-weight: 700;
    }

    /* 인풋 및 셀렉트 공통 스타일 */
    #proc-mod-scope .mod-in {
        width: 100%;
        padding: 0.8rem 1rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 1rem;
        font-family: inherit;
        transition: border-color 0.3s, box-shadow 0.3s;
    }

    /* 철칙 6: 포커스 시 서브 컬러 반응 */
    #proc-mod-scope .mod-in:focus {
        outline: none;
        border-color: #5C6BC0;
        box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
    }

    /* [4] 버튼 그룹 (철칙 21: 위치 동일화) */
    #proc-mod-scope .mod-btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        margin-top: 3rem;
        border-top: 1px solid #eee;
        padding-top: 2rem;
    }

    #proc-mod-scope .mod-btn {
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
        #proc-mod-scope .mod-wrap { width: 95%; padding: 2rem 1.5rem; }
        #proc-mod-scope .mod-btn-grp { flex-direction: column-reverse; gap: 0.8rem; }
        #proc-mod-scope .mod-btn { width: 100%; padding: 1rem; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp"%>

    <div id="proc-mod-scope">
        <div class="mod-wrap">
            <div class="mod-tit">공정 정보 수정</div>

            <form id="modForm" method="post" action="process">
                <input type="hidden" name="cmd" value="update">
                <input type="hidden" name="process_num" value="${ processDTO.process_num }">

                <div class="mod-row">
                    <span class="mod-lb">공정 번호</span>
                    <span class="mod-txt">${ processDTO.process_num }</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">공정 내용</span>
                    <input type="text" name="process_content" value="${ processDTO.process_content }" class="mod-in">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">공정 흐름 (Flow - 숫자만 입력 가능)</span>
                    <input type="text" name="flow" value="${ processDTO.flow }" class="mod-in" inputmode="numeric">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">이미지 URL</span>
                    <input type="text" name="img_url" value="${ processDTO.img_url }" class="mod-in">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">기준정보 연결</span>
                    <select name="mdm_num" class="mod-in">
                        <c:forEach var="item" items="${ list }">
                            <option value="${ item.mdm_num }" ${processDTO.mdm_num == item.mdm_num ? 'selected' : ''}>
                                ${ item.name } (${ item.code })
                            </option>
                        </c:forEach>
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
    const flow_el = document.querySelector("input[name='flow']");

    /* [방어 로직] flow 필드 숫자 전용 정규표현식 적용 */
    flow_el.addEventListener("input", function() {
        this.value = this.value.replace(/[^0-9]/g, "");
    });

    /* 철칙 14: 유효성 검사 및 컨펌 이식 */
    modForm.addEventListener('submit', (e) => {
        const content_el = document.querySelector("input[name='process_content']");
        
        if (content_el.value.trim() === "") {
            alert("공정 내용을 입력하세요.");
            e.preventDefault();
            content_el.focus();
            return;
        }
        if (flow_el.value.trim() === "") {
            alert("공정 흐름을 입력하세요.");
            e.preventDefault();
            flow_el.focus();
            return;
        }

        if (confirm("해당 내용으로 공정 정보를 수정하시겠습니까?")) {
            alert("수정이 완료되었습니다.");
        } else {
            e.preventDefault(); 
        }
    });

    /* 철칙 21: 취소 버튼 컨펌 로직 */
    btn_can.addEventListener('click', () => {
        if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
            location.href = "process?cmd=list";
        }
    });
</script>
</body>
</html>