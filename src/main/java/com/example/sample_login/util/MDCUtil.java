package com.example.sample_login.util;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class MDCUtil {

    public static enum LOG_COMMON_INFO{
        METHOD_NAME("methodName"),
        METHOD_ARGS("methodArgs"),
        METHOD_RETURN("methodReturn"),
        ;

        String key;

        LOG_COMMON_INFO(String key){
            this.key = key;
        }

        public String getValue(){
            return get(key);
        }
        public void putValue ( String value ) {
            put( key, value );
        }

    }

    public static enum WEB_LOG_INFO {

        REQUEST_UID ( "requestUID" ),
        REQUEST_USERAGENT ( "requestUserAgent" ),
        REQUEST_START_TIME ( "requestStartTime" ),
        ;

        String key;

        WEB_LOG_INFO ( String key ) {
            this.key = key;
        }

        public String getValue () {
            return get( key );
        }

        public void putValue ( String value ) {
            put( key, value );
        }
    }

    /**
     * MDC에 값을 넣습니다.
     *
     * @author hj
     * @param key - Log에서 받을 Key, Logger에서 %X로 접근 가능
     * @param value - Log로 남길 Value
     */
    public static void put (String key, String value){
        MDC.put(key,value);
    }

    /**
     * MDC에서 Key로 값을 얻습니다.
     *
     * @author hj
     * @param key - 조회 KEY
     * @return {string|""} - 값이 없으면 ""를 반환
     * */
    public static String get(String key){
        return MDCUtil.get(key,"");
    }

    /**
     * MDC에서 Key로 값을 얻습니다.
     *
     * @author hj
     * @param key -  조회 KEY
     * @param defaultVal - Key로 조회한 값이 null이면 받을 값
     * */
    public static String get( String key , String defaultVal ) {

        String value = MDC.get( key );

        if ( !StringUtils.hasText( value ) ) {

            return defaultVal;
        }

        return value;
    }

    /**
     * MDC에서 해당 Key를 삭제합니다.
     *
     * @author hj
     * @param key - 식제할 KEY
     * */
    public static void remove ( String key ) {

        if ( !StringUtils.hasText( key ) ) {
            return;
        }

        MDC.remove( key );
    }

    /**
     * MDC 초기화
     *
     * @author hj
     * */
    public static void clear() {
        MDC.clear();
    }
}
