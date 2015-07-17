/* Utility class for calculating low, high and avg scores
*Author: Vignan Uppugandla
 */
package com.android.vignan.studentstats.Util;

import com.android.vignan.studentstats.model.Statistics;
import com.android.vignan.studentstats.model.Student;

import java.util.ArrayList;


public class AnalyzeScores {

    private Statistics stats = new Statistics();
    private ArrayList<Student> students = new ArrayList<>();

    public AnalyzeScores(ArrayList<Student> students){
        this.students = students;
    }

    public int[] findLowScores(){

        for(int i = 0 ; i<5; i++){
            int lowScore = 100;
            for(Student stud : students){
                switch (i){
                    case 0:
                        if(stud.getQ1()<lowScore) lowScore = stud.getQ1();
                        break;
                    case 1:
                        if(stud.getQ2()<lowScore) lowScore = stud.getQ2();
                        break;
                    case 2:
                        if(stud.getQ3()<lowScore) lowScore = stud.getQ3();
                        break;
                    case 3:
                        if(stud.getQ4()<lowScore) lowScore = stud.getQ4();
                        break;
                    case 4:
                        if(stud.getQ5()< lowScore) lowScore = stud.getQ5();
                        break;
                }
            }
            stats.setLowScore(lowScore, i);
        }

    return stats.getLowScores();
    }

    //calculating high scores
    public int[] findHighScores(){

        for(int i = 0 ; i<5; i++){
            int highScore = 0;
            for(Student stud : students){
                switch (i){
                    case 0:
                        if(stud.getQ1()>highScore) highScore = stud.getQ1();
                        break;
                    case 1:
                        if(stud.getQ2()>highScore) highScore = stud.getQ2();
                        break;
                    case 2:
                        if(stud.getQ3()>highScore) highScore = stud.getQ3();
                        break;
                    case 3:
                        if(stud.getQ4()>highScore) highScore = stud.getQ4();
                        break;
                    case 4:
                        if(stud.getQ5()> highScore) highScore = stud.getQ5();
                        break;
                }
            }
            stats.setHighScore(highScore, i);
        }

    return stats.getHighScores();
    }

    //calculating avg scores
    public float[] findAvgScores(){

        for(int i = 0 ; i<5; i++){
            float avgScore = 0;
            for(Student stud : students){
                switch (i){
                    case 0:
                        avgScore += stud.getQ1();
                        break;
                    case 1:
                        avgScore += stud.getQ2();
                        break;
                    case 2:
                        avgScore += stud.getQ3();
                        break;
                    case 3:
                        avgScore += stud.getQ4();
                        break;
                    case 4:
                        avgScore += stud.getQ5();
                        break;
                }
            }
            stats.setAvgScore(avgScore / students.size(), i);
        }

    return stats.getAvgScores();
    }
}
