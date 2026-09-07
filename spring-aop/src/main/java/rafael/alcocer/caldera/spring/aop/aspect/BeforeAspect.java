package rafael.alcocer.caldera.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAspect {

    @Before("execution(void set*(*))")
    public void beforeAspect() {
        System.out.println("beforeAspect()...");
    }
    
    @Before("execution(void set*(*))")
    public void beforeAspectWithContext(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object beanName = joinPoint.getTarget();
        Object newValue = joinPoint.getArgs()[0];
        
        System.out.println("beforeAspectWithContext()...");
        System.out.println("beforeAspectWithContext()... methodName: " + methodName);
        System.out.println("beforeAspectWithContext()... beanName: " + beanName);
        System.out.println("beforeAspectWithContext()... newValue: " + newValue);
    }
}
