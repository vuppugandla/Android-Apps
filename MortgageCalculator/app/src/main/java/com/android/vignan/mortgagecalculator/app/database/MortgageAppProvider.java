/*This is the main class which holds the CRUD operations that are performed on database.
Takes in URI matches to specific query and performs the possible action
* Author: Vignan U
* */

package com.android.vignan.mortgagecalculator.app.database;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Vignan on 7/11/2015.
 */
public class MortgageAppProvider extends ContentProvider{

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MortgageAppDBHelper mOpenHelper;
    private static final int MORTGAGE = 100;
    private static final int MORTGAGE_WITH_ZIP_CODE = 101;;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MortgageAppContract.CONTENT_AUTHORITY;
        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, MortgageAppContract.PATH_MORTGAGE + "/*", MORTGAGE_WITH_ZIP_CODE);
        matcher.addURI(authority, MortgageAppContract.PATH_MORTGAGE, MORTGAGE);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new MortgageAppDBHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case MORTGAGE_WITH_ZIP_CODE:
                return MortgageAppContract.MortgageEntry.CONTENT_ITEM_TYPE;
            case MORTGAGE:
                return MortgageAppContract.MortgageEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    private static final String sZipCodeSelection =
            MortgageAppContract.MortgageEntry.TABLE_NAME +
                    "." + MortgageAppContract.MortgageEntry.COLUMN_ZIP_CODE+ " = ? ";

    //For Read operations
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        int zipCode = Integer.parseInt(MortgageAppContract.MortgageEntry.getZipCodeFromUri(uri));
        switch (sUriMatcher.match(uri)) {
            //Weather with location and date
            case MORTGAGE_WITH_ZIP_CODE:
            {
                retCursor = mOpenHelper.getReadableDatabase().query(MortgageAppContract.MortgageEntry.TABLE_NAME,
                        projection,
                        sZipCodeSelection,
                        new String[]{Integer.toString(zipCode)},
                        null,
                        null,
                        sortOrder
                        );
                break;
            }
            //Weather with location
            case MORTGAGE: {
                retCursor = mOpenHelper.getReadableDatabase().query(MortgageAppContract.MortgageEntry.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    //For insert operations
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case MORTGAGE: {
                long _id = db.insert(MortgageAppContract.MortgageEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = MortgageAppContract.MortgageEntry.buildMortgageUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return returnUri;
    }
    //For delete operations
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        if(null == selection) selection = "1";
        switch (match){
            case MORTGAGE:
                rowsDeleted = db.delete(
                        MortgageAppContract.MortgageEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rowsDeleted != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    //For update operations
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match){
            case MORTGAGE:
                rowsUpdated = db.update(
                        MortgageAppContract.MortgageEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }



}
