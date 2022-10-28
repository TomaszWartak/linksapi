package pl.dev4lazy.linksapi.utils.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("wrong password");
    }
}
