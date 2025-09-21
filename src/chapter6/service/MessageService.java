package chapter6.service;

import static chapter6.utils.CloseableUtil.*;
import static chapter6.utils.DBUtil.*;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import chapter6.beans.Message;
import chapter6.beans.UserMessage;
import chapter6.dao.MessageDao;
import chapter6.dao.UserMessageDao;
import chapter6.logging.InitApplication;

public class MessageService {

	Logger log = Logger.getLogger("twitter");

	public MessageService() {
		InitApplication application = InitApplication.getInstance();
		application.init();
	}

	public void insert(Message message) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		Connection connection = null;
		try {
			connection = getConnection();
			new MessageDao().insert(connection, message);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} catch (Error e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<UserMessage> select(String userId, String start, String end) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		final int LIMIT_NUM = 1000;

		Connection connection = null;
		try {
			connection = getConnection();

			Integer id = null;
			String startDate = null;
			String endDate = null;

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			if (!StringUtils.isEmpty(userId)) {
				id = Integer.parseInt(userId);
			}

			//4パターン作成(ON&ON, ON&OFF, OFF&ON, OFF&OFF)
			if (!StringUtils.isEmpty(start)) {
				startDate = (start + " 00:00:00");
			} else {
				startDate = "2020-01-01 00:00:00";
			}

			if (!StringUtils.isEmpty(end)) {
				endDate = (end + " 23:59:59");
			} else {
				endDate = sdf.format(date);
			}

			List<UserMessage> messages = new UserMessageDao().select(connection, id, LIMIT_NUM, startDate, endDate);

			commit(connection);
			return messages;
		} catch (RuntimeException e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} catch (Error e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} finally {
			close(connection);
		}
	}

	//編集したいメッセージをidから取得するためのメソッド
	public Message selectEdit(String editId) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		Connection connection = null;
		try {
			connection = getConnection();
			Message messages = new MessageDao().selectEdit(connection, editId);
			commit(connection);
			return messages;
		} catch (RuntimeException e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} catch (Error e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void delete(String deletedId) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());
		Connection connection = null;

		try {
			connection = getConnection();

			new MessageDao().delete(connection, deletedId);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} catch (Error e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} finally {
			close(connection);
		}
	}

	/**
	 * メッセージの更新 update
	 * edit_id、edited_textを受け取り、Daoまで送ること
	 * DB更新後、トップ画面を表示させること
	 */
	public void update(Message message) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());
		Connection connection = null;

		try {
			connection = getConnection();

			new MessageDao().update(connection, message);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} catch (Error e) {
			rollback(connection);
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw e;
		} finally {
			close(connection);
		}
	}
}