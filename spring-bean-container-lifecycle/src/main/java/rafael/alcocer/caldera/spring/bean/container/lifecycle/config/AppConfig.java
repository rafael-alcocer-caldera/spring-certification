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
package rafael.alcocer.caldera.spring.bean.container.lifecycle.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rafael.alcocer.caldera.spring.bean.container.lifecycle.factory.MyBeanFactoryPostProcessor;
import rafael.alcocer.caldera.spring.bean.container.lifecycle.model.Message;
import rafael.alcocer.caldera.spring.bean.container.lifecycle.post.MyBeanPostProcessor;

@Configuration
public class AppConfig {

    @Bean(initMethod = "init")
    public Message message() {
        return new Message();
    }

    // It needs to run before any beans are created
    // - Use of static @Bean method is needed
    @Bean
    public static BeanFactoryPostProcessor myBeanFactoryPostProcessor() { // <-- IMPORTANT: Must be static!
        return new MyBeanFactoryPostProcessor();
    }

    @Bean
    public BeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
