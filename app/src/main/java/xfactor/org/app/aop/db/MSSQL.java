package xfactor.org.app.aop.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "mssqlEntityManagerFactory", transactionManagerRef = "mssqlTransactionManager", basePackages = {"xfactor.org.app.repository.iMSSQL"})
public class MSSQL {

    private final Environment env;

    public MSSQL(Environment env) {
        this.env = env;
    }

    @Primary
    @Bean(name = "mssqlDataSource")
    public DataSource mssqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(env.getProperty("mssql.datasource.url"));
        dataSource.setUsername(env.getProperty("mssql.datasource.username"));
        dataSource.setPassword(env.getProperty("mssql.datasource.password"));
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("mssql.datasource.driver-class-name")));

        return dataSource;
    }

    @Primary
    @Bean(name = "mssqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(mssqlDataSource());
        em.setPackagesToScan("xfactor.org.app.models.mssql");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", env.getProperty("mssql.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("mssql.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.show_sql", env.getProperty("mssql.jpa.show-sql"));

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean(name = "mssqlTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

}
