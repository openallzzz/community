package com.nowcoder.community.utils;

public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_USER_LIKE = "like:user";
    private static final String PREFIX_FOLLOWEE = "followee"; // 目标
    private static final String PREFIX_FOLLOWER = "follower"; // 关注者

    // 某个实体的赞
    // like:entity:entityType:entityId -> set(userId) -> 谁给这个实体点过赞
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    // 某个用户获得的赞
    // like:user:userId -> int
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    // 某个用户关注的实体（followee）
    // followee:userId:entityType -> zset(entityId, now) 键：用户id + 实体类型 值：实体id + 实体id关联的分数（这里是时间）
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    // 某个实体拥有的粉丝（follower）
    // follower:entityType:entityId -> zset(userId, now) 键：实体类型 + 实体id 值：用户id + 用户id关联的分数（这里是时间）
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

}
