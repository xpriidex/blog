package com.nisum.blog.service;

import com.nisum.blog.dao.PostDAO;
import com.nisum.blog.domain.Post;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {
    private Post post1;
    private Post post2;
    private List<Post> postList;

    @Mock
    @Qualifier("postDAOJdbc")
    private PostDAO postDAO;

    @InjectMocks
    private PostService postService;

    @Before
    public void setUp() throws Exception {
        //Arrange
        DateTime nowLocal = DateTime.now();

        postList = new ArrayList<>();
        post1 = new Post();
        post2 = new Post();

        post1.setId(1);
        post1.setPublicationDate(nowLocal);
        post1.setTitle("Narnia");
        post1.setAuthorId(1);
        post1.setBody("I am post about ANDROID");

        post2.setId(2);
        post2.setPublicationDate(nowLocal);
        post2.setTitle("Papelucho");
        post2.setAuthorId(1);
        post2.setBody("I am post about AnImAlS");

        postList.add(post1);
        postList.add(post2);
    }


    @Test
    public void shouldReturnAPostIfIdExists() {
        //Arrange
        when(postDAO.findById(post1.getId())).thenReturn(post1);

        //Act
        Post result = postService.findById(post1.getId());

        //Assert
        assertEquals(result.getTitle(), "Narnia");
        verify(postDAO).findById(post1.getId());
    }

    @Test
    public void shouldReturnNullIfIdNotExists() {
        //Arrange
        when(postDAO.findById(2)).thenReturn(null);

        //Act
        Post result = postService.findById(2);

        //Assert
        assertNull(result);
        verify(postDAO).findById(2);
    }

    @Test
    public void shouldReturnAllPosts() throws Exception {
        //Arrange
        when(postDAO.findAll()).thenReturn(postList);

        //Act
        List<Post> result = postService.findAll();

        //Assert
        assertThat(result.size(), is(equalTo(2)));
        assertEquals(result.get(0).getId(), 1);
        assertEquals(result.get(1).getId(), 2);
        verify(postDAO).findAll();

    }

    @Test
    public void shouldReturnEmptyListWhenNoPostsFound() {
        //Arrange
        when(postDAO.findAll()).thenReturn(new ArrayList<>());

        //Act
        List<Post> result = postService.findAll();

        assertEquals(result.size(), 0);
    }

    @Test
    public void shouldReturnAllPostsByTitle() {
        //Arrange
        List<Post> postsByTitle = new ArrayList<>();
        postsByTitle.add(post1);
        when(postDAO.findAllByTitle("NaRNIA")).thenReturn(postsByTitle);

        //Act
        List<Post> result = postService.findAllByTitle("NaRNIA");

        //Assert
        assertThat(result.size(), is(equalTo(1)));
        assertEquals(result.get(0).getTitle(), "Narnia");
        verify(postDAO).findAllByTitle("NaRNIA");
    }

    @Test
    public void shouldReturnNullWhenNoPostsFoundByTitle() {
        //Arrange
        when(postDAO.findAllByTitle("El principito")).thenReturn(null);

        //Act
        List<Post> result = postService.findAllByTitle("El principito");

        //Assert
        assertNull(result);
        verify(postDAO).findAllByTitle("El principito");
        ;
    }

    @Test
    public void shouldReturnAllPostsByAlias() {
        //Act
        when(postDAO.findAllByAuthorsAlias("Felipe")).thenReturn(postList);

        //Act
        List<Post> result = postService.findAllByAuthorsAlias("Felipe");

        //Assert
        assertThat(result.size(), is(equalTo(2)));

    }

    @Test
    public void shouldReturnEmptyListNoPostsFoundByAlias() {
        //Arrange
        when(postDAO.findAllByAuthorsAlias("German")).thenReturn(new ArrayList<>());

        //Act
        List<Post> result = postService.findAllByAuthorsAlias("German");

        //Assert
        assertThat(result.size(), is(equalTo(0)));
        verify(postDAO).findAllByAuthorsAlias("German");
    }

    @Test
    public void shouldReturnAllPostsByContent() {
        //Arrange
        List<Post> postContent = new ArrayList<>();
        postContent.add(post1);
        when(postDAO.findAllByContent("AndroiD")).thenReturn(postContent);

        //Act
        List<Post> result = postService.findAllByContent("AndroiD");

        //Assert
        assertThat(result.size(), is(equalTo(1)));
    }

    @Test
    public void shouldReturnEmptyListWhenNoFoundPostsByContent() {
        //Arrange
        when(postDAO.findAllByContent("AiRPlanE")).thenReturn(new ArrayList<>());

        //Act
        List<Post> result = postService.findAllByContent("AiRPlanE");

        //Assert
        assertThat(result.size(), is(equalTo(0)));
    }

    @Test
    public void shouldReturnIdWhenCreateANewPost() {
        //Arrange
        when(postDAO.create(post1)).thenReturn(1);

        //Act
        int result = postService.create(post1);

        //Assert
        assertEquals(result, 1);
        verify(postDAO).create(post1);
    }

    @Test
    public void shouldReturnMinusOneWhenCreateAPostAlreadyExist() {
        //Arrange
        when(postDAO.create(post1)).thenReturn(-1);

        //Act
        int result = postService.create(post1);

        //Assert
        assertEquals(result, -1);
        verify(postDAO).create(post1);
    }

    @Test
    public void shouldReturnIdWhenUpdateAPost(){
        //Arrange
        when(postDAO.update(post1)).thenReturn(1);
        when(postService.findById(1)).thenReturn(post1);

        //Act
        int result = postService.update(post1);

        //Assert
        assertEquals(result, 1);
        verify(postDAO).update(post1);
    }

    @Test
    public void checkDeletePost(){
        postService.delete(1);

        verify(postDAO).delete(1);
    }

    @Test
    public  void shouldReturnCountPostDeleted(){
        when(postDAO.deleteByUserId(1)).thenReturn(1);

        int result= postService.deleteByUserId(1);

        assertThat(result,is(equalTo(1)));
    }
}