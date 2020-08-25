
package com.xuan.surl.dao.daoImpl;

import com.xuan.surl.dao.UrlDao;
import com.xuan.surl.mapper.UrlMapper;
import com.xuan.surl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class UrlDaoImpl implements UrlDao {
    @Autowired
    private UrlMapper urlMapper;
    @Override
    public String getShortUrl(String shortUrl) {
        return urlMapper.getShortUrl(shortUrl);
    }

    @Override
    public int insert(String long_url, String short_url, Date createTime) {
        return urlMapper.insert(long_url,short_url,createTime);
    }
}
