package cn.simida.socialFeed.dao;

import cn.simida.socialFeed.pojo.Like;
import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;

import java.util.List;

public interface LikeDao {
    boolean addLike(Like like);
    boolean deleteLike(String feedId, String userId);
    Like getLike(String feedId, String userId);

    List<Like> getLikesByFeedId(String feedId);
    List<Like> getLikesByUserId(String userId);
}
