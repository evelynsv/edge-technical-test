# edge-technical-test
Edge Technical Test

### The challenge

Write a class to check if an int value is contained in a list of type CustomNumberEntity by the fastest means possible.

### Constraints

- Your class MUST implement the provided NumberFinder interface.
- The list of CustomNumberEntity values should be read from a Json file, a short example is given below.
- The contains method of your implementation MUST use the provided FasterComparator.compare method to compare the int value with each CustomNumberEntity. How you do this in the fastest possible time is the key. FastestComparator.compare cannot be modified and no other comparison method should be used (hashing, indexes etc)
- Do not cast or convert provided parameter types, compare method from FastestComparator will handle this. e.g. do not cast from int to String, or CustomNumberEntity.number to int (even if is not used for comparison purpose)
- You MUST include Junit tests for running your code.
- Write your code using java 7 or 8

### Solution

Instead of using a unique stream of processing and execute it sequentially, I decided to use Java Parallel Streams and utilize multiple cores of the processor.
That way, the code is divided into multiple streams that are executed in parallel on separate cores. 
Since the order of execution, in this case, doesn't matter, the parallel streams worked well.

### Results

Using parallel streams resulted in faster execution of the code.

The number of the thread pool can be increased by passing an argument for the VM: 

> -Djava.util.concurrent.ForkJoinPool.common.parallelism=5

We can increase the number according to the necessity of the code.

In order to avoid the NumberFormatException in the FastestComparator.compare method when trying to parse the String value coming to the CustomNumberEntity, 
I could use a library to check if the value is parsable like below:

                return list.parallelStream()
                .filter(entity -> NumberUtils.isParsable(entity.getNumber()))
                .map(entity -> fastestComparator.compare(valueToFind, entity))
                .anyMatch(value -> value == 0);
                
 However, I avoided using a Library or implementing some method to make the validations because this is not the main of the test, so I decided to just use a try/catch to capture the exceptional cases that are not parsable like, null, negative, or not digit values.



