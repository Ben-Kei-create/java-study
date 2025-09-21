package com.example.forum.repository;

import com.example.forum.repository.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {


    void deleteById(Integer id);


    //これは必要ない？
    List<Report> findAllByOrderByIdDesc();

    List<Report> findAllByCreatedDateBetween(Date startDate, Date endDate);


}