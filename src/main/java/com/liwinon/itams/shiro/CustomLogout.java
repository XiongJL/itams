package com.liwinon.itams.shiro;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomLogout extends LogoutFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        // 在这里执行退出系统需要情况的数据,这是重新的目的,若没有额外操作,只需要在ShiroConfig配置 logout即可.不需要此类
        System.out.println("开始清除登录信息");
        HttpSession session = ((HttpServletRequest)request).getSession();
        session.invalidate();  //清空Session

        Subject subject = getSubject(request, response);

        String redirectUrl = getRedirectUrl(request, response, subject);

        try {

            subject.logout();

        } catch (SessionException ise) {

            ise.printStackTrace();

        }

        issueRedirect(request, response, redirectUrl);
        //返回false表示不执行后续的过滤器，直接返回跳转到登录页面

        return true;
    }

}
