package com.github.pukkertje.TCPServer.helpers;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HTTPSClient {

    public static InputStream fetchURL(String pageUrl) {
        URL url;
        try {
            url = new URL(pageUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            if(connection != null) {
                if(connection.getResponseCode() == 200) {
                    return connection.getInputStream();
                } else {
                    System.err.println("RSS Fetch has response code `" + connection.getResponseCode() + "`");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
