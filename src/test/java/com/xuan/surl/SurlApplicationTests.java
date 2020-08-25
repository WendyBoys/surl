package com.xuan.surl;

import com.xuan.surl.Enum.Enum;
import com.xuan.surl.mapper.UrlMapper;
import com.xuan.surl.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class SurlApplicationTests {

    @Autowired
    private UrlMapper urlMapper;

    @Autowired
    private UrlService urlService;

    @Test
    void contextLoads() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String format = df.format(new Date());// new Date()为获取当前系统时间
        String bHfsw = urlService.getShortUrl("bHfsw");
        System.out.println(bHfsw);
       /* Connection conn=null;
        try
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (ClassNotFoundException ex)
            {
                System.out.println("加载驱动程序有错误");
            }

            String url = "jdbc:mysql://localhost:3306/sx?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2b8";
            conn = DriverManager.getConnection(url,"root","199951");
            System.out.println("成功连接数据库！！");

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }*/


    }

}


