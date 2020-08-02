package com.example.springbootweb.web.controller;

import com.example.springbootweb.common.entity.Result;
import com.example.springbootweb.common.error.SpringBootWebErrors;
import com.example.springbootweb.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author william
 * @date 2020/7/23
 */
@Slf4j
@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> notLogin() {
        log.debug("用户尚未登录");
        return Result.wrapErrorResult(SpringBootWebErrors.NOT_LOGIN);
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> notRole() {
        return Result.wrapErrorResult(SpringBootWebErrors.NOT_PERMISSION);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.wrapSuccessfulResult("退出成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<Object> login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        String role = userMapper.getRole(username);
        if ("user".equals(role)) {
            return Result.wrapSuccessfulResult("欢迎登陆");
        }
        if ("admin".equals(role)) {
            return Result.wrapSuccessfulResult("欢迎来到管理员页面");
        }
        return Result.wrapErrorResult(SpringBootWebErrors.NOT_PERMISSION);
    }
}
