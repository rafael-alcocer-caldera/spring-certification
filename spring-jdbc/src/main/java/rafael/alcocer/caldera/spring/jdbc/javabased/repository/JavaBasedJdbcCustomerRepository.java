/**
 * Copyright [2026] [RAFAEL ALCOCER CALDERA]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rafael.alcocer.caldera.spring.jdbc.javabased.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import rafael.alcocer.caldera.spring.jdbc.model.Customer;
import rafael.alcocer.caldera.spring.jdbc.repository.CustomerRepository;

public class JavaBasedJdbcCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JavaBasedJdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * It uses:
     * 
     * <T> T queryForObject(String sql, Class<T> requiredType, Object... args)
     */
    @Override
    public int getCustomerCount() {
        String sql = "SELECT COUNT(*) FROM customer";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    /**
     * It uses:
     * 
     * <T> T queryForObject(String sql, Class<T> requiredType, Object... args)
     */
    @Override
    public int getCustomerCountBetween(int initialId, int finalId) {
        String sql = "SELECT COUNT(*) FROM customer WHERE id BETWEEN ? AND ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, initialId, finalId);
        return count;
    }

    /**
     * It uses:
     * 
     * int update(String sql, Object... args)
     */
    @Override
    public int insertCustomer(int id, String name, String email) {
        String sql = "INSERT INTO customer (id, name, email) VALUES (?, ?, ?)";
        int inserted = jdbcTemplate.update(sql, id, name, email);
        return inserted;
    }

    /**
     * It uses:
     * 
     * int update(String sql, Object... args)
     */
    @Override
    public int updateCustomer(String email, int id) {
        String sql = "UPDATE customer SET email = ? WHERE id = ?";
        int updated = jdbcTemplate.update(sql, email, id);
        return updated;
    }

    /**
     * It uses:
     * 
     * Map<String, Object> queryForMap(String sql, Object... args)
     */
    @Override
    public Map<String, Object> getCustomerMap(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
        return map;
    }

    /**
     * It uses:
     * 
     * <T> List<T> queryForList(String sql, Class<T> elementType, Object... args)
     */
    @Override
    public List<Map<String, Object>> getCustomerList() {
        String sql = "SELECT * FROM customer";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    /**
     * It uses:
     * 
     * <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
     */
    @Override
    public Customer getCustomerRowMapper(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";

        RowMapper<Customer> rowMapper = new RowMapper<>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                return customer;
            }
        };

        Customer customer = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return customer;
    }
}
