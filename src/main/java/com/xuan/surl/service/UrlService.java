
package com.xuan.surl.service;


import java.util.Date;

public interface UrlService {

    String getShortUrl(String short_url);

    int insert(String long_url, String short_url, Date createTime);

}
