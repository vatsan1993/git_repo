package com.example.android.miwok;

/**
 * Created by Srivatsan on 9/25/2016.
 *
 * Word Class instaces will hold the english words and the miwok words.
 * This instances goes as input to the adapter that we used to form a list.
 */
public class Word {

    //A Class to use for Custom Adapter
    private int mres_id;
    private String mMiwokWord, mEngWord;

    public String getMiwokWord()
    {
        return mMiwokWord;
    }

    public String getEngWord(){
        return mEngWord;
    }

    public int getMres_id(){return mres_id;}

    public Word(String mMiwok, String mEng, int id)
    {
        mMiwokWord=mMiwok;
        mEngWord=mEng;
        mres_id=id;
    }
    public Word(String mMiwok, String mEng)
    {
        mMiwokWord=mMiwok;
        mEngWord=mEng;

    }


}
