<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입출고이력 수정</title>
<style>
/* 1. 기본 초기화 */
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

body {
    background-color: #f5f5f5;
    font-family: sans-serif;
}

/* 메인 컨테이너 - 최소 높이 설정 (철칙 4번 및 요청사항 반영) */
.mat-all {
    min-height: 84vh; /* 화면 높이에 맞춘 유동적 높이 설정 */
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 3rem 0 5rem 0;
}

/* 메인 폼 래퍼 (반응형 단위를 위한 rem, vw 사용) */
.mod-wrap {
    width: 90vw;
    max-width: 800px;
    margin: 0 auto;
    background-color: #fff;
    border-top: 3rem solid #4B2C20; /* 5. 메인 컬러 적용 */
    border-radius: 6px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08); /* 2. !important 배제 */
    padding: 3rem;
}

/* 페이지 타이틀 */
.mod-tit {
    font-size: 1.5rem;
    font-weight: 700;
    color: #333;
    margin-bottom: 2rem;
    border-bottom: 2px solid #eee;
    padding-bottom: 1rem;
}

/* 테이블 구조 */
.mod-tbl {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 1.5rem;
}

/* 테이블 헤더 (12. 글씨 굵기 참고 반영) */
.mod-tbl th {
    font-weight: 600;
    color: #4B2C20;
    text-align: left;
    padding: 1rem;
    background-color: #f9f9f9;
    border-bottom: 2px solid #ddd;
    font-size: 1.05rem;
}

.mod-tbl td {
    padding: 1rem;
    border-bottom: 1px solid #eee;
}

/* 입력창 공통 스타일 */
.mod-in {
    width: 100%;
    padding: 0.8rem 1rem;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 1rem;
    font-family: inherit;
    transition: border-color 0.3s, box-shadow 0.3s;
}

/* 포커스 시 6. 서브 컬러(#5C6BC0) 반응 */
.mod-in:focus {
    outline: none;
    border-color: #5C6BC0;
    box-shadow: 0 0 0 3px rgba(92, 107, 192, 0.15);
}

/* 13. a태그 밑줄 및 간격 설정 */
.mod-wrap a {
    text-decoration: underline;
    text-underline-offset: 4px;
    color: #333;
    transition: color 0.2s;
}

.mod-wrap a:hover {
    color: #5C6BC0;
}

/* 9. 페이징 버튼 (현재 페이지 강조) */
.mod-paging a.on {
    font-weight: bold;
    color: #5C6BC0;
    font-size: 1.1rem;
}

/* 하단 버튼 그룹 */
.mod-btn-grp {
    display: flex;
    justify-content: flex-end;
    gap: 1rem;
    margin-top: 2rem;
    border-top: 1px solid #eee;
    padding-top: 2rem;
}

/* 버튼 공통 스타일 */
.mod-btn {
    padding: 0.8rem 2.5rem;
    border-radius: 4px;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    border: none;
    transition: all 0.2s;
}

/* 수정 완료 버튼 (메인 컬러) */
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
}

.btn-can:hover {
    color: #5C6BC0;
    border-color: #5C6BC0;
    background-color: #f8f9ff;
}

/* 태블릿 및 모바일 반응형 처리 */
@media (max-width: 768px) {
    .mod-wrap {
        width: 95vw;
        padding: 2rem 1.5rem;
    }
    
    .mod-tbl th, .mod-tbl td {
        display: block;
        width: 100%;
    }
    .mod-tbl th {
        border-bottom: none;
        padding-bottom: 0.5rem;
    }
    .mod-tbl td {
        padding-top: 0;
        margin-bottom: 1rem;
    }

    .mod-btn-grp {
        flex-direction: column-reverse;
        gap: 0.8rem;
    }
    
    .mod-btn {
        width: 100%;
    }
}
</style>
</head>
<body>
    <%@ include file="/header.jsp"%>

    <div class="mat-all">
        <div class="mod-wrap">
            <div class="mod-tit">입출고이력 수정</div>

            <form id="modForm" method="post" action="log">
                <table class="mod-tbl">
                    <thead>
                        <tr>
                            <th>입출고날짜</th>
                            <th>구분</th>
                            <th>사용기한</th>
                            <th>lot번호</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <input type="hidden" name="mod" value="up">
                            <input type="hidden" name="log_num" value="${map.list[0].log_num}">
                            
                            <td><input type="date" id="io_time" name="io_time" value="${map.list[0].io_time}" class="mod-in"></td>
                            <td>
                                <select name="io_type" class="mod-in">
                                    <option value="입고" ${map.list[0].io_type == '입고' ? 'selected' : ''}>입고</option>
                                    <option value="출고" ${map.list[0].io_type == '출고' ? 'selected' : ''}>출고</option>
                                </select>
                            </td>
                            <td><input type="date" id="exp_date" name="exp_date" value="${map.list[0].exp_date}" class="mod-in"></td>
                            <td>
                                <select name="lot_num" class="mod-in">
                                    <c:forEach var="l" items="${lot}">
                                        <option value="${l.lot_num}" ${l.lot_num == map.list[0].lot_num ? 'selected' : ''}>
                                            ${l.lot_num}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
                
                <div class="mod-btn-grp">
                    <button type="button" class="mod-btn btn-can">수정취소</button>
                    <button type="submit" class="mod-btn btn-sub">수정완료</button>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="/footer.jsp"%>

    <script>
        window.onload = function() {
            const modForm = document.getElementById("modForm");
            const btn_can = document.querySelector(".btn-can");
            const io_time = document.getElementById("io_time");
            const exp_date = document.getElementById("exp_date");

            // 14, 15. JS 로직 및 날짜 방어 로직
            modForm.addEventListener('submit', (e) => {
                if (io_time.value.trim() === "") {
                    alert("입출고날짜를 입력하세요.");
                    e.preventDefault();
                    io_time.focus();
                    return;
                }
                if (exp_date.value.trim() === "") {
                    alert("사용기한을 입력하세요.");
                    e.preventDefault();
                    exp_date.focus();
                    return;
                }

                // 15. 날짜 비교 방어 로직
                const ioDate = new Date(io_time.value);
                const expDate = new Date(exp_date.value);

                if (expDate < ioDate) {
                    alert("사용기한은 입출고날짜보다 빠를 수 없습니다.");
                    e.preventDefault();
                    exp_date.focus();
                    return;
                }

                if (!confirm("해당 내용으로 수정하시겠습니까?")) {
                    e.preventDefault(); 
                }
            });

            // 취소 버튼 이벤트
            btn_can.addEventListener('click', () => {
                if (confirm("수정을 취소하시겠습니까? 작성 중인 내용은 저장되지 않습니다.")) {
                    history.back();
                }
            });
        }
    </script>
</body>
</html>