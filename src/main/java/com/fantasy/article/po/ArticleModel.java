package com.fantasy.article.po;

/**
 * Created by fantasy on 2017/7/22.
 */
public class ArticleModel {

    /*id*/
    public Long articleId;
    /*用户*/
    public Long userId;
    /*文章标题*/
    public String title;
    /*文章图片*/
    public String imgUrl;
    /*文章发布时间*/
    public String postTime;
    /*文章内容*/
    public String content;
    /*用户姓名*/
    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
