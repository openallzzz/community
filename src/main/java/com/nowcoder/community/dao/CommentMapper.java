package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    int selectCountByEntity(int entityType, int entityId);

    int insertComment(Comment comment);

    Comment selectCommentById(int id);

    /**
     * 获取当前用户对帖子发布评论的数量
     *
     * @param userId
     * @param entityType
     * @return
     */
    List<Comment> selectCountByEntityAndPub(int userId, int entityType);
}
