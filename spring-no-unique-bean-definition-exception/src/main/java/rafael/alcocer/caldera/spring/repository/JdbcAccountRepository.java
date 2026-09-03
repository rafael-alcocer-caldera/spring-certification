package rafael.alcocer.caldera.spring.repository;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class JdbcAccountRepository implements AccountRepository {

    private final DataSource dataSource;

    public JdbcAccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
