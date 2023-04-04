package com.example.sample_login.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalDataSourceConfigurer {

    private final String jdbcUrl;

    private final String driveClassName;

    private final String userName;

    private final String password;

    public LocalDataSourceConfigurer( @Value( "${spring.datasource.url}" ) String jdbcUrl ,
                                      @Value( "${spring.datasource.driver-class-name}" ) String drierClassName,
                                      @Value( "${spring.datasource.username}" ) String userName,
                                      @Value( "${spring.datasource.password}" ) String password
    ){

        this.jdbcUrl = jdbcUrl;
        this.driveClassName = drierClassName;
        this.userName = userName;
        this.password = password;

    }

    @Bean( name = "local-dataSource" )
    public HikariConfig hikariConfig() {

        HikariConfig hc = new HikariConfig();

        hc.setJdbcUrl( jdbcUrl );
        hc.setUsername( userName );
        hc.setPassword( password );
        hc.setDriverClassName( driveClassName );

        // MinumumIdle : 일하지 않는 Connection을 유지하는 설정
        hc.setMinimumIdle( 1 );

        // IdleTimeOut : 일하지 않는 Connection Pool을 유지하는 시간
        hc.setIdleTimeout( 10000 );

        // ConnectionTimeout : Connection을 가져오기 위해 기다리는 시간 Timout 되면 SqlException을 던짐
        hc.setConnectionTimeout( 50000 );

        // MaximumPoolSize : Connection Pool에 유지할 Connection의 수 -> 현재 Pool Size가 10 이라면 idle 상태인 pool은 없음
        hc.setMaximumPoolSize( 10 );

        return hc;
    }
}
