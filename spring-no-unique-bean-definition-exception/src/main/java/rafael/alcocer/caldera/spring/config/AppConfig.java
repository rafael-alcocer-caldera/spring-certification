package rafael.alcocer.caldera.spring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages="rafael.alcocer.caldera.spring")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource();
    }
}
