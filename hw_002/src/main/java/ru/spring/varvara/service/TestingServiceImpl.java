package ru.spring.varvara.service;

import lombok.RequiredArgsConstructor;
import ru.spring.varvara.dao.QuestionDao;
import ru.spring.varvara.domain.Question;

import java.util.List;

/**
 * Сервис проведения тестирования
 */
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    /** DAO по работе с вопросами тестирования */
    private final QuestionDao questionDao;
    /**
     * Сервис проверки вопросов на соответствие необходимым требованиям
     */
    private final QuestionValidator questionValidator;

    @Override
    public void printQuestions() {
        List<Question> questions = questionDao.findAll();
        questionValidator.validate(questions);
        questions.forEach(question -> {
            System.out.println(question.getQuestion());
            question.getAnswers().forEach(System.out::println);
            System.out.println();
        });
    }
}
