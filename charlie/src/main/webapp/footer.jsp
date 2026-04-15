<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MES 상태바 푸터</title>
<style>
    /* 1. 상태바 본체 고정 (Fixed 레이아웃) */
    .st-bar {
/*         position: fixed; */
        bottom: 0;
        left: 0;
        width: 100%;
        z-index: 9999; /* 최상단 레이어 유지 */
        background-color: #f4f4f4;
        border-top: 0.15rem solid #4B2C20; /* 메인 컬러 */
        padding: 0.5rem 2%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-wrap: wrap;
        gap: 0.8rem;
        font-size: 0.85rem;
        /* 기본 마진/패딩 초기화 (푸터 한정) */
        margin: 0;
        box-sizing: border-box;
    }

    /* 2. 정보 그룹 및 아이템 (철칙 1: 짧은 클래스명) */
    .st-grp {
        display: flex;
        align-items: center;
        gap: 1.5rem;
        flex-wrap: wrap;
    }

    .st-itm {
        display: flex;
        align-items: center;
        gap: 0.4rem;
        color: #333;
        transition: color 0.2s;
    }

    /* 철칙 6: 서브 컬러 호버 시에만 적용 */
    .st-itm:hover {
        color: #5C6BC0;
        cursor: default;
    }

    /* 3. 라벨 및 상태 표시등 */
    .st-lb {
        font-weight: 700;
        color: #4B2C20; 
    }

    .st-dot {
        display: inline-block;
        width: 0.6rem;
        height: 0.6rem;
        border-radius: 50%;
        background-color: #28a745; 
    }

    /* 4. 웹 반응형 (철칙 4: px 고정 지양) */
    @media (max-width: 768px) {
        .st-bar {
            flex-direction: column; 
            align-items: flex-start;
            gap: 0.5rem;
            padding: 0.8rem 4%;
        }
        
        .st-grp {
            width: 100%;
            justify-content: flex-start;
            gap: 1rem;
        }
    }

    @media (max-width: 480px) {
        .st-grp {
            justify-content: space-between;
        }
    }
</style>
</head>
<body>
    
    <footer class="st-bar">
        <div class="st-grp">
            <div class="st-itm">
                <span class="st-dot"></span>
                <span class="st-lb">SYS</span>
                <span>Online</span>
            </div>
            <div class="st-itm">
                <span class="st-dot"></span>
                <span class="st-lb">DB</span>
                <span>Connected</span>
            </div>
        </div>

        <div class="st-grp">
            <div class="st-itm">
                <span class="st-lb">User:</span>
                <%-- 세션 또는 리퀘스트에서 성함 바인딩 --%>
                <span>${name != null ? name : 'Guest'}</span>
            </div>
            <div class="st-itm">
                <span class="st-lb">Plant:</span>
                <span>제1공장 (Main)</span>
            </div>
        </div>

        <div class="st-grp">
            <div class="st-itm">
                <span class="st-lb">Ver:</span>
                <span>v1.2.4</span>
            </div>
            <div class="st-itm">
                <span class="st-lb">Ping:</span>
                <span>12ms</span>
            </div>
        </div>
    </footer>

</body>
</html>