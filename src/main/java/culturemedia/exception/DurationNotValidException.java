package culturemedia.exception;

public class DurationNotValidException extends CultureotecaException {
    public DurationNotValidException(String title, Double duration) {
        super("The video \"" + title + "\" has an invalid duration: " + duration);
    }
}
