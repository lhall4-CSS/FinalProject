package com.ljedesign.landonhall.finalproject;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by lhall4 on 5/7/2018.
 */

public class networkUtils {
    private static final String TAG = networkUtils.class.getSimpleName();

    private static final String FB_GRAPH_URL = "https://graph.facebook.com/";

    private static final String BASE_URL = FB_GRAPH_URL;

    /* The format we want our API to return */
    private static final String pageID = "185100801507900";
    //private static final String FB_ACCESS_TOKEN = "1170726992984176|Ld-sZPdSAQTzq2RN80WAXnkTvD8";
    private static  final String FB_ACCESS_TOKEN = "EAACEdEose0cBAFZAOzOtvfZB5cjZAWWnWYYftiZBxHW0QGyzbHZArV5MZC8cnxKsxY0QN6CqZApCf5cA7TKkz8FqiwWZAB6Ugc2YehFK2CGEczxAdHjYCglW6N1HcCy88fdBpCSneIZAEeoMi1tiZAiZCeBPQQqmmT6SPg2Gt1ISwZB7uoeXvLCaheleYPM7OlzB3b4ZD";
    final static String ACT_PARAM = "access_token";

    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     * @return The URL to use to query FB.
     */
    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(pageID)
                .appendPath("feed")
                .appendQueryParameter(ACT_PARAM, FB_ACCESS_TOKEN)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
