package com.week07.task02;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class, DruidDataSource.class})
public class RoutingDataSourceAutoConfiguration {

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create(this.getClass().getClassLoader()).
                type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slaveDataSource(@Autowired @Qualifier("masterDataSource") DataSource dataSource) {
        return DataSourceBuilder.create(this.getClass().getClassLoader()).
                type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }

    @Bean
    public DataSource dataSource(
            @Autowired @Qualifier("masterDataSource") DataSource masterDataSource,
            @Autowired @Qualifier("slaveDataSource") DataSource slaveDataSource
    ) {
        DataSource defaultDataSource;

        Map<Object, Object> targetDataSource = ImmutableMap.of(DataSourceAddressEnum.MASTER, defaultDataSource = masterDataSource,
                DataSourceAddressEnum.SLAVE, slaveDataSource);
        return new RoutingDataSourceWithAddress(defaultDataSource, targetDataSource);
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(
            @Autowired @Qualifier("dataSource") DataSource routingDataSourceWithAddress,
            @Autowired MybatisProperties mybatisProperties,
            @Autowired ResourceLoader resourceLoader
            ) throws Exception {
        final SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(routingDataSourceWithAddress);

        final org.apache.ibatis.session.Configuration configuration = mybatisProperties.getConfiguration();
        factory.setConfiguration(configuration);

        String configLocation;
        if (StringUtils.isNotBlank(configLocation = mybatisProperties.getConfigLocation())) {
            factory.setConfigLocation(resourceLoader.getResource(configLocation));
        }

        Resource[] resolveMapperLocation;
        if (ArrayUtils.isNotEmpty(resolveMapperLocation = mybatisProperties.resolveMapperLocations())) {
            factory.setMapperLocations(resolveMapperLocation);
        }

        String typeHandlerPackage;
        if (StringUtils.isNotBlank(typeHandlerPackage = mybatisProperties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(typeHandlerPackage);
        }

        String typeAliasPackage;
        if (StringUtils.isNotBlank(typeAliasPackage = mybatisProperties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(typeAliasPackage);
        }

        return factory.getObject();
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager dataSourceTransactionManager(
            @Autowired @Qualifier("dataSource") DataSource routingDataSOurceWithAddress
    ) {
        return new DataSourceTransactionManager(routingDataSOurceWithAddress);
    }

    @Bean
    public TransactionTemplate transactionTemplate(
            @Autowired @Qualifier("dataSourceTransactionManager")PlatformTransactionManager platformTransactionManager
            ) {
        return new TransactionTemplate(platformTransactionManager);
    }

    @Bean
    @ConditionalOnMissingBean(RoutingDataSourceAOP.class)
    public RoutingDataSourceAOP routingDataSourceAOP() {
        return new RoutingDataSourceAOP();
    }
}
