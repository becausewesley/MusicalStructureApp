package com.example.musicalstructureproject;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    Button mPlayButton ;
    Button mPauseButton;


    @Nullable
    @Override

    public View onCreateView (@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.frag_2_layout, container, false);

        mPlayButton = (Button) rootView.findViewById(R.id.play_button);
        mPlayButton.setVisibility(View.VISIBLE);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayButton.setVisibility(View.INVISIBLE);
                mPauseButton.setVisibility(View.VISIBLE);
            }
        });

        mPauseButton = (Button) rootView.findViewById(R.id.pause_button);
        mPauseButton.setVisibility(View.INVISIBLE);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPauseButton.setVisibility(View.INVISIBLE);
                mPlayButton.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }
}
