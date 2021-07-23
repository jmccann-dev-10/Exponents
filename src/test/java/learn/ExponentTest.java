package learn;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ExponentTest {

    private static Random random;

    @BeforeAll
    public static void init() {
         random = new Random();
    }

    @RepeatedTest(1000)
    public void methodsShouldProduceSameResults() {
        int base = random.nextInt(100);
        int power = random.nextInt(100);

        System.out.println("=".repeat(60));
        assertEquals(Exponent.exponent(base, power), Exponent.binaryExponent(base, power));
    }

    @RepeatedTest(1000)
    public void binaryTimeIsFasterThanForLoopTime() {
        int count = 10000;
        double binaryTime = Exponent.avgBinTime(count);
        double exponentTime = Exponent.avgExpTime(count);
        double javaUtilTime = Exponent.avgMathUtilTime(count);
        System.out.printf("binary: %f, exponent: %f, bigIntPow: %f%n", binaryTime, exponentTime, javaUtilTime);
        assertTrue(binaryTime < exponentTime);
    }

}