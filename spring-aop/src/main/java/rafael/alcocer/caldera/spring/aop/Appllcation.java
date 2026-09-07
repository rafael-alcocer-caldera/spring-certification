package rafael.alcocer.caldera.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import rafael.alcocer.caldera.spring.aop.business.logic.Cache;
import rafael.alcocer.caldera.spring.aop.business.logic.Message;
import rafael.alcocer.caldera.spring.aop.config.AppConfig;

public class Appllcation {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Cache cache = context.getBean(Cache.class);

        System.out.println("Executing setCacheSize...");
        System.out.println("-------------------------");
        cache.setCacheSize(100);

        System.out.println("");

        System.out.println("Executing setCacheTime...");
        System.out.println("-------------------------");
        cache.setCacheTime(1L);

        System.out.println("");

        System.out.println("Executing setMessage...");
        System.out.println("-------------------------");
        Message message = new Message();
        message.setMessage("NEW MESSAGE...");
        cache.setMessage(message);

        System.out.println("");

        System.out.println("Executing getMessage...");
        System.out.println("-------------------------");
        cache.getMessage();

        System.out.println("");

        System.out.println("Executing throwRuntimeException...");
        System.out.println("-------------------------");
        try {
            cache.throwRuntimeException();
        } catch (RuntimeException ex) {
            System.out.println("Exception caught in Main: " + ex.getMessage());
        }
    }
}
