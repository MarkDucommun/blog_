package com.trial.posts;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PostEntityTranslator {
    public Post toPost(PostEntity postEntity) {
        Post post = new Post();
        post.setAuthorName(postEntity.getAuthorName());
        post.setTitle(postEntity.getTitle());
        post.setContent(postEntity.getContent());
        post.setCreatedAt(postEntity.getCreatedAt().toInstant());
        return post;
    }
}
