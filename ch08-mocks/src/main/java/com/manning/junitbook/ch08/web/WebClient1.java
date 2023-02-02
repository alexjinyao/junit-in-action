package com.manning.junitbook.ch08.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A WebClient implementation that retrieves the content from a URL
 */
public class WebClient1 {

    /**
     * A method to retrieve the content from the given URL
     * @param url
     * @return
     */
    public String getContent(URL url) {
        StringBuffer content = new StringBuffer();

        try {
            final HttpURLConnection connection = createHttpURLConnection(url);
            final InputStream inputStream = connection.getInputStream();

            int count;
            while (-1 != (count = inputStream.read())) {
                content.append(new String(Character.toChars(count)));
            }
        } catch (IOException e) {
            return null;
        }

        return content.toString();
    }

    /**
     * Creates an HTTP connection.
     * @param url
     * @return
     * @throws IOException
     */
    protected HttpURLConnection createHttpURLConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
