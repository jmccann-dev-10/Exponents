package learn;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.Random;

@State(Scope.Benchmark)
public class RandomExecutionPlan {

    public int base;
    public int power;

    private static Random random = new Random();

    @Setup
    public void setup() {
        base = random.nextInt(100);
        power = random.nextInt(100);
    }

}
