# Exponents
This is based on a [binary exponent](https://cp-algorithms.com/algebra/binary-exp.html).
I created two methods for the actual exponent,
one for the actual binary exponent, one for a standard `for loop`.
I also left in a bunch of commented out code that keeps track of and outputs interesting
parts of the code.  If you want to test true benchmarking, leave the code commented out though,
as they affect performance. 

### Benchmarking Results
I tested out both methods using the `OpenJDK JMH`, and also included both static and random execution plans.
For comparison, I also included the `BigInteger.power` for comparison.  Binary does indeed work faster, but only
with larger exponents.  Initially when I was doing this with integers and longs, a standard `for loop` ran faster than
the binary exponent by a negligible amount (roughly 10ish or less nanoseconds).  When I changed this to `BigInteger`
to account for the larger numbers, I started seeing better performance from the binary exponents, but only by a millisecond at most.
In most cases, though, using `BigInteger.power` nearly always beat out both methods hands down
quite a bit, almost every time.

### Conclusion
- Standard `for loop` performed faster or at the same speed as the binary exponent formula with smaller exponents.
- I had to use a `BigInteger` in order to get any real significant performance increase from the binary exponent formula.
- `BigInteger.power()` already exists, performed more consistently, and hands down was faster than either of these methods almost uniformly**.
- I could have spent the time building this project doing something else, like watching TV!

** The one caveat to this, `BigInterger.power` method seemed to be directly related to how big the base number was as opposed to the exponent.
While `BigInteger` pretty much performed at uniform rates, there was a significant jump between the base at 15 and the base at 50 in performance.
Even with that being said, `binary` didn't perform faster than `BigInteger` unless if it was working with a large base and a small exponent.
Either way, I'd still just recommend using the tools at your disposal.