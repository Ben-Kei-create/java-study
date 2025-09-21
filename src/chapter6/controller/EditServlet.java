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
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import chapter6.beans.Message;
import chapter6.exception.NoRowsUpdatedRuntimeException;
import chapter6.logging.InitApplication;
import chapter6.service.MessageService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {

	Logger log = Logger.getLogger("twitter");

	public EditServlet() {
		InitApplication application = InitApplication.getInstance();
		application.init();
	}

	//編集画面の表示
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		HttpSession session = request.getSession();

		List<String> errorMessages = new ArrayList<String>();

		String editId = request.getParameter("edit_id");

		//受け取ったedit_idについて、バリデーションチェック：存在しないか、または数字でない場合（正規表現を使用、半角数字のみ）
		if (editId == null || !editId.matches("^[0-9]+$")) {
			errorMessages.add("不正なパラメータです");
			session.setAttribute("errorMessages", errorMessages);
			response.sendRedirect("./");
			return;
		}

		//getMessageTextメソッドにeditIdを代入(idのテキストを表示させる)
		Message editMessage = new MessageService().selectEdit(editId);

		//editMessageの中にデータが入っている場合
		if (editMessage != null) {
			request.setAttribute("editMessage", editMessage);
			request.getRequestDispatcher("edit.jsp").forward(request, response);
		} else {
			errorMessages.add("不正なパラメータです");
			session.setAttribute("errorMessages", errorMessages);
			response.sendRedirect("./");
		}
	}

	/**
	 * メッセージの編集 doPost
	 * edit_id、edited_textを受け取り、Serviceまで送ること
	 * DB更新後、トップ画面を表示させること
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		List<String> errorMessages = new ArrayList<String>();

		String editedText = request.getParameter("edited_text");
		String editId = request.getParameter("edit_id");
		int intEditId = Integer.parseInt(editId);

		Message editMessage = new Message();
		editMessage.setText(editedText);
		editMessage.setId(intEditId);

		//isValidがtrue(エラー無し)なら処理
		if (isValid(editedText, errorMessages)) {
			try {
				new MessageService().update(editMessage);
			} catch (NoRowsUpdatedRuntimeException e) {
				log.warning("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				errorMessages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
			}
		}

		//isValidが0でない=false(エラーが有る)なら処理
		if (errorMessages.size() != 0) {
			request.setAttribute("errorMessages", errorMessages);
			request.setAttribute("editMessage", editMessage);
			request.getRequestDispatcher("edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("./");
	}

	private boolean isValid(String text, List<String> errorMessages) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		if (StringUtils.isBlank(text)) {
			errorMessages.add("入力してください");
		} else if (140 < text.length()) {
			errorMessages.add("140文字以下で入力してください");
		}

		if (errorMessages.size() != 0) {
			return false;
		}
		return true;
	}
}