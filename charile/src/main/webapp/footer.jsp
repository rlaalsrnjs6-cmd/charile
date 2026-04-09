<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
        body {
            margin: 0;
        }

        .f-logo,
        .f-mid,
        .f-top {
            flex-shrink: 0;
        }

        #footer-all {
            background-color: #F5F5F5;
            box-sizing: border-box;
            border-top: 2px solid black ;
            border-bottom: 2px solid black ;
            

            height: 175px;
            width: 100%;
            display: flex;


        }

        .f-top {
            height: 30%;
            width: 35%;
            margin-left:60%;


        }

        .f-logo {

            height: 100%;
            width: 16%;
            display: inline-flex;
            justify-content: center;
            align-items: center;
        }

        .f-logo img {
            height: 100%;
            width: 80%;
            object-fit: cover;
            object-position: center;

        }

        .f-top-ul {
            list-style: none;
            display: flex;
            gap: 20px;

        }

        .f-top-ul a {
            color: black;
            text-decoration: none;
            font-weight: 500;
        }

        .f-mid {
            height: 40%;
            width: 100%;
            margin-top: 1%;
            align-self: flex-end;
            font-size: 1.1rem;
            font-weight: 600;
        }
        .gary, .f-bottom{
            color: gray;
        }
        .f-bottom{
            display: flex;
            justify-self: center;
        }
    </style>
</head>
<body>
    <div id="footer-all">
        <div class="f-logo">
            <img src="${pageContext.request.contextPath}/assets/img/로고-배경제거.png" alt="찰리 MES">
        </div>

        <div>

            <div class="f-top">
                <ul class="f-top-ul">
                    <li><a href="">개인정보 처리방침</a></li>
                    <li><a href="">이용약관</a></li>
                    <li><a href="">고객센터</a></li>
                </ul>
            </div>


            <div class="f-mid">
                <span>(주)Charlie | MES</span>&emsp;
                <span class="gary">관리자</span>&nbsp;<span>(주)Charlie</span>&emsp;
                <span class="gary">대표전화</span>&nbsp;<span>1566-0123</span>&emsp;
                <span class="gary">이메일</span>&nbsp;<span>cvb5123@nate.com</span>&emsp;
                <span class="gary">사업자등록번호</span>&nbsp;<span>667-00-01234</span>&emsp;
            </div>

            <div class="f-bottom">
                <span>@ 2026 CHARRLIE. All  rights reserved.</span>
            </div>
        </div>
    </div>
</body>
</html>