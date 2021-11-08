package Lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class BoxTest {
    Box box;

    @Nested
    class WhenIsEmpty {
        @BeforeEach
        void createBox() {
            box = new Box();
        }

        @Test
        void addBallTest() {
            box.addBall();
            assertThat(box.getBallsCount()).isEqualTo(1);
        }

        @Test
        void exceptionWhenTryToShuffleEmptyBox() {
            Assertions.assertThrows(Exception.class, () -> box.shuffleBalls());
            assertThatNullPointerException().isThrownBy(() -> box.shuffleBalls());
        }

        @Test
        void exceptionWhenTryToRemoveBallFromEmptyBox() {
            assertThatExceptionOfType(BallsCountIsZeroException.class).isThrownBy(() -> box.removeBall());
        }

        @Nested
        class WhenWithBalls {
            @BeforeEach
            void addBall() {
                box.addBall();
            }

            @Test
            void removeBallTest() throws BallsCountIsZeroException {
                box.removeBall();
                assertThat(box.getBallsCount()).isEqualTo(0);
            }
        }
    }

}
