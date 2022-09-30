package com.pjb.springbootjjwt.api;

import com.alibaba.fastjson.JSONObject;
import com.pjb.springbootjjwt.annotation.PassToken;
import com.pjb.springbootjjwt.annotation.UserLoginToken;
import com.pjb.springbootjjwt.entity.User;
import com.pjb.springbootjjwt.service.TokenService;
import com.pjb.springbootjjwt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author jinbin
 * @date 2018-07-08 20:45
 */
@Controller
@Api(tags = "page")
@RequestMapping("/page")
public class PageApi {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @UserLoginToken
    @GetMapping("/UserLoginToken")
    public String userLoginToken() {
        return "html";
    }

    @PassToken
    @GetMapping("/PassToken")
    public String passToken() {
        return "html";
    }

    @GetMapping("/message")
    public String message() {
        return "html";
    }
}
