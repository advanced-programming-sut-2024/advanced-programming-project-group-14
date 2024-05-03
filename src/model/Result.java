package model;


public class Result {
    private final boolean isSuccessful;
    private final String message;

    public Result(boolean isSuccess, String message) {
        this.isSuccessful = isSuccess;
        this.message = message;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return message;
    }
}
