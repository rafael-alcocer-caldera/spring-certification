package rafael.alcocer.caldera.spring.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterAspect {
    
    @After("execution(void set*(*))")
    public void afterAspect() {
        System.out.println("afterAspect()...");
    }
}
