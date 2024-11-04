package culturemedia.exception;

public class VideoNotFoundException extends CultureotecaException {
    public VideoNotFoundException() {
        super("Video not found");
    }

    public VideoNotFoundException(String title) {
        super("Video not found with title: " + title);
    }
}
