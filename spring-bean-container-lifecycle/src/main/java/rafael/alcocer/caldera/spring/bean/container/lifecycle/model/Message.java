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
package rafael.alcocer.caldera.spring.bean.container.lifecycle.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;

public class Message implements InitializingBean {

    private String message = "Hello world!";
    
    public Message() {
        System.out.println("Step D: Bean Instantiation & Dependency Injection");
        System.out.println("-------------------------------------------------");
        System.out.println("  -> 1. Constructor called (Bean Instantiated)");
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        System.out.println("  -> 2. Property/Dependency Injection (setMessage called)");
        System.out.println("     [Original Message] " + this.message);
        this.message = message;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("  -> [@PostConstruct]");
    }

    public void init() {
        System.out.println("  -> [@Bean(initMethod = \"init\")]");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("  -> [InitializingBean.afterPropertiesSet()]");

    }
}
