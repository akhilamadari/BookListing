package com.example.andriod.booklisting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by akhilamadari on 3/4/18.
 */

public final class BookUtils {

    public static final String LOG_TAG = BookUtils.class.getSimpleName();

    public static ArrayList<Book> fetchEarthquakeData(String requestUrl) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            Log.e(LOG_TAG, " 1closing input stream");
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        ArrayList<Book> quake = extractBooks(jsonResponse);
        return quake;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
            Log.e(LOG_TAG, " 2closing input stream");
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the books JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Book> extractBooks(String JSON_response) {

        ArrayList<Book> books = new ArrayList<>();

        try {
            String title ="";
            String[] authors = new String[]{"No Authors"};

            JSONObject s = new JSONObject(JSON_response);
            if (!s.isNull("items")) {
                JSONArray contacts = s.getJSONArray("items");

                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);
                    JSONObject d = c.getJSONObject("volumeInfo");
                    title = d.optString("title");
                    authors = new String[]{"No Authors"};

                    if (!d.isNull("authors")) {
                        JSONArray authorsArray = d.getJSONArray("authors");
                        authors = new String[authorsArray.length()];

                        for (int j = 0; j < authorsArray.length(); j++) {
                            authors[j] = authorsArray.getString(j);
                            //books.add(new Book(title, authors));
                        }
                    }
                    books.add(new Book(title, authors));
                }
            }


        } catch (JSONException e) {

            Log.e(LOG_TAG, "Problem parsing the Books JSON results", e);
        }

        return books;
    }
}
