package com.regalite.routing.config;


import com.regalite.routing.domain.BranchEnum;
import com.regalite.routing.domain.Intern;
import com.regalite.routing.routing.DataSourceRouting;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Configuration
@EnableJpaRepositories(basePackages = "com.regalite.routing.repository", transactionManagerRef = "transcationManager", entityManagerFactoryRef = "entityManager")
@EnableTransactionManagement
public class DataSourceConfig {
    @Bean
    @Primary
    @Autowired
    public DataSource dataSource() {
        DataSourceRouting dataSourceRouting = new DataSourceRouting();
        dataSourceRouting.setTargetDataSources(targetDataSources());
        dataSourceRouting.setDefaultTargetDataSource(noidaDataSource());
        return dataSourceRouting;
    }

    private Map<Object, Object> targetDataSources() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(BranchEnum.NOIDA, noidaDataSource());
        targetDataSources.put(BranchEnum.BANGALORE, bangaloreDataSource());
        return targetDataSources;
    }

    @Bean
    @ConfigurationProperties("datasource.korea")
    public DataSourceProperties koreaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource noidaDataSource() {
        return koreaDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @ConfigurationProperties("datasource.japan")
    public DataSourceProperties japanDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource bangaloreDataSource() {
        return japanDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource()).packages(Intern.class)
                .build();
    }

    @Bean(name = "transcationManager")
    public JpaTransactionManager transactionManager(
            @Autowired @Qualifier("entityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }
}
