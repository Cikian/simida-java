package cn.simida.socialFeed.mapper;

import cn.simida.socialFeed.pojo.Follow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
    @Select("SELECT following " +
            "FROM follow " +
            "WHERE following NOT IN (${followedUserIds}) " +
            "GROUP BY following " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT #{limit}")
    List<String> selectRecommendedUserIds(
            @Param("followedUserIds") String followedUserIds,
            @Param("limit") int limit
    );
}
