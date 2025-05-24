
package main.java.exception;

public class MaxLoanLimitReachedException extends RuntimeException {
    public MaxLoanLimitReachedException(String message) {
        super(message);
    }
}
