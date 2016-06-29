package com.trial.posts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static java.time.Instant.EPOCH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {
    private PostService postService;
    private PostTranslator mockPostTranslator;
    private PostRepository mockPostRepository;
    private PostEntityTranslator mockPostEntityTranslator;

    @Before
    public void setUp() throws Exception {
        mockPostRepository = mock(PostRepository.class);
        mockPostTranslator = mock(PostTranslator.class);
        mockPostEntityTranslator = mock(PostEntityTranslator.class);
        postService = new PostService(
                mockPostRepository,
                mockPostTranslator,
                mockPostEntityTranslator,
                Clock.fixed(EPOCH, ZoneOffset.UTC)
        );
    }

    @Test
    public void create_translatesPostToPostEntityWithACreatedAtInstant() throws Exception {
        Post inputPost = new Post();

        postService.create(inputPost);

        verify(mockPostTranslator).withCreatedAtToPostEntity(same(inputPost), eq(Instant.EPOCH));
    }

    @Test
    public void create_passesTranslatedPostEntityToPostRepository() throws Exception {
        PostEntity translatedPostEntity = new PostEntity();
        doReturn(translatedPostEntity).when(mockPostTranslator).withCreatedAtToPostEntity(any(Post.class), any(Instant.class));

        postService.create(new Post());

        verify(mockPostRepository).save(translatedPostEntity);
    }

    @Test
    public void create_translatesTheReturnedPostEntityToPostObjectAndReturnsIt() throws Exception {
        doReturn(new PostEntity()).when(mockPostTranslator)
                .withCreatedAtToPostEntity(any(Post.class), any(Instant.class));

        PostEntity savedPostEntity = new PostEntity();
        doReturn(savedPostEntity).when(mockPostRepository).save(any(PostEntity.class));

        Post translatedPost = new Post();
        doReturn(translatedPost).when(mockPostEntityTranslator).toPost(same(savedPostEntity));

        Post returnedPost = postService.create(new Post());

        assertThat(returnedPost).isSameAs(translatedPost);
    }
}