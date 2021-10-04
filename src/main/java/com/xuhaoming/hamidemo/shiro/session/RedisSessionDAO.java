package com.xuhaoming.hamidemo.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Shiro会话的Redis存储实现
 *
 * @author xuhaoming
 * @date 2021/10/4
 */
@SuppressWarnings("all")
public class RedisSessionDAO extends AbstractSessionDAO {

    public static final long SESSION_EXPIRE_TIME = 300;

    public static final String SESSION_KEY_PREFIX = "shifo_session:";

    /**
     * 使用JDK序列化存储Session接口类
     */
    @Autowired
    @Qualifier("jdkRedisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(this.getSessionIdWithPrefix(session), session, SESSION_EXPIRE_TIME, TimeUnit.SECONDS);
        return sessionId;
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        return (Session)redisTemplate.opsForValue().get(this.getSessionIdWithPrefix(sessionId));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        redisTemplate.opsForValue().set(this.getSessionIdWithPrefix(session), session, SESSION_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void delete(Session session) {
        redisTemplate.delete(this.getSessionIdWithPrefix(session));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Collection<Session> activeSessions = new ArrayList<>();
        Set sessionIdRedisKeys = redisTemplate.keys(SESSION_KEY_PREFIX + "*");
        if (sessionIdRedisKeys != null && !sessionIdRedisKeys.isEmpty()) {
            for (Object sessionIdRedisKey : sessionIdRedisKeys) {
                activeSessions.add((Session)redisTemplate.opsForValue().get(sessionIdRedisKey));
            }
        }
        return activeSessions;
    }


    private Serializable getSessionIdWithPrefix(Serializable sessionId) {
        return SESSION_KEY_PREFIX + sessionId;
    }

    private Serializable getSessionIdWithPrefix(Session session) {
        return SESSION_KEY_PREFIX + session.getId();
    }

}
