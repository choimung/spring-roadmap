package hello.jdbc.repository.ex;

public class MyDuplicationException extends MyDbException{
    public MyDuplicationException() {
        super();
    }

    public MyDuplicationException(String message) {
        super(message);
    }

    public MyDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicationException(Throwable cause) {
        super(cause);
    }
}
