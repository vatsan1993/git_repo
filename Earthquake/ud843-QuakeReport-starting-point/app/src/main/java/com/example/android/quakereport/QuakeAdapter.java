package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Srivatsan on 10/15/2016.
 *
 * gets the
 */

public class QuakeAdapter extends ArrayAdapter<QuakeDetail> {

    public QuakeAdapter(Context context, ArrayList<QuakeDetail> arrayList){
        super(context,0,arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View  listView=convertView;
        int magnitudeColor;

        if(listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent , false);
        }

        QuakeDetail currentQuake= getItem(position);

        TextView mag=(TextView) listView.findViewById(R.id.quake_magnitude);

        Double magWithoutDecimal=currentQuake.getMag();
        DecimalFormat decimalPattern= new DecimalFormat("0.0");
        String formattedMagnitude= decimalPattern.format(magWithoutDecimal);

        mag.setText(formattedMagnitude);

        GradientDrawable magCircle=(GradientDrawable) mag.getBackground();



        magnitudeColor=ContextCompat.getColor(getContext(), R.color.magnitude6);
        magCircle.setColor(magnitudeColor);

        TextView placekm=(TextView) listView.findViewById(R.id.quake_placeKm);

        String placeString=currentQuake.getPlace();
        String[] parts;
        String placeOfset;
        String country;
        if(placeString.contains("of")){
            parts= placeString.split("(?<=of)");
            placeOfset=parts[0];
            country=parts[1];

        }
        else{
            placeOfset="Nearby";
            country=placeString;
        }


        placekm.setText(placeOfset);
        TextView countryText= (TextView)listView.findViewById(R.id.quake_place);
        countryText.setText(country);

        TextView date= (TextView) listView.findViewById(R.id.quake_date);

        //date.setText(currentQuake.getMdate());

        Date dateObject=new Date(currentQuake.getTimeInMill());

        String formattedDate= formatDate(dateObject);

        date.setText(formattedDate);

        TextView time= (TextView) listView.findViewById(R.id.quake_time);

        String formattedTime=formatTime(dateObject);

        time.setText(formattedTime);

        return listView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /*private int magcolors(double localmag){
        int magColor=0;
        int intMag=(int) Math.floor(localmag);
        switch(intMag){

            case 1:
                magColor=R.color.magnitude1;
                break;
            case 2:
                magColor=R.color.magnitude2;
                break;
            case 3:
                magColor=R.color.magnitude3;
                break;
            case 4:
                magColor=R.color.magnitude4;
                break;
            case 5:
                magColor=R.color.magnitude5;
                break;
            case 6:
                magColor=R.color.magnitude6;
                break;
            case 7:
                magColor=R.color.magnitude7;
                break;
            case 8:
                magColor=R.color.magnitude8;
                break;
            case 9:
                magColor=R.color.magnitude9;
                break;


        }
        return  ContextCompat.getColor(getContext(),magColor);
    }*/
}
