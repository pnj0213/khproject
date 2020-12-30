package kr.or.kh.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.mb")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDTO memberDTO;
	private MemberDAO memberDAO;
	private int cnt;
	private ArrayList<MemberDTO> memberList;
	private ArrayList<String> memberIdCheck;
	public MemberServlet() {
		memberDTO = new MemberDTO();
		memberDAO = new MemberDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		HttpSession session = request.getSession();
		
		if(command.equals("/memberRegister.mb")) {
			memberDTO.setId(request.getParameter("id"));
			memberDTO.setPw(request.getParameter("pw"));
			memberDTO.setAddr(request.getParameter("addr"));
			memberDTO.setTel(request.getParameter("tel"));
			try {
				cnt = memberDAO.memberRegister(memberDTO);
				out.print(cnt + "건 회원이 등록됨");
				response.sendRedirect("memberList.mb");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/memberList.mb")) {
			try {
				memberList = memberDAO.memberList();
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp?page=member/memberList");
				request.setAttribute("memberList", memberList);
				dis.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
        else if(command.equals("/memberLogin.mb")) {
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            try {
               memberDTO = memberDAO.memberLogin(id, pw);
            } catch (SQLException e) {
               e.printStackTrace();
            }
            if(!id.equals(memberDTO.getId())){
               out.print("아이디를 다시 확인해주세요");
            }else if(!pw.equals(memberDTO.getPw())){
               out.print("패스워드가 틀립니다.");
            }else {
               out.print(id+"님 환영합니다. 로그인되었습니다.");
               session = request.getSession();
               session.setAttribute("id", id);
               session.setAttribute("pw", pw);
            }
            response.sendRedirect("index.jsp");
         }
        else if(command.equals("/memberLogout.mb")) {
            session.invalidate();
            response.sendRedirect("index.jsp");
         }
		
        else if(command.equals("/memberDelete.mb")) {
        	String deleteId = (String)session.getAttribute("id");
    		String deletePw = request.getParameter("pw");
    		int cnt;
			try {
				cnt = memberDAO.memberDelete(deleteId, deletePw);
				if(cnt == 0) {
					out.print("비밀번호가 틀렸습니다.<br>");
					out.print("<a href=index.jsp?page=member/memberOutForm>돌아가기</a>");
				}
				else if(cnt == 1) {
					out.print("<script>alert('정상적으로 탈퇴되었습니다.'); location.href='index.jsp'</script>"); 
					session.invalidate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        else if(command.equals("/idCheckConfirm.mb")) {
        	String telSearch = request.getParameter("tel");
        	 try {
                 memberIdCheck = memberDAO.memberIdCheck(telSearch);
                 out.print("<br>입력하신 전화번호로 검색된 아이디입니다.<br>");
                 for (int i = 0; i < memberIdCheck.size(); i++) {
                    out.print("<br>" + memberIdCheck.get(i) + "<br>");
                 }
                 out.print("<br><a href=index.jsp?page=member/loginForm>로그인하기</a><br>");

              } catch (SQLException e) {
                 e.printStackTrace();
              }
           }
        else if(command.equals("/passCheckConfirm.mb")) {
        	String idSearch = request.getParameter("id");
        	try {
				String pw = memberDAO.memberPwCheck(idSearch);
				out.print("찾으시는 비밀번호는 "+pw+" 입니다.<br>");
				out.print("<a href=index.jsp>메인으로</a>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        else if(command.equals("/memberUpdateConfirm.mb")) {
        	String idSearch = request.getParameter("id");
				try {
					memberDTO = memberDAO.memberUpdateConfirm(idSearch);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dis = request.getRequestDispatcher("index.jsp?page=member/memberUpdateConfirm");
				request.setAttribute("memberDTO", memberDTO );
				dis.forward(request, response);
        }
        else if(command.equals("/memberUpdateFinal.mb")) {
        	memberDTO.setId(request.getParameter("id"));
        	memberDTO.setPw(request.getParameter("pw"));
        	memberDTO.setAddr(request.getParameter("addr"));
        	memberDTO.setTel(request.getParameter("tel"));
        	String idSearch = request.getParameter("idSearch");
        	try {
				cnt = memberDAO.memberUpdateFinal(memberDTO, idSearch);
				session.setAttribute("id", memberDTO.getId());
				response.sendRedirect("memberList.mb");
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

	}

}
