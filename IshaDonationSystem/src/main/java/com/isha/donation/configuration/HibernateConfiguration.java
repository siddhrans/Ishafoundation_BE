package com.isha.donation.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.isha.donation.IdGeneration.CompositeId;
import com.isha.donation.excelhead.ExcelColumn;

 

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.isha.donation.configuration" })
@PropertySource(value = { "classpath:application.properties" }) 
public class HibernateConfiguration {
	 
	@Bean
	public CompositeId getCompositeId(){
		return new CompositeId();
	}
	
	
	@Bean
	public ExcelColumn getColumn(){
		return new ExcelColumn();
	}
	
	static{
		System.out.println("HibernateCOnfiguration->s.b");
	}
 
    @Autowired
    private Environment environment;
 
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
    	System.out.println("HibernateCOnfiguration->LocalSessionFactoryBean()");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.isha.donation.entity" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
     
    @Bean
    public DataSource dataSource() {
    	System.out.println("HibernateCOnfiguration->DataSource()");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
     
    private Properties hibernateProperties() {
    	System.out.println("HibernateCOnfiguration->Properties->hibernateProperties()");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.id.new_generator_mappings","false");
        return properties;        
    }
     
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
    	System.out.println("HibernateCOnfiguration->HibernateTransactionManager->transactionManager()");
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
    
     
   /* @Bean
    @Autowired
    public SchoolPdfView getBean(){
    	return new SchoolPdfView();
    } */
}

	
 
