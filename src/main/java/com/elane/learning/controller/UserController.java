package com.elane.learning.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Cacheable(value = "USER", key = "#id")
    @GetMapping("/user1")
    public User getUser(String id) {
        log.info("get user:{}", id);
        User user = new User();
        user.setId(id);
        user.setName("王二毛");
        user.setAge(12);
        return user;
    }

    @Caching(put={
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

    @Cacheable(value = "USER", key="#name")
    @GetMapping("/user2")
    public User getUserByName(String name) {
        log.info("get user:{}", name);
        User user = new User();
        user.setId("1");
        user.setName(name);
        user.setAge(12);
        return user;
    }
}
