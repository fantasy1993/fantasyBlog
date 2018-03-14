package com.fantasy.comment.controller;

import com.fantasy.article.po.ArticleModel;
import com.fantasy.comment.model.Comment;
import com.fantasy.comment.po.CommentModel;
import com.fantasy.comment.service.ICommentService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fantasy on 2018/1/7.
 */
@RestController
public class CommentController {

    @Autowired
    ICommentService commentService;

    /**
     *  获取登录人的发布的文章
     */
    @RequestMapping(value="/comment/{articleId}", method = RequestMethod.GET)
    public ResponseEntity<List<CommentModel>> getComments(@PathVariable("articleId") Long articleId, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userId = Long.valueOf(String.valueOf(session.getAttribute("userId")));
        Comment comment =new Comment();
        comment.setArticleId(articleId);
        comment.setUserId(userId);
        List<CommentModel> comments = commentService.getComments(comment);
        HttpStatus status = comments.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<List<CommentModel>>(comments,status);
    }

    /**
     * 保存评论人的评论信息
     */
    @RequestMapping(value = "/saveComment/",method = RequestMethod.POST)
    public ResponseEntity<Void> saveComments(@RequestBody Comment comment,UriComponentsBuilder ucb){
        comment.setCommentTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        commentService.insert(comment);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/saveComment/").path(String.valueOf(comment.getId())).build().toUri();
        headers.setLocation(locationUri);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
