package mate.academy.rickandmorty.exeption;

public class CharacterClientException extends RuntimeException {
    public CharacterClientException(String message, Exception e) {
        super(message);
    }
}
