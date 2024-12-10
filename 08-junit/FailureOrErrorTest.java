package put.io.testing.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FailureOrErrorTest {

    @Test
    void test1() {
        assertTrue(2 == 3);
    }

    @Test
    void test2() {
        Integer.parseInt("dwa");
    }

    @Test
    void test3() {
        try {
            assertTrue(2 == 3);
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }
}
