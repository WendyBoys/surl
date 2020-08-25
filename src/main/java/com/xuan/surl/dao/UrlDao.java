
package com.xuan.surl.dao;


import java.util.Date;

public interface UrlDao {

    String getShortUrl(String short_url);

    int insert(String long_url, String short_url, Date createTime);
}
