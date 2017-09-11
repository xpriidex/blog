package com.nisum.blog.service;

import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import com.nisum.blog.service.exceptions.PostNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PostServiceTest {
    private PostService postService;
    private Post post1;
    private Post post2;
    private User user;

    @Before
    public void setUp() throws Exception {
        //Arrenge
        postService = new PostService();
        post1 = new Post();
        post2 = new Post();
        user = new User();

        user.setAlias("Felipe");
        post1.setId(1);
        post1.setTitle("Narnia");
        post1.setAuthor(user);
        post1.setBody("I am post about ANDROID");

        post2.setId(2);
        post2.setTitle("Papelucho");
        post2.setAuthor(user);
        post2.setBody("I am post about AnImAlS");

        postService.add(post1);
        postService.add(post2);

    }

    @Test
    public void itShouldReturnAllPosts() throws Exception {
        //Act
        List<Post> result = postService.findAll();

        //Assert
        assertThat(result.size(), is(equalTo(2)));
        assertEquals(result.get(0).getId(), 1);
        assertEquals(result.get(1).getId(), 2);
    }

    @Test(expected = PostNotFoundException.class)
    public void itShouldReturnPostNotFoundExceptionWhenNoPosts() throws PostNotFoundException {
        //Arrenge
        postService.empty();

        //Act
        List<Post> result = postService.findAll();
    }

    @Test
    public void itShouldReturnAllPostsByTitle() throws PostNotFoundException {
        //Act
        List<Post> result = postService.findAllByTitle("NaRNIA");

        //Assert
        assertThat(result.size(), is(equalTo(1)));


    }

    @Test(expected = PostNotFoundException.class)
    public void itShouldReturnPostNotFoundExceptionWhenNoPostsByTitle() throws PostNotFoundException {
        //Act
        List<Post> result = postService.findAllByTitle("El principito");
    }

    @Test
    public void itShouldReturnAllPostsByAuthorsAliasWhenAliasExists() throws PostNotFoundException {
        //Act
        List<Post> result = postService.findAllByAuthorsAlias("Felipe");

        //Assert
        assertThat(result.size(), is(equalTo(2)));
        assertEquals(result.get(0).getAuthor().getAlias(), "Felipe");
        assertEquals(result.get(1).getAuthor().getAlias(), "Felipe");
    }

    @Test(expected = PostNotFoundException.class)
    public void itShouldReturnPostNotFoundExceptionWhenNoPostsByAlias() throws PostNotFoundException {
        //Act
        List<Post> result = postService.findAllByAuthorsAlias("German");
    }

    @Test
    public void itShouldReturnAllPostsByContent() throws PostNotFoundException {
        //Act
        List<Post> result = postService.findAllByContent("ANDROID");

        //Assert
        assertThat(result.size(), is(equalTo(1)));
    }

    @Test(expected = PostNotFoundException.class)
    public void itShouldReturnPostNotFoundExceptionWhenNoPostsByContent() throws PostNotFoundException {
        //Act
        List<Post> result = postService.findAllByContent("AiRPlanE");
    }

    @Test
    public void shouldFindNothingOnSearchByBodyContent(){

    }

}