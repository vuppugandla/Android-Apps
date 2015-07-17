/* This is the contract class which hold task of URI generation for connection to database and generating required URI
This also hold static text for the respective columns of database table
Author: Vignan
 */

package com.android.vignan.mortgagecalculator.app.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Vignan on 7/11/2015.
 */
public class MortgageAppContract {
    public static final String CONTENT_AUTHORITY = "com.android.vignan.mortgagecalculator.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MORTGAGE = "mortgage";

    public static final class MortgageEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MORTGAGE).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MORTGAGE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MORTGAGE;
        public static final String TABLE_NAME = "mortgage_details";

        public static final String COLUMN_PURCHASE_PRICE = "purchase_price";
        public static final String COLUMN_DOWN_PAYMENT_PERCENT = "down_payment_percent";

        public static final String COLUMN_MORTGAGE_TERM = "mortgage_term";
        public static final String COLUMN_INTEREST_RATE = "interest_rate";
        public static final String COLUMN_PROPERTY_TAX = "property_tax";
        public static final String COLUMN_PROPERTY_INSURANCE = "property_insurance";
        public static final String COLUMN_PMI = "pmi";
        public static final String COLUMN_ZIP_CODE = "zip_code";
        public static final String COLUMN_FIRST_PAYMENT_MONTH = "first_month";
        public static final String COLUMN_FIRST_PAYMENT_YEAR = "first_year";
        public static final String COLUMN_TOTAL_MONTHLY_PAYMENT = "total_monthly_payment";
        public static final String COLUMN_TOTAL_PAYMENT_MORTGAGE_TERM = "total_payment_mortgage_term";
        public static final String COLUMN_PAYOFF_DATE = "payoff_date";

        public static Uri buildMortgageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getZipCodeFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static Uri builMortgageUriWithZip(int zipCode){
            return CONTENT_URI.buildUpon()
                    .appendPath(Integer.toString(zipCode)).build();
        }
    }
}
