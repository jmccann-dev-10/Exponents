package learn;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class StaticExecutionPlan {

    @Param({"5", "10", "15", "50", "99"})
    public int base;

    @Param({"5", "10", "15", "50", "99"})
    public int power;

}
