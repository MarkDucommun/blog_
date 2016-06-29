package com.trial.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PostsControllerTest {

    private MockMvc mockMvc;
    private PostsController postsController;
    private PostService mockPostService;

    @Before
    public void setUp() throws Exception {
        mockPostService = mock(PostService.class);
        postsController = new PostsController(mockPostService);
        mockMvc = MockMvcBuilders.standaloneSetup(postsController).build();
    }

    @Test
    public void create_delegatesToPostService() throws Exception {
        doReturn(new Post()).when(mockPostService).create(any(Post.class));

        Post inputPost = new Post();
        inputPost.setAuthorName("Author Name");
        String contentString = new ObjectMapper().writeValueAsString(inputPost);

        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contentString))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(mockPostService).create(inputPost);
    }

    @Test
    public void create_returnsTheCreatedPost() throws Exception {
        Post expectedPost = new Post();
        Post inputPost = new Post();

        doReturn(expectedPost).when(mockPostService).create(same(inputPost));

        Post returnedPost = postsController.create(inputPost);

        assertThat(returnedPost).isSameAs(expectedPost);
    }
}
