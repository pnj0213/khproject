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
ul { list-style-type: none; }
.kh01 {
	position : absolute;
	top: 10px; left: 290px;
	width: 50px; height: 40px;
}
</style>
<meta charset="UTF-8">
<title>KHJSP</title>
</head>
<body>
<form action="../idCheckConfirm.mb" method="get">
<ul>
	<li><label for="전화번호">전화번호</label>
		<input type="text" name="tel" required="required" autofocus="autofocus" placeholder="전화번호를 입력하세요">	
	</li>
	<li><input type="image" src="../images/search.png" class="kh01">
	</li>
</ul>
</form>
</body>
</html>