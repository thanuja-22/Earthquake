package com.example.android.rev5;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.rev5.word;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class QueryUtils {

    public static ArrayList<word> fetchEarthQuakeData(String requestUrl){
    URL url = createUrl(requestUrl);
    Log.e("QueryUtils","result");
    // Perform HTTP request to the URL and receive a JSON response back
    String jsonResponse = null;
        try {
        jsonResponse = makeHttpRequest(url);
    } catch (IOException e) {
        Log.e("MainActivity", "Problem making the HTTP request.", e);
    }
        Log.e("QueryUtils","befor extract"+jsonResponse);
    ArrayList<word> earthquakes = extractFeatureFromJson(jsonResponse);

    // Return the list of {@link Earthquake}s
        return earthquakes;
    }
    private static URL createUrl(String stringUrl) {
        URL url = null;
        Log.e("QueryUtils","createurl");
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("MainActivity", "Problem building the URL ", e);
        }
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
       Log.e("QueryUtils","makehttp");
        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                    InputStream in=urlConnection.getInputStream();
                    jsonResponse=readFromStream(in);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        Log.e("QueryUtils","readFromstream");
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        String j=output.toString();
        Log.e("QueryUtils","ouput"+j);
        return output.toString();
    }
    public static ArrayList<word> extractFeatureFromJson(String earthquakeJSON){
        ArrayList<word> earthquakes=new ArrayList<word>();
        Log.e("QueryUtils","extract"+earthquakeJSON);
        try{
        JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
        JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");
        for (int i = 0; i < earthquakeArray.length(); i++) {
            JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
            JSONObject properties = currentEarthquake.getJSONObject("properties");
            double magnitude = properties.getDouble("mag");
            Log.e("QueryUtils","mag"+magnitude);
            String location = properties.getString("place");
            long time = properties.getLong("time");
            String url = properties.getString("url");
            word earthquakesa = new word(magnitude, location, time, url);
            earthquakes.add(earthquakesa);
        }

    } catch (JSONException e) {
        Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
    }


     return earthquakes;

    }
}