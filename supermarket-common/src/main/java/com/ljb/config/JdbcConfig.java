package com.ljb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/15<br>
 * 描述: <br>
 */
@Configuration
public class JdbcConfig {

    @Bean
    @Autowired
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
