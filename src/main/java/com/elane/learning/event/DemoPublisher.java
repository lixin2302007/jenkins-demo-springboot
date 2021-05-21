package com.elane.learning.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DemoPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public void setApplicationContext(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(String msg) {
        applicationEventPublisher.publishEvent(new DemoEvent(this, msg));
    }
}
