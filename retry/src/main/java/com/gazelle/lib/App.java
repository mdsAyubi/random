package com.gazelle.lib;

/**
 * Hello world!
 *
 */
public class App 
{
    int attempNumber = 0;
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        App app = new App();
        int result = 0;

        GRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy(4);
        GRetryPolicy executionTimeBasedRetryPolicy = new ExecutionTimeBasedRetryPolicy();

        GBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        GBackOffPolicy defaultFixedBackOffPolicy = new DefaultFixedBackOffPolicy();

        GExceptionPolicy defaultExceptionPolicy = new DefaultExceptionPolicy();

        GRetry<Integer> integerGRetry = new GRetry<>(executionTimeBasedRetryPolicy, defaultFixedBackOffPolicy, defaultExceptionPolicy);

        result = integerGRetry.tryOf(() -> app.aFunction(4));

        System.out.println("Result " + result);
    }

    public Integer aFunction(int succeedNum) {
        if(attempNumber < succeedNum){
            attempNumber++;
            System.out.println("Attempt Number "+ attempNumber);
            throw new RuntimeException("Attempt Number "+ attempNumber);
        }
        return 1;
    }
}
