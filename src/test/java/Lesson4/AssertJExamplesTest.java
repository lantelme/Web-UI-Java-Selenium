package Lesson4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AssertJExamplesTest {
    @Test
    void firstTest() {
        boolean result = Functions.isPalindrome("123321");
        assertThat(result).isTrue();
    }

    @Test
    void secondTest() {
        List<String> testCollection = new ArrayList<>(Arrays.asList("test", "test2", "663333"));
        assertThat(testCollection).containsAnyOf("test5", "test1");
        assertThat(5).isGreaterThan(3).isLessThan(6);
    }
}
