package kr.co.bit.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.ZipcodeVO;

public class ZipcodeDAO {

	
	public boolean insert(String path) {
		
		boolean flag = false;
		ZipcodeVO vo = new ZipcodeVO();
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "insert into zipcode_tbl values(?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		Reader fr;
		
		try {
			fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			int count=0;
			ArrayList<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
			while(true) {
				
				String line = br.readLine();
				if(line==null) {
					break;
				}
				
				String[] zip_info = line.split(",");
				
				for(int i=0; i<zip_info.length; i++) {
					if(zip_info[i].equals("")) {
						zip_info[i]=" ";
					}
				}
				vo = new ZipcodeVO(zip_info[0],zip_info[1],zip_info[2],zip_info[3],zip_info[4],zip_info[5],zip_info[6],zip_info[7]);
				list.add(vo);
				
				
			}
			
				if(con == null) {
					System.out.println("연결 실패");
				}	else {
						stmt = con.prepareStatement(sql);
						
						for(ZipcodeVO vo2 : list) {
						count++;
						flag = true;
						stmt.setString(1, vo2.getSeq());
						stmt.setString(2, vo2.getZipcode());
						stmt.setString(3, vo2.getSido());
						stmt.setString(4, vo2.getGugun());
						stmt.setString(5, vo2.getDong());
						stmt.setString(6, vo2.getRi());
						stmt.setString(7, vo2.getBldg());
						stmt.setString(8, vo2.getBunji());
						
						
						int affectedCount = stmt.executeUpdate();
						if(affectedCount>0) {

							System.out.println(count + "번째 삽입완료");
						}
					}
						
				}
				
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			
			mgr.connectClose(con, stmt, null);
		}
		return flag;
		
	}
	
	public ArrayList<ZipcodeVO> getSearchList(String dong) {
		ArrayList<ZipcodeVO> list =  new ArrayList<ZipcodeVO>();
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "select * from zipcode_tbl where dong like ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//Statement stmt = null;
		try {
			//stmt= con.createStatement();  // createStatement는 통로를 만듦과 동시에 sql문을 보낼 수 없다.
			pstmt= con.prepareStatement(sql);  // 그러나 prepared~ 는 만듦과 동시에 sql문을 보낼 수 있다.
			pstmt.setString(1, "%"+dong+"%");   // prepared로 선언해서  ?꼴로 sql 작성하고 이렇게 물음표에 원하는 검색어를 넣어 줄 수 도 있다.(검색하고 싶은 필드가 많아질 수록 이 방식이 유리) 
			//rs = pstmt.executeQuery(sql);     // create~ 로 선언했을때는 sql을 excutequary에 넣어줘야한다.
			rs = pstmt.executeQuery();   //그러나 prepared~로 햇을땐 sql을 한번더 excute 해주면 중복쿼리 전달로 오류가 난다.  
			ZipcodeVO vo = null;
			while(rs.next()) {
				vo = new ZipcodeVO();
				vo.setSeq(rs.getString(1));  // 숫자 대신 컬럼명으로 불러올 수도 있다.
				vo.setZipcode(rs.getString(2));
				vo.setSido(rs.getString(3));
				vo.setGugun(rs.getString(4));
				vo.setDong(rs.getString(5));
				vo.setRi(rs.getString(6));
				vo.setBldg(rs.getString(7));
				vo.setBunji(rs.getString(8));
				
				list.add(vo);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, pstmt, rs);
		}
		
		return list;
	}
}
		
	/*	if(con == null) {
			System.out.println("연결 실패");
		}	else {
			
			
				flag = true;
		
		
			try {
				stmt = con.prepareStatement(sql);
				stmt.setString(1, vo.getZipcode());
				stmt.setString(2, vo.getSido());
				stmt.setString(3, vo.getGugun());
				stmt.setString(4, vo.getDong());
				stmt.setString(5, vo.getRi());
				stmt.setString(6, vo.getBldg());
				stmt.setString(7, vo.getBunji());
				stmt.setString(8, vo.getSeq());
				
				int affectedCount = stmt.executeUpdate();
				if(affectedCount>0) {

					System.out.println("삽입완료");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
				mgr.connectClose(con, stmt, null);
			
			}
			
		}
			
		return flag;*/

