package kr.co.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.day4.MemberVO;

public class MemberDAO {

	
	
	public boolean insert(MemberVO vo) {
		boolean flag = false;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "insert into member_tbl values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt=null; //compile을 한번만 하고 데이터를 양식에 맞게 보낼 수 있다?  그냥 statement를 쓰고 하드코딩을 하면 보낼때마다 컴파일된다
		if( con == null)
			System.out.println("연결 노노");
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPw());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getEmail());
			stmt.setString(5, vo.getZipcode());
			stmt.setString(6, vo.getAddr1());
			stmt.setString(7, vo.getAddr2());
			stmt.setString(8, vo.getTool());
			String[] temp = vo.getLangs();
			StringBuffer sb = new StringBuffer(temp[0]);
			for(int i=1; i<temp.length;i++) {
				// - 구분자를 이용하여 구현
				sb.append("-"+temp[i]);
			}
			stmt.setString(9, sb.toString());
			stmt.setString(10, vo.getProject());
			
			int affectedCount = stmt.executeUpdate(); // affectedCount - 삽입이 실행 되면 1이됨
			if(affectedCount>0){
				flag=true;
				System.out.println("삽입완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {   //finally는 무조건 실행
			mgr.connectClose(con, stmt, null);
		}
		return flag;
	
		
	}
	
	public ArrayList<MemberVO> selectAll(){
		
		ArrayList<MemberVO> list = null;
		//테이블 접속 코드
		//Department 의
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion(); // connection을 받아온다. 
		if(con!=null){
		Statement stmt=null;   // 쿼리문 보낼 준비(코드가 지나가는 길 만들어 주기)
		
		try {
			stmt = con.createStatement();   // 코드가 지나가는 길 만들어  주고
			String sql = "select * from MEMBER_TBL";      // 쿼리문 작성하고
			ResultSet rs = stmt.executeQuery(sql);			// statement 를 통해 쿼리문 시행시키고 그결과를 rs에 대입
			list = new ArrayList<MemberVO>();
			MemberVO dao = null;
			while(rs.next()) {   // Resultset 객체에는 결과물을 받아내는 방식에 대한 메소드가 들어있음 
				dao = new MemberVO();
				dao.setId(rs.getString(1));
				dao.setPw(rs.getString(2));
				dao.setName(rs.getString(3));
				dao.setEmail(rs.getString(4));
				dao.setZipcode(rs.getString(5));
				dao.setAddr1(rs.getString(6));
				dao.setAddr2(rs.getString(7));
				dao.setTool(rs.getString(8));
				String temp = rs.getString(9); //langs
				//배열로 변환하는 코드 작성
				String[] langs = temp.split("-");
				String[] vals = {"","","",""};
				
				for(String index : langs) {
					int idx = Integer.parseInt(index);
					vals[idx] =index;
				}
				dao.setLangs(langs);
				
				
				dao.setProject(rs.getString(10));
				list.add(dao);
			}
			
			
			
			mgr.connectClose(con,stmt,rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		return list;
		
	}
	
	public MemberVO selectCondition(String id){
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		Statement stmt = null;
		MemberVO dao = null;
		if(con!=null) {
			 try {
				stmt=con.createStatement();
				String sql = "SELECT * from MEMBER_TBL where user_id ='" +id+"'";
				ResultSet rs = stmt.executeQuery(sql);
			
				while(rs.next()) { 
					if(rs.getString(1).equals(id)) {
						dao = new MemberVO();
						dao.setId(rs.getString(1));
						dao.setPw(rs.getString(2));
						dao.setName(rs.getString(3));
						dao.setEmail(rs.getString(4));
						dao.setZipcode(rs.getString(5));
						dao.setAddr1(rs.getString(6));
						dao.setAddr2(rs.getString(7));
						dao.setTool(rs.getString(8));
						String temp = rs.getString(9); //langs
						//배열로 변환하는 코드 작성
						String[] langs = temp.split("-");
						String[] vals = {"","","",""};
						
						for(String index : langs) {
							int idx = Integer.parseInt(index);
							vals[idx] =index;
						}
						dao.setLangs(langs);
						
					
						dao.setProject(rs.getString(10));
						
					} else {
							dao=null;
					}
				}
				
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		
		
		return dao;
	}

}
