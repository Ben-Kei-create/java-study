<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編集画面</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
	<body>
		<div class="main-contents">
			<div class="header">
				<!-- ログイン情報がなければ表示 -->
				<c:if test="${ empty loginUser }">
					<a href="login">ログイン</a>
					<a href="signup">登録する</a>
				</c:if>
				<!-- ログイン情報があれば表示 -->
				<c:if test="${ not empty loginUser }">
					<a href="./">ホーム</a>
					<a href="setting">設定</a>
					<a href="logout">ログアウト</a>
				</c:if>
			</div>
			<!-- エラーメッセージを表示する -->
			<c:if test="${ not empty errorMessages }">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMessages}" var="errorMessage">
							<li><c:out value="${errorMessage}" /></li>
						</c:forEach>
					</ul>
				</div>
			</c:if><br />
			<div class="form-area">
				<form action="edit" method="POST">
					<textarea name="edited_text" cols="80" rows="5" class="edit-box"><c:out value="${editMessage.text}" /></textarea>
					<br />
					<input type="hidden" name="edit_id" value="${editMessage.id}">
					<input type="submit" value="更新">
				</form>
			</div>
			<div class="copyright">Copyright(c)FumiakiMogi</div>
		</div>
	</body>
</html>