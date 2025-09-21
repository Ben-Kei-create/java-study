package chapter6.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter6.beans.User;
import chapter6.beans.UserComment;
import chapter6.beans.UserMessage;
import chapter6.logging.InitApplication;
import chapter6.service.CommentService;
import chapter6.service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger("twitter");

	public TopServlet() {
		InitApplication application = InitApplication.getInstance();
		application.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		boolean isShowMessageForm = false;

		/*
		 * セッションからログインユーザーを取得し、
		 * loginUserのオブジェクトが取得できた場合(nullではない場合)
		 * 変数isShowMessageFormにtrueを設定する
		 * top.jspで参照することで、isShowMessageFormの値で可変（つぶやきに関する表示非常時のフラグ設定）
		 */
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user != null) {
			isShowMessageForm = true;
		}

		String userId = request.getParameter("user_id");
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		List<UserMessage> messages = new MessageService().select(userId, start, end);
		List<UserComment> comments = new CommentService().select();

		request.setAttribute("start", start);
		request.setAttribute("end", end);

		request.setAttribute("messages", messages);
		request.setAttribute("isShowMessageForm", isShowMessageForm);
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}
}