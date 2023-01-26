package ru.spring.varvara.dao;

import ru.spring.varvara.domain.Question;

import java.util.List;

/**
 * DAO по работе с вопросами для тестирования
 */
public interface QuestionDao {

    /**
     * Возвращает все вопросы для тестирования вместе с вариантами ответов
     * @return все вопросы для тестирования вместе с вариантами ответов
     */
    List<Question> findAll();
}
