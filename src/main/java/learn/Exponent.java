package learn;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Exponent {

    private static final NumberFormat formatter = new DecimalFormat("0.######E0", DecimalFormatSymbols.getInstance(Locale.ROOT));

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    public static double avgExpTime(int count) {
        Random random = new Random();
        int number, power;
        long expTime = 0L;
        for (int i = 0; i < count; i++) {
            number = random.nextInt(15);
            power = random.nextInt(15);
            long start = System.nanoTime();
            exponent(number, power);
            expTime += System.nanoTime() - start;
        }
        return (expTime + 0.0) / count;
    }

    public static double avgBinTime(int count) {
        Random random = new Random();
        int number, power;
        long expTime = 0L;
        for (int i = 0; i < count; i++) {
            number = random.nextInt(15);
            power = random.nextInt(15);
            long start = System.nanoTime();
            binaryExponent(number, power);
            expTime += System.nanoTime() - start;
        }
        return (expTime + 0.0) / count;
    }

    public static double avgMathUtilTime(int count) {
        Random random = new Random();
        int number, power;
        long expTime = 0L;
        for (int i = 0; i < count; i++) {
            number = random.nextInt(15);
            power = random.nextInt(15);
            long start = System.nanoTime();
            BigInteger.valueOf(number).pow(power);
            expTime += System.nanoTime() - start;
        }
        return (expTime + 0.0) / count;
    }

    public static BigInteger exponent(int num, int power) {
        BigInteger value = BigInteger.ONE;
//        int totalCalculation = 0;
//        int loops = 0;
        for (int i = 0; i < power; i++) {
            value = value.multiply(BigInteger.valueOf(num));
//            totalCalculation++;
//            loops++;
        }
//        System.out.printf("exp ~ num: %2d, power: %2d, value: %-12s, calcs: %2d, loops: %2d%n", num, power, formatter.format(value), totalCalculation, loops);
        return value;
    }

    public static BigInteger binaryExponent(int num, int power) {
        BigInteger value = BigInteger.ONE;
        BigInteger exp = BigInteger.valueOf(num);
//        int totalCalculations = 0;
//        int loops = 0;
        for (int i = power; i > 0; i >>= 1) {
            /* This will demonstrate how the for loop works
            String iteration = Integer.toBinaryString(i);
            String left = iteration.length() > 1 ? iteration.substring(0, iteration.length() - 1) : "";
            char right = iteration.charAt(iteration.length() - 1);
            System.out.printf("Iteration: %2d, %7s -> %s ~ %s%n", i, left, right, right == '1');
            */
            if ((i & 1) == 1) {
//                totalCalculations++;
                value = value.multiply(exp);
            }
            exp = exp.multiply(exp);
//            totalCalculations += 2;
//            loops++;
        }
//        System.out.printf("bin ~ num: %2d, power: %2d, value: %-12s, calcs: %2d, loops: %2d%n", num, power, formatter.format(value), totalCalculations, loops);
        return value;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public static BigInteger binaryExponent(StaticExecutionPlan plan) {
        return binaryExponent(plan.num, plan.power);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public static BigInteger exponent(StaticExecutionPlan plan) {
        return exponent(plan.num, plan.power);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public static BigInteger bigIntegerPow(StaticExecutionPlan plan) {
        return BigInteger.valueOf(plan.num).pow(plan.num);
    }

}