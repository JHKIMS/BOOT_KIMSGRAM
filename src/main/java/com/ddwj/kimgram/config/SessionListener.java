package com.ddwj.kimgram.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Slf4j
public class SessionListener implements HttpSessionListener {

    private final static int MAX_SESSION_TIMEOUT = 60 * 60;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("시간 설정됨. 1시간 ");
        se.getSession().setMaxInactiveInterval(MAX_SESSION_TIMEOUT);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
