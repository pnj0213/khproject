<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>강아지 테스트</title>
</head>
<body>
<h1>당신이 좋아하는 강아지를 선택하세요</h1>
<form action="ChoiceDog" method="post">
<input type="checkbox" name="dog" value="images/pu.jpg">푸들
<input type="checkbox" name="dog" value="images/gold.jpg">골든리트리버
<input type="checkbox" name="dog" value="images/siba.jpg">시바견
<input type="checkbox" name="dog" value="images/gray.jpg">그레이하운드
<input type="submit" value="선택">
</form>
</body>
</html>
