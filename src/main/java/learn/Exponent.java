package learn;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Exponent {

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
            Math.pow(number, power);
            expTime += System.nanoTime() - start;
        }
        return (expTime + 0.0) / count;
    }

    public static long exponent(int num, int power) {
        long value = 1L;
//        int totalCalculation = 0;
//        int loops = 0;
        for (int i = 0; i < power; i++) {
            value *= num;
//            totalCalculation++;
//            loops++;
        }
//        System.out.printf("num: %2d, power: %2d, value: %21d, calcs: %2d, loops: %2d%n", num, power, value, totalCalculation, loops);
        return value;
    }

    public static long binaryExponent(int num, int power) {
        long value = 1L;
        int exp = num;
//        int totalCalculations = 0;
//        int loops = 0;
        for (int i = power; i > 0; i >>= 1) {
//            String iteration = Integer.toBinaryString(i);
//            String left = iteration.length() > 1 ? iteration.substring(0, iteration.length() - 1) : "";
//            char right = iteration.charAt(iteration.length() - 1);
//            System.out.printf("Iteration: %2d, %4s -> %s ~ %s%n", i, left, right, right == '1');
            if ((i & 1) == 1) {
                value *= exp;
            }
            exp *= exp;
//            totalCalculations += 3;
//            loops++;
        }
//        System.out.printf("num: %2d, power: %2d, value: %21d, calcs: %2d, loops: %2d%n", num, power, value, totalCalculations, loops);
        return value;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public static long binaryExponent(StaticExecutionPlan plan) {
        return binaryExponent(plan.num, plan.power);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public static long exponent(StaticExecutionPlan plan) {
        return exponent(plan.num, plan.power);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Measurement(iterations = 5, time = 1)
    @Warmup(iterations = 5, time = 1)
    public static double mathDotPower(StaticExecutionPlan plan) {
        return Math.pow(plan.num, plan.power);
    }

}