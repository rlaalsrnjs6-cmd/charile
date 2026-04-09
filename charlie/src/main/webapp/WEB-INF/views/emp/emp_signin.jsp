<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="emp">
		<input type="hidden" name="mod" value="add">
		<input type="text" name="empno" value="0"><br>
		<input type="text" id="id" onblur="check_id()" name="id" value="rlaalsrnjs1">
		<span type="text" id="sid" style="color:red"></span><br>
		<input type="text" id="pw" onblur="check_pw()" name="pw" value="Rlaalsrnjs1@">
		<span type="text" id="spw" style="color:red"></span><br>
		<input type="text" id="rpw" onblur="check_rpw()" name="rpw" value="Rlaalsrnjs1@">
		<span type="text" id="srpw" style="color:red"></span><br>
		<input type="text" id="ename" name="ename" value="김민권"><br>
		<input type="text" id="tel" name="tel" value="01067077558"><br>
		<input type="text" id="addr" name="addr" value="경상남도 창원시 성산구 삼동로 128번길 49"><br>
		<input type="text" id="email" onblur="check_id()" name="email" value="rlaalsrnjs1">
		<span type="text" id="semail" style="color:red"></span>
		<input type="hidden" name="email2" value="@">@
		<input type="text" id="domain" name="email3">
		<select name="domain" id="domain_list" onchange="setDomain()">
			<option name="gg" value="">직접입력</option>
			<option name="naver" value="naver.com">naver.com</option>
			<option name="google" value="google.com">google.com</option>
		</select><br>
		생년월일<input type="date" name="birthday"><br>
		<input type="submit" value="회원가입">
	</form>
	
	<script>
	
	function check_id(){
		let sid = document.querySelector('#sid')
		let check = /^[a-z][a-z0-9]{4,14}$/
		let id = document.querySelector('#id').value
	if(!id || !check.test(id.trim())){
		sid.innerText = "첫글자는 소문자, 영소문자 숫자를 사용한 4글자이상 14이하의 id를 작성해주세요"
		return;
	} else{
		sid.innerText = ""
	}
	}
	
	function check_pw(){
		let spw = document.querySelector('#spw')
		let check = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=-]).{8,20}$/
		let pw = document.querySelector('#pw').value
		if(!pw || !check.test(pw)){
			spw.innerText = "대문자 소문자 숫자 특수기호를 포함한 8글자 이상 20글자 이하로 작성해주세요 "
				return;
		} else {
			spw.innerText = ""
			}
	}
	
	function check_rpw(){
		let srpw = document.querySelector('#srpw')
		let rpw = document.querySelector('#rpw').value
		let pw = document.querySelector('#pw').value
		if(pw!=rpw){
			srpw.innerText = "입력하신 비밀번호와 다릅니다 확인해주세요"
				return;
		} else {
			srpw.innerText = ""
			}
	}
	
	function check_email(){
		let semail = document.querySelector('#semail')
		let check = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/
		let email = document.querySelector('#email').value
		
		if(!email || !check.test(email)){
			semail.innerText = "이메일 형식에 맞춰 작성하세요"
				return;
		} else {
			semail.innerText = ""
		}
		
	}
		function setDomain(){
			let domain_list = document.querySelector('#domain_list');
			let option = domain_list.options[domain_list.selectedIndex].value;
			
			let domain = document.querySelector('#domain');
				domain.value = option;
			if(option==''){
				console.log('집접입력');
				domain.value='';
			}
		}
	
</script>
</body>
</html>