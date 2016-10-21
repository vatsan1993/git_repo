package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Phrases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);


        ArrayList<Word> phrasesList= new ArrayList<Word>();

        phrasesList.add(new Word("minto wuksus", "Where are you going?"));
        phrasesList.add(new Word("tinnә oyaase'nә", "What is your name?"));
        phrasesList.add(new Word("oyaaset...", "My name is..."));
        phrasesList.add(new Word("How are you feeling?", "michәksәs?"));
        phrasesList.add(new Word("I’m feeling good.", "kuchi achit"));
        phrasesList.add(new Word("Are you coming?", "әәnәs'aa?"));
        phrasesList.add(new Word("Yes, I’m coming.", "hәә’ әәnәm"));
        phrasesList.add(new Word("әәnәm","I’m coming."));
        phrasesList.add(new Word("Let’s go.", "yoowutis"));
        phrasesList.add(new Word("әnni'nem", "Come Here"));




        WordAdapter phraseAdapter= new WordAdapter(this, phrasesList);

        ListView listView= (ListView) findViewById(R.id.phrases_listview);
        listView.setAdapter(phraseAdapter);
    }
}
