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
package rafael.alcocer.caldera.spring.jdbc.repository;

import java.util.List;
import java.util.Map;

import rafael.alcocer.caldera.spring.jdbc.model.Customer;

public interface CustomerRepository {

    /**
     * Count all rows from customer table.
     * 
     * @return total rows
     */
    int getCustomerCount();

    /**
     * Count all rows from customer where id between a range
     * 
     * @param initialId
     * @param finalId
     * @return total rows between initialId and finalId
     */
    int getCustomerCountBetween(int initialId, int finalId);

    /**
     * Insert a customer.
     * 
     * @param id    customer id
     * @param name  customer name
     * @param email customer email
     * @return number of rows inserted
     */
    int insertCustomer(int id, String name, String email);

    /**
     * Update a customer.
     * 
     * @param email customer email
     * @param id    customer id
     * @return number of rows updated
     */
    int updateCustomer(String email, int id);

    /**
     * Return a Map.
     * 
     * @param id customer id
     * @return a map
     */
    Map<String, Object> getCustomerMap(int id);

    /**
     * Return a List of Maps.
     * 
     * @return list of maps
     */
    List<Map<String, Object>> getCustomerList();
    
    /**
     * Return a Customer RowMapper
     * 
     * @param id customer id
     * @return customer
     */
    Customer getCustomerRowMapper(int id);
}
