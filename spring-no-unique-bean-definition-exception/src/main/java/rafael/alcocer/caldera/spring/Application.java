package rafael.alcocer.caldera.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rafael.alcocer.caldera.spring.config.AppConfig;
import rafael.alcocer.caldera.spring.service.TransferService;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TransferService ts = context.getBean(TransferService.class);
    }
}
