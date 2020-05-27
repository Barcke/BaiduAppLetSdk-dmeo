package com.barcke.y.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.barcke.y.baidu.core.BaiduServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
  *                  ,;,,;
  *                ,;;'(    社
  *      __      ,;;' ' \   会
  *   /'  '\'~~'~' \ /'\.)  主
  * ,;(      )    /  |.     义
  *,;' \    /-.,,(   ) \    码
  *     ) /       ) / )|    农
  *     ||        ||  \)     
  *     (_\       (_\
  *
  * @ProjectName BaiduAppLetSdk-dmeo
  * @ClassName TicketController
  * @Description TODO
  * @Author Barcke
  * @Date 2020/5/27 5:11 下午
  * @Version 1.0
  * @slogan: 源于生活 高于生活
  * @description: 
  **/
@RestController
public class TicketController {

    @Autowired
    private BaiduServiceFactory baiduServiceFactory;

    private String url;

    /**
     * 需要在申请第三方时写入调用地址
     * 由百度触发（每十分钟触发一次） 可从该接口中获取到 ticket 不过是加密过的 通过工具类可进行解密等操作
     * @author barcke
     * @date 2020/5/27
     * @param map
     * @return java.lang.String
     */
    @RequestMapping("/ticket")
    public String getTicket(@RequestBody(required = false) Map<String,Object> map){
        System.out.println("map===》"+ JSONObject.toJSONString(map));

        //进行认证  该接口包含从ticket到预授权码 直接给定回调地址即可
        //不需要每次ticket都进行操作 只要在第三方accessToken过期前调用即可
        if (StringUtils.isBlank(url)) {
            url = baiduServiceFactory.getBaiduAuthService().getAuthorizationUrlByTicket(map, "http://yyyyyy.vipgz1.idcfengye.com/code");
        }

        System.out.println("ticket方法Url===》"+url);

        return "success";
    }

    @RequestMapping("/getUrl")
    public String getUrl(){
        return url;
    }
}
