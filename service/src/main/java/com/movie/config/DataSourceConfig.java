package com.movie.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @Author: movie
 * @Date: 2020/4/29 17:06
 */
@Configuration
@MapperScan(value = "com.movie.module.student.dao", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DataSourceConfig implements TransactionManagementConfigurer {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Primary
    public DataSource db1DataSource() {
        return new HikariDataSource();
    }

    @Bean
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("txManager1")
    @Primary
    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(db1DataSource());
    }
}
