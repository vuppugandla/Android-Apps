/* This class extends SQLiteopenhelper class and is responsible for creation, dropping and version changes of the database
Author: Vignan Uppugandla
*/

package com.android.vignan.studentstats.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentStatsAppDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "studentstats.db";

    public StudentStatsAppDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_SCORES_TABLE = "CREATE TABLE " + StudentStatsAppContract.ScoresEntry.TABLE_NAME + " (" +
                StudentStatsAppContract.ScoresEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                StudentStatsAppContract.ScoresEntry.COLUMN_STUDENT_ID + " TEXT NOT NULL, " +
                StudentStatsAppContract.ScoresEntry.COLUMN_Q1 + " INTEGER NOT NULL, " +
                StudentStatsAppContract.ScoresEntry.COLUMN_Q2 + " INTEGER NOT NULL, " +
                StudentStatsAppContract.ScoresEntry.COLUMN_Q3 + " INTEGER NOT NULL, " +
                StudentStatsAppContract.ScoresEntry.COLUMN_Q4 + " INTEGER NOT NULL, " +
                StudentStatsAppContract.ScoresEntry.COLUMN_Q5 + " INTEGER NOT NULL, " +
                " UNIQUE (" + StudentStatsAppContract.ScoresEntry.COLUMN_STUDENT_ID + ") ON CONFLICT REPLACE);";


        sqLiteDatabase.execSQL(SQL_CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentStatsAppContract.ScoresEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
