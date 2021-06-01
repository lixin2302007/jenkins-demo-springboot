package com.elane.learning.controller;

import com.elane.learning.utils.MongoLogUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Cacheable(value = "USER", key = "#id")
    @GetMapping("/user1")
    public User getUser(String id) {
        logger.info("get user:{}", id);
        User user = new User();
        user.setId(id);
        user.setName("王二毛");
        user.setAge(12);
//        MongoLogUtil.printLog(new Gson().toJson(user));
        logger.debug("测试是否初始化信息：{}", () -> new Gson().toJson(user));
        return user;
    }

    @Caching(put = {
            @CachePut(value = "USER", key = "#id"),
            @CachePut(value = "USER", key = "#name")
    })
    @GetMapping("/cache/put")
    public User cachePut(String id, String name, Integer age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Cacheable(value = "USER", key = "#name")
    @GetMapping("/user2")
    public User getUserByName(String name) {
        logger.info("get user:{}", name);
        User user = new User();
        user.setId("1");
        user.setName(name);
        user.setAge(12);
        return user;
    }
}
