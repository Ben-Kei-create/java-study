package com.example.forum.controller.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class CommentForm {
    private int id;
    @NotBlank
    private String comment;
    private int messageId;
    private Date createdDate;
    private Date updatedDate;
}