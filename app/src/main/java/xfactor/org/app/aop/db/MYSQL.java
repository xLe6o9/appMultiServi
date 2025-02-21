package xfactor.org.app.aop.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory", transactionManagerRef = "mysqlTransactionManager", basePackages = {"xfactor.org.app.repository.iMYSQL"})
public class MYSQL {

    private final Environment env;

    public MYSQL(Environment env) {
        this.env = env;
    }

    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(env.getProperty("mysql.datasource.url"));
        dataSource.setUsername(env.getProperty("mysql.datasource.username"));
        dataSource.setPassword(env.getProperty("mysql.datasource.password"));
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("mysql.datasource.driver-class-name")));

        return dataSource;
    }

    @Bean(name = "mysqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(mysqlDataSource());
        em.setPackagesToScan("xfactor.org.app.models.mysql");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("mysql.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("mysql.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", env.getProperty("mysql.jpa.show-sql"));

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

}
