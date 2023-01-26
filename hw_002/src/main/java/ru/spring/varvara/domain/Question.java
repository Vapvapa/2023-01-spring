package ru.spring.varvara.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Вопрос для тестирования
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    /** Текст вопроса */
    @CsvBindByName(required = true)
    private String question;

    /** Варианты ответов */
    @CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = "\\,")
    private List<String> answers;

    /** Индекс правильного ответа */
    @CsvBindByName(required = true)
    private int rightAnswerIndex;
}
