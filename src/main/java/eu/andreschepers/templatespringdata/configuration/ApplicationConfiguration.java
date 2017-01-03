/*
 * Copyright 2016 Andre Schepers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.andreschepers.templatespringdata.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.vibur.dbcp.ViburDBCPDataSource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"eu.andreschepers.templatespringdata"})
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories("eu.andreschepers.templatespringdata.repositories")
@EnableTransactionManagement
public class ApplicationConfiguration {

    @Autowired
    private Environment env;

    @Bean(name="datasource")
    public DataSource dataSource() {

        try {
            Class.forName(env.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ViburDBCPDataSource ds = new ViburDBCPDataSource();
        ds.setJdbcUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));
        ds.setPoolInitialSize(env.getProperty("db.poolinitialsize", Integer.class));
        ds.setPoolMaxSize(env.getProperty("db.poolmaxsize", Integer.class));
        ds.setConnectionIdleLimitInSeconds(env.getProperty("db.connectionidlelimitinseconds", Integer.class));
        ds.setTestConnectionQuery(env.getProperty("db.testconnectionquery"));
        ds.setLogQueryExecutionLongerThanMs(env.getProperty("db.logqueryexecutionlongerthanms", Integer.class));
        ds.setLogStackTraceForLongQueryExecution(env.getProperty("db.logstacktraceforlongqueryexecution", Boolean.class));
        ds.setStatementCacheMaxSize(env.getProperty("db.statementcachemaxsize", Integer.class));
        ds.start();
        return ds;
    }

    @Bean
    @DependsOn("datasource")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPackagesToScan(env.getProperty("db.packagestoscan"));
        Properties p = new Properties();
        p.put("hibernate.dialect",env.getProperty("db.hibernate.dialect"));
        p.put("hibernate.format_sql", env.getProperty("db.hibernate.format_sql"));
        p.put("hibernate.hbm2ddl.auto",env.getProperty("db.hibernate.hbm2ddl.auto"));
        p.put("hibernate.ejb.naming_strategy",env.getProperty("db.hibernate.ejb.naming_strategy"));
        p.put("hibernate.show_sql",env.getProperty("db.hibernate.show_sql"));
        em.setJpaProperties(p);

        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
