package ru.spring.varvara.service;

import lombok.RequiredArgsConstructor;
import ru.spring.varvara.dao.QuestionDao;
import ru.spring.varvara.domain.Question;

import java.util.List;

import static java.text.MessageFormat.format;

/**
 * Сервис проведения тестирования
 */
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    /** DAO по работе с вопросами тестирования */
    private final QuestionDao questionDao;
    @Override
    public void printQuestions() {
        List<Question> questions = questionDao.findAll();
        validateQuestions(questions);
        questions.forEach(question -> {
            System.out.println(question.getQuestion());
            question.getAnswers().forEach(System.out::println);
            System.out.println();
        });
    }

    /**
     * Проверяет валидность вопросов
     * @param questions список вопросов
     * @throws IllegalStateException если на вопрос меньше двух возможных ответов или невозможно определить правильный ответ
     */
    private void validateQuestions(List<Question> questions) {
        questions.forEach(question -> {
            if (question.getAnswers() == null || question.getAnswers().size() < 2) {
                throw new IllegalStateException(format("У вопроса \"{0}\" должно быть несколько вариантов ответа", question.getQuestion()));
            }
            if (question.getAnswers().size() - 1 < question.getRightAnswerIndex()) {
                throw new IllegalStateException(format("У вопроса \"{0}\" указан неверный ответ", question.getQuestion()));
            }
        });
    }
}
