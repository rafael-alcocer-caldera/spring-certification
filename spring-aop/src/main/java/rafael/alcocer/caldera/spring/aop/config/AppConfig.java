package rafael.alcocer.caldera.spring.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import rafael.alcocer.caldera.spring.aop.business.logic.Cache;
import rafael.alcocer.caldera.spring.aop.business.logic.CacheImpl;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages="rafael.alcocer.caldera.spring.aop")
public class AppConfig {

    @Bean
    public Cache cache() {
        return new CacheImpl();
    }
}
