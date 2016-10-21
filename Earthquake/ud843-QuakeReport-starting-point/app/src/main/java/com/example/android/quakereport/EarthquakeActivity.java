/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ArrayList<QuakeDetail> earthquakeslist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
       /* ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("San Francisco");
        earthquakes.add("London");
        earthquakes.add("Tokyo");
        earthquakes.add("Mexico City");
        earthquakes.add("Moscow");
        earthquakes.add("Rio de Janeiro");
        earthquakes.add("Paris");

        // Find a reference to the {@link ListView} in the layout


        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, earthquakes);*/

        String strurl="http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";



        QuakeNetworkCall getJSONTask= new QuakeNetworkCall();

        getJSONTask.execute(strurl);




        //The ArrayList returned by the QuakeUtils is assigned to the earthquake ArrayList that is needed for the listView Adapter.



        //earthquakes.add(new QuakeDetail("1.1","San Francisco", "1-2-2016"));
        //earthquakes.add(new QuakeDetail("1.7","London", "2-2-2016"));
        //earthquakes.add(new QuakeDetail("1.9","Tokyo", "3-2-2016"));
        //earthquakes.add(new QuakeDetail("1.3","Mexico", "4-2-2016"));
        //earthquakes.add(new QuakeDetail("1.5","Rio de Janeiro", "5-2-2016"));
        //earthquakes.add(new QuakeDetail("2.1","India", "6-2-2016"));
        //earthquakes.add(new QuakeDetail("2.3","Paris", "7-2-2016"));
        //earthquakes.add(new QuakeDetail("2.1","Japan", "8-2-2016"));

        final QuakeAdapter adapter=new QuakeAdapter(this, earthquakeslist);




        ListView earthquakeListView = (ListView) findViewById(R.id.list);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                QuakeDetail currentQuake= adapter.getItem(position);

                Uri quakeUrl= Uri.parse(currentQuake.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, quakeUrl);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });



    }
    private class  QuakeNetworkCall extends AsyncTask<String, Void, ArrayList<QuakeDetail>>
    {
        @Override
        protected ArrayList<QuakeDetail> doInBackground(String... strurls ){
            if(strurls.length<1|| strurls[0]==null){
                return null;
            }
            ArrayList<QuakeDetail> earthquakes= new ArrayList<>();
            earthquakes=QueryUtils.extractEarthquakes(strurls[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<QuakeDetail> arrayList) {
            earthquakeslist=arrayList;
        }
    }


}
