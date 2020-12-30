package kr.or.kh.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.kh.board.BoardDTO;

public class MemberDAO {
	private Connection conn;
	private MemberDTO memberDTO;
	private MemberDAO memberDAO;
	private PreparedStatement pstmt;
	private String sql;
	private int cnt;
	private ResultSet rs;
	private ArrayList<MemberDTO> memberList;
	private ArrayList<String> memberIdCheck;
	
	public MemberDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://underdogb.cafe24.com:3306/underdogb", "underdogb","khacademy1!");
		return conn;
	}
	
	public int memberRegister(MemberDTO memberDTO) throws SQLException {
		conn = getConnection();
		sql = "insert into NJmember(id, pw, addr, tel) values (?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberDTO.getId());
		pstmt.setString(2, memberDTO.getPw());
		pstmt.setString(3, memberDTO.getAddr());
		pstmt.setString(4, memberDTO.getTel());
		cnt = pstmt.executeUpdate();
		return cnt;
	}
	
	public ArrayList<MemberDTO> memberList() throws SQLException {
		conn = getConnection();
		sql = "select id, pw, addr, tel from NJmember";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		memberList = new ArrayList <MemberDTO>();
		while(rs.next()) {
			memberDTO = new MemberDTO();
			memberDTO.setId(rs.getString("id"));
			memberDTO.setPw(rs.getString("pw"));
			memberDTO.setAddr(rs.getString("addr"));
			memberDTO.setTel(rs.getString("tel"));
			memberList.add(memberDTO);
		}
		return memberList;
	}
	
	public int memberDelete(String deleteId, String deletePw) throws SQLException {
		conn = getConnection();
		sql = "delete from NJmember where id=? and pw=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, deleteId);
		pstmt.setString(2, deletePw);
		cnt = pstmt.executeUpdate();
		return cnt;
	}
	
	   public MemberDTO memberLogin(String id, String pw) throws SQLException {
		      conn = getConnection();
		      sql = "select id,pw from NJmember where id=?";
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, id);
		      rs = pstmt.executeQuery();
		      while(rs.next()){
		         memberDTO.setId(rs.getString("id"));
		         memberDTO.setPw(rs.getString("pw"));
		      }
		      return memberDTO;
		   }
	   

	   public ArrayList<String> memberIdCheck(String telSearch) throws SQLException {
		   conn = getConnection();
		   sql = "select id from NJmember where tel=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, telSearch);
		   rs = pstmt.executeQuery();
		   memberIdCheck = new ArrayList<String>();
		   while(rs.next()) {
			   memberDTO.setId(rs.getString("id"));
		         String id = memberDTO.getId();
		         memberIdCheck.add(id);
		      }
		      return memberIdCheck;

	   }
	   
	   public String memberPwCheck(String idSearch) throws SQLException {
		   conn = getConnection();
		   String sql = "select pw from NJmember where id=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, idSearch);
		   rs = pstmt.executeQuery();
		   String pw = null;
		   while(rs.next()) {
			   pw = rs.getString("pw");
		   }
		   return pw;
	   }
	   
	   public MemberDTO memberUpdateConfirm(String idSearch) throws SQLException {
		   conn = getConnection();
		   sql = "select id, pw, addr, tel from NJmember where id=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, idSearch);
		   rs = pstmt.executeQuery();
		   while(rs.next()) {
			   memberDTO.setId(rs.getString("id"));
			   memberDTO.setPw(rs.getString("pw"));
			   memberDTO.setAddr(rs.getString("addr"));
			   memberDTO.setTel(rs.getString("tel"));
		   }
		   return memberDTO;
	   }
	   
	   public int memberUpdateFinal(MemberDTO memberDTO, String idSearch) throws SQLException {
		   conn = getConnection();
		   sql = "update NJmember set id=?, pw=?, addr=?, tel=? where id=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, memberDTO.getId());
		   pstmt.setString(2, memberDTO.getPw());
		   pstmt.setString(3, memberDTO.getAddr());
		   pstmt.setString(4, memberDTO.getTel());
		   pstmt.setString(5, idSearch);
		   cnt = pstmt.executeUpdate();
		   return cnt;
	   }
}
