package com.fantasy.article.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fantasy.article.model.Article;
import com.fantasy.article.po.ArticleModel;
import com.fantasy.article.service.IArticleService;
import com.fantasy.base.FantasyDate;
import com.fantasy.base.FantasyResult;
import com.fantasy.base.FantasyResultCode;
import com.fantasy.comment.model.Comment;
import com.fantasy.comment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fantasy on 2017/7/22.
 */

@RestController
public class ArticleController {

    @Autowired
    IArticleService articleService;

    @Autowired
    ICommentService commentService;


    /**
     *  获取登录人的发布的文章
     */
    @RequestMapping(value="/article/", method = RequestMethod.GET)
    public FantasyResult getArticles(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long userId = Long.valueOf(String.valueOf(session.getAttribute("userId")));
        List<ArticleModel> articles = articleService.getArticles(userId);
        for(int i = 0;i < articles.size();i++){
            List<Comment> commentList = commentService.selectList(
                    new EntityWrapper<Comment>().eq("articleId",articles.get(i).getArticleId())
            );
            articles.get(i).setCommentSum(commentList.size());
        }
//        HttpStatus status = articles.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
//        return new ResponseEntity<List<ArticleModel>>(articles,status);
        return new FantasyResult(FantasyResultCode.SUCCESS,articles);
    }

    /**
     * 保存文章
     * @return
     */
    @RequestMapping(value = "/article/", method = RequestMethod.POST)
    public ResponseEntity<Void> saveArticles(@RequestBody Article article, UriComponentsBuilder ucb){
        if(!StringUtils.isEmpty(article.getArticleId())){
            article.setPostTime(FantasyDate.getStringDateShort());
            articleService.updateById(article);
        }else{
            article.setPostTime(FantasyDate.getStringDateShort());
            articleService.insert(article);
        }
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/article/").path(String.valueOf(article.getArticleId())).build().toUri();
        headers.setLocation(locationUri);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * 根据文章id删除文章
     * @return
     */
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeArticles(@PathVariable("articleId") String articleId, UriComponentsBuilder ucb){
        if(!StringUtils.isEmpty(articleId)){
            articleService.deleteById(Long.valueOf(articleId));
        }
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/article/").path(String.valueOf(articleId)).build().toUri();
        headers.setLocation(locationUri);
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}


