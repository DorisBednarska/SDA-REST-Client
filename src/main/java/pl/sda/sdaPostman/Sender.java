package pl.sda.sdaPostman;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import pl.sda.utils.HttpUtils;

import java.io.IOException;

/**
 * Created by RENT on 2017-03-06.
 */
public class Sender {

    @SuppressWarnings("deprecation")
    public static String createUser(String url, String request) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        post.setEntity(new StringEntity(request));

        HttpResponse response = httpClient.execute(post);
        return HttpUtils.parseResponse(response);
    }
}
