package ru.spring.varvara.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.spring.varvara.dao.QuestionDao;
import ru.spring.varvara.domain.Question;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@DisplayName("Тест сервиса на работу с тестированием")
@ExtendWith(MockitoExtension.class)
public class TestingServiceImplTest {
    @Mock
    private QuestionDao questionDao;
    @InjectMocks
    private TestingServiceImpl testingService;

    /**
     * Тестирует успешный вывод вопросов
     * @param question вопрос
     * @param isValid  {@code true} вопрос валидный, {@code false} - иначе
     */
    @ParameterizedTest
    @DisplayName("Тест на проведение тестирования")
    @MethodSource("generateQuestions")
    void shouldGetDataBit(Question question, boolean isValid) {
        when(questionDao.findAll()).thenReturn(List.of(question));
        if (isValid) {
            testingService.printQuestions();
        } else {
            assertThatThrownBy(testingService::printQuestions)
                    .as("Ожидается ошибка о том, что не заполнено обязательное поле")
                    .isInstanceOf(IllegalStateException.class);
        }
    }

    /**
     * Генерация тестовых наборов данных
     * @return тестовый набор данных
     */
    private static Stream<Arguments> generateQuestions() {
        return Stream.of(
                Arguments.of(new Question("Question 1", List.of("a", "b", "c"), 1), true),
                Arguments.of(new Question("Question 2", List.of("a", "b"), 1), true),
                Arguments.of(new Question("Question 3", List.of("a", "b", "c", "d"), 3), true),
                Arguments.of(new Question("Question 4", List.of("a", "b", "c"), 3), false),
                Arguments.of(new Question("Question 5", List.of("a"), 0), false),
                Arguments.of(new Question("Question 6", List.of(), 0), false)
        );
    }
}
