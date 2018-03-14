package com.fantasy.comment.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fantasy.comment.mapper.ICommentMapper;
import com.fantasy.comment.model.Comment;
import com.fantasy.comment.po.CommentModel;
import com.fantasy.comment.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by fantasy on 2018/1/7.
 */
@Service
public class CommentServiceImpl extends ServiceImpl<ICommentMapper, Comment> implements ICommentService {

    @Autowired
    ICommentMapper commentMapper;

    @Override
    public void saveComments(CommentModel commentModel) {

    }

    @Override
    public List<CommentModel> getComments(Comment comment) {
        return commentMapper.getComments(comment);
    }
}
