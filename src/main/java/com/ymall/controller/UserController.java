package com.ymall.controller;

import com.ymall.common.Const;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.UnauthorizedException;
import com.ymall.pojo.User;
import com.ymall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zc on 2017/6/13.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse<User> login(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password,HttpSession session) throws UnauthorizedException {

            User user=new User();
            user.setUsername(username);
            user.setPassword(password);

        ServerResponse response = userService.login(user);

        // 向session中加入当前用户信息
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }

        return response;
    }
}
