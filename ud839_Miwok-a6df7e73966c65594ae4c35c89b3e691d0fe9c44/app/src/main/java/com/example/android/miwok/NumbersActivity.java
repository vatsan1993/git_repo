package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> numbersList= new ArrayList<Word>();

        numbersList.add(new Word("Lutti", "One",R.drawable.number_one));
        numbersList.add(new Word("Ottiko", "Two",R.drawable.number_two));
        numbersList.add(new Word("Tolookosu", "Three", R.drawable.number_three));
        numbersList.add(new Word("Oyissa", "Four", R.drawable.number_four));
        numbersList.add(new Word("Masokka", "Five",R.drawable.number_five));
        numbersList.add(new Word("Temokka", "Six",R.drawable.number_six));
        numbersList.add(new Word("Kenakaku", "Seven",R.drawable.number_seven));
        numbersList.add(new Word("Kawinta", "Eight",R.drawable.number_eight));
        numbersList.add(new Word("wo's", "Nine",R.drawable.number_nine));
        numbersList.add(new Word("na'aacha", "Ten",R.drawable.number_ten));




        WordAdapter adaptForNumbers= new WordAdapter(this, numbersList);

        ListView listView= (ListView) findViewById(R.id.list);
        listView.setAdapter(adaptForNumbers);


    }
}
