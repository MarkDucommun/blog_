package com.trial.posts;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class PostTranslator {
    public PostEntity withCreatedAtToPostEntity(Post post, Instant createdAt) {
        PostEntity postEntity = new PostEntity();
        postEntity.setAuthorName(post.getAuthorName());
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setCreatedAt(Timestamp.from(createdAt));
        return postEntity;
    }
}
