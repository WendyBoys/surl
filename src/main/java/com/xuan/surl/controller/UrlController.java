
package com.xuan.surl.controller;

import com.google.common.hash.*;
import com.xuan.surl.Enum.Enum;
import com.xuan.surl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Date;

@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;
    private String projectPath = Paths.get(System.getProperty(Enum.USERDIR.getMapping())).toString();
    private int size = 1000000;//预计要插入多少数据
    private double fpp = 0.001;//期望的误判率
    BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    @RequestMapping("/{short_url}")
    public String url(@PathVariable("short_url") String short_url) {
        //根据短链查询对应的长链
        String longurl = urlService.getShortUrl(short_url);
        //重定向到长链
        return "redirect:" + longurl;
    }

    @RequestMapping("bloom")
    @ResponseBody
    public String bloom(String long_Url) {
        //将长url转换为短url
        String to62url = short_url(long_Url);
        //插入数据库
        int result = urlService.insert(long_Url, to62url, new Date());
        return to62url;
    }

    //将长url转换为短url
    public String short_url(String longUrl) {
        //MurmurHash算法
        HashFunction function = Hashing.murmur3_32();
        HashCode hashCode = function.hashString(longUrl, StandardCharsets.UTF_8);
        //i为长url的murmur值
        int i = Math.abs(hashCode.asInt());
        //准备一个url在生成的murmur值重复时拼接字符串用
        String newurl = longUrl;
        //isContain如果为true说明布隆过滤器中已存在
        boolean isContain = bloomFilter.mightContain(i);
        while (isContain) {
            newurl += Enum.ALREADY.getMapping();
            hashCode = function.hashString(newurl, StandardCharsets.UTF_8);
            //使用拼接过字符串的url重新生成murmur值
            i = Math.abs(hashCode.asInt());
            isContain = bloomFilter.mightContain(i);
        }
        //将murmur值放入布隆过滤器
        bloomFilter.put(i);
        //转成62进制位数更短
        return encode(i);
    }

    //将目标转换为62进制 位数更短
    private String encode(long num) {
        String chars = Enum.CHARS.getMapping();
        int scale = 62;
        StringBuilder sb = new StringBuilder();
        int remainder;
        while (num > scale - 1) {
            remainder = Long.valueOf(num % scale).intValue();
            sb.append(chars.charAt(remainder));
            num = num / scale;
        }
        sb.append(chars.charAt(Long.valueOf(num).intValue()));
        return sb.reverse().toString();
    }


    @RequestMapping("persist")
    @ResponseBody
    public String persist()
    {
        File bloomDump= new File(projectPath + File.separator + "bloomDump");
        OutputStream out;
        try {
            out = new FileOutputStream(bloomDump);
            bloomFilter.writeTo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "持久化布隆过滤器数据成功";
    }
    //将本地的数据还原到布隆过滤器
    @RequestMapping("resume")
    @ResponseBody
    public String resume()
    {
        File bloomDump= new File(projectPath + File.separator + "bloomDump") ;
        InputStream in;
        try {
            in = new FileInputStream(bloomDump);
            bloomFilter = BloomFilter.readFrom(in,Funnels.integerFunnel());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "恢复布隆过滤器数据成功";
    }
}
