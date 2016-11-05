package com.example.android.pets.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Srivatsan on 10/29/2016.
 */

public final class PetsContract  {
    public static final String CONTENT_AUTHORITY="com.example.android.pets";

    public static  final Uri BASE_URI= Uri.parse("content://"+CONTENT_AUTHORITY);
    public static  final String RESOURCE_NAME= "pets";



    public static final class petsEntries implements BaseColumns{

        public static final Uri CONTENT_URI= Uri.withAppendedPath(BASE_URI, RESOURCE_NAME);


        public static final String TABLE_NAME="Pets";
        public static final String COLUMN_PET_ID=BaseColumns._ID;
        public static final String COLUMN_PET_NAME="Name";
        public static final String COLUMN_PET_BREED="Breed";
        public static final String COLUMN_PET_GENDER="Gender";
        public static final String COLUMNPET_WEIGHT="Weight";

        public static final int GENDER_UNKNOWN=0;
        public static final int GENDER_MALE=1;
        public static final int GENDER_FEMALE=2;

    }
}

