package culturemedia.exception;

import java.text.MessageFormat;

public class VideoNotFoundException extends CultureotecaException {
    public VideoNotFoundException() {
        super("Video not found.");
    }

    public VideoNotFoundException(String title) {
        super(MessageFormat.format("Video not found by title: {0}", title));
    }

    // Nuevo constructor para el rango de duración
    public VideoNotFoundException(double fromDuration, double toDuration) {
        super(MessageFormat.format("No videos found within the specified duration range: {0} - {1}", fromDuration, toDuration));
    }
}
