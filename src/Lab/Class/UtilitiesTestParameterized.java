package Lab.Class;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UtilitiesTestParameterized {

    private Utilities util;

    @BeforeEach
    public void setup() {
        util = new Utilities();
    }

    static Stream<Arguments> testConditions() {
        return Stream.of(
            Arguments.of("ABCDEFF", "ABCDEF"),
            Arguments.of("AB88EFFG", "AB8EFG"),
            Arguments.of("112233445566", "123456"),
            Arguments.of("A", "A")
        );
    }

    @ParameterizedTest
    @MethodSource("testConditions")
    public void removePairs(String input, String expected) {
        assertEquals(expected, util.removePairs(input));
    }
}
