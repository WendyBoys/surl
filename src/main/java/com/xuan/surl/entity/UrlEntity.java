package com.xuan.surl.entity;

import java.util.Date;

public class UrlEntity {

    private Integer id;
    private String long_url;
    private String short_url;
    private Date createTime;

    public UrlEntity() {
    }

    public UrlEntity(Integer id, String longUrl, String short_url, Date createTime) {
        this.id = id;
        this.long_url = longUrl;
        this.short_url = short_url;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "id=" + id +
                ", long_url='" + long_url + '\'' +
                ", short_url='" + short_url + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
