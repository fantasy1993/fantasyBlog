package com.fantasy.article.model;

/**
 * Created by fantasy on 2017/7/22.
 */

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 博客文章实体类
 */
@TableName("article")
public class Article implements Serializable{

    /*id*/
    @TableId(type = IdType.AUTO)
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
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
