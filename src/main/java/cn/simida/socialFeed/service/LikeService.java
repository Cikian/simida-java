package cn.simida.socialFeed.service;

import java.util.List;

public interface LikeService {
    boolean addLike(String feedId, String userId, String feedUserId);

    boolean deleteLike(String feedId, String userId, String feedUserId);

    List<String> getLikesByFeedId(String feedId);

    List<String> getLikesByUserId(String userId);
}
