<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>불량품 정보 수정</title>
<style>
    /* [1] 기본 초기화 및 철칙 20 (.mat-all) */
    * { box-sizing: border-box; margin: 0; padding: 0; }
    a { text-decoration: none; color: inherit; }
    
    .mat-all {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    /* [2] 수정 폼 컨테이너 (철칙 1, 4, 5) */
    #def-mod-scope {
        width: 100%;
        padding: 2rem 0 5rem 0;
        flex: 1; /* 푸터 하단 고정 보조 */
    }

    #def-mod-scope .mod-wrap {
        width: 90%;
        max-width: 800px;
        margin: 0 auto;
        background-color: #fff;
        border-top: 3rem solid #4B2C20; /* 메인 컬러 */
        border-radius: 6px;
        box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
        padding: 3rem;
    }

    #def-mod-scope .mod-tit {
        font-size: 1.5rem;
        font-weight: 700;
        color: #333;
        margin-bottom: 2rem;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
    }

    /* [3] 항목 레이아웃 (철칙 12, 17 준수) */
    #def-mod-scope .mod-row {
        display: flex;
        flex-direction: column;
        margin-bottom: 1.5rem;
    }

    #def-mod-scope .mod-lb {
        font-weight: 600;
        color: #4B2C20;
        margin-bottom: 0.6rem;
        font-size: 1.05rem;
    }

    #def-mod-scope .mod-txt {
        font-size: 1rem;
        color: #555;
        padding: 0.8rem 1rem;
        background-color: #f9f9f9;
        border-radius: 4px;
        border: 1px solid #eee;
        font-weight: 700; /* 철칙 12 */
    }

    #def-mod-scope .mod-in {
        width: 100%;
        padding: 0.8rem 1rem;
        border: 1px solid #ddd;
        border-radius: 4px;
        font-size: 1rem;
        font-family: inherit;
        transition: border-color 0.3s, box-shadow 0.3s;
    }

    /* 철칙 6: 서브 컬러 반응 */
    #def-mod-scope .mod-in:focus {
        outline: none;
        border-color: #5C6BC0;
        box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
    }

    /* [4] 버튼 그룹 (철칙 21: 위치 동일화) */
    #def-mod-scope .mod-btn-grp {
        display: flex;
        justify-content: flex-end;
        gap: 1rem;
        margin-top: 3rem;
        border-top: 1px solid #eee;
        padding-top: 2rem;
    }

    #def-mod-scope .mod-btn {
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

    /* 철칙 4: 반응형 */
    @media ( max-width : 768px) {
        #def-mod-scope .mod-wrap { width: 95%; padding: 2rem 1.5rem; }
        #def-mod-scope .mod-btn-grp { flex-direction: column-reverse; gap: 0.8rem; }
        #def-mod-scope .mod-btn { width: 100%; padding: 1rem; }
    }
</style>
</head>
<body>
<div class="mat-all">
    <%@ include file="/header.jsp"%>

    <div id="def-mod-scope">
        <div class="mod-wrap">
            <div class="mod-tit">불량품 정보 수정</div>

            <form id="modForm" method="post" action="defective">
                <input type="hidden" name="mod" value="up">
                <input type="hidden" name="defective_num" value="${map.list[0].defective_num}">

                <div class="mod-row">
                    <span class="mod-lb">불량 번호</span>
                    <span class="mod-txt">${map.list[0].defective_num}</span>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">불량 카테고리</span>
                    <select name="category" class="mod-in">
                        <option value="깨짐" ${map.list[0].category == '깨짐' ? 'selected' : ''}>깨짐</option>
                        <option value="녹음" ${map.list[0].category == '녹음' ? 'selected' : ''}>녹음</option>
                        <option value="이물질" ${map.list[0].category == '이물질' ? 'selected' : ''}>이물질</option>
                    </select>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">불량 개수</span>
                    <input type="text" name="count" value="${map.list[0].count}" class="mod-in" inputmode="numeric">
                </div>

                <div class="mod-row">
                    <span class="mod-lb">불량 조치 방법</span>
                    <select name="action" class="mod-in">
                        <option value="폐기" ${map.list[0].action == '폐기' ? 'selected' : ''}>폐기</option>
                        <option value="통과" ${map.list[0].action == '통과' ? 'selected' : ''}>통과</option>
                    </select>
                </div>

                <div class="mod-row">
                    <span class="mod-lb">LOT 번호</span>
                    <select name="lot_num" class="mod-in">
                         <c:forEach var="l" items="${lot}">
                            <option value="${l.lot_num}" ${map.list[0].lot_num == l.lot_num ? 'selected' : ''}>
                                ${l.lot_num} (상태: ${l.qc_chk}, 수량: ${l.lot_count})
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
    const count_el = document.querySelector("input[name='count']");

    /* [실시간 방어] 정규표현식 사용하여 숫자 이외 입력 원천 차단 */
    count_el.addEventListener("input", function(e) {
        this.value = this.value.replace(/[^0-9]/g, "");
    });

    /* 철칙 14: 유효성 검사 및 컨펌 이식 */
    modForm.addEventListener('submit', (e) => {
        const count_val = count_el.value.trim();
        
        if (count_val === "") {
            alert("불량 개수를 입력하세요.");
            e.preventDefault();
            count_el.focus();
            return;
        }

        if (confirm("불량 정보를 수정하시겠습니까?")) {
            alert("수정이 완료되었습니다.");
        } else {
            e.preventDefault(); 
        }
    });

    /* 철칙 21: 취소 컨펌 로직 */
    btn_can.addEventListener('click', () => {
        if (confirm("정말 수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
            location.href = "defective?mod=list";
        }
    });
</script>
</body>
</html>