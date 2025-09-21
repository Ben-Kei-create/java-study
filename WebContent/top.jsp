<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>簡易Twitter</title>
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
			<!-- 絞り込み機能実装 -->
			<div class="scope-area">
				<form action="./" method="get">
					日付
					<input type="date" name="start" value="${start}">～
					<input type="date" name="end" value="${end}">
					<input type="submit" value="しぼりこみ">
				</form>
			</div>
			<!-- loginUserが空でない場合に表示  -->
			<c:if test="${ not empty loginUser }">
				<div class="profile">
					<div class="name">
						<h2>
							<c:out value="${loginUser.name}" />
						</h2>
					</div>
					<div class="account">
						@
						<c:out value="${loginUser.account}" />
					</div>
					<div class="description">
						<c:out value="${loginUser.description}" />
					</div>
				</div>
			</c:if>
			<!-- ログインでない場合は表示されない -->
			<c:if test="${ not empty errorMessages }">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMessages}" var="errorMessage">
							<li><c:out value="${errorMessage}" /></li>
						</c:forEach>
					</ul>
				</div>
			<c:remove var="errorMessages" scope="session" />
			</c:if>
			
			<!--  つぶやきフォーム  -->
			<div class="form-area">
				<c:if test="${ isShowMessageForm }">
					<form action="message" method="post">
						いま、どうしてる？<br />
						<textarea name="text" cols="95" rows="5" class="text-box"></textarea>
						<br /><input type="submit" value="つぶやく">（140文字まで）
					</form>
				</c:if>
			</div>
			<!-- メッセージを表示 -->
			<div class="messages">
				<c:forEach items="${messages}" var="message">
					<div class="message">
						<div class="account-name">
							<span class="account">
								<a href="./?user_id=<c:out value="${message.userId}"/> "> <c:out value="${message.account}" /></a>
							</span><span class="name"><c:out value="${message.name}" /></span>
						</div>
						<div class="text">
							<pre><c:out value="${message.text}" /></pre>
						</div>
						<div class="date">
							<fmt:formatDate value="${message.createdDate}" pattern="yyyy/MM/dd HH:mm:ss" />
						</div>
						<!-- ログインユーザーである(and)自分のidとメッセージに紐づけられているuserIdが一致(＝)している時、表示する-->
						<c:if test="${not empty loginUser and loginUser.id eq message.userId}">
							<!-- 編集機能ボタン実装 //messagesテーブルのidをedit_idとして送る -->
							<form action="edit" method="GET">
								<input type="hidden" name="edit_id" value="${message.id}">
								<input type="submit" value="編集">
							</form>
							<!-- 削除機能ボタン -->
							<form action="deleteMessage" method="post">
								<input type="hidden" name="deleted_id" value="${message.id}">
								<input type="submit" value="削除">
							</form>
						</c:if>
						<!--  返信テキストエリア -->
						<c:if test="${ not empty loginUser }">
							<div class="comment-area">
								☝の返信
								<form action="comment" method="post">
									<textarea name="comment" cols="95" rows="5" class="text-box"></textarea>
									<input type="hidden" name="comment_id" value="${message.id}">
									<br /><input type="submit" value="へんしん">
								</form>
							</div>
						</c:if>
						<c:forEach items="${comments}" var="comment">
							<c:if test="${ comment.messageId eq message.id }">
							<div class="account-name">
								<span class="name">
									返信した人の名前：<c:out value="${comment.name}" />
								</span>
								<span class="account">
									返信した人のアカウント名：<c:out value="${comment.account}" />
								</span>
							</div>
							<!--  返信一覧表示  -->
							<div class="text">
								<pre><c:out value="${comment.text}" /></pre>
							</div>
							<div class="date">
								<fmt:formatDate value="${comment.createdDate}" pattern="yyyy/MM/dd HH:mm:ss" />
							</div>
							<!-- 見やすいようにつけました。 -->
							--------------------------------------------------------------------------------
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
			<div class="copyright">Copyright©FumiakiMogi</div>
		</div>
	</body>
</html>