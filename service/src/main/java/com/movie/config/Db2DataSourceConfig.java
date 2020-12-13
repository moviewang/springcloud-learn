package com.movie.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: movie
 * @Date: 2020/4/29 17:06
 */
@Configuration
@MapperScan(value = "com.movie.module.employee", sqlSessionFactoryRef = "db2SqlSessionFactory")
public class Db2DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource() {
        return new HikariDataSource();
    }

    @Bean
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager txManager2() {
        return new DataSourceTransactionManager(db2DataSource());
    }
}
