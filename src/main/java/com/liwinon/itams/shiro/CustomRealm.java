package com.liwinon.itams.shiro;

import com.liwinon.itams.dao.UserDao;
import com.liwinon.itams.entity.User;
import com.liwinon.itams.entity.UserRoleModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 这个类是参照JDBCRealm写的，主要是自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    UserDao userDao;

    /**
     * 获取授权信息
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开始权限认证");
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        //从凭证中获得用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //根据用户名查询用户对象
        List<UserRoleModel> models = userDao.findByUname(username);
        Set<String> set = new HashSet<>();
        for (UserRoleModel model : models){
            set.add((String)model.getName());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(set);
        //设置权限
      //  info.setStringPermissions();
        return info;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("开始身份验证");
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
       // UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获得当前用户的用户名
        String username = (String) authenticationToken.getPrincipal();

        //
        //从数据库中根据用户名查找用户
        List<UserRoleModel> urms = userDao.findByUname(username);
        if (urms == null  || urms.size()<=0) {
            throw new UnknownAccountException(
                    "没有在本系统中找到对应的用户信息。");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(urms.get(0).getUname(), urms.get(0).getPwd(),getName());
        return info;

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
     /*
        还可以获取盐
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDB, userDB.getPwd(), getName());
        if (userDB.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(userDB.getSalt()));
        }*/
    //    return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());

    }
}
