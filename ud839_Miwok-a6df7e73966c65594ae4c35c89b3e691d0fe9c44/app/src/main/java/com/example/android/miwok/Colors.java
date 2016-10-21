package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<Word> colorsList= new ArrayList<Word>();

        colorsList.add(new Word("weṭeṭṭi", "red",R.drawable.color_red));
        colorsList.add(new Word("chokokki", "green",R.drawable.color_green));
        colorsList.add(new Word("ṭakaakki", "brown",R.drawable.color_brown));
        colorsList.add(new Word("ṭopoppi", "gray",R.drawable.color_gray));
        colorsList.add(new Word("kululli", "black",R.drawable.color_black));
        colorsList.add(new Word("kelelli", "white",R.drawable.color_white));
        colorsList.add(new Word("ṭopiisә","dusty yellow",R.drawable.color_dusty_yellow));
        colorsList.add(new Word("chiwiiṭә","mustard yellow",R.drawable.color_mustard_yellow));




        WordAdapter colorAdapter= new WordAdapter(this, colorsList);

        ListView listView= (ListView) findViewById(R.id.colors_list_view);
        listView.setAdapter(colorAdapter);
    }
}
