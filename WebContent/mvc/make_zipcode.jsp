<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.bit.vo.ZipcodeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">

	function popup(){
		
		var zip_addr = document.getElementById("popup").value;
		var zipcode = zip_addr.split("!")[0];
		var addr1 = zip_addr.split("!")[1];
		alert(zipcode);
		var zip1 = zipcode.split("-")[0];
		var zip2 = zipcode.split("-")[1];
		document.getElementById("zip1").value=zip1;
		document.getElementById("zip2").value=zip2;
		document.getElementById("addr1").value = addr1;
			
	}
	
	function insert_data(){
		
		opener.document.getElementById("zip1").value = document.getElementById("zip1").value;
		opener.document.getElementById("zip2").value = document.getElementById("zip2").value;
		opener.document.getElementById("addr1").value = document.getElementById("addr1").value;
		opener.document.getElementById("addr2").value = document.getElementById("addr2").value;
		opener.document.getElementById("isZipCheck").value = true;
		self.close();


	}
			

</script>
<body>
	
	<h3>우편 번호 검색</h3>
	<form action="./controller?cmd=searchDong" method="post">

	<input type="text" name="dong" >
	<input type ="submit" value="검색">
	
	</form>
	  
	<br>
	
			<%  ArrayList<ZipcodeVO> list = (ArrayList<ZipcodeVO>)request.getAttribute("list");
				if(list==null){
					list = new ArrayList<ZipcodeVO>();
					out.print("동 이름을 입력세요");
				} else {
						StringBuffer sb = new StringBuffer("<select onchange='popup()' id = 'popup'> <option>주소선택") ; 
						for(ZipcodeVO vo : list){
							sb.append("<option value='"+vo.getZipcode()+"!"+vo.getSido()+" "+vo.getGugun()+" "+vo.getDong()+" "+vo.getRi()+""+vo.getBldg()+"!"+vo.getBunji()+"'>");					
							sb.append(vo.toString());
						}
						sb.append("</select>");
						out.print(sb.toString());
				}
				
			%>
	<br><br>
	
	
	<input type="text" name= "zip1" id="zip1" size="5"> -
	<input type="text" name= "zip2" id="zip2" size="5">		<br><br>
	<input type="text" name= "addr1" id="addr1" size="50"> 	<br><br>
	<input type="text" name= "addr2" id="addr2"size="50"> 	<br><br>
	
	
	<button onclick="insert_data()">사용하기</button>
	
	
			
	
</body>
</html>