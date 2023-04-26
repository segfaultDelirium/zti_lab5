package zti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import zti.aspect.AspectLogger;
import zti.model.PersonDaoImpl;

@Configuration
@ComponentScan("zti.web")
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class AppConfig {

    PersonDaoImpl personDao;

    @Bean
    public AspectLogger aspectLogger() {
        return new AspectLogger();
    }

}