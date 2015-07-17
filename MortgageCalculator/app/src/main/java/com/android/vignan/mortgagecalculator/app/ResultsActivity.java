
/* This class is the main activity which is the second screen which displays the results after calculation.
This is called from main acitivity
Author: Vignan U
 */
package com.android.vignan.mortgagecalculator.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.vignan.mortgagecalculator.app.database.MortgageAppContract;


public class ResultsActivity extends Activity {

    private static final String[] RESULTS_COLUMNS = {
            MortgageAppContract.MortgageEntry.COLUMN_TOTAL_MONTHLY_PAYMENT,
            MortgageAppContract.MortgageEntry.COLUMN_TOTAL_PAYMENT_MORTGAGE_TERM,
            MortgageAppContract.MortgageEntry.COLUMN_PAYOFF_DATE
    };

    public static final int COLUMN_TOTAL_MONTHLY_PAYMENT =0;
    public static final int COLUMN_TOTAL_PAYMENT_MORTGAGE_TERM =1;
    public static final int COLUMN_PAYOFF_DATE =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        int zipCode = intent.getIntExtra("zipcode",0);
//        MortgageDetails details = (MortgageDetails) getIntent().getExtras().getSerializable("Details");

        TextView monthlypaymentsView = (TextView) findViewById(R.id.totalMonthlyPaymentTextView);
        TextView totalMortgagePaymentView = (TextView) findViewById(R.id.totalMortgagePaymentTextView);
        TextView payOffDateView = (TextView) findViewById(R.id.payoffDateTextView);

        Cursor cursor = getContentResolver().query(MortgageAppContract.MortgageEntry.builMortgageUriWithZip(zipCode),
                RESULTS_COLUMNS , null, null, null);

        try {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                monthlypaymentsView.setText("$" + Double.toString(cursor.getDouble(COLUMN_TOTAL_MONTHLY_PAYMENT)));
                totalMortgagePaymentView.setText("$" + Double.toString(cursor.getDouble(COLUMN_TOTAL_PAYMENT_MORTGAGE_TERM)));
                payOffDateView.setText(cursor.getString(COLUMN_PAYOFF_DATE));
            }
        }finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
