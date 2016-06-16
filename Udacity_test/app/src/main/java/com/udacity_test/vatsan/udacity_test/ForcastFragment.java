package com.udacity_test.vatsan.udacity_test;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForcastFragment extends Fragment {

    public ForcastFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.string.action_refresh)
        {
            fetchWeatherData fetchingdata=new fetchWeatherData();
            fetchingdata.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        String tmp[] = {
                "Sunday-", "monday", "tueday", "wednesday", "thursday", "friday", "saturday"
        };
        ArrayList<String> fake_data = new ArrayList<String>(Arrays.asList(tmp));
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forcast, R.id.list_item_forecast_textview, fake_data);
        ListView mylistview = (ListView) rootView.findViewById(R.id.fragment_main_listview);
        mylistview.setAdapter(adapt);


        return rootView;
    }

    public class fetchWeatherData extends AsyncTask<Void, Void, Void> {
        private final String LOG_TAG=fetchWeatherData.class.getSimpleName();

        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection http_conn = null;
            BufferedReader reader = null;

            String forcastJsonString = null;

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=London,us&mode=json&appid=3bf3416656f6602769ffcbf872613412&cnt=7&units=metric");

                http_conn = (HttpURLConnection) url.openConnection();
                http_conn.setRequestMethod("GET");
                http_conn.connect();


                InputStream stream = http_conn.getInputStream();
                StringBuffer str_buff = new StringBuffer();

                if (stream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(stream));
                String line;

                if ((line = reader.readLine()) != null) {

                    str_buff.append(line + "\n");

                }

                if (str_buff == null)
                    return null;
                forcastJsonString = str_buff.toString();
                Log.v(LOG_TAG, "Forecast String" +forcastJsonString);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Some IO Error", e);
                return null;
            } finally {
                if (http_conn != null)
                    http_conn.disconnect();
                try {
                    reader.close();
                } catch (final IOException e) {

                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
       return null;
    }
    }
}
