package learn;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class StaticExecutionPlan {

    @Param({"5", "10", "15", "50"})
    public int num;

    @Param({"5", "10", "15", "50"})
    public int power;

}
