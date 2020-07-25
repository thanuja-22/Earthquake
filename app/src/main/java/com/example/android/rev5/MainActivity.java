package com.example.android.rev5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public wordAdapter mAdapter;
    public static final String USGS_REQUEST_URL ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new wordAdapter(MainActivity.this, new ArrayList<word>());
        ListView li = (ListView) findViewById(R.id.list);
        li.setAdapter(mAdapter);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                word currentEarthquake = mAdapter.getItem(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        EarthQuakeAsyncTask task = new EarthQuakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    class EarthQuakeAsyncTask extends AsyncTask<String, Void, ArrayList<word>> {


        @Override
        protected ArrayList<word> doInBackground(String... urls) {
            Log.e("MainActivity","resukt");
//            if (urls.length < 1 || urls[0] == null) {
//                return null;
//            }
            ArrayList<word> ear = QueryUtils.fetchEarthQuakeData(USGS_REQUEST_URL);
            return ear;
        }

        @Override
        protected void onPostExecute(ArrayList<word> data) {
//            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);

        }
    }//asynctask
}//main class

