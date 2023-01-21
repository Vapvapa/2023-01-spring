package ru.spring.varvara.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.spring.varvara.domain.Question;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Тест класса QuestionDaoCsvTest, чтение csv файла с вопросами")
public class QuestionDaoCsvTest {
    @Test
    @DisplayName("Тестна правильность чтения вопросов с валидного файла")
    void findAll() {
        QuestionDaoCsv daoCsv = new QuestionDaoCsv("validFile.csv");
        List<Question> questions = daoCsv.findAll();

        assertThat(questions.size()).as("Количество вопросов должно быть равно 5")
                .isEqualTo(5);

        Question question = questions.get(2);
        assertAll("Неверное состояние вопроса N2",
                () -> assertThat(question.getQuestion())
                        .as("Правильность считывания вопроса")
                        .isEqualTo("2. What is the longest-tailed cat breed?"),
                () -> assertThat(question.getAnswers().size())
                        .as("Правильность количества ответов")
                        .isEqualTo(5),
                () -> assertThat(question.getRightAnswerIndex())
                        .as("Правильность считывания правильного ответа")
                        .isEqualTo(4)
        );
    }

    @DisplayName("Тест на проверку обязательных полей")
    @ParameterizedTest(name = "Файл {0}")
    @ValueSource(strings = {"withoutQuestion.csv", "withoutRightAnswerIndex.csv"})
    void testRequiredFields(String resourceName) {
        QuestionDaoCsv daoCsv = new QuestionDaoCsv(resourceName);

        assertThatThrownBy(daoCsv::findAll)
                .as("Ожидается ошибка о том, что не заполнено обязательное поле")
                .isInstanceOf(RuntimeException.class);
    }
}
