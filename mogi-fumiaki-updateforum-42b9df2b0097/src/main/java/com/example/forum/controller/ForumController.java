package com.example.forum.controller;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.controller.form.ReportForm;
import com.example.forum.service.CommentService;
import com.example.forum.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ForumController {

    @Autowired
    ReportService reportService;
    @Autowired
    CommentService commentService;

    @Autowired
    HttpSession session;

    /*
     * 0-1 投稿内容表示処理
     * 1-1 返信内容表示処理
     * 4-1 日付で投稿を絞り込むことができる機能を追加
     * 6-3 バリデーション機能（返信投稿）
     */
    @GetMapping
    public ModelAndView top(@RequestParam(name="startDate", required = false) String startDate, @RequestParam(name="endDate", required = false) String endDate) {
        ModelAndView mav = new ModelAndView();

        // バリデーションでの追加
        List<String> errorMessage = (List<String>) session.getAttribute("errorMessages");
        session.removeAttribute("errorMessages"); // エラーメッセージを削除

        // 投稿を全件取得
        List<ReportForm> contentData = null;
        try {
            contentData = reportService.findAllReport(startDate, endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        // 返信を全件取得
        List<CommentForm> commentData = commentService.findAllReport();

        // 画面遷移先を指定
        mav.setViewName("/top");

        // バリデーションでの追加
        mav.addObject("errorMessages", errorMessage);

        // 投稿データオブジェクトを保管
        mav.addObject("contents", contentData);
        // 返信データオブジェクトを保管
        mav.addObject("comments", commentData);
        return mav;
    }

    //投稿に関するController

    /*
     * 0-2 新規投稿画面表示（遷移するため用）
     * 　新規投稿ボタンが押された時、新しい画面へ遷移する
     */
    @GetMapping("/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        ReportForm reportForm = new ReportForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", reportForm);
        return mav;
    }

    /*
     * setViewName("/new");でフォワード、(redirect:/new)でリダイレクト。
     * 0-3 新規投稿を登録する処理
     * 6-1 バリデーション追加(新規投稿)
     * パラメータにBindingResultを定義する場合は、対応するモデルオブジェクトの直後に定義しなければならないので、引数の順番に注意をする。
     */
    @PostMapping("/add")
    public ModelAndView addContent(@Validated @ModelAttribute("formModel") ReportForm reportForm, BindingResult result){
        //エラーメッセージリストを作成すること。
        List<String> errorMessages = new ArrayList<String>();

        // contentが存在していないこと　または　空白のみであること
        if (reportForm.getContent() == null || reportForm.getContent().isEmpty()) {
            errorMessages.add("投稿内容を入力してください");
            // エラーメッセージをsessionに格納してredirectで画面遷移する。→ hasErrors()がtrueになる
            session.setAttribute("errorMessages", errorMessages);
        }
        /*
         errorMessageリストが空でない場合＝エラーが生じている(失敗している)時、新規投稿画面へ戻るという記述
         ModelAndViewオブジェクトmavを作成することによって、/new.htmlへリダイレクト??
         hasError× => hasErrors●
        */
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView("/new");
            mav.addObject("errorMessages", errorMessages);
            // フォワード
            mav.setViewName("/new");
            return mav;
        }

        // エラーが無ければ、（登録成功しているので）投稿テーブルに格納
        reportService.saveReport(reportForm);
        // エラーが無ければ、（登録成功しているので）rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 0-4 投稿削除処理
     */
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Integer id) {
        // 投稿をテーブルに格納
        reportService.deleteReport(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 0-5 編集画面表示処理（edit.htmlへ遷移用）
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editContent(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        // 編集する投稿を取得
        ReportForm report = reportService.editReport(id);
        // 編集する投稿をセット
        mav.addObject("formModel", report);
        // 画面遷移先を指定
        mav.setViewName("/edit");
        return mav;
    }

    /*
     * 投稿更新機能
     * 6-2 バリデーション追加(投稿編集)
     */
    @PutMapping("/update/{id}")
    public ModelAndView updateContent (@Validated @ModelAttribute("formModel") ReportForm reportForm, BindingResult result, @PathVariable Integer id) {

        //エラーメッセージリストを作成すること。
        List<String> errorMessages = new ArrayList<String>();

        if (reportForm.getContent() == null || reportForm.getContent().isEmpty()) {
            errorMessages.add("投稿内容を入力してください");
            // エラーメッセージをsessionに格納してredirectで画面遷移する。→ hasErrors()がtrueになる
            session.setAttribute("errorMessages", errorMessages);
        }
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView("/new");
            mav.addObject("errorMessages", errorMessages);
            // フォワード
            mav.setViewName("/new");
            return mav;
        }

        // UrlParameterのidを更新するentityにセット
        reportForm.setId(id);
        // 編集した投稿を更新
        reportService.saveReport(reportForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 1-2 返信を登録する処理
     * 6-3 バリデーション追加(返信投稿)
     */
    @PostMapping("/comment")
    public ModelAndView addComment(@Validated @ModelAttribute("commentFormModel") CommentForm commentForm, BindingResult result) {

        //エラーメッセージリストを作成すること。
        List<String> errorMessages = new ArrayList<String>();
        // contentが存在していないこと　または　空白のみであること

        if(result.hasErrors()){
            errorMessages.add("コメントを入力してください");
            // エラーメッセージをsessionに格納してredirectで画面遷移する。→ hasErrors()がtrueになる
            session.setAttribute("errorMessages", errorMessages);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/");
            return mav;
        }

        // 返信をテーブルに格納
        commentService.saveComment(commentForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 2-1 返信編集画面表示処理（コメントへ遷移）
     * 返信編集ボタンが押されたときにデータを取得、格納、その後画面を遷移する
     */
    @GetMapping("/comment/{id}")
    // @PathVariable Integer idでも、top画面でidを指定しているためこれでもよい
    public ModelAndView editComment(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();

        // バリデーションでの追加
        List<String> errorMessage = (List<String>) session.getAttribute("errorMessages");
        session.removeAttribute("errorMessages");
        // バリデーションでの追加
        mav.addObject("errorMessages", errorMessage);

        // 編集する投稿を取得
        CommentForm comment = commentService.editComment(id);
        // 編集する投稿をセット
        mav.addObject("commentFormModel", comment);
        // 画面遷移先を指定
        mav.setViewName("/comment");
        return mav;
    }

    /*
     * 2-2 返信編集処理（返信編集画面で、編集を行う処理→top.htmlへ遷移）
     * 6-4 バリデーション追加(返信編集)
     * 引数は、ただの入れ物→htmlから送らないと入らない
     */
    @PutMapping("/update/comment/{id}")
    public ModelAndView updateComment(@Validated @ModelAttribute("commentFormModel") CommentForm commentForm, BindingResult result, @PathVariable Integer id, Integer messageId) {

        // エラー処理
        List<String> errorMessages = new ArrayList<String>();
        if(result.hasErrors()){
            errorMessages.add("コメントを入力してください");
            session.setAttribute("errorMessages", errorMessages);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/comment/{id}");
            return mav;
        }

        // UrlParameterのidを更新するentityにセット
        commentForm.setId(id);
        // UrlParameterのmessageIdを更新するentityにセット
        commentForm.setMessageId(messageId);
        // 編集した投稿を更新
        commentService.saveComment(commentForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 3-1 返信削除処理
     */
    @DeleteMapping("/delete/comment/{id}")
    public ModelAndView deleteComment(@PathVariable Integer id) {
        // 投稿をテーブルに格納
        commentService.deleteComment(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}