package com.morning.morningshiro.shiro;

import com.morning.morningshiro.redis.JedisUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Collection;

@Component
public class CustomizeSessionDao extends AbstractSessionDAO {

    private static final String SHIRO_SESSION = "SHIRO_SESSION";
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        try (Jedis jedis = JedisUtils.getJedis()){
            jedis.set(getKey(sessionId.toString()), SerializationUtils.serialize(session));
        }
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        try (Jedis jedis = JedisUtils.getJedis()){
            System.out.println("read session from:" + sessionId);
            byte[] bytes = jedis.get(getKey(sessionId.toString()));
            Session session = (Session) SerializationUtils.deserialize(bytes);
            return session;
        }
    }

    @Override
    public void update(Session session) throws UnknownSessionException {

    }

    @Override
    public void delete(Session session) {

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }

    byte[] getKey(String key) {
        return (SHIRO_SESSION + key).getBytes();
    }
}
