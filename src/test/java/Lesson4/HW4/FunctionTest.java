package Lesson4.HW4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static Lesson4.HW4.Function.functionTriangleSquare;

public class FunctionTest {
    @Test
    @DisplayName("Позитивная проверка площади треугольника")
    void positiveSquareCalcTest() throws Exception {
        double result = functionTriangleSquare(6, 5, 7);
        Assertions.assertEquals(14.696938456699069, result);
    }

    @Test
    @DisplayName("Негативная проверка площади треугольника")
    void negativeSquareCalcTest() {
        Assertions.assertThrows(Exception.class, () ->  functionTriangleSquare(0, -3, 9));
    }

}
