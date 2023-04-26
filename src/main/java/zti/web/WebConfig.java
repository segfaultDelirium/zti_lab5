package zti.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import javax.sql.DataSource;

@EnableWebMvc
@ComponentScan(basePackages = "zti")
@Configuration

public class WebConfig implements ApplicationContextAware, WebMvcConfigurer {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/views/jsp/");
        bean.setViewNames("*.jsp");
        return bean;
    }


    @Bean
    public DataSource dbDataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/postgres");
        return dataSource;
    }

}