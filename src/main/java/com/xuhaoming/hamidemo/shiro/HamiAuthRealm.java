package com.xuhaoming.hamidemo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 自定义Realm
 *
 * @author xuhaoming
 * @date 2021/10/4
 */
public class HamiAuthRealm extends AuthorizingRealm {


    /**
     * 授权操作
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String) super.getAvailablePrincipal(principals);
        List<String> roleList = new ArrayList<>();
        HashSet<String> permissionSet = new HashSet<>();
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        simpleAuthorInfo.addRoles(roleList);
        simpleAuthorInfo.addStringPermissions(permissionSet);
        return simpleAuthorInfo;
    }

    /**
     * 验证当前登录的Subject
     * 经测试:本例中该方法的调用时机为LoginController.login方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        if (username == null) {
            throw new AccountException("Null loginName are not allowed by this realm.");
        }
        // 交由校验器校验 CredentialsMatcher.doCredentialsMatch
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(username, token.getPassword(), this.getName());
        return authcInfo;
    }


    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

}
