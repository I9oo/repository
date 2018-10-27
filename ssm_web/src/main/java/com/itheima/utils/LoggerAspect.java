package com.itheima.utils;
import java.util.Date;

import com.itheima.domain.SysLog;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志的切面类
 * 1.在访问控制器方法时候，自动记录系统日志
 * 2.关键点：
 *   切入点表达式
 *   环绕通知
 */
@Component
@Aspect
public class LoggerAspect {
    // 注入service
    @Autowired
    private ISysLogService sysLogService;
    // 注入request对象(配置ServletRequest监听器：RequestContextListener)
    @Autowired
    private HttpServletRequest request;

    /**
     * 环绕通知,在执行controller方法之后插入日志
     */
    @Around("execution(* com.itheima.controller.*.*(..))")
    public Object insertLog(ProceedingJoinPoint pjp){

        //1. 获取用户名
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();

        //2. 获取当前执行的类+方法。如：com.itheima.controller.UserController.save()
        //2.1 获取当前执行的类全名
        String className = pjp.getTarget().getClass().getName();
        //2.2 获取方法
        String method = pjp.getSignature().getName();

        //3.获取来访者ip
        String ip = request.getRemoteAddr();

        //2. 封装SysLog对象
        SysLog sysLog = new SysLog();
        // 访问时间
        sysLog.setVisitTime(new Date());
        // 用户名
        sysLog.setUsername(username);
        sysLog.setMethod(className+"."+method+"(..)");
        // 获取来访者ip
        sysLog.setIp(ip);



        try {
            //放行
            Object retV = pjp.proceed();
            //记录日志
            sysLogService.save(sysLog);
            return retV;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}





















