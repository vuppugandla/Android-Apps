/* Student class to student student id and socres
*Author: Vignan Uppugandla
 */
package com.android.vignan.studentstats.model;

public class Student {
    private String studentID;
    private int q1;
    private int q2;
    private int q3;
    private int q4;
    private int q5;

    private int scores[] = new int[5];

    public Student(){
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int[] getScores() {
        return scores;
    }
    public void setScores(int[] scores){
        this.scores=scores;
    }

    public int getIndScore(int position){
        return scores[position];
    }
    public void setIndScores(int score, int position) {
        this.scores[position] = score;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
        setIndScores(q1,0);
    }

    public int getQ2() {
        return q2;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
        setIndScores(q2,0);
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
        setIndScores(q3,0);
    }

    public int getQ4() {
        return q4;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
        setIndScores(q4,0);
    }

    public int getQ5() {
        return q5;
    }

    public void setQ5(int q5) {
        this.q5 = q5;
        setIndScores(q5,0);
    }
}
