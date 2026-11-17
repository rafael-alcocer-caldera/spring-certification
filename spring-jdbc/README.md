# 🛠 🚀 Spring Certification 🚀 🛠 
Exercises for Spring Certified Professional

## ✅ Spring JDBC ✅
Here is an example of applying JDBC.

### EXAMPLE 1

```java
// Count all rows from customer table
String sql = "SELECT COUNT(*) FROM customer";
int count = jdbcTemplate.queryForObject(sql, Integer.class);
// <T> T queryForObject(String sql, Class<T> requiredType, Object... args)
```

### EXAMPLE 2

```java
// Count all rows from customer where id between a range
String sql = "SELECT COUNT(*) FROM customer WHERE id BETWEEN ? AND ?";
int count = jdbcTemplate.queryForObject(sql, Integer.class, 2, 5);
// <T> T queryForObject(String sql, Class<T> requiredType, Object... args)
```

### EXAMPLE 3

```java
// Insert a customer
String sql = "INSERT INTO customer (id, name, email) VALUES (?, ?, ?)";
int inserted = jdbcTemplate.update(sql, 7, "Newman", "new@example.com");
// int update(String sql, Object... args)
```

### EXAMPLE 4

```java
// Update email of a customer
String sql = "UPDATE customer SET email = ? WHERE id = ?";
int updated = jdbcTemplate.update(sql, "x@example.com", 1);
// int update(String sql, Object... args)
```

### EXAMPLE 5

```java
// Return a Map
String sql = "SELECT * FROM customer WHERE id = ?";
Map<String, Object> map = jdbcTemplate.queryForMap(sql, 7);
// Map<String, Object> queryForMap(String sql, Object... args)
// Returns A Map of [Column Name | Field Value] pairs
Map: {ID=7, NAME=Newman, EMAIL=newman@example.com}
```

### EXAMPLE 6

```java
// Return a List of Maps
String sql = "SELECT * FROM customer";
List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
// <T> List<T> queryForList(String sql, Class<T> elementType, Object... args)
// Returns a List of Maps of [Column Name | Field Value] pairs
[{ID=1, NAME=Rafael, EMAIL=rafael@example.com}, 
 {ID=2, NAME=Alcocer, EMAIL=alcocer@example.com}, 
 {ID=3, NAME=Caldera, EMAIL=caldera@example.com}, 
 {ID=4, NAME=Mary, EMAIL=mary@example.com}, 
 {ID=5, NAME=Lisa, EMAIL=lisa@example.com}, 
 {ID=6, NAME=Nora, EMAIL=nora@example.com}, 
 {ID=7, NAME=Newman, EMAIL=newman@example.com}
]
```

### EXAMPLE 7

```java
<T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
------------------------------------------------------------------------
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

Customer customer = jdbcTemplate.queryForObject(sql, rowMapper, 1);
```

### LOGS

```log
nov. 17, 2026 12:45:48 P. M. org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory initDatabase
INFORMACIÓN: Starting embedded database: url='jdbc:hsqldb:mem:testdb', username='sa'
**************************************
****** Java-Based Configuration ******
**************************************
Count all rows from customer table: 6
-----------------------------------------------
Count all rows from customer where id between 2 and 5: 4
-----------------------------------------------
Insert a customer: 1
-----------------------------------------------
Update email of a customer: 1
-----------------------------------------------
Return a Map: {ID=7, NAME=Newman, EMAIL=newman@example.com}
-----------------------------------------------
Return a List of Maps: [{ID=1, NAME=Rafael, EMAIL=rafael@example.com}, {ID=2, NAME=Alcocer, EMAIL=alcocer@example.com}, {ID=3, NAME=Caldera, EMAIL=caldera@example.com}, {ID=4, NAME=Mary, EMAIL=mary@example.com}, {ID=5, NAME=Lisa, EMAIL=lisa@example.com}, {ID=6, NAME=Nora, EMAIL=nora@example.com}, {ID=7, NAME=Newman, EMAIL=newman@example.com}]
-----------------------------------------------
Return Customer using RowMapper: Customer [id=1, name=Rafael, email=rafael@example.com]
-----------------------------------------------
nov. 17, 2026 12:45:48 P. M. org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory shutdownDatabase
INFORMACIÓN: Shutting down embedded database: url='jdbc:hsqldb:mem:testdb'
nov. 17, 2026 12:45:48 P. M. org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory initDatabase
INFORMACIÓN: Starting embedded database: url='jdbc:hsqldb:mem:testdb', username='sa'
********************************************
****** Annotation-Based Configuration ******
********************************************
Count all rows from customer table: 6
-----------------------------------------------
Count all rows from customer where id between 2 and 5: 4
-----------------------------------------------
Insert a customer: 1
-----------------------------------------------
Update email of a customer: 1
-----------------------------------------------
Return a Map: {ID=7, NAME=Newman, EMAIL=newman@example.com}
-----------------------------------------------
Return a List of Maps: [{ID=1, NAME=Rafael, EMAIL=rafael@example.com}, {ID=2, NAME=Alcocer, EMAIL=alcocer@example.com}, {ID=3, NAME=Caldera, EMAIL=caldera@example.com}, {ID=4, NAME=Mary, EMAIL=mary@example.com}, {ID=5, NAME=Lisa, EMAIL=lisa@example.com}, {ID=6, NAME=Nora, EMAIL=nora@example.com}, {ID=7, NAME=Newman, EMAIL=newman@example.com}]
-----------------------------------------------
Return Customer using RowMapper: Customer [id=1, name=Rafael, email=rafael@example.com]
-----------------------------------------------
nov. 17, 2026 12:45:48 P. M. org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory shutdownDatabase
INFORMACIÓN: Shutting down embedded database: url='jdbc:hsqldb:mem:testdb'
```
