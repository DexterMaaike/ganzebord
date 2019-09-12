package exceptions;

public class LostPlayerException extends RuntimeException {
    public LostPlayerException(String message) {
        System.out.println(message);
    }
}
