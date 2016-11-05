package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Srivatsan on 10/29/2016.
 */

public class PetDbHelper extends SQLiteOpenHelper {
   public static int databseVersion=1;
    public static final String DATABASE_NAME="Shelter.db";





    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, databseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String SQL_CREATE_TABLE="CREATE TABLE pets("+PetsContract.petsEntries.COLUMN_PET_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+PetsContract.petsEntries.COLUMN_PET_NAME+"  TEXT," +PetsContract.petsEntries.COLUMN_PET_BREED+" TEXT, "+PetsContract.petsEntries.COLUMN_PET_GENDER+" INTEGER," +PetsContract.petsEntries.COLUMNPET_WEIGHT+" INTEGER);";

     db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
