package com.fantasy.article.service;

import com.baomidou.mybatisplus.service.IService;
import com.fantasy.article.model.Article;
import com.fantasy.article.po.ArticleModel;

import java.util.List;

/**
 * Created by fantasy on 2017/7/15.
 */

public interface IArticleService extends IService<Article> {

    /**
     * 获取所有文章
     * @return
     */
    public abstract List<ArticleModel> getArticles(Long userId);


    /**
     * 保存发布的文章
     */
    public abstract void saveArticle(ArticleModel article);

    /**
     * 更新发布的文章
     * @param article
     */
    public abstract void updateArticle(ArticleModel article);

    /**
     * 删除文章
     * @param articleId
     */
    void deleteArticleById(Long articleId);
}
