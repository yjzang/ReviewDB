<%@page import="kr.co.bit.vo.MemberVO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	MemberVO vo = (MemberVO) request.getAttribute("vo");
	String temp1 = vo.getId();
	String temp2 =vo.getPw();
	String temp3 =vo.getName();
	String temp4 =vo.getEmail();
	String temp5 = vo.getZipcode();
	String temp6 = vo.getAddr1();
	String temp7 = vo.getAddr2();
	String temp8 = vo.getTool();
	String[] temp9 = vo.getLangs(); 
	String temp10  = vo.getProject();
	out.print(temp1+","+temp2+","+temp3+","+temp4+","+temp5
						+","+temp6+","+temp7+","+temp8+","+Arrays.toString(temp9)+","+temp10);
	



%>
</body>
</html>