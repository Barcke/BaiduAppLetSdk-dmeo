package com.barcke.y.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.barcke.y.baidu.core.BaiduServiceFactory;
import com.barcke.y.baidu.pojo.thirdpart.auto.response.MiniTokenResponse;
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
  * @ClassName AuthCodeController
  * @Description TODO
  * @Author Barcke
  * @Date 2020/5/27 5:15 下午
  * @Version 1.0
  * @slogan: 源于生活 高于生活
  * @description: 
  **/
@RestController
public class AuthCodeController {

    @Autowired
    private BaiduServiceFactory baiduServiceFactory;

    /**
     * 由百度进行回调
     * 在ticket操作完成后百度回调到该地址即可获得code 并通过code获取到小程序的accessToken
     * @author barcke
     * @date 2020/5/27
     */
    @RequestMapping("/code")
    public String getCode(String authorization_code, @RequestBody(required = false) Map<String,Object> map){
        System.out.println("code===》"+authorization_code);

        //调用该方法即可获得小程序的accessToken 获得token后可解密code信息拿到sessionKey
        //该方法在JVM缓存中拿到第三方的accessToken 也可通过重载方法给定第三方的accessToken
        //可通过baiduApplicationContext获得对应的tokent  信息存取在JVM缓存中
        MiniTokenResponse miniTokenResponse = baiduServiceFactory.getBaiduAuthService().codeChangeMiniToken(authorization_code);
        System.out.println("miniTokenResponse===》"+JSONObject.toJSONString(miniTokenResponse));

        return "success";
    }
}
