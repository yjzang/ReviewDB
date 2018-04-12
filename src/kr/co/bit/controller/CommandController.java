package kr.co.bit.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.dao.MemberDAO;
import kr.co.bit.dao.ZipcodeDAO;
import kr.co.bit.vo.MemberVO;
import kr.co.bit.vo.ZipcodeVO;

public class CommandController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		String cmd = request.getParameter("cmd"); 
		cmd= cmd==null?cmd="":cmd;
		String url = "./mvc/home.jsp";
		
		if(cmd.equals("viewRegist")) {
			
			url = "./mvc/regist_member.jsp";
			
		} else if(cmd.equals("regist")) {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String zip1 = request.getParameter("zip1");
		String zip2 = request.getParameter("zip2");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String tool = request.getParameter("tool");
		String project = request.getParameter("project");
		String[] langs = request.getParameterValues("lang");
		/*String[] temp = {"","","",""};
		for(String index : langs){
			int idx = Integer.parseInt(index);
			temp[idx] = index;
		}*/
		//MemberVO 데이터 클래스를 만들어서 인스턴스를 하나 생성
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setEmail(email);
		vo.setZipcode(zip1+"-"+zip2);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setTool(tool);
		vo.setProject(project);
		vo.setLangs(langs);
			
		MemberDAO dao = new MemberDAO();
		boolean flag = dao.insert(vo);
			if(flag) {
				url="./mvc/home.jsp";
			}	else {
				url="./mvc/error.jsp";
			}
		request.setAttribute("message", "success");
		//response.sendRedirect("storage.jsp");
		
		
		} else if(cmd.equals("serachAll")) {
			
			MemberDAO dao = new MemberDAO();
			ArrayList<MemberVO> list = null;
			list= dao.selectAll();
			request.setAttribute("list",list);
			url="./mvc/searchAll.jsp";
			
		} else if(cmd.equals("search")) {
			MemberDAO dao = new MemberDAO();
			String id = request.getParameter("id");
			MemberVO vo = dao.selectCondition(id);
			request.setAttribute("vo",vo);
			url="./mvc/regist_member.jsp";
			
		} else if (cmd.equals("searchDong")){
			String dong = request.getParameter("dong");
	//		dong = new String(dong.getBytes("ISO-8859-1"),"UTF-8");  // 한글 처리하는 방법 2
			System.out.println(dong);
			ZipcodeDAO dao = new ZipcodeDAO();
			ArrayList<ZipcodeVO> list = null;
			list = dao.getSearchList(dong);
			request.setAttribute("list", list);
			url= "./mvc/make_zipcode.jsp";
					
		} else if(cmd.equals("openZipcode")) {
			
			url = "./mvc/make_zipcode.jsp";
			
		}  else if(cmd.equals("openID")) {
			
			url = "./mvc/id_check.jsp";
		}
			
		 else if(cmd.equals("searchId")) {
			MemberDAO dao = new MemberDAO();
			String id = request.getParameter("id");
			MemberVO vo = dao.selectCondition(id);
			request.setAttribute("vo",vo);
			url="./mvc/id_check.jsp?id="+id;
			
		} else if(cmd.equals("viewIdService")) {
			
			url= "./mvc/id_service.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, resp);
	}


}
