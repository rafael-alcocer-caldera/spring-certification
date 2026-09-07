package rafael.alcocer.caldera.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import rafael.alcocer.caldera.spring.aop.business.logic.Message;

@Aspect
@Component
public class AfterReturningAspect {

    @AfterReturning(value = "execution(rafael.alcocer.caldera.spring.aop.business.logic.Message rafael.alcocer.caldera.spring.aop.business.logic.Cache.getMessage())", returning = "message")
    public void afterReturningAspect(JoinPoint joinPoint, Message message) {
        String methodName = joinPoint.getSignature().getName();
        Object beanName = joinPoint.getTarget();

        System.out.println("afterReturningAspect()...");
        System.out.println("afterReturningAspect()... methodName: " + methodName);
        System.out.println("afterReturningAspect()... beanName: " + beanName);
        System.out.println("afterReturningAspect()... message: " + message.getMessage());
    }
}
