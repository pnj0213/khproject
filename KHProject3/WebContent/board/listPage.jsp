<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

#boardlist {
	position: absolute;
	top: 250px;
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

</style>
<meta charset="UTF-8">
<title>KHJSP</title>
</head>
<body>
<div id="boardlist">
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
   <c:forEach items="${list }" var="board">
   <tr>
      <td><a href="editForm.bo?no=${board.no }">${board.no }</a></td>
      <td>${board.title }</td>
      <td>${board.content }</td>
      <td>${board.author }</td>
      <td>${board.nal }</td>
      <td>${board.readCount }</td>
      <td><a href="boardDelete.bo?no=${board.no }">삭제</a></td>
   </tr>
   </c:forEach>   
   <tr>
      <td colspan="7"><jsp:include  page="page.jsp"  flush="true" /></td>
   </tr>
</table>
		<a href="index.jsp?page=board/boardSearchForm">검색</a>&nbsp;&nbsp; 
		<a href="index.jsp?page=board/boardUpdateForm">수정</a>&nbsp;&nbsp; 
		<a href="index.jsp?page=board/boardWrite" class="write">글쓰기</a>
</div>
</body>
</html>