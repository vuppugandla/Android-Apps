/* This class extends SQLiteopenhelper class and is responsible for creation, dropping and version changes of the database
Author: Vignan U
*/

package com.android.vignan.mortgagecalculator.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vignan on 7/11/2015.
 */
public class MortgageAppDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "mortgage.db";

    public MortgageAppDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_MORTGAGE_TABLE = "CREATE TABLE " + MortgageAppContract.MortgageEntry.TABLE_NAME + " (" +
                MortgageAppContract.MortgageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MortgageAppContract.MortgageEntry.COLUMN_PURCHASE_PRICE+ " REAL NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_DOWN_PAYMENT_PERCENT + " REAL NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_MORTGAGE_TERM + " INTEGER NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_INTEREST_RATE + " REAL NOT NULL," +
                MortgageAppContract.MortgageEntry.COLUMN_PROPERTY_TAX + " REAL NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_PROPERTY_INSURANCE + " REAL NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_PMI + " REAL NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_ZIP_CODE + " INTEGER NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_FIRST_PAYMENT_MONTH + " TEXT NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_FIRST_PAYMENT_YEAR + " INTEGER NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_TOTAL_MONTHLY_PAYMENT + " REAL NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_TOTAL_PAYMENT_MORTGAGE_TERM + " REAL NOT NULL, " +
                MortgageAppContract.MortgageEntry.COLUMN_PAYOFF_DATE + " TEXT NOT NULL, " +
                " UNIQUE (" + MortgageAppContract.MortgageEntry.COLUMN_PURCHASE_PRICE + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_DOWN_PAYMENT_PERCENT + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_MORTGAGE_TERM + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_INTEREST_RATE + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_PROPERTY_TAX + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_PROPERTY_INSURANCE + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_PMI + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_ZIP_CODE + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_FIRST_PAYMENT_MONTH + ", " +
                MortgageAppContract.MortgageEntry.COLUMN_FIRST_PAYMENT_YEAR + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_MORTGAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MortgageAppContract.MortgageEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
