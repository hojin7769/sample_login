package com.example.sample_login.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class BridgeAspect {

    private static final Logger bridgeLog = LoggerFactory.getLogger("Bridge_Log");

    private static final Logger log = LoggerFactory.getLogger("log");

    @Around( "@annotation(com.example.sample_login.common.annotation.Bridge)" )
    public Object bridgeLogging( ProceedingJoinPoint joinPoint ) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();

        Object[] signatureArgs = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();

        for (Object signatureArg : signatureArgs){
            String log = signatureArg.toString();
            sb.append( log );
        }

        log.info(sb.toString());

        Object result = null;
        result = joinPoint.proceed();

        return result;

    }
}
