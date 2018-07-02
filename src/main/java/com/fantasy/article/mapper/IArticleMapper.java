package com.fantasy.article.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fantasy.article.model.Article;
import com.fantasy.article.po.ArticleModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by fantasy on 2017/7/22.
 */
public interface IArticleMapper extends BaseMapper<Article> {

    /**
     * 获取文章
     * @return
     */
    @Select("<script>"+
            "select title,imgUrl,postTime,content,username,userId," +
            "articleId from article as a left join user u on a.userId = u.id where " +
            "<if test='userId != null'>"+
            "a.userId = #{userId} " +
            "</if>"+
            "order by a.postTime desc"+
            "</script>")
    public abstract List<ArticleModel> getArticles(@Param("userId") Long userId);

    /**
     * 保存文章
     * @param article
     */

    public abstract void saveArticle(ArticleModel article);

    /**
     * 更新发布的文章
     * @param article
     */
    public abstract void updateArticle(ArticleModel article);

    @Delete("delete from article where articleId = #{articleId}")
    void deleteArticleById(@Param("articleId")Long articleId);

}
