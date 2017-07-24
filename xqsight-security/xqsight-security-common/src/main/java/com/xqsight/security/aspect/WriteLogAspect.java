package com.xqsight.security.aspect;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.model.SysLog;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.web.WebUtils;
import com.xqsight.security.annontation.WriteLog;
import com.xqsight.security.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author wangganggang
 * @date 2017年07月24日 11:02
 */
@Aspect
@Component
public class WriteLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("@annotation(com.xqsight.security.annontation.WriteLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        WriteLog syslog = method.getAnnotation(WriteLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSON.toJSONString(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {

        }

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //设置IP地址
        sysLog.setIp(WebUtils.getUserIp(request));

        //用户名
        String username = ((BaseUserModel) SecurityUtils.getSubject().getPrincipal()).getUserName();
        sysLog.setUsername(username);

        sysLog.setTime(time);
        //保存系统日志
        sysLogService.add(sysLog);
    }
}
