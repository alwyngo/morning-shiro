package com.morning.morningshiro.config.shiro;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.morning.morningshiro.dao.entity.UserEntity;
import com.morning.morningshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;

import java.util.List;
import java.util.Optional;

public class CustomizeRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;

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
        String userName = (String) authenticationToken.getPrincipal();
        String passWord = String.valueOf((char[])authenticationToken.getCredentials());
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        UserEntity entity = new UserEntity();
        entity.setUserName(userName);
        queryWrapper.setEntity(entity);
        Optional<UserEntity> optional = userService.list(queryWrapper).stream().findFirst();
        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            userEntity.getPassWord();
            String encode = Base64.encode(passWord.getBytes()).toString();
            if (encode.equals(userEntity.getPassWord())) {
                SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, passWord, getName());
                return simpleAuthenticationInfo;
            } else {
                throw new IncorrectCredentialsException("密码错误");
            }
        } else {
            throw new UnknownAccountException("未知用户");
        }
    }
}
