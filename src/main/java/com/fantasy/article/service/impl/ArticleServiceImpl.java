package com.fantasy.article.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fantasy.article.mapper.IArticleMapper;
import com.fantasy.article.model.Article;
import com.fantasy.article.po.ArticleModel;
import com.fantasy.article.service.IArticleService;
import com.fantasy.base.FantasyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fantasy on 2017/7/15.
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<IArticleMapper, Article> implements IArticleService {

    @Autowired
    IArticleMapper articleMapper;

    @Override
    public List<ArticleModel> getArticles(Long userId) {

        List<ArticleModel> articles = articleMapper.getArticles(userId);
        /*
        //字符串长度大于250,截断
        for(int i = 0; i < articles.size();i++){
            String articleContent = articles.get(i).content;
            if(articleContent.length() > 20){
                articleContent = articleContent.substring(0,20);
                articleContent += "...";
                articles.get(i).content = articleContent;
            }
        }
        */
        return articles;
    }

    /**
     * 保存文章
     * @param article
     */
    @Override
    public void saveArticle(ArticleModel article) {
            //设置文章发布日期
            article.setPostTime(FantasyDate.getStringDateShort());
            articleMapper.saveArticle(article);
    }

    /**
     * 更新文章
     * @param article
     */
    @Override
    public void updateArticle(ArticleModel article) {
           articleMapper.updateArticle(article);
    }


}
