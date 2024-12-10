package put.io.testing.audiobooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {

    private AudiobookPriceCalculator audiobookcalculator;
    private Audiobook audiobook;

    @BeforeEach
    void setUp() {
        audiobookcalculator = new AudiobookPriceCalculator();
        audiobook = new Audiobook("TEST", 100);
    }

    @Test
    void test_calculateStandard() {
        Customer Standard = new Customer("Standard", Customer.LoyaltyLevel.STANDARD, false);
        assertEquals(audiobookcalculator.calculate(Standard, audiobook), 100);
    }

    @Test
    void test_calculateSilver() {
        Customer Silver = new Customer("Silver", Customer.LoyaltyLevel.SILVER, false);
        assertEquals(audiobookcalculator.calculate(Silver, audiobook), 0.9 * 100);
    }

    @Test
    void test_calculateGold() {
        Customer Gold = new Customer("Gold", Customer.LoyaltyLevel.GOLD, false);
        assertEquals(audiobookcalculator.calculate(Gold, audiobook), 0.8 * 100);
    }

    @Test
    void test_calculateSubscriber() {
        Customer Subscriber = new Customer("Subscriber", Customer.LoyaltyLevel.STANDARD, true);
        assertEquals(audiobookcalculator.calculate(Subscriber, audiobook), 0);
    }
}
