//package com.liwinon.itams.shiro;
//
//import net.sf.json.JSONObject;
//import org.apache.commons.lang.StringUtils;
//import org.apache.shiro.ShiroException;
//import org.apache.shiro.authz.UnauthenticatedException;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
///**
// * 统一捕捉shiro的异常，返回给前台一个json信息，前台根据这个信息显示对应的提示，或者做页面的跳转。
// */
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    //不满足@RequiresGuest注解时抛出的异常信息
//    private static final String GUEST_ONLY = "Attempting to perform a guest-only operation";
//
//    @ExceptionHandler(ShiroException.class)
//    @ResponseBody
//    public String  handleShiroException(ShiroException e) {
//        String eName = e.getClass().getSimpleName();
//        System.out.println("shiro执行出错："+eName);
//        String res = eName+ "/n" + "鉴权或授权过程出错";
//        return res;
//    }
//
//    @ExceptionHandler(UnauthenticatedException.class)
//    @ResponseBody
//    public String page401(UnauthenticatedException e) {
//        String eMsg = e.getMessage();
//        if (StringUtils.startsWithIgnoreCase(eMsg,GUEST_ONLY)){
//            return  "401: 只允许游客访问，若您已登录，请先退出登录 /n"+ e.getMessage();
//        }else{
//            return "401: 用户未登录 /n"+e.getMessage();
//        }
//    }
//
//    @ExceptionHandler(UnauthorizedException.class)
//    @ResponseBody
//    public String page403() {
//        return "403: 用户没有访问权限";
//    }
//}
