package com.morning.morningshiro.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.io.Serializable;

@Component
@Slf4j
public class CustomizeSessionManager extends DefaultWebSessionManager {

    @Override
    public Serializable getSessionId(SessionKey key) {
        return super.getSessionId(key);
    }

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        if (sessionId == null) {
            log.debug("Unable to resolve session ID from SessionKey [{}].  Returning null to indicate a " +
                    "session could not be found.", sessionKey);
            return null;
        }

        // 避免多次调用redis
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
            Session session = (Session) request.getAttribute(sessionId.toString());
            if (session != null){
                return session;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (session != null) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
