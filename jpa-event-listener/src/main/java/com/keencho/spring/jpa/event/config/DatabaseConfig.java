package com.keencho.spring.jpa.event.config;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.SpringBeanContainer;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

//@Configurable
//@EnableJpaRepositories(
//        basePackages = "com.keencho.spring.jpa.event.repository",
//        entityManagerFactoryRef = "entityManager"
//)
public class DatabaseConfig {

////    @Bean
////    @ConfigurationProperties(prefix = "spring.datasource")
////    public DataSource mainDataSource() {
////        return DataSourceBuilder.create().build();
////    }
//
//    @Bean
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManager(
//            DataSource dataSource,
//            ConfigurableListableBeanFactory beanFactory
//    ) {
//
//        var em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
////        em.setPackagesTocan("com.keencho.spring.jpa.event.model");
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//
//        var properties = new HashMap<String, Object>();
//        properties.put("hibernate.hbm2ddl.auto", "create-drop");
//        properties.put(AvailableSettings.BEAN_CONTAINER, new SpringBeanContainer(beanFactory));
//
//        em.setJpaPropertyMap(properties);
//        return em;
//    }
}
