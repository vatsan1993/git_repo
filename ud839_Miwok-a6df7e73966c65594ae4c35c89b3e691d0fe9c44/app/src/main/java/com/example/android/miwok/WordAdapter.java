package com.example.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Srivatsan on 9/25/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(Context context, ArrayList<Word> words){
        super(context,0,words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView= convertView;
        if(listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord= getItem(position);

        TextView eng_word= (TextView) listView.findViewById(R.id.eng_word);
        eng_word.setText(currentWord.getEngWord());

        TextView miwok_word=(TextView) listView.findViewById(R.id.miwok_word);
        miwok_word.setText(currentWord.getMiwokWord());

        ImageView res_image=(ImageView) listView.findViewById(R.id.list_image);
        res_image.setImageResource(currentWord.getMres_id());

        return listView;
    }


}
