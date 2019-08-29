package com.morning.morningshiro.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomizeRealm  extends AuthorizingRealm {
    /**
     * @desc 获取授权信息
     * @param
     * @author lxu003
     * @date   2019/8/29 10:18
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * @desc 获取认证信息
     * @param
     * @author lxu003
     * @date   2019/8/29 10:18
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object userName = authenticationToken.getPrincipal();
        Object credentials = authenticationToken.getCredentials();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, credentials,getName());
        return simpleAuthenticationInfo;
    }
}
