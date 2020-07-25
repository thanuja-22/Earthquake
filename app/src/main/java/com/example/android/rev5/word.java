package com.example.android.rev5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class word {
    Double mag;
    String place;
    long tim;
    String url;


    public word() {
    }

    public Double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public long getDat() {
        return tim;
    }

    public String getUrl() {
        return url;
    }

    public word(Double mag, String place, long dat, String url) {
        this.mag = mag;
        this.place = place;
        this.tim = dat;
        this.url = url;
    }
}