package com.barcke.y.demo.controller;

import com.barcke.y.baidu.core.BaiduServiceFactory;
import com.barcke.y.baidu.pojo.mini.auth.response.MiniLoginResponse;
import com.barcke.y.baidu.pojo.mini.user.response.GetUserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  * @ClassName UserController
  * @Description TODO
  * @Author Barcke
  * @Date 2020/5/27 5:20 下午
  * @Version 1.0
  * @slogan: 源于生活 高于生活
  * @description: 
  **/
@RestController
public class UserController {

    @Autowired
    private BaiduServiceFactory baiduServiceFactory;

    /**
     * 通过code换取sessionKey
     * @author barcke
     * @date 2020/5/27
     */
    @RequestMapping("/getSessionKey")
    public MiniLoginResponse getSessionKey(String code){

        //该方法使用重载方法 从缓存中获取到token 如若需要指定token 使用重载方法即可
        //注意token有失效时间 token快失效时 可通过refre方法重新换取 接口地址：baiduServiceFactory.getBaiduAuthService().refreshMiniToken()
        MiniLoginResponse miniLoginResponse = baiduServiceFactory.getBaiduMiniAuthService().miniLogin(code);
        return miniLoginResponse;
    }

    /**
     * 通过code换取sessionKey
     * @author barcke
     * @date 2020/5/27
     */
    @RequestMapping("/getUserInfo")
    public GetUserInfoResponse getUserInfo(String data,String sessionKey){

        GetUserInfoResponse userInfo = baiduServiceFactory.getBaiduMiniUserService().getUserInfo(data, sessionKey);
        return userInfo;
    }

}
