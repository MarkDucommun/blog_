package com.trial.posts;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class PostEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String authorName;
    private String title;
    private String content;
    private Timestamp createdAt;
}
