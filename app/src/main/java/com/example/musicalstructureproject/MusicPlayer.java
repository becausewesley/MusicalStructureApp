package com.example.musicalstructureproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicPlayer extends AppCompatActivity {

    //These are the views where the info will be displayed
    TextView songNameTextView;
    TextView artistNameTextView;
    ImageView albumArt;

    //These are the variable which store the current info from the selection
    int songArt;
    String songName;
    String songArtist;

    //These are the play/pause buttons
    Button playBtn;
    Button pauseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        //This uses the infoSetter method to display all the info from the selection
       infoSetter();

       //This sets the play button to VISIBLE by default
       playBtn = findViewById(R.id.play_button);
       playBtn.setVisibility(View.VISIBLE);

        //This sets the pause button to VISIBLE by default
       pauseBtn = findViewById(R.id.pause_button);
       pauseBtn.setVisibility(View.INVISIBLE);

       //Play button On Click Listener
       playBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               playBtn.setVisibility(View.INVISIBLE);
               pauseBtn.setVisibility(View.VISIBLE);
           }
       });

        //Pause button On Click Listener
       pauseBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               pauseBtn.setVisibility(View.INVISIBLE);
               playBtn.setVisibility(View.VISIBLE);
           }
       });
    }

    //This method gets the info from the selection and displays it on the UI
    public void infoSetter(){

        songName = getIntent().getExtras().getString("songNm");
        songArtist = getIntent().getExtras().getString("songAr");
        songArt = getIntent().getExtras().getInt("songArt");

        albumArt = findViewById(R.id.album_art_img_view);
        albumArt.setImageResource(songArt);

        songNameTextView = findViewById(R.id.player_song_name);
        songNameTextView.setText(songName);

        artistNameTextView = findViewById(R.id.player_artist_name);
        artistNameTextView.setText(songArtist);

    }
}
