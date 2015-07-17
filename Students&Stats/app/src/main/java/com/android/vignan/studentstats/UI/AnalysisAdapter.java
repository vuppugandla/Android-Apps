/* Class which extends cursor adaptor, it collects details from database cursor and shows them to list on main acitvity screen
*Author: Vignan Uppugandla
 */
package com.android.vignan.studentstats.UI;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.android.vignan.studentstats.R;
import com.android.vignan.studentstats.model.Student;

import java.util.ArrayList;

/**
 * Created by Vignan on 7/14/2015.
 */
public class AnalysisAdapter extends CursorAdapter {

    public AnalysisAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_score_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

//        int iD = cursor.getInt(AnalysisActivityFragment.COL_ID);

        String studID = cursor.getString(AnalysisActivityFragment.COL_STUD_ID);
        viewHolder.sIDView.setText(studID);
        if(studID == "Avg Scores"){
            float q1 = cursor.getFloat(AnalysisActivityFragment.COL_Q1);
            viewHolder.q1View.setText(String.format("%.1f", q1));
            float q2 = cursor.getFloat(AnalysisActivityFragment.COL_Q2);
            viewHolder.q2View.setText(String.format("%.1f", q2));
            float q3 = cursor.getFloat(AnalysisActivityFragment.COL_Q3);
            viewHolder.q3View.setText(String.format("%.1f", q3));
            float q4 = cursor.getFloat(AnalysisActivityFragment.COL_Q4);
            viewHolder.q4View.setText(String.format("%.1f", q4));
            float q5 = cursor.getFloat(AnalysisActivityFragment.COL_Q5);
            viewHolder.q5View.setText(String.format("%.1f", q5));
        }else {
            int q1 = cursor.getInt(AnalysisActivityFragment.COL_Q1);
            viewHolder.q1View.setText(String.valueOf(q1));
            int q2 = cursor.getInt(AnalysisActivityFragment.COL_Q2);
            viewHolder.q2View.setText(String.valueOf(q2));
            int q3 = cursor.getInt(AnalysisActivityFragment.COL_Q3);
            viewHolder.q3View.setText(String.valueOf(q3));
            int q4 = cursor.getInt(AnalysisActivityFragment.COL_Q4);
            viewHolder.q4View.setText(String.valueOf(q4));
            int q5 = cursor.getInt(AnalysisActivityFragment.COL_Q5);
            viewHolder.q5View.setText(String.valueOf(q5));
        }
    }

    public static class ViewHolder {
        final TextView sIDView;
        final TextView q1View;
        final TextView q2View;
        final TextView q3View;
        final TextView q4View;
        final TextView q5View;

        public ViewHolder(View view) {
            sIDView = (TextView) view.findViewById(R.id.list_item_stud_id_textview);
            q1View = (TextView) view.findViewById(R.id.list_item_q1_textview);
            q2View = (TextView) view.findViewById(R.id.list_item_q2_textview);
            q3View = (TextView) view.findViewById(R.id.list_item_q3_textview);
            q4View = (TextView) view.findViewById(R.id.list_item_q4_textview);
            q5View = (TextView) view.findViewById(R.id.list_item_q5_textview);
        }
    }
}
