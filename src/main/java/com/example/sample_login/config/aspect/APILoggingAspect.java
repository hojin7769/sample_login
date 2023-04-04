package com.example.sample_login.config.aspect;

import com.example.sample_login.util.MDCUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
public class APILoggingAspect {

    private static final Logger apiLog = LoggerFactory.getLogger("API_LOG");

    @Around(value = "execution(* *..*Controller.*(..))")
    public Object onExecutionAPI(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long lStartTime = System.currentTimeMillis();
        Object result = null;
        boolean isSuccess = false;

        try {

            result = proceedingJoinPoint.proceed();
        }catch (Throwable throwable) {
            throw throwable;
        }finally {
            String apiName = proceedingJoinPoint.getSignature().getName();
            String reqId = MDCUtil.WEB_LOG_INFO.REQUEST_UID.getValue();
            String startTime = LocalDateTime.ofInstant( Instant.ofEpochMilli(lStartTime), ZoneId.systemDefault()).format( DateTimeFormatter.ISO_DATE_TIME );

            MDCUtil.WEB_LOG_INFO.REQUEST_UID.putValue( reqId );
            MDCUtil.WEB_LOG_INFO.REQUEST_START_TIME.putValue( startTime );
            MDCUtil.LOG_COMMON_INFO.METHOD_NAME.putValue( apiName );
            MDCUtil.LOG_COMMON_INFO.METHOD_ARGS.putValue( Arrays.toString( proceedingJoinPoint.getArgs() ) );
            MDCUtil.LOG_COMMON_INFO.METHOD_RETURN.putValue( result != null ? result.toString() : "void" );
            apiLog.info("");
        }
        return result;
    }
}
