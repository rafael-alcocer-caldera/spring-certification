# 🛠 🚀 Spring Certification 🚀 🛠 
Exercises for Spring Certified Professional

## ✅ Spring NoUniqueBeanDefinitionException ✅
In the Spring Framework Essentials Course at Broadcom Spring Academy

[Spring Academy](https://spring.academy/)

We have the following example:

![01](./images/01.png)


Which one should get injected?

At startup:

```log
NoSuchBeanDefinitionException,
no unique bean of type [AccountRepository] is defined:
expected single bean but found 2...
```

But the above error is wrong, what we really get is:

```log
Exception in thread "main" org.springframework.beans.factory.UnsatisfiedDependencyException: 
Error creating bean with name 'transferServiceImpl' defined in file 
[/Users/rac/RAC/GitHub/spring-certification/spring-no-unique-bean-definition-exception/target/classes/rafael/alcocer/caldera/spring/service/TransferServiceImpl.class]: 
Unsatisfied dependency expressed through constructor parameter 0; 
nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: 
No qualifying bean of type 'rafael.alcocer.caldera.spring.repository.AccountRepository' available: expected single matching bean but found 2: 
jdbcAccountRepository,jpaAccountRepository 
```

SOLUTION:

![02](./images/02.png)

```java
@Component
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;
    
    // SOLUTION
    public TransferServiceImpl(@Qualifier("jdbcAccountRepository") AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
```

### NOTE
Adding the names to the @Component is optional because by default Spring takes the name of the class with first letter lower-cased.

This one:

```java
@Component
public class JpaAccountRepository implements AccountRepository {...}
```

Is equivalent to this one:

```java
@Component("jpaAccountRepository")
public class JpaAccountRepository implements AccountRepository {...}
```



