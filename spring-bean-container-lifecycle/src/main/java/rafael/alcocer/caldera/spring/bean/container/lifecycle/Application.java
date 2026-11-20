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
package rafael.alcocer.caldera.spring.bean.container.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rafael.alcocer.caldera.spring.bean.container.lifecycle.config.AppConfig;
import rafael.alcocer.caldera.spring.bean.container.lifecycle.model.Message;

public class Application {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            System.out.println("Step A: Load Bean Definitions");
            System.out.println("-----------------------------");
            System.out.println("Step B: Add/Store Bean Definitions to BeanFactory");
            System.out.println("-------------------------------------------------");
            context.register(AppConfig.class); // Spring reads AppConfig and add/store the Bean Definitions

            context.refresh(); // Here triggers Step C (BeanFactoryPostProcessor), Step D (DI) and Step E (BeanPostProcessor)

            Message message = context.getBean(Message.class);
            System.out.println("[Modified Message by BeanFactoryPostProcessor] " + message.getMessage());
        }
    }
}
