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
package rafael.alcocer.caldera.spring.bean.container.lifecycle.post;

import org.springframework.beans.factory.config.BeanPostProcessor;

import rafael.alcocer.caldera.spring.bean.container.lifecycle.model.Message;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof Message) {
            System.out.println("Step E: Post Process Bean");
            System.out.println("-------------------------");
            System.out.println("[BeforeInit] Executing...");
            System.out.println("  -> Object postProcessBeforeInitialization(Object bean, String beanName)");
            System.out.println("[Initializers called]");
        }

        return bean; // Remember to return your bean or you'll lose it!
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof Message) {
            System.out.println("[AfterInit] Executing...");
            System.out.println("  -> Object postProcessAfterInitialization(Object bean, String beanName)");
        }

        return bean; // Remember to return your bean or you'll lose it!
    }
}
