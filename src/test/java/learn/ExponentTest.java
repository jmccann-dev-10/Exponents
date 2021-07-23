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
        int number = random.nextInt(15);
        int power = random.nextInt(15);

        System.out.println("=".repeat(60));
        assertEquals(Exponent.exponent(number, power), Exponent.binaryExponent(number, power), Math.pow(number, power));
    }

    @RepeatedTest(1000)
    public void exponentTimeIsFasterThanBinaryTime() {
        int count = 10000;
        double binaryTime = Exponent.avgBinTime(count);
        double exponentTime = Exponent.avgExpTime(count);
        double javaUtilTime = Exponent.avgMathUtilTime(count);
        System.out.printf("binary: %f, exponent: %f, Math.pow: %f%n", binaryTime, exponentTime, javaUtilTime);
        assertTrue(binaryTime > exponentTime && javaUtilTime > exponentTime);
    }

}