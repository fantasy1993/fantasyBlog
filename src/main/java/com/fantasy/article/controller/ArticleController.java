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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
        Long userId = Long.valueOf(String.valueOf(session.getAttribute("userId")));
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

        if(article.getArticleId() != null){
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

}


