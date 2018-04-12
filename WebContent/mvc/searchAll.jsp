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

	ArrayList<MemberVO> list = (ArrayList<MemberVO>) request.getAttribute("list");
	
	for(int i=0; i<list.size();i++) {
		
		String temp1 = list.get(i).getId();
		String temp2 =list.get(i).getPw();
		String temp3 =list.get(i).getName();
		String temp4 =list.get(i).getEmail();
		String temp5 =list.get(i).getZipcode();
		String temp6 =list.get(i).getAddr1();
		String temp7 =list.get(i).getAddr2();
		String temp8 =list.get(i).getTool();
		String[] temp9 =list.get(i).getLangs();
		String temp10 =list.get(i).getProject();
		out.println(temp1+","+temp2+","+temp3+","+temp4+","+temp5
							+","+temp6+","+temp7+","+temp8+","+	Arrays.toString(temp9)+","+temp10);
%>
		<br>	
<%		
	}

%>
</body>
</html>