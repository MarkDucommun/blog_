package com.trial.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;

@Service
public class PostService {
    private PostRepository postRepository;
    private PostTranslator postTranslator;
    private PostEntityTranslator postEntityTranslator;
    private Clock clock;

    @Autowired
    public PostService(PostRepository postRepository,
                       PostTranslator postTranslator,
                       PostEntityTranslator postEntityTranslator,
                       Clock clock
    ) {
        this.postRepository = postRepository;
        this.postTranslator = postTranslator;
        this.postEntityTranslator = postEntityTranslator;
        this.clock = clock;
    }

    public Post create(Post post) {
        PostEntity postEntity = postTranslator.withCreatedAtToPostEntity(post, clock.instant());
        PostEntity savedPostEntity = postRepository.save(postEntity);
        return postEntityTranslator.toPost(savedPostEntity);
    }
}
