package com.example.android.quakereport;

/**
 * Created by Srivatsan on 10/14/2016.
 */

public class QuakeDetail {
    private String mPlace;
    //private String mdate; gets the string of date and time combined.
    private long mTimeInMill;
    private Double mMag;
    private String mUrl;

    public QuakeDetail(Double mag, String place, long timeInMill, String url ){
        mMag=mag;
        mPlace=place;
        //mdate=date;
        mUrl=url;
        mTimeInMill=timeInMill;

    }

    public Double getMag(){return mMag;}

    public  String getPlace(){return mPlace;}

    //public  String  getMdate(){return  mdate;}
    public long getTimeInMill(){return  mTimeInMill;}

    public String getUrl(){return  mUrl;}

}
