package com.example.forum.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String comment;

    //スネークケースとキャメルケースは異なっていてもOK
    @Column
    private int messageId;

    @Column(insertable = false)
    private Date createdDate;

    @Column(insertable = false)
    private Date updatedDate;
}
