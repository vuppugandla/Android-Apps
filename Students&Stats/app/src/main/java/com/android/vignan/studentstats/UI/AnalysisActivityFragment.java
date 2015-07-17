/* fragment under analysis activity for showing student details and analysis
*Author: Vignan Uppugandla
 */

package com.android.vignan.studentstats.UI;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.vignan.studentstats.R;
import com.android.vignan.studentstats.Util.AnalyzeScores;
import com.android.vignan.studentstats.database.StudentStatsAppContract;
import com.android.vignan.studentstats.database.StudentStatsAppContract.ScoresEntry;
import com.android.vignan.studentstats.model.Statistics;
import com.android.vignan.studentstats.model.Student;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A placeholder fragment containing a simple view.
 */
public class AnalysisActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ANALYSIS_LOADER = 0;

    private static final String[] ANALYSIS_COLUMNS = {
            ScoresEntry.TABLE_NAME+"."+ScoresEntry._ID,
            ScoresEntry.COLUMN_STUDENT_ID,
            ScoresEntry.COLUMN_Q1,
            ScoresEntry.COLUMN_Q2,
            ScoresEntry.COLUMN_Q3,
            ScoresEntry.COLUMN_Q4,
            ScoresEntry.COLUMN_Q5
    };

    static final int COL_ID =0;
    static final int COL_STUD_ID =1;
    static final int COL_Q1 =2;
    static final int COL_Q2 =3;
    static final int COL_Q3 =4;
    static final int COL_Q4 =5;
    static final int COL_Q5 =6;

    private AnalysisAdapter mAnalysisAdapter;

    public AnalysisActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_analysis, container, false);
        mAnalysisAdapter = new AnalysisAdapter(getActivity(), null, 0);

        ListView aListView = (ListView) rootView.findViewById(R.id.listview_statistics);
        aListView.setAdapter(mAnalysisAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        getLoaderManager().initLoader(ANALYSIS_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri AnalysisUri = StudentStatsAppContract.ScoresEntry.buildScoresUri();
        return new CursorLoader(
                getActivity(),
                AnalysisUri,
                ANALYSIS_COLUMNS,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        Cursor analyzeCursor = analysisCursor(cursor);
        mAnalysisAdapter.swapCursor(analyzeCursor);
        MergeCursor mergeCursor = new MergeCursor(new Cursor[] {cursor, analyzeCursor});

        mAnalysisAdapter.swapCursor(mergeCursor);



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAnalysisAdapter.swapCursor(null);
    }

    private Cursor analysisCursor(Cursor cursor){
        ArrayList<Student> list = new ArrayList<>();
        Statistics stats = new Statistics();
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStudentID(cursor.getString(COL_STUD_ID));
                student.setQ1(Integer.parseInt(cursor.getString(COL_Q1)));
                student.setQ2(Integer.parseInt(cursor.getString(COL_Q2)));
                student.setQ3(Integer.parseInt(cursor.getString(COL_Q3)));
                student.setQ4(Integer.parseInt(cursor.getString(COL_Q4)));
                student.setQ5(Integer.parseInt(cursor.getString(COL_Q5)));

                list.add(student);
            } while (cursor.moveToNext());
        }

        AnalyzeScores as = new AnalyzeScores(list);
        stats.setLowScores(as.findLowScores());
        stats.setHighScores(as.findHighScores());
        stats.setAvgScores(as.findAvgScores());

        MatrixCursor matrixCursor= new MatrixCursor(new String[]{"_id","scoreType","q1","q2","q3","q4","q5"});
        MatrixCursor.RowBuilder row=matrixCursor.newRow();
        MatrixCursor.RowBuilder row2=matrixCursor.newRow();
        MatrixCursor.RowBuilder row3=matrixCursor.newRow();

        row.add(0).add("LowScores").add(stats.getLowScore(0)).add(stats.getLowScore(1)).add(stats.getLowScore(2)).add(stats.getLowScore(3)).add(stats.getLowScore(4));
        row2.add(0).add("High Scores").add(stats.getHighScore(0)).add(stats.getHighScore(1)).add(stats.getHighScore(2)).add(stats.getHighScore(3)).add(stats.getHighScore(4));
        row3.add(0).add("Avg Scores").add(stats.getAvgScore(0)).add(stats.getAvgScore(1)).add(stats.getAvgScore(2)).add(stats.getAvgScore(3)).add(stats.getAvgScore(4));

        return matrixCursor;
    }
}
