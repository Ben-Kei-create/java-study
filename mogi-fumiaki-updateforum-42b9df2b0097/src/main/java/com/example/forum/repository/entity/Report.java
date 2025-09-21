package com.example.forum.repository.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
@Getter
@Setter
public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    private String content;
    @Column(insertable = false)
    private Date createdDate;
    @Column(insertable = false)
    private Date updatedDate;
    @Column
    private String startDate;
    @Column
    private String endDate;


}