package com.manning.junitbook.ch08.web;

import java.io.InputStream;

/**
 * A sample WebClient that opens a connection to a web-server and reads the data from it
 */
public class WebClient2 {


    /**
     * Open a connection to the given URL and read the content out of it.
     * @param connectionFactory
     * @return
     */
    public String getContent(ConnectionFactory connectionFactory) {
        String workingContent;

        StringBuffer content = new StringBuffer();
        try (InputStream inputStream = connectionFactory.getData()){
            int count;
            while (-1 != (count = inputStream.read())) {
                content.append(new String(Character.toChars(count)));
            }

            workingContent = content.toString();
        } catch (Exception e) {
            workingContent = null;
        }

        return workingContent;
    }
}
