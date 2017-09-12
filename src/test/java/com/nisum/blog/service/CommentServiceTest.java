package com.nisum.blog.service;

import com.nisum.blog.dao.CommentDAO;
import com.nisum.blog.domain.Comment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    private Comment comment1;
    private Comment comment2;
    private Comment comment3;
    private List<Comment> comments;

    @Mock
    private CommentDAO commentDAO;

    @InjectMocks
    private  CommentService commentService;

    public CommentServiceTest() {
    }

    @Before
    public void setUp() throws Exception {
        comments = new ArrayList<>();

        comment1 = new Comment();
        comment1.setAuthorId(1);
        comment1.setPostId(1);

        comment2 = new Comment();
        comment2.setAuthorId(2);
        comment2.setPostId(1);

        comment3 = new Comment();
        comment3.setAuthorId(3);
        comment3.setPostId(2);

        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
    }

    @Test
    public void itShouldReturnAllCommentByAuthorId() throws Exception {
        List<Comment> commentsByAuthor  = new ArrayList<>();
        commentsByAuthor.add(comment1);
        when(commentDAO.findByAuthorId(1)).thenReturn(commentsByAuthor);

        List<Comment> commentList = commentService.findByAuthorId(1);
        int commentListSize = commentList.size();

        assertThat(commentListSize, is(equalTo(1)));
        verify(commentDAO).findByAuthorId(1);
    }

    /*@Test
    public void itShouldReturnAllCommentByAuthorAlias() throws Exception {

        int commentListSIze = commentService.findByAuthorAlias("Miki").size();
        assertThat(commentListSIze, is(equalTo(1)));
    }*/

    @Test
    public void itShouldReturnAllCommentByPostId() throws Exception {
        List<Comment> commentsByPost = new ArrayList<>();
        commentsByPost.add(comment3);
        when(commentDAO.findByPostId(2)).thenReturn(commentsByPost);

        List<Comment> commentList = commentService.findByPostId(2);
        int commentListSize = commentList.size();

        assertThat(commentListSize, is(equalTo(1)));
        verify(commentDAO).findByPostId(2);
    }

}