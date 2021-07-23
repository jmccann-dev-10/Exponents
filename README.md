# Exponents
This is based on a [binary exponent](https://cp-algorithms.com/algebra/binary-exp.html).
I created two methods for the actual exponent,
one for the actual binary exponent, one for a standard for loop.
I also left in a bunch of commented out code that keeps track of and outputs interesting
parts of the code.  If you want to test true benchmarking, leave the code commented out though,
as they affect performance. 

### Benchmarking Results
I tested out both methods using the OpenJDK JMH, and also included both static and random execution plans.
For comparison, I also included the BigInteger.power for comparison.  Binary does indeed work faster, but only
with larger exponents.  Initially when I was doing this with integers and longs, a standard for loop ran faster than
the binary exponent by a negligible amount (roughly 10ish or less nanoseconds).  When I changed this to BigInteger
to account for the larger numbers, I started seeing better performance from the binary exponents, but never more than
500 nanoseconds or so.  In most cases, though, using BigInteger.power nearly always beat out both methods hands down
by nearly half the time either of the two methods could finish in, almost every time.

### Conclusion
Don't be clever, the tools written for Java will likely outperform any kinds of optimizations you may attempt to write
yourself!