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
