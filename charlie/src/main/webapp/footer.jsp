<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MES 상태바 푸터</title>
<style>
    /* 1. 상태바 본체 고정 */
    .st-bar {
        bottom: 0;
        left: 0;
        width: 100%;
        z-index: 9999;
        background-color: #f4f4f4;
        border-top: 0.15rem solid #4B2C20; /* 메인 컬러 */
        padding: 0.5rem 2%;
        display: flex;
        /* 핵심: 양 끝으로 배치 (시스템정보는 왼쪽, 유저는 오른쪽) */
        justify-content: space-between; 
        align-items: center;
        box-sizing: border-box;
    }

    /* 2. 정보 그룹 */
    .st-grp {
        display: flex;
        align-items: center;
        gap: 1rem; /* 아이템 간 간격 살짝 조절 */
    }

    .st-itm {
        display: flex;
        align-items: center;
        gap: 0.4rem;
        color: #333;
        font-size: 0.85rem;
        white-space: nowrap; /* 글자가 절대 줄바꿈되지 않도록 설정 */
    }

    .st-itm:hover {
        color: #5C6BC0; /* 서브 컬러 */
    }

    .st-lb {
        font-weight: 700;
        color: #4B2C20; 
    }

    .st-dot {
        display: inline-block;
        width: 0.5rem;
        height: 0.5rem;
        border-radius: 50%;
        background-color: #28a745; 
    }

    /* 3. 웹 반응형 수정 */
    @media (max-width: 768px) {
        .st-bar {
            /* 모바일에서도 한 줄 유지를 위해 column으로 변하지 않게 함 */
            padding: 0.6rem 3%; 
            gap: 0.5rem;
        }
        
        .st-grp {
            gap: 0.8rem; /* 모바일에서 시스템 정보 간격 축소 */
        }

        /* 텍스트가 너무 많아 겹칠 경우 대비해 폰트 사이즈 미세 조절 */
        .st-itm {
            font-size: 0.75rem;
        }
    }

    /* 초소형 기기 대응 */
    @media (max-width: 480px) {
        .st-dot { display: none; } /* 화면이 너무 좁으면 점은 숨겨서 공간 확보 */
        .st-grp:first-child .st-itm:nth-child(2) { display: none; } /* 필요시 DB상태 숨김 */
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
                <span>${name != null ? name : 'Guest'}</span>
            </div>
        </div>
    </footer>

</body>
</html>