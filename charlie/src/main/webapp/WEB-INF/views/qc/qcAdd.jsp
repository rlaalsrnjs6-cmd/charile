<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="qc" onsubmit="return back(event)">
	<table border=1>
		<tr>
			<th>qc번호</th>
			<th>로트번호</th>
			<th>사원번호</th>
		</tr>
		<tr>
			<input type="hidden" name="mod" value="add">
			<td><input type="text" id="qc" name="qc_num" onblur="qc_check()"></td>
			<td><input type="text" id="lot" name="lot_num" onblur="lot_check()"></td>
			<td><input type="text" id="emp" name="empno" onblur="emp_check()"></td>
		</tr>
		<input type="submit" value="작성">
	</table>
			<span type="text" ID="qc_ch" style="color:red"></span>
			<span type="text" ID="lot_ch" style="color:red"></span>
			<span type="text" ID="emp_ch" style="color:red"></span>
	</form>
	
	<script>
		function qc_check(){
			const qc = document.querySelector('#qc').value
			const qc_ch = document.querySelector('#qc_ch');
			const num_check = /^[0-9]*$/
			if(!qc || !num_check.test(qc.trim())){
				qc_ch.innerText = " qc번호를 확인해주세요 ";
					return;
			} else{
				qc_ch.innerText = "";
			}
		}
		
		function lot_check(){
			const lot = document.querySelector('#lot').value
			const lot_ch = document.querySelector('#lot_ch');
			const num_check = /^[0-9]*$/
			if(!lot || !num_check.test(lot.trim())){
				lot_ch.innerText = " 로트번호를 확인해주세요 ";
					return;
			} else{
				lot_ch.innerText = "";
			}
		}
		
		function emp_check(){
			const emp = document.querySelector('#emp').value
			const emp_ch = document.querySelector('#emp_ch');
			const num_check = /^[0-9]*$/
			if(!emp || !num_check.test(emp.trim())){
				emp_ch.innerText = " 사원번호를 확인해주세요 ";
					return;
			} else{
				emp_ch.innerText = "";
			}
		}
		
		async function back(event){
			event.preventDefault();		
			
			const qc = document.querySelector('#qc').value
			const lot = document.querySelector('#lot').value
			const emp = document.querySelector('#emp').value
			const num_check = /^[0-9]*$/
			if(!qc || !num_check.test(qc.trim()) ||
				!lot || !num_check.test(lot.trim()) ||
				!emp || !num_check.test(emp.trim())){
				alert('입력란을 확인하세요');
				return;
			} else {
				const response = await fetch('/charlie/lot?lot_num=' + lot + '&mod=fetch')
				const data = await response.text()
					if(data === "false"){
						event.preventDefault();		
						alert('존재하지않는 lot번호 입니다');
					} else {
						alert('성공');
						event.target.submit();
					}
				}
			}
		
		
		
	</script>
</body>
</html>