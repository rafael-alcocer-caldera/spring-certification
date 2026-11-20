# 🛠 🚀 Spring Certification 🚀 🛠 
Exercises for Spring Certified Professional

## ✅ Spring Bean Container Lifecycle ✅
Spring Bean container runs through 3 distinct phases:

#### 1. Initialization
   - Spring Beans are created
   - We create the ApplicationContext, that's where we create all the components
   - Dependency injection occurs
   - When a context is created, the initialization phase completes
   
   ```java
       // Create application context from the configuration...
       
       // Using Spring Boot: 
       ApplicationContext context = SpringApplication.run(AppConfig.class);
       
       // Using raw Spring:
       AnnotationConfigApplicationContext context = 
               new AnnotationConfigApplicationContext(AppConfig.class);
       
   ```

#### 2. Usage
   - Beans are available for use in the application
   - We retrieve beans from ApplicationContext and use them

#### 3. Destruction
   - Beans are released for Garbage Collection
   - Happens when we close the ApplicationContext
   
### ✅ Initialization ✅
I broke it down into 5 steps instead of the 2 provided in the course:

- ✅ Step A: Load Bean Definitions
- ✅ Step B: Add/Store Bean Definitions to BeanFactory
- ✅ Step C: Post Process Bean Definitions (here interface BeanFactoryPostProcessor takes place, Post Process those Bean Definitions, the template of the Bean)
- ✅ Step D: Dependency Injection occurs
- ✅ Step E: Post Process Bean (here interface BeanPostProcessor takes place, Post Process the instance, the object)


My Analogy like Class vs Object
-------------------------------
- Class --------------> is like a blueprint to create Objects
- Bean Definitions ---> is like a blueprint to create Beans

- Object ---> it's the instance of the Class
- Bean -----> it's the instance of the Bean Definition


### ✅ Step A: Load Bean Definitions ✅
- We have to load all Bean Definitions from:
  - Java Configuration
  - Annotation-Based Configuration
  - XML
  - etc


- We get:
  - the name
  - the type
  - the dependencies
  - whether it is singleton or not


We only load the information.
We have to gather all the information about the components.
   
- @Configuration classes are processed
- @Component classes are scanned for


### ✅ Step B: Bean definitions added to a BeanFactory ✅
- All this information gotten from Step A, will be then stored into the BeanFactory
  - The ApplicationContext is a BeanFactory
    - It extends from the BeanFactory
- That's where we're going to store all these Bean Definitions
- Each indexed under its id and type


### ✅ Step C: Post Process Bean Definitions ✅
- After we know all the information (all the Definitions in memory inside the BeanFactory) we'll be able and ready to Post Process those Bean Definitions
- Notice that we haven't created any instance yet, we just have Definitions
- We're going to keep them in memory
- We'll be executing some special components called the BeanFactoryPostProcessor, which is where we have the Bean Definitions
  - This is an extension point that we have in the container
  - Is an internal bean invoked by Spring (not your code)
- We can apply transformations to the components


BeanFactoryPostProcessor:

```java
    public interface BeanFactoryPostProcessor {
        void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
    }
```


> **If BeanFactoryPostProcessor is implemented**
>
> **It has to be an Spring Managed Bean**


### ✅ Implementation using Java-Based Configuration ✅
```java
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("Step C: Post Process Bean Definitions");
        System.out.println("-------------------------------------");

        // We obtain the BeanDefinition (metadata) before the bean is instantiated
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("message");

        // We modify the property value in the BeanDefinition
        beanDefinition.getPropertyValues().add("message", "Hello my friend!");
    }
}
```


```java
@Configuration
public class AppConfig {

    // ...

    // It needs to run before any beans are created
    // - Use of static @Bean method is needed
    @Bean
    public static BeanFactoryPostProcessor myBeanFactoryPostProcessor() { // <-- IMPORTANT: Must be static!
        return new MyBeanFactoryPostProcessor();
    }

   // ...
}
```

### ✅ Implementation using Annotation-Based Configuration ✅
```java
@Component
public class MyTransformer implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        // Logic to modify Bean Definitions here...
    }
}
```

### ⚠️ Summary ⚠️
> ✅ If BFPP is declared with @Bean inside @Configuration class ➡️ The method MUST be static
>
> ✅ If BFPP is declared with @Component ➡️ The class MUST NOT be static


```text
┌─────────────────────────────────────┐
│       ApplicationContext is a       │
│              BeanFactory            │
│                                     │
│   ┌──────────────────────────────┐  │
│   │  appConfig                   │  │
│   │  message                     │  │
│   │  myBeanFactoryPostProcessor  │  │
│   │  myBeanPostProcessor         │  │
│   └──────────────────────────────┘  │
│                                     │
└──────────────────┬──────────────────┘
                   |
        postProcess|BeanFactory()
                   v
   ┌───────────────────────────────┐
   │    BeanFactoryPostProcessors  │
   └───────────────────────────────┘
     Can modify the definition of
     any bean in the factory
     before any objects are created
```

- If you have more than one configuration file, all have to be parsed

- All the Bean Definitions will be loaded into the same BeanFactory

- Even if we have the configuration split into multiple configuration files, we'll be creating one and only one ApplicationContext

- When we have everything in the ApplicationContext we are ready to call the BeanFactoryPostProcessors

- This is an extension point in the container


### ✅ Step D: Dependency Injection occurs ✅
For each bean...

Find/Create its dependencies.

After we know what needs to be created, we're going to try to find the dependencies of every single component because we'll try to create the Beans in the right order based on the dependencies.

So, for every single Bean we're going to initialize it, maybe doing constructor injection as well. 
And then we're going to do field and setter injection.

The first thing will be determining the dependencies of our components, Spring will try to create the beans in the right order based on those dependencies.

And it will do it eagerly because we want to fail fast.

If there's any misconfiguration in our Application Context we want to know at this particular point.

But remember you can also mark your beans to be lazy and this will postpone the initialization to the first time you need that particular bean after we have everything created we'll go to a post processing phase.

```text
        for-each bean
      +-----------------+
      | Find/create its |
      |   Dependencies  |
      +-----------------+
               | 
               v
      +-----------------+
      |   Instantiate   |
      |      Beans      |<-----+
      +-----------------+      |
               |               | Dependency
               v               |  Injection
      +-----------------+      |
      |     Perform     |<-----+
      | Setter Injection|
      +-----------------+
               |
               v 
```

### ✅ Step E: Post Processing Phase ✅
When we have the component created (refers to any Bean whether it is a dependency or a main Bean that has just finished being instantiated and populated), we're going to go through a Post Processing Phase.

And this time that Post Processing Phase will be at the Bean level not at the Definition but at the instance level.

And we can break it down into the Bean Post Processors and the Initializer.

This post processing phase is at the Bean level. 
That means that the post processors will have access to every single instance in our Application Context.
This will be done by the Bean Post Processors.

When we're done with the post processing phase, everything will be fully configured and ready to use.

```java
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);
    
    // Return type Object => Post-processed bean (your Bean or a Proxy)
    // The parameter bean, this is the Original Bean
    Object postProcessAfterInitialization(Object bean, String beanName);
}
```

### ✅ Implementation using Java-Based Configuration ✅
```java
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // Some code
        return bean; // Remember to return your bean or you'll lose it!
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // Some code
        return bean; // Remember to return your bean or you'll lose it!
    }
}
```

```java
@Configuration
public class AppConfig {

    // ...

    @Bean
    public BeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
```

### ✅ Implementation using Annotation-Based Configuration ✅
```java
// It has to be a Spring Managed Bean
// Can be found by component-scanner, like any other bean
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // Some code
        return bean; // Remember to return your bean or you'll lose it!
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // Some code
        return bean; // Remember to return your bean or you'll lose it!
    }
}
```

**We have to make this a Spring Managed Bean.**

**You can do it explicitly by using Java Configuration or implicitly by using component scanning like in the examples above.**

```text
               |
               v 
      +-----------------+
      |     Before      |
      |      Init       |<-----+
      +-----------------+      |
               |               |
               v               |
      +-----------------+      |
      |   Initializers  |      |
      |      Called     |<-----+--- Bean post-processing steps
      +-----------------+      |
               |               |
               v               |
      +-----------------+      |
      |      After      |      |
      |      Init       |<-----+
      +-----------------+
               | 
               v
```

### ✅ Before Init ✅
Here it executes:

```java
Object postProcessBeforeInitialization(Object bean, String beanName);
```

### ✅ The Initializer Extension Point ✅
- Special case of a bean post-processing
  - Causes initialization methods to be called
    - Such as @PostContruct, InitializingBean.afterPropertiesSet(), @Bean(initMethod=...)
    
- Internally Spring uses several initializers BPPs (Bean Post Processors)
  - Example: CommonAnnotationBeanPostProcessor enables @PostConstruct, @Resource...
  
### The exact order of execution of the initialization methods is
1. @PostConstruct (Standard Java Annotation JSR-250)

2. InitializingBean.afterPropertiesSet() (Native Spring interface)

3. initMethod (The custom attribute that you declare in the @Bean(initMethod = "customInit"))


### ✅ After Init ✅
Here it executes:

```java
Object postProcessAfterInitialization(Object bean, String beanName);
```

- The "InfrastructureAdvisorAutoProxyCreator" (an internal Spring "BeanPostProcessor") intercepts the actual object (the Target)

- If it detects that a method carries aspect-related annotations—such as "@Transactional" or "@Async"—it does not return the raw object; instead, it creates a CGLIB/JDK Proxy, encapsulates your object within it, and returns the Proxy

- If the "BeanPostProcessor" finds even a single aspect-related annotation (like "@Transactional") anywhere within the class—whether at the class level or on one of its methods—the outcome is definitive: 
  ***** The entire bean instance is wrapped within a Proxy *****


******************************************************************
➡️ BeanPostProcessor returns the Bean (raw object) or creates the Proxy

➡️ BeanPostProcessor creates the Proxy during Initialization phase
   (After Init ➡ postProcessAfterInitialization())
******************************************************************

### Sequential Order Summary
Load Definitions ➡️ Add/Store Bean Definitions to BeanFactory ➡️ BFPP ➡️ DI (Constructor Injection, Field Injection, Setter/Method Injection) ➡️ BPP BeforeInit ➡️ BPP Initializers (@PostConstruct, InitializingBean.afterPropertiesSet(), @Bean(initMethod = "customInit")) ➡️ BPP AfterInit.

### LOGS
```log
Step A: Load Bean Definitions
-----------------------------
Step B: Add/Store Bean Definitions to BeanFactory
-------------------------------------------------
Step C: Post Process Bean Definitions
-------------------------------------
nov. 19, 2026 4:01:21 P. M. org.springframework.context.support.PostProcessorRegistrationDelegate$BeanPostProcessorChecker postProcessAfterInitialization
INFORMACIÓN: Bean 'appConfig' of type [rafael.alcocer.caldera.spring.bean.container.lifecycle.config.AppConfig$$EnhancerBySpringCGLIB$$bc2251ef] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
Step D: Bean Instantiation & Dependency Injection
-------------------------------------------------
  -> 1. Constructor called (Bean Instantiated)
  -> 2. Property/Dependency Injection (setMessage called)
     [Original Message] Hello world!
Step E: Post Process Bean
-------------------------
[BeforeInit] Executing...
  -> Object postProcessBeforeInitialization(Object bean, String beanName)
[Initializers called]
  -> [@PostConstruct]
  -> [InitializingBean.afterPropertiesSet()]
  -> [@Bean(initMethod = "init")]
[AfterInit] Executing...
  -> Object postProcessAfterInitialization(Object bean, String beanName)
[Modified Message by BeanFactoryPostProcessor] Hello my friend!

```

### ✅ Console Output ✅
You can see this in the console:

```text
Bean 'appConfig' of type [rafael.alcocer.caldera.spring.bean.container.lifecycle.config.AppConfig$$EnhancerBySpringCGLIB$$bc2251ef]
```

### ✅ Console Output Explanation ✅
When using Java-Based Configuration you have two fashions:
1. Methods as parameters (the name of the method is the name of the bean)
2. References as parameters

#### Example 1: Methods as parameters
```java
@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        // configuration...
        return dataSource;
    }
    
    @Bean
    public AccountRepository accountRepository() {
        return new AccountRepositoryImpl(dataSource());
    }
    
    @Bean
    public TransferService transferService() {
        return new TransferServiceImpl(accountRepository());
    }
}
```

#### Example 2: References as parameters
```java
@Configuration
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        // configuration...
        return dataSource;
    }
   
    @Bean
    public AccountRepository accountRepository(DataSource dataSource) {
        return new AccountRepositoryImpl(dataSource);
    }
    
    @Bean
    public TransferService transferService(AccountRepository accountRepository) {
        return new TransferServiceImpl(accountRepository);
    }
}
```

---

### ✅ Kinds of Proxies in Spring ✅
Spring supports two proxy mechanisms at runtime: **JDK Proxies** and **CGLIB Proxies**.

#### 1. JDK Proxy (Interface-Based)
- Also called dynamic proxies
- API is built into the JDK (`java.lang.reflect.Proxy`)
- **Requirement:** The Target class must implement at least one Java interface
- All interfaces implemented by the Target are proxied

```text
                         ┌─────────────────┐
                         │ TransferService │
                         └────▲───▲────────┘
                             /    │
                 implements /     │ implements
                           /      │
   ┌──────────────────────┴───────│─────────────┐
   │        Spring Proxy          │             │                 
   │                              │    Target   │
──►│ ┌───────────┐       ┌────────┴──────────┐  │     
   │ │ Extra     ├──────►│TransferServiceImpl│  │        
   │ │ Logic     │◄──────│                   │  │
◄──│ └───────────┘       └───────────────────┘  │                    
   └────────────────────────────────────────────┘
```

#### 2. CGLIB Proxy (Subclass-Based)
- Not built into the JDK (included inside `spring-core.jar`)
- Used automatically when the target class does **not** implement an interface (such as `@Configuration` classes)
- **Limitation:** Cannot be applied to `final` classes or `final` methods because the proxy works by extending the target class at runtime

```text
                      ┌─────────────────────┐
                      │ TransferServiceImpl │
                      └───────────▲─────────┘
                                  │
                                  │ extends
                                  │
   ┌──────────────────────────────┴─────────────┐
   │        Spring Proxy                        │                 
   │                                   Target   │
──►│ ┌───────────┐       ┌───────────────────┐  │     
   │ │ Extra     ├──────►│TransferServiceImpl│  │        
   │ │ Logic     │◄──────│                   │  │
◄──│ └───────────┘       └───────────────────┘  │                    
   └────────────────────────────────────────────┘
```

#### Comparison: JDK Dynamic Proxy vs. CGLIB Proxy

| Feature | JDK Dynamic Proxy (Interface-Based) | CGLIB Proxy (Subclass-Based) |
| :--- | :--- | :--- |
| **Relationship with Target** | Both the Proxy and Target implement the same interface. | The Proxy extends (is a subclass of) the Target. |
| **Class Hierarchy** | `Proxy implements TransferService`<br>`Target implements TransferService` | `Proxy extends TransferServiceImpl` |
| **Main Limitation** | Target must implement at least one interface. | Target class and public/protected methods cannot be `final`. |

---

### ✅ Full Mode (`proxyBeanMethods = true`) vs. Lite Mode (`proxyBeanMethods = false`) ✅

By default, Spring creates a **CGLIB proxy subclass** of `@Configuration` classes at runtime (`@Configuration(proxyBeanMethods = true)`):

* **`proxyBeanMethods = true` (Default / Full Mode):**
  * If one `@Bean` method calls another `@Bean` method directly within the same class, the CGLIB proxy intercepts the call and returns the **same singleton instance** stored in the `ApplicationContext`
  * *This is why we see `AppConfig$$EnhancerBySpringCGLIB` in the console log.*

* **`proxyBeanMethods = false` (Lite Mode):**
  * Does **not** generate CGLIB proxies
  * `@Bean` methods behave like standard Java methods
  * Startup is faster, consumes less memory, and is ideal for native compilation (**GraalVM / Spring AOT**)

#### When should you use `proxyBeanMethods = false`?
* When `@Bean` methods are independent and do not call each other directly
* When dependencies between beans are injected via **method parameters**

> **Note:** Declaring `@Bean` methods inside a `@Component` class has the exact same technical effect as `@Configuration(proxyBeanMethods = false)` (Lite Mode), though using `@Configuration` makes the architectural intent clear.

---

### ✅ Common Mistakes ✅

#### ❌ INCORRECT — Methods as parameters invocation with `proxyBeanMethods = false`
This will compile and run without errors, **but it breaks the Singleton pattern** by creating multiple instances (e.g., two separate `DataSource` connection pools, one completely outside Spring's lifecycle management):

```java
@Configuration(proxyBeanMethods = false)
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        // configuration...
        return dataSource;
    }
    
    @Bean
    public AccountRepository accountRepository() {
        // ❌ ERROR: Invokes standard Java method and creates a second DataSource
        return new AccountRepositoryImpl(dataSource());
    }
    
    @Bean
    public TransferService transferService() {
        // ❌ ERROR: Creates a second AccountRepository
        return new TransferServiceImpl(accountRepository());
    }
}
```

#### ✅ CORRECT — References as parameters with `proxyBeanMethods = false`
When injecting bean references as parameters with `proxyBeanMethods = false`, Spring resolves and injects the single managed bean instance from the container:

```java
@Configuration(proxyBeanMethods = false)
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        // configuration...
        return dataSource;
    }
   
    @Bean
    public AccountRepository accountRepository(DataSource dataSource) {
        // ✅ CORRECT: Spring injects the managed DataSource singleton
        return new AccountRepositoryImpl(dataSource);
    }
    
    @Bean
    public TransferService transferService(AccountRepository accountRepository) {
        // ✅ CORRECT: Spring injects the managed AccountRepository singleton
        return new TransferServiceImpl(accountRepository);
    }
}
```

