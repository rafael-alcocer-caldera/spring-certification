package rafael.alcocer.caldera.spring.repository;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class JpaAccountRepository implements AccountRepository {

    private final DataSource dataSource;

    public JpaAccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
