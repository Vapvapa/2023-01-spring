package ru.spring.varvara;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.spring.varvara.service.TestingService;

/**
 * Приложение проведения тестирования пользователя
 */
public class TestingApp {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml")) {
            TestingService service = context.getBean(TestingService.class);
            service.printQuestions();
        }
    }
}