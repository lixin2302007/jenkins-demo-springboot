package com.elane.learning.controller;

import com.elane.learning.security.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class AuthenticationController {

    //请求缓存
    private RequestCache requestCache = new HttpSessionRequestCache();
    //跳转工具
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 当需要身份认证时跳转到这里
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authencation/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws IOException {
        //获取引发跳转的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                //如果是html请求跳转过来的则跳转到配置登录页,如果没有配置登录页则跳转到标准登录页
                redirectStrategy.sendRedirect(request, response, "signIn.html");
            }
        }

        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
    }
}
