package com.fantasy.comment.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fantasy.comment.model.Comment;
import com.fantasy.comment.po.CommentModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * Created by fantasy on 2018/1/7.
 */
public interface ICommentMapper extends BaseMapper<Comment> {

    @Select("select *" +
            " from comment as c left join user u on c.userId = u.id where " +
            "c.userId = #{userId} and c.articleId = #{articleId} order by c.commentTime desc")
    List<CommentModel> getComments(Comment comment);

}
