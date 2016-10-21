package com.example.android.quakereport;

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
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private static String strurl;

    private static URL murl=createURl(strurl);


    /**
     * Sample JSON response for a USGS query
     */
    private static String JSON_RESPONSE = getJSONStream(murl);

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    private static String getJSONStream(URL murl) {
        StringBuilder response = new StringBuilder();
        InputStream inputStream = null;
        HttpURLConnection urrConn = null;

        if (murl == null) {
            return null;
        }
        try {
            urrConn = (HttpURLConnection) murl.openConnection();
            urrConn.connect();
            inputStream = urrConn.getInputStream();
        } catch (IOException e) {
            Log.e("QueryUtil", "Server Connection or getInputStream was not successful");
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                response.append(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            Log.e("QueryUtils", "unable to perform readline");
        }

        String JSONResponse = response.toString();

        return JSONResponse;

    }  private static URL createURl(String strurl) {
        URL url = null;
        try {
            url = new URL(strurl);
        } catch (MalformedURLException e) {
            Log.e("QueryUtil", "Malformed URL: Url has not been formed properly");
        }
        return url;
    }



    /**
     * Return a list of {@link QuakeDetail} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<QuakeDetail> extractEarthquakes(String url) {

        strurl=url;

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<QuakeDetail> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            //parsing the json response.

            JSONObject rootObject = new JSONObject(JSON_RESPONSE);
            JSONArray featuresArray = rootObject.getJSONArray("features");
            for (int JSONObjectIndex = 0; JSONObjectIndex < featuresArray.length(); JSONObjectIndex++) {
                JSONObject properties = featuresArray.getJSONObject(JSONObjectIndex).getJSONObject("properties");

                Double magnitude = properties.getDouble("mag");
                String place = properties.getString("place");

                //long is used to format the time which in millisec
                Long longTime = properties.getLong("time");

                String urlstring = properties.getString("url");


                //Also can be done like this
                //Date quakeDate= new Date(longTime);
                //creating a pattern in which the date should be displayed in
                //SimpleDateFormat datePattern=new SimpleDateFormat("dd-MM-yyyy");
                //SimpleDateFormat timepattern= new SimpleDateFormat("h : mm");
                // String date=datepattern.format(quakeDate);
                //String time= timepattern.format(quakeDate);
                //Both can be merged together and added to the earthquakes ArrayList.

                earthquakes.add(new QuakeDetail(magnitude, place, longTime, urlstring));
            }
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}