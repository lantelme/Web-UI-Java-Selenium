package Lesson4;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;


public class AssuptionsTest {
    @Test
    void assuptionTest() {
        Assumptions.assumeTrue(1 == 2);
        Assertions.assertTrue(1 == 1);
    }

    @Test
    void softAssertionTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(false),
                () -> Assertions.assertTrue(false),
                () -> Assertions.assertFalse(false)
        );
    }
}
