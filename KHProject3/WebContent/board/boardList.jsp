<%@page import="kr.or.kh.board.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body {
   	background-image: url("images/backg.png");
   	background-repeat: no-repeat;
   	background-size: 2000px;
	background-attachment: fixed;
	width: 1500px; height: 1000px;
	}
	
#boardList {
	position: absolute;
	top: 300px;
	left: 420px;
	width: 800px;
}

table {
	border: 0px solid lightgray;
	border-collapse: collapse;
	width: 700px;
}

th {
	border: 1px solid lightgray;
	background-color: lightblue;
	color: white;
	border-bottom: 2px solid #0067A3;
}

.List {
	background-color: white;
	text-align: center;
}

.write {
	position: absolute;
	left: 100px;
}
</style>
<meta charset="UTF-8">
<title>KHJSP</title>
</head>
<body>
	<div id="boardList">
	<h1>커뮤니티 목록</h1>
		<table border="1" cellspacing="0" cellpadding="0">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>&nbsp;</th>
			</tr>
			<%
			 ArrayList<BoardDTO> boardList=(ArrayList<BoardDTO>)request.getAttribute("boardList");
		    for(int i=0;i<boardList.size();i++){
		       BoardDTO boardDTO = boardList.get(i);       

			%>
			<tr>
				<td><%=boardDTO.getNo()%></td>
				<td><%=boardDTO.getTitle()%></td>
				<td><%=boardDTO.getContent()%></td>
				<td><%=boardDTO.getAuthor()%></td>
				<td><%=boardDTO.getNal()%></td>
				<td><%=boardDTO.getReadCount()%></td>
				<td><a href="boardDelete.bo?no=<%=boardDTO.getNo()%>">삭제</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<a href="index.jsp?page=board/boardSearchForm">검색</a>&nbsp;&nbsp; <a
			href="index.jsp?page=board/boardUpdateForm">수정</a>&nbsp;&nbsp; <a
			href="index.jsp?page=board/boardWrite" class="write">글쓰기</a>

	</div>
</body>
</html>