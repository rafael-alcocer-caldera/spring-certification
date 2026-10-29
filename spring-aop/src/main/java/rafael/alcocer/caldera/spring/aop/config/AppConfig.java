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
package rafael.alcocer.caldera.spring.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import rafael.alcocer.caldera.spring.aop.business.logic.Cache;
import rafael.alcocer.caldera.spring.aop.business.logic.CacheImpl;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages="rafael.alcocer.caldera.spring.aop")
public class AppConfig {

    @Bean
    public Cache cache() {
        return new CacheImpl();
    }
}
