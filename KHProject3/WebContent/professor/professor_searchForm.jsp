<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.kh01 {
	position: absolute;
	width: 60px; height: 50px;
	top: 70px; left : 290px;
}

ul {list-style-type: none;}
</style>
<meta charset="UTF-8">
<title>KHJSP</title>
</head>
<body>
<h1>교수검색</h1>
   <div id="professorSearch">
      <form action="../professorSearch.do" method="get">
         <ul>
            <li><label for="찾을이름">찾을이름</label> 
            <input type="text"  name="irum"></li>
            <li><input type="image" src="../images/search.png" class="kh01">
            </li>
         </ul>
      </form>
      &nbsp;&nbsp;&nbsp; <a href="professorList.jsp">교수목록</a>
   </div>
</body>
</html>