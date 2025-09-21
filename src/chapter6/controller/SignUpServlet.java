package chapter6.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import chapter6.beans.User;
import chapter6.logging.InitApplication;
import chapter6.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {

	/**
	 * ロガーインスタンスの生成
	 */
	Logger log = Logger.getLogger("twitter");

	/**
	 * デフォルトコンストラクタ
	 * アプリケーションの初期化を実施する。
	 */
	public SignUpServlet() {
		InitApplication application = InitApplication.getInstance();
		application.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		List<String> errorMessages = new ArrayList<String>();

		User user = getUser(request);

		//ヴァリデーションメソッドを実行
		if (!isValid(user, errorMessages)) {
			//request.setAttribute(1引数:属性名(String),2引数:インスタンス(Object))リクエストスコープに保存する
			request.setAttribute("errorMessages", errorMessages);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}

		//UserServiceクラスのinsertメソッドの呼び出し
		new UserService().insert(user);
		//リダイレクト構文:"リダイレクト先URL=>ログイン前のトップ画面"
		response.sendRedirect("./");
	}

	private User getUser(HttpServletRequest request) throws IOException, ServletException {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		User user = new User();
		user.setName(request.getParameter("name"));
		user.setAccount(request.getParameter("account"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setDescription(request.getParameter("description"));
		return user;
	}

	//実装課題③修正箇所
	private boolean isValid(User user, List<String> errorMessages) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		String name = user.getName();
		String account = user.getAccount();
		String password = user.getPassword();
		String email = user.getEmail();

		User duplicateUser = new UserService().select(user.getAccount());

		if (!StringUtils.isEmpty(name) && (20 < name.length())) {
			errorMessages.add("名前は20文字以下で入力してください");
		}

		if (StringUtils.isEmpty(account)) {
			errorMessages.add("アカウント名を入力してください");
		} else if (20 < account.length()) {
			errorMessages.add("アカウント名は20文字以下で入力してください");
			//入力したduplicateが空白でないとき、かつDB内のaccountと、入力されたduplicateが"文字列"として正しければ表示
		}

		if (duplicateUser != null) {
			errorMessages.add("すでに存在するアカウントです");
		}

		if (StringUtils.isEmpty(password)) {
			errorMessages.add("パスワードを入力してください");
		}

		if (!StringUtils.isEmpty(email) && (50 < email.length())) {
			errorMessages.add("メールアドレスは50文字以下で入力してください");
		}

		if (errorMessages.size() != 0) {
			return false;
		}
		return true;
	}
}