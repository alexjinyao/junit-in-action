package com.manning.junitbook.ch07;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A sample web-client class that opens an HTTP connection to a web-server and reads the response from it.
 */
public class WebClient {

    public String getContent(URL url) {
        StringBuffer content = new StringBuffer();
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            final InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int count;
            while (-1 != (count = inputStream.read(buffer))) {
                content.append(new String(buffer, 0, count));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }
}
