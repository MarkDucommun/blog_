package com.trial.posts;

import lombok.Data;

import java.time.Instant;

@Data
public class Post {
    private String authorName;
    private String title;
    private String content;
    private Instant createdAt;
}
