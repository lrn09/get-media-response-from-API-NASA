import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

class GetResponseTest {

    @Test
    public void IsUrlExist() throws IOException {
        //Given
        HttpUriRequest request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=Zwkidy2dXmyTSYO2nLeFMHAQ3bqkjCSoVTz5dd1Y");

        //When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        //Then
        assertNotEquals(HttpStatus.SC_NOT_FOUND, response.getStatusLine().getStatusCode());

    }

    @Test
    public void IsResponseContainJson() throws IOException {
        //Given
        HttpUriRequest request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=Zwkidy2dXmyTSYO2nLeFMHAQ3bqkjCSoVTz5dd1Y");

        //When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        //Then
        String jsonMimeType = "application/json";
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);

    }


}