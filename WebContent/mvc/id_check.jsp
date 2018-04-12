<%@page import="kr.co.bit.vo.MemberVO"%>
<%@page import="kr.co.bit.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 검사</title>
<style type="text/css">

	#msg{
		color : red;
	}
</style>
<script type="text/javascript">
	
	function work_check(val){
		
		var flag = document.getElementById("id_check").value;
		
		if(flag=="true"){
			
			this.work_close();
			
			
		} else if (flag=="false"){
			
			msg.innerHTML="다른 아이디를 입력해주세요.";
			
		} else if(flag=="null"){
			
			msg.innerHTML="잘못된 입력입니다.";
			
		} else{
			
			msg.innerHTML="아이디 확인 버튼을 눌러주세요";
			
		}
		
		return false;
			
	}
	
	function work_close(){
		//id의 값을 가져오기
		var userid = document.getElementById("id").value;
		opener.document.getElementById("userid").value = userid;
		opener.document.getElementById("isIdCheck").value = true;
		self.close();
	}
</script>
</head>
<body>
<%
	String id = request.getParameter("id");
%>	
ID check
<form action="./controller?cmd=searchId" method="post">
	<br>
	<%
	MemberVO vo=(MemberVO)request.getAttribute("vo");
	String msg="";
	String val="";
	if(id==null||id.equals("")){
		id="";
		msg= "아이디를 입력하세요";
		val="null";
		
	}
	else if(vo==null){
		msg = "사용 가능한 아이디입니다." ;
		val="true";
	} else {
		msg = "이미 존재하는 아이디입니다." ;
		val="false";
	}
	
	out.print(msg+ "<br><br>" );

%>	
	아이디 : <input type="text" name="id" value="<%=id%>" id="id">
	<br>
	<span id="msg"></span>
	<br>
	<input type="submit" value="아이디확인">
	<button onclick="return work_check()">아이디사용하기</button>
	<input type="hidden" name="id_check" value="<%=val%>" id="id_check">
</form>
</body>
</html>