package com.trial.posts;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PostEntityTranslatorTest {
    @Test
    public void toPost_translatesPostEntityToPost() throws Exception {
        String authorName = "Author Name";
        String title = "Title";
        String content = "Content";
        Timestamp createdAt = Timestamp.from(Instant.EPOCH);

        PostEntity postEntity = new PostEntity();
        postEntity.setAuthorName(authorName);
        postEntity.setTitle(title);
        postEntity.setContent(content);
        postEntity.setCreatedAt(createdAt);

        Post post = new PostEntityTranslator().toPost(postEntity);

        assertThat(post.getAuthorName()).isEqualTo(authorName);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getCreatedAt()).isEqualTo(Instant.EPOCH);

    }
}