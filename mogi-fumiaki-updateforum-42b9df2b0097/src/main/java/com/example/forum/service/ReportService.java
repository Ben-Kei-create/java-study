package com.example.forum.service;

import com.example.forum.controller.form.ReportForm;
import com.example.forum.repository.ReportRepository;
import com.example.forum.repository.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;

//    /*　(絞り込み機能を実装する前の状態)
//     * レコード全件取得処理
//     */
//    public List<ReportForm> findAllReport() {
//
//
//        List<Report> results = reportRepository.findAllByOrderByIdDesc();
//        //setReportFormメソッド→EntityをFormに詰めなおしている（Entityはデータアクセス時の入れ物、FormはViewへの入出力時に使用する入れ物）
//
//
//        List<ReportForm> reports = setReportForm(results);
//        return reports;
//    }

    /*
     * レコード全件取得処理
     */
//    public List<ReportForm> findAllReport() {
//        List<Report> results = reportRepository.findAllByOrderByIdDesc();
//        //setReportFormメソッド→EntityをFormに詰めなおしている（Entityはデータアクセス時の入れ物、FormはViewへの入出力時に使用する入れ物）
//        List<ReportForm> reports = setReportForm(results);
//        return reports;
//    }

//    public List<ReportForm> findAllReport(String startDate, String endDate) {
//
//        // Date()メソッドを使用　System.currentTimeMillis()が使える
//        Date date = new Date();
//        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        // 絞り込みstartとendを宣言
//        String start = null;
//        String end = null;
//
//        // 4パターン作成(ON&ON, ON&OFF, OFF&ON, OFF&OFF)
//        if (!StringUtils.isEmpty(startDate)) {
//            start = (start + " 00:00:00");
//        } else {
//            start = "2020-01-01 00:00:00";
//        }
//        if (!StringUtils.isEmpty(endDate)) {
//            end = (end + " 23:59:59");
//        } else {
//            end = sdFormat.format(date);
//        }
//
//        // DateStartDateとしてDate型へ
//        Date DateStartDate = sdFormat.parse(start);
//        Date DateEndDate = sdFormat.parse(end);
//
//        List<Report> results = reportRepository.findAllByOrderByIdDesc(DateStartDate, DateEndDate);
//        //setReportFormメソッド→EntityをFormに詰めなおしている（Entityはデータアクセス時の入れ物、FormはViewへの入出力時に使用する入れ物）
//        // List<ReportForm> reports = setReportForm(results);
//        // return reports;
//
//        return setReportForm(results);
//    }

    public List<ReportForm> findAllReport(String startDate, String endDate) throws ParseException {

        // Date()メソッドを使用　System.currentTimeMillis()が使える
        Date date = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 絞り込みstartとendを宣言
        String start = null;
        String end = null;

        // 4パターン作成(ON&ON, ON&OFF, OFF&ON, OFF&OFF)
        if (!StringUtils.isEmpty(startDate)) {
            start = startDate + " 00:00:00"; // startDateを直接代入
        } else {
            start = "2020-01-01 00:00:00";
        }
        if (!StringUtils.isEmpty(endDate)) {
            end = endDate + " 23:59:59"; // endDateを直接代入
        } else {
            end = sdFormat.format(date);
        }

        // DateStartDateとしてDate型へ
        Date DateStartDate = sdFormat.parse(start);
        Date DateEndDate = sdFormat.parse(end);

        List<Report> results = reportRepository.findAllByCreatedDateBetween(DateStartDate, DateEndDate);

        return setReportForm(results);
    }





//
//    public List<ReportForm> findByDate(String startDate, String endDate) throws ParseException {
//
//        Date date = new Date();
//        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        String start = null;
//        String end = null;
//
//        //4パターン作成(ON&ON, ON&OFF, OFF&ON, OFF&OFF)
//        if (!StringUtils.isEmpty(startDate)) {
//            start = (start + " 00:00:00");
//        } else {
//            start = "2020-01-01 00:00:00";
//        }
//        if (!StringUtils.isEmpty(endDate)) {
//            end = (end + " 23:59:59");
//        } else {
//            end = sdFormat.format(date);
//        }
//        Date DateStartDate = sdFormat.parse(start);
//        Date DateEndDate = sdFormat.parse(end);
//
//        List<Report> results = reportRepository.findByCreatedDateBetween(DateStartDate, DateEndDate);
//        //setReportFormメソッド→EntityをFormに詰めなおしている（Entityはデータアクセス時の入れ物、FormはViewへの入出力時に使用する入れ物）
//        List<ReportForm> reports = setReportForm(results);
//        return reports;
//    }


    /*
     * DBから取得したデータをFormに設定
     *
     */
    private List<ReportForm> setReportForm(List<Report> results) {
        List<ReportForm> reports = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            ReportForm report = new ReportForm();
            Report result = results.get(i);
            report.setId(result.getId());
            report.setContent(result.getContent());
            report.setCreatedDate(result.getCreatedDate());
            report.setUpdatedDate(result.getUpdatedDate());
            reports.add(report);
        }
        return reports;
    }

    /*
     * レコード追加
     */
    public void saveReport(ReportForm reqReport) {
        Report saveReport = setReportEntity(reqReport);
        reportRepository.save(saveReport);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
//    private Report setReportEntity(ReportForm reqReport) {
//        Report report = new Report();
//        report.setId(reqReport.getId());
//        report.setContent(reqReport.getContent());
//
//        //Date()に、this(System.currentTimeMillis());があるので、
//        //それを引数にしてreportに代入する。
//        Date date = new Date();
//        //そのままだとフォーマットに合わないためpatternを決める
//        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String end = null;
//        end = sdFormat.format(date);
//        report.setUpdatedDate(end);
//
//        return report;
//    }

    private Report setReportEntity(ReportForm reqReport) {
        Report report = new Report();
        report.setId(reqReport.getId());
        report.setContent(reqReport.getContent());
        Date date = new Date();
        report.setCreatedDate(date); // 現在の日時を設定
        report.setUpdatedDate(date); // 現在の日時を設定
        return report;
    }

    /*
     * 0-4 削除機能実装
     */
    public void deleteReport(Integer id) {
        reportRepository.deleteById(id);
    }

    /*
     * 0-5 レコード1件取得
     */
    public ReportForm editReport(Integer id) {
        List<Report> results = new ArrayList<>();
        results.add((Report) reportRepository.findById(id).orElse(null));
        List<ReportForm> reports = setReportForm(results);
        return reports.get(0);
    }
}