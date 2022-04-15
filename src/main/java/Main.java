import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {


    public static void main(String[] args) throws IOException {

        GetResponse getResponse = new GetResponse();
        GetMediaResponse getMediaResponse = getResponse.GetResponseFromUrl(
                "https://api.nasa.gov/planetary/apod?api_key=Zwkidy2dXmyTSYO2nLeFMHAQ3bqkjCSoVTz5dd1Y");

        String fileName = getMediaResponse.getUrl();
        if (fileName.lastIndexOf("/") == -1) {
            try (InputStream in = new URL(getMediaResponse.getUrl()).openStream()) {
                Files.copy(in, Path.of(fileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } else {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            try (InputStream in = new URL(getMediaResponse.getUrl()).openStream()) {
                Files.copy(in, Path.of(fileName), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}

