package com.example.forum.service;

import com.example.forum.controller.form.CommentForm;

import com.example.forum.controller.form.ReportForm;
import com.example.forum.repository.CommentRepository;
import com.example.forum.repository.entity.Comment;

import com.example.forum.repository.entity.Report;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    /*
     * レコード全件取得処理
     */
    public List<CommentForm> findAllReport() {
        List<Comment> results = commentRepository.findAllByOrderByIdDesc();
        List<CommentForm> comments = setCommentForm(results);
        return comments;
    }

    /*
     * DBから取得したデータをFormに入れる設定
     */
    private List<CommentForm> setCommentForm(List<Comment> results) {
        List<CommentForm> comments = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            CommentForm comment = new CommentForm();
            Comment result = results.get(i);
            comment.setId(result.getId());
            comment.setComment(result.getComment());
            comment.setMessageId(result.getMessageId());
            comments.add(comment);
        }
        return comments;
    }

    /*
     *　返信を登録するため
     *　返信を更新するため
     * （２つ使える）
     */

    public void saveComment(CommentForm reqComment) {
        Comment saveComment = setCommentEntity(reqComment);
        commentRepository.save(saveComment);
    }

    //返信のidとcomment,messageIdを登録する
    private Comment setCommentEntity(CommentForm reqComment) {
        Comment comment;
        comment = new Comment();
        comment.setId(reqComment.getId());
        comment.setComment(reqComment.getComment());
        comment.setMessageId(reqComment.getMessageId());
        return comment;
    }
    /*
     * 返信レコード1件取得
     */
    public CommentForm editComment(Integer id) {
        List<Comment> results = new ArrayList<>();
        results.add(commentRepository.findById(id).orElse(null));
        List<CommentForm> comments = setCommentForm(results);
        return comments.get(0);
    }

    /*
     * 08追加実装課題解説
     * （削除機能実装）
     */
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }
}