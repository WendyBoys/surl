
package com.xuan.surl.service.impl;

import com.xuan.surl.dao.UrlDao;
import com.xuan.surl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlDao urlDao;

    @Override
    public String getShortUrl(String shortUrl) {
        return urlDao.getShortUrl(shortUrl);
    }

    @Override
    public int insert(String long_url, String short_url, Date createTime) {
        return urlDao.insert(long_url,short_url,createTime);

    }
}
