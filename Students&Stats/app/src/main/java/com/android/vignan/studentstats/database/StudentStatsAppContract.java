/* This is the contract class which hold task of URI generation for connection to database and generating required URI
This also hold static text for the respective columns of database table
Author: Vignan Uppugandla
 */

package com.android.vignan.studentstats.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Vignan on 7/11/2015.
 */
public class StudentStatsAppContract {
    protected static final String CONTENT_AUTHORITY = "com.android.vignan.studentstats";
    protected static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    protected static final String PATH_STUDENTS_DETAILS = "scores";
    protected static final String PATH_STATISTICS = "statistics";

    public static final class ScoresEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_STUDENTS_DETAILS).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENTS_DETAILS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENTS_DETAILS;
        public static final String TABLE_NAME = "students_scores";

        public static final String COLUMN_STUDENT_ID = "student_id";
        public static final String COLUMN_Q1 = "q1";

        public static final String COLUMN_Q2 = "q2";
        public static final String COLUMN_Q3 = "q3";
        public static final String COLUMN_Q4 = "q4";
        public static final String COLUMN_Q5 = "q5";

        public static Uri buildScoresUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildScoresUri() {
            return CONTENT_URI.buildUpon().build();
        }

        public static String getStudentIDFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }
}

