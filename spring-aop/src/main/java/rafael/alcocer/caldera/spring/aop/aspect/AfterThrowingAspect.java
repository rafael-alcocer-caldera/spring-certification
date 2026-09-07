package rafael.alcocer.caldera.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterThrowingAspect {

    @AfterThrowing(value = "execution(void rafael.alcocer.caldera.spring.aop.business.logic.Cache.throwRuntimeException())", throwing = "e")
    public void afterThrowingAspect(JoinPoint joinPoint, RuntimeException e) {
        String methodName = joinPoint.getSignature().getName();
        Object beanName = joinPoint.getTarget();

        System.out.println("afterThrowingAspect()...");
        System.out.println("afterThrowingAspect()... methodName: " + methodName);
        System.out.println("afterThrowingAspect()... beanName: " + beanName);
        System.out.println("afterThrowingAspect()... exception: " + e.getMessage());
    }
}
