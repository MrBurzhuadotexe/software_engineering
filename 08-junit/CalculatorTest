package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new Calculator();
    }

    @Test
    void testAdd(){
        assertEquals(calculator.add(4, 3), 7);
        assertEquals(calculator.add(1, -1), 0);
    }

    @Test
    void test_multiply() {
        assertEquals(calculator.multiply(3, 3), 9);
        assertEquals(calculator.multiply(4, 6), 24);
    }
    @Test
    void test_addPositiveNumbers() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            calculator.addPositiveNumbers(-2,3);
        });
    }
}
