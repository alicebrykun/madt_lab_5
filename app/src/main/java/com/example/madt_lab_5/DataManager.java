package com.example.madt_lab_5;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataManager {

    public static ArrayList<String> getRateFromECB() throws IOException {
        String rate = "Data were not retrieved.";
        ArrayList<String> arrayList = new ArrayList<>();

        InputStream stream = downloadUrl(Const.ECB_URL);
        try {
            arrayList = Parser.getRateFromECB(stream);
        }
        finally {
            if (stream != null) {
                stream.close();
            }
        }
        return arrayList;
    }

    private static InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
