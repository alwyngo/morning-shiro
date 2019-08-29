package com.morning.morningshiro.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        Session s = retrieveSessionFromDataSource(sessionId);
        if (s == null) {
            //session ID was provided, meaning one is expected to be found, but we couldn't find one:
            String msg = "Could not find session with ID [" + sessionId + "]";
            throw new UnknownSessionException(msg);
        }
        return s;
    }
}
