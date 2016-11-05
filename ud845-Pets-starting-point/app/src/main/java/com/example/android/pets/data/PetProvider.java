
package com.example.android.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import static android.R.attr.id;


/**
 * {@link ContentProvider} for Pets app.
 */
public class PetProvider extends ContentProvider {

    public PetDbHelper dbHelper;

    private static final UriMatcher mUriMatcher= new UriMatcher(UriMatcher.NO_MATCH);

    private static final int  PETS=100;
    private static final int PETS_ID=101;

    static{
        mUriMatcher.addURI(PetsContract.CONTENT_AUTHORITY, PetsContract.RESOURCE_NAME, PETS);
        mUriMatcher.addURI(PetsContract.CONTENT_AUTHORITY, PetsContract.RESOURCE_NAME+"/#", PETS_ID);
    }


    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = PetProvider.class.getSimpleName();

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {

       dbHelper= new PetDbHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        SQLiteDatabase database= dbHelper.getReadableDatabase();

        Cursor cursor=null;

        int match=mUriMatcher.match(uri);
        switch (match){
            case PETS:

                break;
            case PETS_ID:

                selection=PetsContract.petsEntries._ID+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=database.query(PetsContract.petsEntries.TABLE_NAME, projection,selection, selectionArgs, null, null,sortOrder);

                break;
            default:
                throw new IllegalArgumentException("Cannot query. Unknown Exception"+uri);
        }
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = mUriMatcher.match(uri);
        switch (match) {
            case PETS:
                return insertPet(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a pet into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertPet(Uri uri, ContentValues values) {

        // Check that the name is not null
        String name = values.getAsString(PetsContract.petsEntries.COLUMN_PET_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Pet requires a name");
        }

        SQLiteDatabase database= dbHelper.getWritableDatabase();

        Long newRowId=database.insert(PetsContract.petsEntries.TABLE_NAME, null, values);
        if(newRowId==-1){
            Toast.makeText(getContext(),"Error With inserting in database",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(getContext(), "Pet saved in Database", Toast.LENGTH_SHORT).show();
        }

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        return null;
    }
}