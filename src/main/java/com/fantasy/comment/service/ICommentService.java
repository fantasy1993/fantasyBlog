package com.fantasy.comment.service;

import com.baomidou.mybatisplus.service.IService;
import com.fantasy.comment.model.Comment;
import com.fantasy.comment.po.CommentModel;

import java.util.List;

/**
 * Created by fantasy on 2018/1/7.
 */
public interface ICommentService extends IService<Comment> {

    List<CommentModel> getComments(Comment comment);

    void saveComments(CommentModel commentModel);
}
