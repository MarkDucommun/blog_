package com.trial.posts;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTranslatorTest {
    @Test
    public void withCreatedAtToEntity_translatesIt() throws Exception {
        String authorName = "Author Name";
        String title = "Title";
        String content = "Content";
        Instant createdAt = Instant.EPOCH;

        Post post = new Post();
        post.setAuthorName(authorName);
        post.setTitle(title);
        post.setContent(content);

        PostEntity postEntity = new PostTranslator().withCreatedAtToPostEntity(post, createdAt);

        assertThat(postEntity.getAuthorName()).isEqualTo(authorName);
        assertThat(postEntity.getTitle()).isEqualTo(title);
        assertThat(postEntity.getContent()).isEqualTo(content);
        assertThat(postEntity.getCreatedAt()).isEqualTo(Timestamp.from(createdAt));
    }
}