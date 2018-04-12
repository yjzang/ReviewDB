<%@page import="kr.co.bit.vo.MemberVO"%>
<%@page import="kr.co.bit.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	String id = request.getParameter("cmd");
	MemberDAO dao = new MemberDAO();
	MemberVO vo = dao.selectCondition(id);
	if(vo==null){
		vo = new MemberVO();
	}
	String result = " ";	
	if(id.equals(vo.getId())){
		result = "false";	
	} else {
		result = "true";
	}
	String json = "{\"user\" : \"admin\",\"message\": \"success\"}";
	out.print(json);
%>