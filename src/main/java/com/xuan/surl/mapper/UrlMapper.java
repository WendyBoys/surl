
package com.xuan.surl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Mapper
public interface UrlMapper {
    String getShortUrl(String short_url);

    int insert(@Param("long_url") String long_url, @Param("short_url")String short_url, @Param("createTime") Date createTime);
}
