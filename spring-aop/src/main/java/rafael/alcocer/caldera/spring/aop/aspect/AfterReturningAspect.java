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
