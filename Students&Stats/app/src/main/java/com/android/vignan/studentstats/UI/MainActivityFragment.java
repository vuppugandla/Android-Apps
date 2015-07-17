/* main activity fragment for main acitivity
*Author: Vignan Uppugandla
 */
package com.android.vignan.studentstats.UI;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.vignan.studentstats.R;
import com.android.vignan.studentstats.database.StudentStatsAppContract;
import com.android.vignan.studentstats.model.Student;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    Button fab;
    EditText studentID;
    EditText q1;
    EditText q2;
    EditText q3;
    EditText q4;
    EditText q5;
    Button addButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        studentID = (EditText) rootView.findViewById(R.id.studentIdTextView);
        q1 = (EditText) rootView.findViewById(R.id.q1TextView);
        q2 = (EditText) rootView.findViewById(R.id.q2TextView);
        q3 = (EditText) rootView.findViewById(R.id.q3TextView);
        q4 = (EditText) rootView.findViewById(R.id.q4TextView);
        q5 = (EditText) rootView.findViewById(R.id.q5TextView);

        addButton = (Button) rootView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

        fab = (Button) rootView.findViewById(R.id.fab);

        fab.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addButton){
            Student details = new Student();
            details.setStudentID(studentID.getText().toString());
            details.setQ1(Integer.parseInt(q1.getText().toString()));
            details.setQ2(Integer.parseInt(q2.getText().toString()));
            details.setQ3(Integer.parseInt(q3.getText().toString()));
            details.setQ4(Integer.parseInt(q4.getText().toString()));
            details.setQ5(Integer.parseInt(q5.getText().toString()));

            addToDatabase(details);

            Context context = getActivity();
            CharSequence text = "Student Scores Added";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            studentID.setText("");
            q1.setText("");
            q2.setText("");
            q3.setText("");
            q4.setText("");
            q5.setText("");
        }
        else if (v.getId() == R.id.fab) {
            Intent i = new Intent(getActivity(), AnalysisActivity.class);
            startActivity(i);
        }
    }

    private void addToDatabase(Student details) {
        ContentValues studentScores = new ContentValues();

        studentScores.put(StudentStatsAppContract.ScoresEntry.COLUMN_STUDENT_ID, details.getStudentID());
        studentScores.put(StudentStatsAppContract.ScoresEntry.COLUMN_Q1, details.getQ1());
        studentScores.put(StudentStatsAppContract.ScoresEntry.COLUMN_Q2, details.getQ2());
        studentScores.put(StudentStatsAppContract.ScoresEntry.COLUMN_Q3, details.getQ3());
        studentScores.put(StudentStatsAppContract.ScoresEntry.COLUMN_Q4, details.getQ4());
        studentScores.put(StudentStatsAppContract.ScoresEntry.COLUMN_Q5, details.getQ5());

        Uri insertedUri = getActivity().getContentResolver().insert(
                StudentStatsAppContract.ScoresEntry.CONTENT_URI,
                studentScores);

        updateAnalysis();
    }

    private void updateAnalysis() {

    }
}
