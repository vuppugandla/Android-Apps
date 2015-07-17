/* Model class for storing details of student score analysis
*Author: Vignan Uppugandla
 */

package com.android.vignan.studentstats.model;

public class Statistics {

    private int lowScores[] = new int[5];
    private int highScores[] = new int[5];
    private float avgScores[] = new float[5];

    public Statistics(){};

    public int[] getLowScores() {
        return lowScores;
    }

    public int getLowScore(int position){
        return lowScores[position];
    }

    public void setLowScores(int[] lowScores) {
        this.lowScores = lowScores;
    }

    public void setLowScore(int score, int position){
        this.lowScores[position] = score;
    }

    public int[] getHighScores() {
        return highScores;
    }

    public int getHighScore(int position){
        return highScores[position];
    }

    public void setHighScores(int[] highScores) {
        this.highScores = highScores;
    }

    public void setHighScore(int score, int position){
        this.highScores[position] = score;
    }

    public float[] getAvgScores() {
        return avgScores;
    }

    public float getAvgScore(int position){
        return avgScores[position];
    }

    public void setAvgScores(float[] avgScores) {
        this.avgScores = avgScores;
    }

    public void setAvgScore(float score, int position){
        this.avgScores[position] = score;
    }
}
