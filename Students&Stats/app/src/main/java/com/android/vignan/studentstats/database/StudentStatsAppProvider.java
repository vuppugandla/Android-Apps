/*This is the main class which holds the CRUD operations that are performed on database.
Takes in URI matches to specific query and performs the possible action
* Author: Vignan Uppugandla
* */

package com.android.vignan.studentstats.database;


import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class StudentStatsAppProvider extends ContentProvider{

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private StudentStatsAppDBHelper mOpenHelper;
    private static final int SCORES = 100;
    private static final int SCORES_WITH_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = StudentStatsAppContract.CONTENT_AUTHORITY;
        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, StudentStatsAppContract.PATH_STUDENTS_DETAILS + "/*", SCORES_WITH_ID);
        matcher.addURI(authority, StudentStatsAppContract.PATH_STUDENTS_DETAILS, SCORES);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new StudentStatsAppDBHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {

        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SCORES_WITH_ID:
                return StudentStatsAppContract.ScoresEntry.CONTENT_ITEM_TYPE;
            case SCORES:
                return StudentStatsAppContract.ScoresEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    private static final String sStudentIDSelection =
            StudentStatsAppContract.ScoresEntry.TABLE_NAME +
                    "." + StudentStatsAppContract.ScoresEntry.COLUMN_STUDENT_ID+ " = ? ";

    //For Read operations
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        // Here's the switch statement that, given a URI, will determine what kind of request it is,
        // and query the database accordingly.
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case SCORES: {
                retCursor = mOpenHelper.getReadableDatabase().query(StudentStatsAppContract.ScoresEntry.TABLE_NAME, projection,selection,selectionArgs,null,null,sortOrder);
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
            case SCORES: {
                long _id = db.insert(StudentStatsAppContract.ScoresEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = StudentStatsAppContract.ScoresEntry.buildScoresUri(_id);
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
            case SCORES:
                rowsDeleted = db.delete(
                        StudentStatsAppContract.ScoresEntry.TABLE_NAME,selection,selectionArgs);
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
        int studentID = Integer.parseInt(StudentStatsAppContract.ScoresEntry.getStudentIDFromUri(uri));
        switch (match){
            case SCORES:
                rowsUpdated = db.update(
                        StudentStatsAppContract.ScoresEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case SCORES_WITH_ID:
                rowsUpdated = db.update(
                        StudentStatsAppContract.ScoresEntry.TABLE_NAME, values, sStudentIDSelection, new String[]{Integer.toString(studentID)});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rowsUpdated != 0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }

    @Override
    @TargetApi(11)
    public void shutdown() {
        mOpenHelper.close();
        super.shutdown();
    }



}
