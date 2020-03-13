package com.example.musicalstructureproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicPlayer extends AppCompatActivity {

    TextView songNameTextView;
    TextView artistNameTextView;
    ImageView albumArt;

    int songArt;
    String songName;
    String songArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

       infoSetter();
    }

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
