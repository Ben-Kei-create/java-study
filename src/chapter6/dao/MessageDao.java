package chapter6.dao;

import static chapter6.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import chapter6.beans.Message;
import chapter6.exception.SQLRuntimeException;
import chapter6.logging.InitApplication;

public class MessageDao {

	/**
	 * ロガーインスタンスの生成
	 */
	Logger log = Logger.getLogger("twitter");

	/**
	 * デフォルトコンストラクタ
	 * アプリケーションの初期化を実施する。
	 */
	public MessageDao() {
		InitApplication application = InitApplication.getInstance();
		application.init();
	}

	public void insert(Connection connection, Message message) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append("    user_id, ");
			sql.append("    text, ");
			sql.append("    created_date, ");
			sql.append("    updated_date ");
			sql.append(") VALUES ( ");
			sql.append("    ?, ");                  // user_id
			sql.append("    ?, ");                  // text
			sql.append("    CURRENT_TIMESTAMP, ");  // created_date
			sql.append("    CURRENT_TIMESTAMP ");   // updated_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, message.getUserId());
			ps.setString(2, message.getText());

			ps.executeUpdate();
		} catch (SQLException e) {
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//メッセージ削除メソッド
	public void delete(Connection connection, String deletedId){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("DELETE FROM messages ");
			sql.append("WHERE id = ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, deletedId);

			ps.executeUpdate();
		} catch (SQLException e) {
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//編集したいメッセージを取得（messagesのidから、textを戻り値に）
	public Message selectEdit(Connection connection, String edit_id){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append("FROM messages ");
			sql.append("WHERE id = ? ");

			ps = connection.prepareStatement(sql.toString());

			int editId = Integer.parseInt(edit_id);

			ps.setInt(1, editId);

			ResultSet rs = ps.executeQuery();

			List<Message> messages = toMessages(rs);

			if (messages.isEmpty()) {
				return null;
			} else {
				Message message = messages.get(0);
				return message;
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Message> toMessages(ResultSet rs) throws SQLException {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() + " : " + new Object(){}.getClass().getEnclosingMethod().getName());

		List<Message> masseges = new ArrayList<Message>();
		try {
			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setUserId(rs.getInt("user_id"));
				message.setText(rs.getString("text"));
				message.setCreatedDate(rs.getTimestamp("created_date"));
				message.setUpdatedDate(rs.getTimestamp("updated_date"));

				masseges.add(message);
			}
			return masseges;
		} finally {
			close(rs);
		}
	}

	/**
	 * メッセージの更新 update
	 * edit_id、edited_textを受け取り、DBにSQL文で更新
	 * ホームには既に更新されているので、戻り値は要らない。
	 * 戻り値(void)はない。最後に、executeUpdate()を使う。
	 */

	public void update(Connection connection, Message message){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE messages ");
			sql.append("SET text = ?, created_date = CURRENT_TIMESTAMP ");  // これを打つだけで、updateDateを更新することができる
			sql.append("WHERE id = ? ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, message.getText()); //バインド変数の「1番目」に更新するtextをset
			ps.setInt(2, message.getId()); //バインド変数の「2番目」に更新されるidをset

			ps.executeUpdate();
		} catch (SQLException e) {
			log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}