package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class Family extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        ArrayList<Word> familyList= new ArrayList<Word>();

        familyList.add(new Word("әpә","father",R.drawable.family_father));
        familyList.add(new Word("әṭa","mother",R.drawable.family_mother));
        familyList.add(new Word("angsi","son",R.drawable.family_son));
        familyList.add(new Word("tune","daughter",R.drawable.family_daughter));
        familyList.add(new Word("taachi","older brother",R.drawable.family_older_brother));
        familyList.add(new Word("chalitti","younger brother", R.drawable.family_younger_brother));
        familyList.add(new Word("teṭe","older sister",R.drawable.family_older_sister));
        familyList.add(new Word("kolliti","younger sister", R.drawable.family_younger_sister));
        familyList.add(new Word("ama","grandmother",R.drawable.family_grandmother));
        familyList.add(new Word("paapa","grandfather",R.drawable.family_grandfather));

        WordAdapter familyAdapter= new WordAdapter(this, familyList);
        ListView familyListView=(ListView) findViewById(R.id.family_list_view);
        familyListView.setAdapter(familyAdapter);

    }
}
