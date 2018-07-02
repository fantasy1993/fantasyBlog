package com.fantasy.article.controller;


import com.fantasy.article.model.Article;
import com.fantasy.article.po.ArticleModel;
import com.fantasy.article.service.IArticleService;
import com.fantasy.base.FantasyDate;
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


    /**
     *  获取登录人的发布的文章
     */
    @RequestMapping(value="/article/", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleModel>> getArticles(HttpServletRequest request){
        HttpSession session = request.getSession();
        //Long userId = Long.valueOf(String.valueOf(session.sgetAttribute("userId")));
        Long userId= Long.valueOf(1);
        List<ArticleModel> articles = articleService.getArticles(userId);
        HttpStatus status = articles.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return new ResponseEntity<List<ArticleModel>>(articles,status);
    }

    /**
     * 保存文章
     * @return
     */
    @RequestMapping(value = "/article/", method = RequestMethod.POST)
    public ResponseEntity<Void> saveArticles(@RequestBody Article article, UriComponentsBuilder ucb){

        if(!StringUtils.isEmpty(article.getArticleId())){
            articleService.updateById(article);
            //articleService.updateArticle(article);
        }else{
            article.setPostTime(FantasyDate.getStringDateShort());
            articleService.insert(article);
            //articleService.saveArticle(article);
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


