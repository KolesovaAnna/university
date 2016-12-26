package com.journaldev.spring;

/**
 * Created by Hanna Kolesava on 12/18/2016.
 */
public class Pair {
    private int right;
    private int wrong;

    public Pair(){
        right =0;
        wrong =0;
    }

    public Pair(int r, int w){
        right =r;
        wrong =w;
    }

    public String toString()
    {
        return "(" + right+ ", " + wrong + ")";
    }

    public int getRight(){
        return right;
    }

    public int getWrong(){return wrong;}

    public void setRight(int r){this.right = r;}

    public void setWrong(int wr){this.wrong=wr;}

    public Pair incRight(){++right; return this;}

    public Pair incWrong(){++wrong; return this;}
}
