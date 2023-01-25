package ru.spring.varvara.service;

import ru.spring.varvara.domain.Question;

import java.util.List;

/**
 * Сервис проверки вопросов на соответствие необходимым требованиям
 */

public interface QuestionValidator {
    /**
     * Проверяет список вопросов на соответствие необходимым требованиям
     * @param questionList список вопросов
     */
    void validate(List<Question> questionList);
}
