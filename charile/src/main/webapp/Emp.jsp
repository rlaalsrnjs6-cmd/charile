<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="emp">
		<input type="hidden" name="mod" value="add">
		<input type="hidden" id="empno" name="empno">
		<input type="text" id="id" name="id">
		<span type="text" id="sid"></span><br>
		<input type="text" id="pw" name="pw">
		<span type="text" id="spw"></span><br>
		<input type="text" id="rpw" name="rpw">
		<span type="text" id="srpw"></span><br>
		<input type="text" id="ename" name="ename">
		<input type="text" id="tel" name="tel">
		<input type="text" id="addr" name="addr">
		<input type="text" name="email">
		<input type="hidden" name="email2" value="@">@
		<input type="text" name="email3">
		<select name="domain">
			<option name="gg">직접입력</option>
			<option name="naver" value="naver.com">naver.com</option>
			<option name="google" value="google.com">google.com</option>
		</select>
		<label>생년월일</label>
		<select><option id="year" class="calendar"></option></select>
		<select><option id="month" class="calendar"></option></select>
		<select><option id="day" class="calendar"></option></select>
		<input type="summit" value="회원가입">
	</form>
	
	<script>
	const year = document.queryselector("#year");
	const month = document.queryselector("#month");
	const day = document.queryselector("#day");
	const Year = new Date().getFullYear();
	//년도
	for(let i=Year; i=1950; i--){
		const calendar = document.createElement(".calendar");
		calendar.value = i;
		calendar.textContent = i;
		year.appendChild(calendar);
	}
	//월
	for(let i=1; i<=12; i++){
		const calendar = document.createElement(".calendar");
		calendar.value = i;
		calendar.textContent = i;
		month.appendChild(calendar);
	}
	//일
	for(let i=1; i<=31; i++){
		const calendar = document.createElement(".calendar");
		calendar.value = i;
		calendar.textContent = i;
		day.appendChild(calendar);
	}
	
	function check_id(){
		let id = document.querySelector('#id')
		let check = /^[a-z][a-z0-9]{4,14}$/
		let mainid = document.querySelector('#mainid').value
	if(!mainid || !check.test(mainid.trim())){
		id.innerText = "첫글자는 소문자, 영소문자와 숫자를 포함한 4글자이상 14이하의 id를 작성해주세요"
		return;
	} else{
		id.innerText = ""
	}
	}
	
	function check_pw(){
		let pw = document.querySelector('#pw')
		let check = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,20}$/
		let mainpw = document.querySelector('#mainpw').value
		if(!mainpw || !check.test(mainpw)){
			pw.innerText = "대문자 소문자 숫자 특수기호를 포함한 8글자 이상 20글자 이하로 작성해주세요 "
				return;
		} else {
			pw.innerText = ""
			}
	}
	
	function check_email(){
		let email = document.querySelector('#email')
		let check = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/
		let mainemail = document.querySelector('#mainemail').value
		
		if(!mainemail || !check.test(mainemail)){
			email.innerText = "이메일 형식에 맞춰 작성하세요"
				return;
		} else {
			email.innerText = ""
		}
	}
	
	function check_name(){
		let name = document.querySelector('#name')
		let check = /^[가-힣]{2,5}$/
		let mainname = document.querySelector('#mainname').value
		
		if(!mainname || !check.test(mainname)) {
			name.innerText = "사람이름을 쓰세요"
				return;
		} else {
			name.innerText = ""
		}
	}
	
	function check_nickname(){
		let nickname = document.querySelector('#nickname')
		let check = /^[가-힣a-zA-Z0-9]{2,10}$/
		let mainnickname = document.querySelector('#mainnickname').value
		if(!mainnickname || !check.test(mainnickname)) {
			nickname.innerText = "특수기호를 포함하지 않은 2~10자의 닉네임을 사용해주세요"
				return;
		} else {
			nickname.innerText = ""
		}
	}
</script>
</body>
</html>